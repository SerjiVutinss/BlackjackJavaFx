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

	abstract void clearHand();

	public int checkHand() {
		int totalScore = 0;
		for (Card c : this.hand.cards) {
			totalScore += c.cardValue;
		}
		return totalScore;
	}
}