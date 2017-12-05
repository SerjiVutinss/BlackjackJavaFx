package application.components;

import application.models.GamePlayer;

public class SeatedPlayerWrapper extends GamePlayerWrapper {

	public SeatedPlayerWrapper(GamePlayer gamePlayer) {
		super(gamePlayer);
		System.out.println("Creating a Player");

		this.inputPanel = new InputPanel(this);
		this.rowInput.getChildren().add(this.inputPanel);
		this.rows.getChildren().add(this.rowInput);
	}

}
