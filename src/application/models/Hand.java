package application.models;

import java.util.ArrayList;

public class Hand {

	public ArrayList<Card> cards;

//	public Hand(ArrayList<Card> cards) {
//		this.cards = cards;
//	}

	public Hand() {
		this.cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		// p.addCardToHand(dealtCard);
		this.cards.add(card);
	}

	public void addCardToHand(Card dealtCard) {
		// TODO Auto-generated method stub
		
	}
}
