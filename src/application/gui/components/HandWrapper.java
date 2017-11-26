package application.gui.components;

import java.util.ArrayList;

import application.models.Card;
import javafx.scene.layout.HBox;

public class HandWrapper extends HBox {

	ArrayList<CardWrapper> handCardWrappers;

	public HandWrapper() {
		this.handCardWrappers = new ArrayList<CardWrapper>();
	}

	public HandWrapper(CardWrapper cardWrapper) {
		this();
		this.handCardWrappers.add(cardWrapper);
		this.initialize();
	}

	public HandWrapper(ArrayList<CardWrapper> cardWrappers) {
		this();
		this.handCardWrappers = cardWrappers;
		this.initialize();
	}
	public void addCardWrapper(CardWrapper cardWrapper) {
		this.getChildren().add(cardWrapper);
	}
	
	private void initialize() {
		this.getChildren().clear();
		for (CardWrapper cardWrapper : handCardWrappers) {
			this.getChildren().add(cardWrapper);
		}
	}

}
