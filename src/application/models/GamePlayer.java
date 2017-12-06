package application.models;

public abstract class GamePlayer {

	public String name;
	// ArrayList<Card> currentHand;
	public Hand hand;

	public GamePlayer(String name) {
		this.name = name;

		this.hand = new Hand();
	}

	abstract String getName();

	public void addCardToHand(Card c) {

		this.hand.cards.add(c);

	}

	public int checkHand() {
		return this.hand.getScore();
	}
}