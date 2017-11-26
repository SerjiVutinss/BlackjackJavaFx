package application;

import application.gui.components.GamePlayerWrapper;
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

	// the top, header row
	@FXML
	HBox hbHeader;

	// the area the dealer will sit
	@FXML
	VBox vbDealerSeat;

	// main content pane within apAppRoot
	// hbPlayerSeats is a child
	@FXML
	private VBox vbContentRoot;

	// the area in which Players are added
	@FXML
	private HBox hbPlayerSeats;

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
		this.hbPlayerSeats.getChildren().clear();
		for (GamePlayer p : GameManager.game_players) {
			this.hbPlayerSeats.getChildren().add(new GamePlayerWrapper(p));
		}
	}
}
