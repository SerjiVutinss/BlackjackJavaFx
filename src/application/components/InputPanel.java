package application.components;

import application.GameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InputPanel extends VBox {

	HBox hbButtons;
	Button btnStand;
	Button btnHit;

	public InputPanel(GamePlayerWrapper gamePlayerWrapper) {

		this.hbButtons = new HBox();
		
		this.btnStand= new Button("Stand");
		this.btnStand.setText("Stand");
		this.hbButtons.getChildren().add(btnStand);
		
		btnStand.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				// handle the stand event
				//GameManager.handleHit(gamePlayerWrapper);
			}
		});
		
		btnHit = new Button("Hit");
		btnHit.setText("Hit!");
		this.hbButtons.getChildren().add(btnHit);
		
		btnHit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				// handle the hit event
				GameManager.handleHit(gamePlayerWrapper);
			}
		});
		
		this.getChildren().add(this.hbButtons);
	}

}
