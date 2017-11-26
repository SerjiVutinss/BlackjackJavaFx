package application.gui.components;

import application.GameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InputPanel extends VBox {

	HBox hbButtons;
	Button btnHit;

	public InputPanel(GamePlayerWrapper gamePlayerWrapper) {

		this.hbButtons = new HBox();
		
		btnHit = new Button("Hit");
		btnHit.setText("Hit!");
		this.hbButtons.getChildren().add(btnHit);
		
		btnHit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// give the player a new card from the top of the deck
								
				GameManager.dealCard(gamePlayerWrapper);
			}
		});
		
		this.getChildren().add(this.hbButtons);
	}

}
