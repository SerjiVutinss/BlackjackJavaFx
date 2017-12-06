package application.components;

import application.App;
import application.models.Card;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

// the UI representation of a card
public class CardWrapper extends Button {

	// the card object which the cardWrapper is created from
	Card card;

	private double size = 150;
	public boolean isMasked = false;

	public CardWrapper(Card card, boolean isMasked) {
		// call the button's super constructor
		super();
		// assign the card object
		this.card = card;
		// determines whether the card appears face up or face down
		this.isMasked = isMasked;
		// finally, build the UI component
		setupCard(this.card);
	}

	public void setupCard(Card card) {

		// set a standard size for all cards based on the images
		this.setMinSize(this.card.img.getWidth() / 2, this.card.img.getHeight() / 2);

		// set a border
		this.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

		// this will determine whether a card appears face up or down (dealer's second
		// card should be dealt face down)
		if (this.isMasked) {
			// set card background to the back of the card
			Image cardImage = new Image("file:" + App.filePath + "card_back_1.jpg");
			this.setBackground(new Background(
					new BackgroundImage(cardImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
							BackgroundPosition.CENTER, new BackgroundSize(size, size, true, true, true, true))));
		} else {
			// set card background to card front
			this.setBackground(new Background(
					new BackgroundImage(this.card.img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
							BackgroundPosition.CENTER, new BackgroundSize(size, size, true, true, true, true))));
		}

		// click event used for debugging purposes to print the card name to the console
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				CardWrapper clicked = (CardWrapper) arg0.getSource();
				System.out.println(clicked.card.fullName);
			}
		});
	}
}
