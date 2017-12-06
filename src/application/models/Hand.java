package application.models;

import java.util.ArrayList;

import application.GameManager;

public class Hand {

	public ArrayList<Card> cards;
	public boolean hasAce = false;

	public boolean hasHit = false;

	public int minHandValue;
	public int maxHandValue;

	public Hand() {
		this.cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		// p.addCardToHand(dealtCard);
		this.cards.add(card);
	}

	public void addCardToHand(Card dealtCard) {

	}

	// get the score of the hand, if an A is present, treat as high until the score
	// is over 21
	public int getScore() {
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

		return totalScore;
	}

	// public boolean handIsValid() {
	// int handValue = 0;
	// for (Card c : this.cards) {
	// handValue += c.cardValue;
	// }
	// if (handValue <= GameManager.MAX_SCORE) {
	// return true;
	// } else {
	// return false;
	// }
	// }
}
