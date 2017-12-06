package application.models;

import application.App;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class Card {

	// the suit of the card, assigned when creating the deck from GameManager.suit_list
	public String suit;
	// the rank of the card Ace=1, 2=2, Jack=11, etc
	public int cardRank;
	// the actual value of the card based on blackjack scoring, 3=3, J=10, K=10
	public int cardValue;

	// the string name of the card, agnostic of its suit, e.g. 5, ace, king, etc
	public String name;
	// the full name of the card as assigned in constructor - e.g. ace_of_spades
	public String fullName;
	// the string value of the path to this card's image file, as assigned in constructor
	public String full_path_to_img;
	// the image assigned to the card - this should be moved to UI component to separate concerns
	public Image img;
	
	// is the card an Ace?  this is used within the Hand class to determine scoring
	public boolean isAce = false;
	// the dealer's second card is always dealt face down
	public boolean isFaceDown = false;

	
	// constructor - card must have a rank and suit
	public Card(String suit, int rank) {

		this.suit = suit;
		this.cardRank = rank; // 1 -13 - Ace to King

		// calculate the value of the card in the game (1 - 11)
		// decide the value of the card based upon blackjack scoring - ace is
		// unique and its value is primarily handled in the Hand class (getScore()) as
		// its value depends on the total value of the cards in the hand
		if (this.cardRank == 1) {
			this.isAce = true;
			this.cardValue = 11;
		} else if (this.cardRank <= 10) {
			this.cardValue = this.cardRank;
		} else {
			this.cardValue = 10;
		}

		// also set the card name based on the rank of the card
		if (this.cardRank == 1) {
			this.isAce = true;
			this.name = "ace";
		} else if (this.cardRank == 11) {
			this.name = "jack";
		} else if (this.cardRank == 12) {
			this.name = "queen";
		} else if (this.cardRank == 13) {
			this.name = "king";
		} else {
			this.name = String.valueOf(this.cardRank);
		}

		// file name for the image is composed as below
		this.fullName = this.name + "_of_" + this.suit;

		// this method may be moved to UI component CardWrapper
		this.setImage();

	}

	// try to add the relevant image to the card - should move this to the UI
	// component in future, hence I have left it in a separate method
	private void setImage() {
		try {
			this.full_path_to_img = App.filePath + this.fullName + ".png";
			// System.out.println(this.full_path_to_img);
			this.img = new Image("file:" + this.full_path_to_img);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(this.full_path_to_img);
		}
	}
}