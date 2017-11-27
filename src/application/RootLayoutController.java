package application;

import application.gui.components.GamePlayerWrapper;
import application.models.GamePlayer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	HBox hbDealerSeat;

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
		this.hbDealerSeat.getChildren().clear();
		// App.filePath + this.fileName + ".png";
		// System.out.println(this.full_path_to_img);
		Image img = new Image("file:" + App.filePath + "card_back_1.jpg");
		ImageView imgView = new ImageView(img);

		imgView.setFitHeight(imgView.getImage().getHeight() / 3);
		imgView.setFitWidth(imgView.getImage().getWidth() / 3);

		this.hbDealerSeat.getChildren().add(imgView);

		this.hbDealerSeat.getChildren().add(new GamePlayerWrapper(GameManager.game_dealer));

		this.hbPlayerSeats.getChildren().clear();
		for (GamePlayer p : GameManager.game_players) {
			if (p != GameManager.game_dealer) {
				this.hbPlayerSeats.getChildren().add(new GamePlayerWrapper(p));
			}
		}
	}
}
