package application;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CalendarController {
	private Stage applicationStage;

	
    @FXML
    void getNewCalendar(ActionEvent event) {
    	Scene mainScene = applicationStage.getScene();
    	VBox rows = new VBox();
    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> applicationStage.setScene(mainScene));
    	rows.getChildren().add(doneButton);
    	Scene calendarInfoScene = new Scene(rows);
    	applicationStage.setScene(calendarInfoScene);
    	System.out.println("Button Pressed");
    }

    public Stage getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage(Stage applicationStage) {
		this.applicationStage = applicationStage;
	}
}

