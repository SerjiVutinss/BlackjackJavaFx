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

		this.gamePlayer = gamePlayer;
		this.handWrapper = new HandWrapper(this.gamePlayer.hand);

		this.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

		// VBox to hold the rows
		this.rows = new VBox();
		this.rowDetails = new HBox();
		this.rowHand = new HBox();
		this.rowInput = new HBox();

		this.rowDetails.getChildren().add(new Label(this.gamePlayer.name));

		this.rowHand.getChildren().clear();

		// decide whether the cards should be face up or face down
		if (this.gamePlayer.getClass() == Dealer.class) {
			this.handWrapper.setIsDealer(true);
		}
		this.rowHand.getChildren().add(handWrapper);

		this.rows.getChildren().add(this.rowDetails);
		this.rows.getChildren().add(this.rowHand);

		// if this is a Dealer, do nothing
		//
		// this.inputPanel = new InputPanel(this);
		// this.rowInput.getChildren().add(this.inputPanel);
		// this.rows.getChildren().add(this.rowInput);

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
