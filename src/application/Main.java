package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;


import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {										// Load the main view
			Parent root = FXMLLoader.load(getClass().getResource("/fxmlFiles/HomeScreen.fxml"));
			Scene scene = new Scene(root);			// create a scene from the view
			primaryStage.setScene(scene);			// set the scene
			primaryStage.show();					// show the scene
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {		// Run the application
		launch(args);
	}
}
