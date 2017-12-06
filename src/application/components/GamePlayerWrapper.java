package application.components;

import application.models.Dealer;
import application.models.GamePlayer;
import application.models.IUpdateable;
import application.models.SeatedPlayer;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GamePlayerWrapper extends VBox implements IUpdateable {

	public GamePlayer gamePlayer;
	public HandWrapper handWrapper;
	InputPanel inputPanel;

	public VBox rows;
	public HBox rowDetails;
	public HBox rowHand;
	public HBox rowInput;

	public GamePlayerWrapper(GamePlayer gamePlayer) {

		// set the gamePlayer variable
		this.gamePlayer = gamePlayer;
		// create a new handwrapper
		this.handWrapper = new HandWrapper(this.gamePlayer.hand);

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

		// if this is a Dealer, do nothing
		//
		// this.inputPanel = new InputPanel(this);
		// this.rowInput.getChildren().add(this.inputPanel);
		// this.rows.getChildren().add(this.rowInput);

		// and add the rows to the GamePlayerWrapper (VBox)
		this.getChildren().add(this.rows);

		if (this.gamePlayer.getClass() == Dealer.class) {
			System.out.println("DEALER");
		} else {
			System.out.println("PLAYER");
			this.rowInput.getChildren().add(new Label("Balance: " + ((SeatedPlayer) this.gamePlayer).balance));
		}

	}

	public void removeHand() {

		HBox hbHandDead = new HBox();
		// hbHandDead.setMinWidth(this.handWrapper.hbCardWrapperSlots.getWidth());
		// hbHandDead.setMinHeight(this.handWrapper.hbCardWrapperSlots.getHeight());
		Label lblHandDead = new Label("BUST");
		lblHandDead.setFont(new Font(32));
		hbHandDead.getChildren().add(lblHandDead);

		this.handWrapper.hbCardWrapperSlots.getChildren().clear();
		// this.handWrapper.hbCardWrapperSlots.getChildren().clear();
		this.handWrapper.lblHandScore.setText("YOU BUST!");
		this.rowInput.getChildren().clear();
		this.handWrapper.hand = null;

	}

	// @Override
	public void update() {

		if (this.gamePlayer.hand != null) {
			this.rowHand.getChildren().clear();
			this.rowHand.getChildren().add(new HandWrapper(this.gamePlayer.hand));
		}
	}
}
