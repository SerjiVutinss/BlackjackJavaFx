package application;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

	public static final Path currentRelativePath = Paths.get("");
	public static String image_path;
	public final double sizeMulti = 1.0;
	//RootLayoutController rootLayoutController;

	// maximum hand score, any hand exceeding this
	// value will be mucked
	public static final int MAX_SCORE = 21;
	// each player is dealt this number of cards at the start of each round
	public static final int NUM_START_CARDS = 2;

	@Override
	public void start(Stage primaryStage) {

		// set up some stuff
		//GameManager gm = new GameManager();

		try {
			// auto-generated application start
			// BorderPane root =
			// (BorderPane)FXMLLoader.load(getClass().getResource("RootLayout.fxml"));

			FXMLLoader loader = new FXMLLoader(getClass().getResource("RootLayout.fxml"));

			BorderPane root = (BorderPane) loader.load();

			Scene scene = new Scene(root, 1024, 768);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// build the path to the image folder
		String s = currentRelativePath.toAbsolutePath().toString();
		s = s + "\\assets\\images\\cards\\";
		image_path = s;
		// launch the application
		launch(args);
	}
}
