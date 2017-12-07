package application;

import application.components.DealerWrapper;
import application.components.GamePlayerWrapper;
import application.components.SeatedPlayerWrapper;
import application.models.SeatedPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
	@FXML
	public Label lblPotBalance;

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

			this.gm = new GameManager(this, new SeatedPlayer(this.tfPlayerName.getText(), 200.0d));
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
	
	@FXML
	public void aboutClick() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Blackjack Rules");
		alert.setHeaderText(null);
		
		StringBuilder sb = new StringBuilder();
		sb.append("1. Any score over 21 and the player will lose the hand");
		sb.append("\n2. Doubling down means that you may only receive one more card");
		sb.append("\n3. The dealer must keep hitting until they reach 17");
		sb.append("\n4. A Blackjack (21 in 2 cards) beats any other 21");
		sb.append("\n5. A Blackjack will draw with any other blackjack");
		sb.append("\n6. Splitting is not allowed!");
		
		alert.setContentText(sb.toString());

		alert.showAndWait();
	}
}
