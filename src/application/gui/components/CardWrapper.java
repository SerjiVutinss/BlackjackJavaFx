package application.gui.components;

import application.models.Card;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CardWrapper extends Button {

	Card card;
	Button btn;
	
	private double size = 150;

	public CardWrapper(Card card) {
		super();
		this.card = card;
		
		this.setMinSize(this.card.img.getWidth(), this.card.img.getHeight());		

		this.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

		//ImageView iView = new ImageView(this.card.img);
		//iView.setFitWidth(80);
		//iView.setPreserveRatio(true);

		this.setBackground(
				new Background(
						new BackgroundImage(
							this.card.img, 
							BackgroundRepeat.NO_REPEAT, 
							BackgroundRepeat.NO_REPEAT, 
							BackgroundPosition.CENTER, 
							new BackgroundSize(size, size, true,true,true,true)
							)
						)
				);

		this.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				CardWrapper clicked = (CardWrapper) arg0.getSource();
				System.out.println(clicked.card.fileName);
			}
		});
	}
}
