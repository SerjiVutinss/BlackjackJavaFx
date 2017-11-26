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
	public static String filePath;
	public final double sizeMulti = 1.0;
	RootLayoutController rootLayoutController;
	
	@Override
	public void start(Stage primaryStage) {
		
		// set up some stuff
//		GameManager gm = new GameManager();
//		//gm.startGame();
//		gm.startGame();
		// set up some stuff
		GameManager gm = new GameManager();
		//gm.startGame();
		gm.startGame();
		
		try {
			// auto-generated application start
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
			
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("RootLayout.fxml"));			
			
			BorderPane root = (BorderPane)loader.load();
			
			// start the game
			
			//RootLayoutController rootController = loader.getController();
			//gm.startGame();
			
			
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {

		// Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		s = s + "\\assets\\images\\cards\\";
		filePath = s;
		launch(args);
	}
}
