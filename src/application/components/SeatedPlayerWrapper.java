package application.components;

import application.models.GamePlayer;
import application.models.SeatedPlayer;
import javafx.scene.control.Label;

public class SeatedPlayerWrapper extends GamePlayerWrapper {
	
	// label to display the player's balance
	private Label lblBalance;
	
	// component which is used to represent a GamePlayer object in the UI
	public SeatedPlayerWrapper(GamePlayer gamePlayer) {
		// call the super constructor
		super(gamePlayer);

		// human players have a control panel, so create and add it to this component
		this.inputPanel = new InputPanel(this);
		this.rowInput.getChildren().add(this.inputPanel);
		
		// they also have a balance, so add a label to display this
		this.lblBalance = new Label("Balance: " + ((SeatedPlayer) this.gamePlayer).getBalance());
		this.rowInput.getChildren().add(lblBalance);
		
		this.rows.getChildren().add(this.rowInput);
	}
	
	public void setAmountBalance() {
		SeatedPlayer sp = (SeatedPlayer)this.gamePlayer;
		this.lblBalance.setText(String.valueOf(sp.getBalance()));
	}
	
	

}
