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

// UI representation of a hand which displays the cards
public class HandWrapper extends FlowPane implements IUpdateable {

	// the hand to be represented
	Hand hand;
	// a flow pane to which the cardWrappers will actually be added
	public FlowPane hbCardWrapperSlots;
	// a HBox used to display the scores
	HBox hbHandScore;
	// the label used to display the hand score
	public Label lblHandScore;

	// if this is true, the second card will be face down
	boolean isDealerHand = false;

	// the only constructor - a hand is required to build a handwrapper component
	public HandWrapper(Hand hand) {
		// set the hand instance
		this.hand = hand;
		// and call the component's setup method
		this.setup();
	}

	// set the isDealerHand from another component when necessary
	public void setIsDealer(boolean isDealerHand) {
		this.isDealerHand = isDealerHand;
		update();
	}

	// set up the UI component and then initialise
	private void setup() {

		// set a margin
		HandWrapper.setMargin(this, new Insets(50, 25, 50, 25));
		// and a border
		this.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

		// add the card wrapper row, flow pane so it will handle any number of cards
		this.hbCardWrapperSlots = new FlowPane();
		// create the score row and score label
		this.hbHandScore = new HBox();
		this.lblHandScore = new Label();
		// add the score label to the score row
		this.hbHandScore.getChildren().add(lblHandScore);

		// now add all elements to the handWrapper object
		this.getChildren().add(hbCardWrapperSlots);
		this.getChildren().add(hbHandScore);

		// finally, call the superclass initialise method
		this.initialize();
	}

	private void initialize() {
		// update on initialisation
		this.update();
	}

	// abstract interface method(IUpdateable)
	// this rebuilds the UI element on the screen
	@Override
	public void update() {
		// clear all cardWrapper elements from the component
		this.hbCardWrapperSlots.getChildren().clear();

		// re-add all cardWrapper elements because new cards have been added
		for (Card card : this.hand.getCards()) {

			// this.hbCardWrapperSlots.setMinWidth(card.img.getWidth() * 2 + 10);
			// this.hbCardWrapperSlots.setMinHeight(card.img.getHeight() / 2);

			if (card == this.hand.getCards().get(this.hand.getCards().size() - 1)) {
				this.hbCardWrapperSlots.getChildren().add(new CardWrapper(card, this.isDealerHand));
			} else {
				this.hbCardWrapperSlots.getChildren().add(new CardWrapper(card, false));
			}
		}
		this.displayTotalScore();
	}

	// display the hand score
	private void displayTotalScore() {

		if (this.hand.getScore() > GameManager.MAX_SCORE) {

			this.lblHandScore.setText(String.valueOf("Score: " + this.hand.getScore() + " BUST!"));

		} else {
			this.lblHandScore.setText(String.valueOf("Score: " + this.hand.getScore()));
		}
	}
}
