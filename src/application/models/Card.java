package application.models;

import application.App;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class Card {

	public String suit;
	public int cardRank;
	public int cardValue;

	public String name;
	public String fullName;
	public String fileName;
	public String full_path_to_img;
	public Image img;
	public boolean isAce = false;
	
	public Card(String suit, int rank) {

		this.suit = suit;
		this.cardRank = rank; // 1 -13 - Ace to King

		this.cardValue = this.getValue(); // the value of the card in the game - 1 - 11

		this.name = this.getCardName();
		this.fileName = this.name + "_of_" + this.suit;
		this.setImage();

	}

	private void setImage() {
		try {
			this.full_path_to_img = App.filePath + this.fileName + ".png";
			// System.out.println(this.full_path_to_img);
			this.img = new Image("file:" + this.full_path_to_img);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(this.full_path_to_img);
		}
	}

	private int getValue() {

		if (this.cardRank <= 10) {
			this.isAce = true;
			this.cardValue = this.cardRank;
		} else {
			this.cardValue = 10;
		}
		return this.cardValue;
	}

	private final String getCardName() {
		if (this.cardRank == 1) {
			this.isAce = true;
			return "ace";
		} else if (this.cardRank == 11) {
			return "jack";
		} else if (this.cardRank == 12) {
			return "queen";
		} else if (this.cardRank == 13) {
			return "king";
		} else {
			return String.valueOf(this.cardRank);
		}
	}

}