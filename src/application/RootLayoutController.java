package application;

import application.components.DealerWrapper;
import application.components.GamePlayerWrapper;
import application.components.SeatedPlayerWrapper;
import application.models.SeatedPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RootLayoutController {

	// public CardContainer _cardContainer;

	// root element of RootLayout.fxml
	@FXML
	AnchorPane apAppRoot;
	
	@FXML
	public Label lblThePot;

	@FXML
	TextField tfPlayerName;

	@FXML
	Pane pWelcome;

	// the top, header row
	@FXML
	HBox hbHeader;
	
	@FXML
	VBox vbWelcome;
	
	@FXML
	public VBox vbGameControls;

	@FXML
	Button btnStartGame;
	
	@FXML
	Button btnNewHand;

	// the area the dealer will sit
	@FXML
	public HBox hbDealerSeat;

	// main content pane within apAppRoot
	// hbPlayerSeats is a child
	@FXML
	private VBox vbContentRoot;

	// the area in which Players are added
	@FXML
	public HBox hbPlayerSeats;
	
	GameManager gm;

	public GamePlayerWrapper dealerWrapper;
	public SeatedPlayerWrapper seatedPlayer;

	public RootLayoutController() {
	}

	@FXML
	private void initialize() {
		// this.tfInput.setText("Hello World!");
		// this.updateHands();
		this.vbGameControls.setVisible(false);
		this.vbContentRoot.setVisible(false);
	}

	@FXML
	private void updateGUI() {

	}

	@FXML
	private void startGame() {

		if (this.tfPlayerName.getText().length() > 0) {

			System.out.println("Button clicked!");
			this.vbWelcome.setVisible(false);
			this.vbWelcome.setMaxWidth(0);
			
			//this.vbGameControls.setVisible(true);
			
			this.vbContentRoot.setVisible(true);

			this.gm = new GameManager(this, new SeatedPlayer(this.tfPlayerName.getText()));
			this.gm.startGame();

			this.updateHands();

		}
	}
	
	@FXML
	public void newHand() {
		this.vbGameControls.setVisible(false);
		gm.newHand();
		this.updateHands();
	}

	@FXML
	public void updateHands() {
		this.hbDealerSeat.getChildren().clear();
		// App.filePath + this.fileName + ".png";
		// System.out.println(this.full_path_to_img);

		// This was supposed to be an image of the deck - disabling for now!

		// Image img = new Image("file:" + App.filePath + "card_back_1.jpg");
		// ImageView imgView = new ImageView(img);

		// imgView.setFitHeight(imgView.getImage().getHeight() / 3);
		// imgView.setFitWidth(imgView.getImage().getWidth() / 3);
		//
		// this.hbDealerSeat.getChildren().add(imgView);
		//
		//

		this.dealerWrapper = new DealerWrapper(GameManager.game_dealer);
		this.hbDealerSeat.getChildren().add(this.dealerWrapper);

		this.hbPlayerSeats.getChildren().clear();
		// for (GamePlayer p : GameManager.game_players) {
		// if (GameManager.player != GameManager.game_dealer) {
		this.hbPlayerSeats.getChildren().add(new SeatedPlayerWrapper(GameManager.player));
		// }
		// }
	}
}
