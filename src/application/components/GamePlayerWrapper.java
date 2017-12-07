package application.components;

import application.models.Dealer;
import application.models.GamePlayer;
import application.models.IUpdateable;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GamePlayerWrapper extends VBox implements IUpdateable {

	// the game player object which the component is created from
	public GamePlayer gamePlayer;
	// the handWrapper which is added to the component to hold the cards, etc
	public HandWrapper handWrapper;
	// the InputPanel object which is tied to this component - contains Hit/Stand buttons
	public InputPanel inputPanel;

	
	public VBox rows;
	public HBox rowDetails;
	public HBox rowHand;
	public HBox rowInput;

	public GamePlayerWrapper(GamePlayer gamePlayer) {

		// set the gamePlayer variable
		this.gamePlayer = gamePlayer;
		// create a new handwrapper
		this.handWrapper = new HandWrapper(this.gamePlayer.getHand());

		// set a background
		this.setStyle("-fx-background-color: white");
		
		// set a border
		this.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

		// VBox to hold the rows
		this.rows = new VBox();
		// holds the player's name
		this.rowDetails = new HBox();
		// holds the handwrapper object
		this.rowHand = new HBox();
		// holds the buttons
		this.rowInput = new HBox();

		// add the player name
		this.rowDetails.getChildren().add(new Label(this.gamePlayer.name));

		// clear the contents of this row
		this.rowHand.getChildren().clear();

		// decide whether the cards should be face up or face down
		if (this.gamePlayer.getClass() == Dealer.class) {
			this.handWrapper.setIsDealer(true);
		}
		// and add the handwrapper to the row
		this.rowHand.getChildren().add(handWrapper);
		// now add all of the rows to the containing VBox
		this.rows.getChildren().add(this.rowDetails);
		this.rows.getChildren().add(this.rowHand);

		// and add the rows to the GamePlayerWrapper (VBox)
		this.getChildren().add(this.rows);

//		if (this.gamePlayer.getClass() == Dealer.class) {
//			System.out.println("DEALER");
//		} else {
//			System.out.println("PLAYER");
//			this.rowInput.getChildren().add(new Label("Balance: " + ((SeatedPlayer) this.gamePlayer).balance));
//		}
	}

	// interface method which is used to update this UI component
	@Override
	public void update() {

		if (this.gamePlayer.getHand() != null) {
			this.rowHand.getChildren().clear();
			this.rowHand.getChildren().add(new HandWrapper(this.gamePlayer.getHand()));
		}
	}
}
