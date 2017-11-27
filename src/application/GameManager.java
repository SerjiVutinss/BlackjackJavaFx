package application;

import java.util.ArrayList;
import java.util.Collections;

import application.gui.components.GamePlayerWrapper;
import application.gui.components.HandWrapper;
import application.models.Card;
import application.models.Dealer;
import application.models.GamePlayer;
import application.models.SeatedPlayer;

public class GameManager {

	RootLayoutController root;

	public static final int MAX_SCORE = 21;
	public static final int NUM_START_CARDS = 2;

	private static ArrayList<String> suitList;
	public static ArrayList<Card> game_deck;
	public static ArrayList<HandWrapper> game_hands;

	// public static ArrayList<GamePlayer> players;

	public static int number_of_decks;

	// public static HandWrapper current_hand;
	public static ArrayList<Dealer> dealers;

	public static Dealer game_dealer;
	// public Player player;
	public static ArrayList<GamePlayer> game_players;

	public static GamePlayerWrapper activeGamePlayerWrapper;

	public GameManager() {
		this(1);
	}

	public void setRootLayoutController(RootLayoutController rootLayout) {
		this.root = rootLayout;
	}

	public GameManager(int numDecks) {
		number_of_decks = numDecks;
		// set up the lists
		this.setUpSuits();
		this.setUpDealers();

		// shuffle and deal
		this.startGame();

		// this.gameDealer.dealhand();
	}

	public void startGame() {
		this.setupDeck();
		this.setupPlayers();
		this.dealHands();
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
		// create a new list of players
		game_players = new ArrayList<GamePlayer>();
		// and add a player
		GamePlayer seat1 = new SeatedPlayer("Justin");
		GameManager.game_players.add(seat1);

		// set gameDealer to a random gameDealer
		GamePlayer dealer = GameManager.dealers.get((int) (Math.random() * GameManager.dealers.size()));
		this.game_dealer = (Dealer) dealer;
		GameManager.game_players.add(this.game_dealer);
		// add the dealer to the list last so it will be dealt last
		//GameManager.game_players.add(this.game_dealer);
		System.out.println("Dealer added successfully!");
	}

	private void dealHands() {
		// dealer will be last element in list, so
		// for each card to be dealt,
		for (int i = 0; i < NUM_START_CARDS; i++) {

			for (GamePlayer p : game_players) {
				// always take the top card off the deck - index 0
				int cardIndex = 0;

				// add the card to the player's hand
				Card dealtCard = game_deck.get(cardIndex);
				p.hand.addCard(dealtCard);

				// and remove the card from the game_deck
				game_deck.remove(cardIndex);

			}
		}
		System.out.println("Cards dealt successfully!");
	}

	public static void handleHit(GamePlayerWrapper gamePlayerWrapper) {

		// deal the player a card
		GameManager.dealCard(gamePlayerWrapper);

		// get the player's total score
		int playerScore = gamePlayerWrapper.gamePlayer.checkHand();

		System.out.println(playerScore);
		// check that the score is 21 or less
		if (playerScore == MAX_SCORE) {
			// auto-stand the player
			System.out.println("You have " + MAX_SCORE + "!");
			handleStand(gamePlayerWrapper);
			
		} else if (playerScore > MAX_SCORE) {
			// bust
			System.out.println("You are Bust!");
			// remove the hand from the table
			gamePlayerWrapper.gamePlayer.hand = null;
			gamePlayerWrapper.removeHand();
		} else {
			System.out.println("Stand or Hit?");
		}

		
	}

	public static void handleStand(GamePlayerWrapper gamePlayerWrapper) {
		// handle the stand event

	}

	public static void setPlayerTurn(GamePlayerWrapper gamePlayerWrapper) {
		// get the player's place in the list
				// try the next player, else go to index 0
				
				int playerIndex = -1;
				// see if the player is in the list
				try {
					playerIndex = game_players.indexOf(gamePlayerWrapper.gamePlayer);
				} catch (Exception e) {
					// THIS SHOULD NEVER HAPPEN!!!
				}
				
				// next turn
				// get the player and set it active?
				if (playerIndex == game_players.size() - 1) {
					// next player is index 0

				} else {
					// next player is index + 1
				}
	}

	public static void dealCard(GamePlayerWrapper gamePlayerWrapper) {

		gamePlayerWrapper.gamePlayer.addCardToHand(game_deck.get(0));
		game_deck.remove(0);
		GamePlayerWrapper.updateHandUI(gamePlayerWrapper);

		for (Card c : gamePlayerWrapper.gamePlayer.hand.cards) {
			System.out.println(c.fileName);
		}

		// System.out.println("Card dealt!");
	}

	private void setUpSuits() {

		// build the list of suits
		GameManager.suitList = new ArrayList<>();
		GameManager.suitList.add("clubs");
		GameManager.suitList.add("spades");
		GameManager.suitList.add("hearts");
		GameManager.suitList.add("diamonds");
	}

	private void setUpDealers() {
		GameManager.dealers = new ArrayList<Dealer>();
		GameManager.dealers.add(new Dealer("Jack P."));
		GameManager.dealers.add(new Dealer("Bob S."));
		GameManager.dealers.add(new Dealer("Bob W."));
		GameManager.dealers.add(new Dealer("Pablo R."));
		GameManager.dealers.add(new Dealer("William H."));
	}

}
