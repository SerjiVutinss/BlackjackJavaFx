package application;

import application.gui.components.CardWrapper;
import application.gui.components.HandWrapper;
import application.models.Card;
import application.models.GamePlayer;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class RootLayoutController {

	// public CardContainer _cardContainer;

	// @FXML
	// TextField tfInput;

	@FXML
	private HBox hbContentRoot;

	public RootLayoutController() {
	}

	@FXML
	private void initialize() {
		// this.tfInput.setText("Hello World!");
		this.updateHands();
	}

	@FXML
	private void updateGUI() {

	}

	@FXML
	private void updateHands() {
		HandWrapper hbHand;
		for (GamePlayer p : GameManager.game_players) {
			for (Card c : p.hand.cards) {
				hbHand = new HandWrapper();
				hbHand.addCardWrapper(new CardWrapper(c));
				hbContentRoot.getChildren().add(hbHand);
			}
		}
	}
}
