package application.models;

import java.util.ArrayList;

import application.GameManager;

public class Hand {

	private ArrayList<Card> cards;
	public boolean hasAce = false;

	public boolean hasHit = false;

	public boolean isBust = false;

	public int minHandValue;
	public int maxHandValue;

	private int handScore = 0;

	public Hand() {
		this.cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		// p.addCardToHand(dealtCard);
		this.cards.add(card);
		this.calculateScore();
	}

	public void addCardToHand(Card dealtCard) {

	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}

	public int getScore() {
		return this.handScore;
	}

	public boolean isBust() {
		
		return this.handScore > GameManager.MAX_SCORE;
	}

	public boolean isBlackJack() {
		return this.handScore == GameManager.MAX_SCORE && this.cards.size() == 2;
	}

	// get the score of the hand, if an A is present, treat as high until the score
	// is over 21
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
