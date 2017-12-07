package application.components;

import application.GameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// custom VBox to hold player controls
public class InputPanel extends VBox {

	HBox hbButtons;
	Button btnStand;
	Button btnHit;
	Button btnDouble;

	public InputPanel(GamePlayerWrapper gamePlayerWrapper) {

		// create a HBox container to hold the buttons
		this.hbButtons = new HBox();

		// create the Stand button, set its text and add it to the container
		this.btnStand = new Button();
		this.btnStand.setText("Stand");
		this.hbButtons.getChildren().add(btnStand);

		// set the Stand button event handler
		btnStand.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// handle the stand event
				System.out.println("Player Stood!");
				GameManager.handleStand(gamePlayerWrapper);
			}
		});

		// add the Hit button and add it to the container
		this.btnHit = new Button();
		this.btnHit.setText("Hit!");
		this.hbButtons.getChildren().add(btnHit);

		// set the Hit button event handler
		btnHit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// handle the hit event
				GameManager.handleHit(gamePlayerWrapper);
			}
		});

		// create the Double button and add it to the container
		this.btnDouble= new Button();
		this.btnDouble.setText("Double");
		this.hbButtons.getChildren().add(btnDouble);

		// set the Hit button event handler
		btnDouble.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// handle the hit event
				GameManager.handleDouble(gamePlayerWrapper);
			}
		});

		// add the button container (HBox) to this component
		this.getChildren().add(this.hbButtons);

	}
}
