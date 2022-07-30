package application;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.stage.Stage;


public class CalendarController {
	private Stage applicationStage;

	public Stage getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage(Stage applicationStage) {
		this.applicationStage = applicationStage;
	}
    @FXML
    void createNewCalendar(ActionEvent event) {
    	System.out.println("Button Pressed");
    }

}

