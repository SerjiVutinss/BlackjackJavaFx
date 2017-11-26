package application.gui.components;

import application.models.Card;
import application.models.Hand;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HandWrapper extends VBox {

	Hand hand;
	HBox hbCardWrapperSlots;
	HBox hbHandScore;

	Label lblHandScore;
	int totalScore = 0;

	public HandWrapper(Hand hand) {
		this.hand = hand;
		HandWrapper.setMargin(this, new Insets(50, 25, 50, 25));

		// add the card wrapper row
		this.hbCardWrapperSlots = new HBox();
		// and the details row
		this.hbHandScore = new HBox();
		this.lblHandScore = new Label();
		
		this.hbHandScore.getChildren().add(lblHandScore);

		this.getChildren().add(hbCardWrapperSlots);
		this.getChildren().add(hbHandScore);

		this.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
		
		this.initialize();
	}

	private void initialize() {
		this.updateHand();
	}

	public void addCard(Card card) {
		this.hand.addCard(card);
		this.updateHand();
	}

	public void updateHand() {
		this.hbCardWrapperSlots.getChildren().clear();
		for (Card card : this.hand.cards) {
			this.totalScore += card.cardValue;
			this.hbCardWrapperSlots.getChildren().add(new CardWrapper(card));
		}
		this.displayTotalScore(this.totalScore);
	}

	private void displayTotalScore(int score) {
		this.lblHandScore.setText(String.valueOf("Score: " + score));
	}

}
