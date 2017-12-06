package application.models;

import java.util.ArrayList;

import application.GameManager;

public class Hand {

	// an array list of the cards held in the hand
	private ArrayList<Card> cards;

	// these flags are set in the calculateScore() method which is called each time
	// a card is added to the hand
	public boolean hasAce = false;
	public boolean isBust = false;
	// the actual score of the hand, adjusted for the holding of an Ace
	private int handScore = 0;

	// constructor - cards are always added to a hand, hence no parameters
	public Hand() {
		// initialise the card list
		this.cards = new ArrayList<>();
	}

	// method used to add a new card to the hand
	public void addCard(Card card) {
		// add the card to the arraylist
		this.cards.add(card);
		// and then recalculate
		this.calculateScore();
	}

	// hand cards getter
	public ArrayList<Card> getCards() {
		return this.cards;
	}

	// score getter
	public int getScore() {
		return this.handScore;
	}

	// check whether this hand is BlackJack (21 in 2 cards)
	public boolean isBlackJack() {
		return this.handScore == GameManager.MAX_SCORE && this.cards.size() == 2;
	}

	// calculate the score of the hand, if an Ace is present, treat as high until
	// the score is over 21
	// sets hasAce and isBust flags
	public void calculateScore() {
		int totalScore = 0;

		for (Card c : this.cards) {
			if (c.isAce) {
				this.hasAce = true;
			}
			totalScore += c.cardValue;
		}

		if (totalScore > GameManager.MAX_SCORE) {
			if (this.hasAce) {
				totalScore -= 10;
			}
		}

		this.handScore = totalScore;
		if (this.handScore > GameManager.MAX_SCORE) {
			this.isBust = true;
		}
	}
}
