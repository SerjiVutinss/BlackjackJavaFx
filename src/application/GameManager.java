package application;

import java.util.ArrayList;
import java.util.Collections;

import application.gui.components.HandWrapper;
import application.models.Card;
import application.models.Dealer;
import application.models.GamePlayer;
import application.models.SeatedPlayer;

public class GameManager {

	private static ArrayList<String> suitList;
	public static ArrayList<Card> game_deck;
	public static ArrayList<HandWrapper> game_hands;

	// public static ArrayList<GamePlayer> players;

	public static int number_of_decks;

	// public static HandWrapper current_hand;
	public static ArrayList<Dealer> dealers;

	public Dealer game_dealer;
	// public Player player;
	public static ArrayList<GamePlayer> game_players;

	public GameManager() {
		this(1);
	}

	public GameManager(int numDecks) {
		// set up the lists
		// this.setUpDealers();
		number_of_decks = numDecks;
		this.setUpSuits();
		this.setUpDealers();

		// shuffle and deal
		// this.startGame();

		// this.gameDealer.dealhand();
	}

	public void startGame() {
		this.setupDeck();
		this.setupPlayers();
		this.dealCards();
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
		this.game_dealer = (Dealer)dealer;
		// add the dealer to the list last so it will be dealt last
		GameManager.game_players.add(this.game_dealer);
		System.out.println("Dealer added successfully!");
	}

	private void dealCards() {
		// dealer will be last element in list, so
		// for each card to be dealt,
		int cardsToDeal = 2;
		for (int i = 0; i < cardsToDeal; i++) {

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
