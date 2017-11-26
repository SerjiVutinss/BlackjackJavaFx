package application.gui.components;

import application.models.GamePlayer;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GamePlayerWrapper extends VBox {

	GamePlayer gamePlayer;

	public GamePlayerWrapper(GamePlayer gamePlayer) {

		this.gamePlayer = gamePlayer;

		// VBox to hold the rows
		VBox rows = new VBox();
		HBox rowDetails = new HBox();
		HBox rowHand = new HBox();

		rowDetails.getChildren().add(new Label(this.gamePlayer.name));
		rowHand.getChildren().add(new HandWrapper(this.gamePlayer.hand));

		rows.getChildren().add(rowDetails);
		rows.getChildren().add(rowHand);
		this.getChildren().add(rows);
	}

}
