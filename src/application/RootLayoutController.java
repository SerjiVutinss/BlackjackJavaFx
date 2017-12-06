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

	// UI elements which need to be accessed

	// a child element of RootLayout.fxml - the main play area
	@FXML
	AnchorPane apAppRoot;

	// main content pane within apAppRoot
	@FXML
	private VBox vbContentRoot;

	// the area in which the dealerWrapper will be placed, child of vbContentRoot
	@FXML
	public HBox hbDealerSeat;

	// the area in which the playerWrapper is placed, child of vbContentRoot
	@FXML
	public HBox hbPlayerSeats;

	// the welcome pane and controls - pWelcome is a child of RootLayout.fxml
	@FXML
	Pane pWelcome;

	@FXML
	VBox vbWelcome;
	
	@FXML
	TextField tfPlayerName;
	
	@FXML
	Button btnStartGame;

	@FXML
	public VBox vbGameControls;

	// in-game controls
	@FXML
	Button btnNewHand;

	// the top, header row - child of vbContentRoot, contains the BlackJack text
	@FXML
	HBox hbHeader;


	@FXML
	public Label lblThePot;

	// the game manager which controls game logic
	GameManager gm;

	// declaring variables for the dealer and player wrappers
	public GamePlayerWrapper dealerWrapper;
	// this was originally a list but simplified to a single variable
	public SeatedPlayerWrapper seatedPlayer;
	
	// default constructor - unused as initialise() method will suffice
	public RootLayoutController() {
	}

	// initialise method which is called on creation of the controller
	@FXML
	private void initialize() {
		// this.tfInput.setText("Hello World!");
		// this.updateHands();
		this.vbGameControls.setVisible(false);
		this.vbContentRoot.setVisible(false);
	}

	// event handler for btnStartGame - starts a new game using the player name in
	// the tfPlayerName TextField
	@FXML
	private void startGame() {

		if (this.tfPlayerName.getText().length() > 0) {

			System.out.println("Button clicked!");
			this.vbWelcome.setVisible(false);
			this.vbWelcome.setMaxWidth(0);

			// this.vbGameControls.setVisible(true);

			this.vbContentRoot.setVisible(true);

			this.gm = new GameManager(this, new SeatedPlayer(this.tfPlayerName.getText()));
			this.gm.startGame();

			this.updateHands();

		}
	}

	// start a new hand within a game
	@FXML
	public void newHand() {
		// set the game controls to invisible
		this.vbGameControls.setVisible(false);
		// use the GameManager instance to start a new game
		gm.newHand();
		// update the UI
		this.updateHands();
	}

	// updates the UI using the GameManager dealer and player objects
	@FXML
	public void updateHands() {
		this.hbDealerSeat.getChildren().clear();

		this.dealerWrapper = new DealerWrapper(GameManager.game_dealer);
		this.hbDealerSeat.getChildren().add(this.dealerWrapper);

		this.hbPlayerSeats.getChildren().clear();
		this.hbPlayerSeats.getChildren().add(new SeatedPlayerWrapper(GameManager.player));
	}
}
