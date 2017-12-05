package application.components;

import application.GameManager;
import application.models.Card;
import application.models.Hand;
import application.models.IUpdateable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class HandWrapper extends FlowPane implements IUpdateable {

	Hand hand;
	public FlowPane hbCardWrapperSlots;
	HBox hbHandScore;

	public Label lblHandScore;
	int totalScore = 0;
	boolean isDealer = false;

	public HandWrapper(Hand hand) {
		this.hand = hand;
		this.setup();
	}

	public void setIsDealer(boolean isDealer) {
		this.isDealer = isDealer;
		update();
	}

	private void setup() {

		HandWrapper.setMargin(this, new Insets(50, 25, 50, 25));

		// add the card wrapper row
		this.hbCardWrapperSlots = new FlowPane();
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
		this.update();
	}

	public void addCard(Card card) {
		this.hand.addCard(card);
		this.update();
	}

	@Override
	public void update() {
		this.hbCardWrapperSlots.getChildren().clear();
		for (Card card : this.hand.cards) {
			this.totalScore += card.cardValue;
			this.hbCardWrapperSlots.setMinWidth(card.img.getWidth() * 2 + 10);
			this.hbCardWrapperSlots.setMinHeight(card.img.getHeight() / 2);
			if (card == this.hand.cards.get(this.hand.cards.size() - 1)) {
				this.hbCardWrapperSlots.getChildren().add(new CardWrapper(card, this.isDealer));
			} else {
				this.hbCardWrapperSlots.getChildren().add(new CardWrapper(card, false));
			}
		}
		this.displayTotalScore(this.totalScore);
	}

	private void displayTotalScore(int score) {
		if (score <= GameManager.MAX_SCORE) {
			this.lblHandScore.setText(String.valueOf("Score: " + score));
		} else {
			this.lblHandScore.setText(String.valueOf("Score: BUST!"));
		}
	}

}
