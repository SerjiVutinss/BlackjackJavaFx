package application;

import java.util.ArrayList;
import java.util.Collections;

import application.components.DealerWrapper;
import application.components.GamePlayerWrapper;
import application.components.HandWrapper;
import application.components.SeatedPlayerWrapper;
import application.models.Card;
import application.models.Dealer;
import application.models.GamePlayer;
import application.models.Hand;
import application.models.SeatedPlayer;

// the main game logic controller
public class GameManager {

	// this is used to store a reference to the RootLayoutController instance
	static RootLayoutController rootLayout;

	// maximum hand score, any hand exceeding this
	// value will be mucked
	public static final int MAX_SCORE = 21;
	// each player is dealt this number of cards at the start of each round
	public static final int NUM_START_CARDS = 2;

	// a list of suits which is used to build (each) deck
	private static ArrayList<String> suitList;
	// the number of decks which are used to build the final game deck - this
	// defaults to 6
	public static int number_of_decks;
	// used to store the built game deck - could be any number of decks
	// the game deck is shuffled at the start of a new game but not a new round
	public static ArrayList<Card> game_deck;

	// the actual hands in each round
	public static ArrayList<HandWrapper> game_hands;

	// public static ArrayList<GamePlayer> players;

	// public static HandWrapper current_hand;
	public static ArrayList<Dealer> dealers;

	// the dealer which is assigned randomly at the start of the game
	public static Dealer game_dealer;

	// the human player - could be adapted to work with a list for multiple players
	public static SeatedPlayer player;

	// betting
	public static final double BET_SIZE = 20.0d;
	// the balance in the pot
	public static double pot_balance;

	// the GamePlayer is the human player, added through the welcome pane
	public GameManager(RootLayoutController rootLayout, SeatedPlayer p) {
		// the default constructor
		// this calls the next constructor and supplies the
		// parameter 6 which sets up just six decks - the default for blackjack
		this(rootLayout, p, 6);
	}

	public GameManager(RootLayoutController rootLayout, SeatedPlayer p, int numDecks) {
		// set the rootLayout reference
		GameManager.rootLayout = rootLayout;

		// set the player variable
		player = p;
		// assign the number of decks to be used - used in the setupDeck() method
		number_of_decks = numDecks;
		// set up the list variables with static data
		this.setUpSuits();
//		this.setUpDealers();
	}

	// method to start a new game
	public void startGame() {
		// create and shuffle a new deck
		this.setupDeck();
		// randomly select a dealer
		this.setupPlayers();
		// deal the player and the dealer a hand
		GameManager.dealHands();
	}

	// The Deck
	private void setupDeck() {
		System.out.println("Setting up the game deck:");

		System.out.println("\t" + number_of_decks + " decks are being used...");
		// create a new game deck, which will consist of
		// number_of_decks # of real decks of cards
		game_deck = new ArrayList<>();

		// build and add number_of_decks #
		// of decks to the game deck
		for (int i = 0; i < number_of_decks; i++) {
			buildDeck();
		}

		// and finally shuffle the game deck
		System.out.println("\tShuffling the deck...");
		Collections.shuffle(game_deck);
		System.out.println("\tDeck shuffled...");
		System.out.println("\tGame deck setup successful!");

	}

	// build a single deck of cards
	private void buildDeck() {
		for (String suit : GameManager.suitList) {
			for (int i = 1; i <= 13; i++) {
				Card card = new Card(suit, i);
				GameManager.game_deck.add(card);
			}
		}
	}

	// Players
	private void setupPlayers() {
		// set gameDealer to a random gameDealer
		Dealer dealer = new Dealer("Dealer");
		GameManager.game_dealer = (Dealer) dealer;
		System.out.println("Dealer added successfully!");
	}

	// private static void clearHands() {
	// game_dealer.setHand(new Hand());
	// player.setHand(new Hand());
	// }

	// deal out a new hand to all players and the dealer
	private static void dealHands() {
		// first take the player's bet -
		// take the bet from the player's balance
		player.deductFromBalance(BET_SIZE);
		// add it to the pot
		// and display the pot balance
		pot_balance += BET_SIZE;
		rootLayout.lblPotBalance.setText(String.valueOf(pot_balance));

		// dealer will be last element in list, so
		// for each card to be dealt,
		for (int i = 0; i < NUM_START_CARDS; i++) {

			// for (GamePlayer p : game_players) {
			// always take the top card off the deck - index 0
			int cardIndex = 0;
			// add the card to the player's hand
			Card dealtCard = game_deck.get(cardIndex);
			player.getHand().addCard(dealtCard);

			// and remove the card from the game_deck
			game_deck.remove(cardIndex);

			dealtCard = game_deck.get(cardIndex);
			game_dealer.addCardToHand(dealtCard);
			game_deck.remove(cardIndex);

			// }
		}
		System.out.println("Cards dealt successfully!");
	}

	// clear the players' hands and deal new ones
	public void newHand() {
		game_dealer.setHand(new Hand());
		player.setHand(new Hand());
		rootLayout.lblThePot.setText("New Hand");
		dealHands();
	}

	// deal a new card to a GamePlayer object and update the UI component
	// accordingly
	private static void dealCard(GamePlayerWrapper gamePlayerWrapper) {
		// add a new card to the
		gamePlayerWrapper.gamePlayer.addCardToHand(game_deck.get(0));
		game_deck.remove(0);
		//gamePlayerWrapper.update();
		gamePlayerWrapper.handWrapper.update();
	}

	// the event when the player Hits
	public static void handleHit(GamePlayerWrapper gamePlayerWrapper) {

		// deal the player a card
		GameManager.dealCard(gamePlayerWrapper);

		// get the player's total score
		int playerScore = gamePlayerWrapper.gamePlayer.checkHand();

		System.out.println(playerScore);
		// check that the score is 21 or less
		if (playerScore == MAX_SCORE) {
			// auto-stand the player if they have 21
			System.out.println("You have " + MAX_SCORE + "!");
			handleStand(gamePlayerWrapper);

		} else if (playerScore > MAX_SCORE) {
			// bust - auto-stand
			handleStand(gamePlayerWrapper);
		} else {
			System.out.println("Stand or Hit?");
		}

	}

	// Seated Player only - bet is doubled and player can only draw one card
	public static void handleDouble(GamePlayerWrapper gamePlayerWrapper) {
		// take the pot balance amount from the player's balance again
		player.deductFromBalance(pot_balance);
		// add this amount to the pot
		pot_balance += pot_balance;
		// update the UI element
		rootLayout.lblPotBalance.setText(String.valueOf(pot_balance));
		
		// deal the player a card
		GameManager.dealCard(gamePlayerWrapper);
		// and then stand
		handleStand(gamePlayerWrapper);
//		gamePlayerWrapper.update();
	}

	// the event where the player stands
	public static void handleStand(GamePlayerWrapper gamePlayerWrapper) {
		// handle the stand event
		if (gamePlayerWrapper.getClass() == SeatedPlayerWrapper.class) {
			// player has stood, end their turn, so disable the input panel buttons
			gamePlayerWrapper.inputPanel.setButtonsVisible(false);			
			try {
				// and handle the dealer's turn
				handleDealerTurn();
			} catch (InterruptedException e) {
				// TODO: Threading not implemented
			}
		}
	}

	public static void handleDealerTurn() throws InterruptedException {
		// GamePlayer dealer = (Dealer)GameManager.game_players.get(0);
		DealerWrapper dealerWrapper = (DealerWrapper) rootLayout.dealerWrapper;
		dealerWrapper.handWrapper.lblHandScore.setVisible(true);

		// turn the dealer's hole card
		dealerWrapper.update();

		// check the dealer's score and keeping hitting if it is still below 17
		while (dealerWrapper.gamePlayer.getHand().getScore() < Dealer.MUST_EQUAL) {
			// // give the dealer a card
			// dealerWrapper.gamePlayer.addCardToHand(GameManager.game_deck.get(0));
			// // remove that card from the deck
			// GameManager.game_deck.remove(0);
			// // update the hand UI component
			// dealerWrapper.handWrapper.update();
			// // and update the dealer UI component
//			dealerWrapper.update();

			// code above is not necessary since refactoring method dealCard()
			GameManager.dealCard(dealerWrapper);
		}

		dealerWrapper.update();

		// set the new variable winner to the winning player, or null if it is a draw
		GamePlayer winner = compareScores();

		// do something with the winner
		// TODO: implement the money aspect of this
		if (winner == null) {
			rootLayout.lblThePot.setText("PUSH");
			// player gets the money back
			player.addToBalance(pot_balance);
		} else {
			if (winner.getClass() == SeatedPlayer.class) {
				// player gets the bet back + the same amount from the dealer
				player.addToBalance(pot_balance * 2);
			}
			rootLayout.lblThePot.setText(winner.name + " won!");
		}
		pot_balance = 0;

		// enable the controls to allow the player to start a new hand
		rootLayout.vbGameControls.setVisible(true);

	}

	// returns null if hand is a push (draw), else return the GamePlayer object
	// - this is quite verbose as I wanted to ensure that all corner cases are
	// covered
	public static GamePlayer compareScores() {

		// first check for blackjacks

		// if the game dealer and player both have blackjack, return null
		if (game_dealer.getHand().isBlackJack() && player.getHand().isBlackJack()) {
			return null;
		}
		// else if dealer has blackjack, dealer wins
		else if (game_dealer.getHand().isBlackJack()) {
			return game_dealer;
		}
		// else if player has blackjack, player wins
		else if (player.getHand().isBlackJack()) {
			return player;

		} else {
			// if dealer is bust
			if (game_dealer.getHand().isBust) {
				// and the player's s score is less than 21
				if (!player.getHand().isBust) {
					// dealer has won
					return player;
				}
				// a push
				else {
					return null;
				}
			}

			// now check if player has bust
			if (player.getHand().isBust) {
				// and check that the dealer's score is less than 21
				if (!game_dealer.getHand().isBust) {
					// dealer has won
					return game_dealer;
				} else {
					// push
					return null;
				}
			}
			// now check if both player and dealer are NOT bust
			if (!player.getHand().isBust && !game_dealer.getHand().isBust) {
				// compare the scores - check if player has higher score
				if (player.getHand().getScore() > game_dealer.getHand().getScore()) {
					// player has won
					return player;
				}
				// check if dealer has higher score
				else if (player.getHand().getScore() < game_dealer.getHand().getScore()) {
					// dealer has won
					return game_dealer;
				} else {
					// otherwise - push
					return null;
				}
			}
		}
		// default to push barring no conditions above are fully met
		return null;
	}

	// initial setup methods to create lists
	private void setUpSuits() {

		// build the list of suits
		GameManager.suitList = new ArrayList<>();
		GameManager.suitList.add("clubs");
		GameManager.suitList.add("spades");
		GameManager.suitList.add("hearts");
		GameManager.suitList.add("diamonds");
	}

//	private void setUpDealers() {
//		GameManager.dealers = new ArrayList<Dealer>();
//		GameManager.dealers.add(new Dealer("Jack P."));
//		GameManager.dealers.add(new Dealer("Bob S."));
//		GameManager.dealers.add(new Dealer("Bob W."));
//		GameManager.dealers.add(new Dealer("Pablo R."));
//		GameManager.dealers.add(new Dealer("William H."));
//	}

}
