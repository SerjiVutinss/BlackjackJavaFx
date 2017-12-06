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
import application.models.SeatedPlayer;

public class GameManager {

	// this is used to store a reference to the RootLayoutController instance
	static RootLayoutController root;

	// maximum hand score, any hand exceeding this
	// value will be mucked
	public static final int MAX_SCORE = 21;
	// each player is dealt this number of cards at the start of each round
	public static final int NUM_START_CARDS = 2;

	// a list of suits which is used to build (each) deck
	private static ArrayList<String> suitList;
	// the number of decks which are used to build the final game deck
	public static int number_of_decks;
	// used to store the built game deck - could be any number of decks
	// the game deck is shuffled at the start of a new game but not a new round
	public static ArrayList<Card> game_deck;

	// the actual hands in each round
	public static ArrayList<HandWrapper> game_hands;

	// public static ArrayList<GamePlayer> players;

	// public static HandWrapper current_hand;
	public static ArrayList<Dealer> dealers;

	public static Dealer game_dealer;
	// public Player player;
	// public static ArrayList<GamePlayer> game_players;

	public static GamePlayer player;

	public static ArrayList<GamePlayerWrapper> game_player_wrappers;

	public static GamePlayerWrapper activeGamePlayerWrapper;

	// the GamePlayer is the human player, added through the welcome pane
	public GameManager(GamePlayer p) {
		// the default constructor
		// this calls the next constructor and supplies the
		// parameter 1 which sets up just one deck
		this(p, 1);
	}

	public GameManager(GamePlayer p, int numDecks) {
		// set the player variable
		player = p;
		// assign the number of decks to be used - used in the setupDeck() method
		number_of_decks = numDecks;
		// set up the list variables with static data
		this.setUpSuits();
		this.setUpDealers();
	}
	
	// method to start a new game
	public void startGame() {
		// create and shuffle a new deck
		this.setupDeck();
		// randomly select a dealer
		this.setupPlayers();
		// deal the player and the dealer a hand
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
		// set gameDealer to a random gameDealer
		Dealer dealer = GameManager.dealers.get((int) (Math.random() * GameManager.dealers.size()));
		GameManager.game_dealer = (Dealer) dealer;
		System.out.println("Dealer added successfully!");
	}

	private void dealHands() {
		// dealer will be last element in list, so
		// for each card to be dealt,
		for (int i = 0; i < NUM_START_CARDS; i++) {

			// for (GamePlayer p : game_players) {
			// always take the top card off the deck - index 0
			int cardIndex = 0;
			// add the card to the player's hand
			Card dealtCard = game_deck.get(cardIndex);
			player.hand.addCard(dealtCard);

			// and remove the card from the game_deck
			game_deck.remove(cardIndex);

			dealtCard = game_deck.get(cardIndex);
			game_dealer.addCardToHand(dealtCard);
			game_deck.remove(cardIndex);

			// }
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
			
			
			// end?
			
		} else {
			System.out.println("Stand or Hit?");
		}

	}

	public static void handleStand(GamePlayerWrapper gamePlayerWrapper) {
		// handle the stand event
		if (gamePlayerWrapper.getClass() == SeatedPlayerWrapper.class) {
			// player has stood, end their turn
			// and handle the dealer's turn
			
			
		} else {
		}
	}

	public static void handleDealerTurn() {
		// GamePlayer dealer = (Dealer)GameManager.game_players.get(0);
		DealerWrapper dealerWrapper = (DealerWrapper) root.dealerWrapper;
		dealerWrapper.gamePlayer.addCardToHand(GameManager.game_deck.get(0));
		dealerWrapper.update();
	}

	public static void dealCard(GamePlayerWrapper gamePlayerWrapper) {

		gamePlayerWrapper.gamePlayer.addCardToHand(game_deck.get(0));
		game_deck.remove(0);
		gamePlayerWrapper.update();

		for (Card c : gamePlayerWrapper.gamePlayer.hand.cards) {
			System.out.println(c.fullName);
		}

		// System.out.println("Card dealt!");
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

	private void setUpDealers() {
		GameManager.dealers = new ArrayList<Dealer>();
		GameManager.dealers.add(new Dealer("Jack P."));
		GameManager.dealers.add(new Dealer("Bob S."));
		GameManager.dealers.add(new Dealer("Bob W."));
		GameManager.dealers.add(new Dealer("Pablo R."));
		GameManager.dealers.add(new Dealer("William H."));
	}

}
