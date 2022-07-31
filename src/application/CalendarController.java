package application;


import java.io.FileInputStream;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CalendarController {
	private Stage applicationStage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private AnchorPane rootPane;
	
    @FXML
    void changeScene(ActionEvent event) {
    	Scene mainScene = applicationStage.getScene();
    	VBox rows = new VBox();
    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> applicationStage.setScene(mainScene));
    	rows.getChildren().add(doneButton);
    	Scene calendarInfoScene = new Scene(rows);
    	applicationStage.setScene(calendarInfoScene);
    	System.out.println("Button Pressed"); 
    }
    
    public void switchHomeScreen(ActionEvent event) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		applicationStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		applicationStage.setScene(scene);
		applicationStage.show();
		// code from https://www.youtube.com/watch?v=hcM-R-YOKkQ&ab_channel=BroCode
    }
    
    
    /*
    public void getNewCalendarInfo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CreateCalendar.fxml"));
    	applicationStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	applicationStage.setScene(scene);
    	applicationStage.show();
    	// code from https://www.youtube.com/watch?v=hcM-R-YOKkQ&ab_channel=BroCode
        }
    */
    // Hi
    
    
    @FXML
    void getNewCalendarInfo(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("CreateCalendar.fxml"));
    	rootPane.getChildren().setAll(pane);
    }
    
    
    public Stage getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage(Stage applicationStage) {
		this.applicationStage = applicationStage;
	}
}

