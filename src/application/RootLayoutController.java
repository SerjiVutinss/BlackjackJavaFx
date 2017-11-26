package application;

import application.gui.components.CardWrapper;
import application.gui.components.GamePlayerWrapper;
import application.gui.components.HandWrapper;
import application.models.Card;
import application.models.GamePlayer;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RootLayoutController {

	// public CardContainer _cardContainer;

	// root element of RootLayout.fxml
	@FXML
	AnchorPane apAppRoot;

	// main content pane within apAppRoot
	@FXML
	private VBox vbContentRoot;
	
	@FXML
	private HBox hbGamePlayer;

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
			
			this.hbGamePlayer.getChildren().add(new GamePlayerWrapper(p));

//			for (Card c : p.hand.cards) {
//				hbHand.addCardWrapper(new CardWrapper(c));
//			}
//			hbGamePlayer.getChildren().add(hbHand);
			//vbContentRoot.getChildren().add(vbGamePlayer);
		}
	}
}
