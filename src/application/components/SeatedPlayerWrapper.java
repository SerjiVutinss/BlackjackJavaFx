package application.components;

import application.models.GamePlayer;
import application.models.SeatedPlayer;
import javafx.scene.control.Label;

public class SeatedPlayerWrapper extends GamePlayerWrapper {

	// component which is used to represent a GamePlayer object in the UI
	public SeatedPlayerWrapper(GamePlayer gamePlayer) {
		// call the super constructor
		super(gamePlayer);

		// human players have a control panel, so create and add it to this component
		this.inputPanel = new InputPanel(this);
		this.rowInput.getChildren().add(this.inputPanel);
		
		
		this.rowInput.getChildren().add(new Label("Balance: " + ((SeatedPlayer) this.gamePlayer).balance));
		
		this.rows.getChildren().add(this.rowInput);
	}

}
