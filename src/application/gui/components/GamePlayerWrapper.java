package application.gui.components;

import application.models.GamePlayer;
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

public class GamePlayerWrapper extends VBox {

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

		this.rowHand.getChildren().add(handWrapper);

		this.rows.getChildren().add(this.rowDetails);
		this.rows.getChildren().add(this.rowHand);

		// if this is a Dealer, do nothing

		this.inputPanel = new InputPanel(this);

		this.rowInput.getChildren().add(this.inputPanel);

		this.rows.getChildren().add(this.rowInput);
		this.getChildren().add(this.rows);
	}

	public void removeHand() {

		HBox hbHandDead = new HBox();
		hbHandDead.setMinWidth(this.handWrapper.hbCardWrapperSlots.getWidth());
		hbHandDead.setMinHeight(this.handWrapper.hbCardWrapperSlots.getHeight());
		Label lblHandDead = new Label("BUST");
		lblHandDead.setFont(new Font(32));
		hbHandDead.getChildren().add(lblHandDead);

		this.rowHand.getChildren().clear();
		this.rowHand.getChildren().add(hbHandDead);
		this.rowInput.getChildren().clear();

	}

	public static void updateHandUI(GamePlayerWrapper gamePlayerWrapper) {

		gamePlayerWrapper.rowHand.getChildren().clear();
		gamePlayerWrapper.rowHand.getChildren().add(new HandWrapper(gamePlayerWrapper.gamePlayer.hand));
	}

}
