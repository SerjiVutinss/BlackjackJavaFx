package application.models;

public abstract class GamePlayer {

	public String name;
	// ArrayList<Card> currentHand;
	private Hand hand;

	public GamePlayer(String name) {
		this.name = name;

		this.hand = new Hand();
	}

	public Hand getHand() {
		return this.hand;
	}
	
	public void setHand(Hand hand) {
		this.hand = hand;
	}

	abstract String getName();

	public void addCardToHand(Card c) {

		this.hand.addCard(c);

	}

	public int checkHand() {
		return this.hand.getScore();
	}
}