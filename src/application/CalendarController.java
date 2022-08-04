package application;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.value.*;


public class CalendarController implements Initializable {
	
	// Initialize calendar data from database
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listViewCalendars.setItems(calendars);
		calendarChoiceBox.setItems(calendars);
		deleteCalendarChoiceBox.setItems(calendars);
	}   
	
	// Load from database textfile
	private CalendarList calendarList = new CalendarList("src\\application\\database.txt");
	
	//store data loaded from database into calendars instance of calendarList
	private ObservableList<BasicCalendar> calendars = calendarList.getCalendars();
	
	// create variable that keeps track of which calendar events are being displayed
	private BasicCalendar displayedCalendar = new BasicCalendar();
	
	// Nodes in main fxml
	@FXML	private AnchorPane rootPane;
	@FXML	private TextField calendarNameTextField;
	@FXML	private TextField descriptionTextField;
	@FXML	private DatePicker datePicker;
	@FXML	private ListView<BasicCalendar> listViewCalendars;
	@FXML	private ListView<BasicEvent> listViewEvents;
	@FXML	private TextField newCalendarName;
	@FXML	private Label numberOfEventsLabel;
	@FXML	private ChoiceBox<BasicCalendar> calendarChoiceBox;
	@FXML	private Label errorLabel;
	@FXML	private ChoiceBox<BasicCalendar> deleteCalendarChoiceBox;
	@FXML	private ChoiceBox<BasicEvent> deleteEventChoiceBox;
	@FXML	private Label currentCalendarLabel;
	
	
	
    @FXML
    void createNewCalendar(ActionEvent event) throws IOException {
    	if (newCalendarName.getText() == "") {
    		errorLabel.setText("Enter a Calendar Name");
    	}
    	else if (newCalendarName.getText().contains("`") || newCalendarName.getText().contains(";")
    			|| newCalendarName.getText().contains("/")) {
    		errorLabel.setText("The characters ; / ` cannot be used");
    	}
    	else {
    		calendars.add(new BasicCalendar(newCalendarName.getText()));
    		errorLabel.setText("");
    		newCalendarName.setText("");
    		listViewCalendars.setItems(calendars);
    		calendarChoiceBox.setItems(calendars);
    		deleteCalendarChoiceBox.setItems(calendars);
    		listViewEvents.setItems(calendars.get(calendars.size()-1).getEvents());
    		deleteEventChoiceBox.setItems(calendars.get(calendars.size()-1).getEvents());
    		calendarChoiceBox.getSelectionModel().select(calendars.size()-1);
    		displayedCalendar = calendars.get(calendars.size()-1);
    		currentCalendarLabel.setText(displayedCalendar.getName());
    		calendarList.saveDataBase();
    	}
    }
    
    
    @FXML
    void selectCalendar(ActionEvent event) {
    	if (calendarChoiceBox.getValue() == null);
    	else {
    		listViewEvents.setItems(calendars.get(calendarChoiceBox.getSelectionModel().getSelectedIndex()).getEvents());
    		deleteEventChoiceBox.setItems(calendars.get(calendarChoiceBox.getSelectionModel().getSelectedIndex()).getEvents());
    		displayedCalendar = calendars.get(calendarChoiceBox.getSelectionModel().getSelectedIndex());
    		currentCalendarLabel.setText(displayedCalendar.getName());
    	}
    }
    

    @FXML
    void addEvent(ActionEvent event) {
    	if (calendars.size() == 0) {
    		errorLabel.setText("Create a Calendar first");
    	}
    	else if (descriptionTextField.getText() == "") {
    		errorLabel.setText("Enter an event description");
    	}
    	else if (descriptionTextField.getText().contains("`") || descriptionTextField.getText().contains(";")
    			|| descriptionTextField.getText().contains("/")) {
    		errorLabel.setText("The characters ; / ` cannot be used");
    	}
    	else if (listViewEvents.getItems() == null);
    	else if (displayedCalendar.getName() == null) {
    		errorLabel.setText("Choose a calendar to add the event");
    	}
    	else {
    		errorLabel.setText("");
    		listViewEvents.getItems().add(new BasicEvent(descriptionTextField.getText(), datePicker.getValue()));
    		deleteEventChoiceBox.setItems(calendars.get(calendarChoiceBox.getSelectionModel().getSelectedIndex()).getEvents());
	    	descriptionTextField.setText("");
	    	datePicker.setValue(null);
	    	numberOfEventsLabel.setText(Integer.toString(calendars.get(0).getEvents().size()));
	    	calendarList.saveDataBase();
    	}
    }
    
    @FXML
    void deleteEvent(ActionEvent event) {
    	if (deleteEventChoiceBox.getItems().size() == 0);
    	if (deleteEventChoiceBox.getValue() == null);
    	else {
    		listViewEvents.getItems().remove(deleteEventChoiceBox.getSelectionModel().getSelectedIndex());
    		deleteEventChoiceBox.setValue(null);
    		calendarList.saveDataBase();
    	}
    }
    
    @FXML
    void deleteCalendar(ActionEvent event) {
    	if (calendars.size() == 0);
    	if (deleteCalendarChoiceBox.getValue() == null);
    	else {
    		if (deleteCalendarChoiceBox.getValue() == displayedCalendar) {
    			calendarChoiceBox.setValue(null);
    			listViewEvents.setItems(null);
    			currentCalendarLabel.setText(null);
    		}
    		calendars.remove(deleteCalendarChoiceBox.getSelectionModel().getSelectedIndex());
    		calendarChoiceBox.setItems(calendars);
    		listViewCalendars.setItems(calendars);
    		deleteCalendarChoiceBox.setItems(calendars);
    		deleteCalendarChoiceBox.setValue(null);
    		calendarList.saveDataBase();
    	}
    }
    
    
    @FXML
    void switchIndividualCalendarView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("IndividualCalendarView.fxml"));
    	rootPane.getChildren().setAll(pane);
    	// code from https://www.youtube.com/watch?v=9uubyM6oHAY&ab_channel=today%27sIT
    }
    
}

/* public void initialize(URL url, ResourceBundle rb) {


calendarChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
		(ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
	    	listViewEventsHome.setItems(calendars.get(
	    			calendarChoiceBox.getSelectionModel().getSelectedIndex()).getEvents());
		});
// Read https://www.tutorialspoint.com/example-to-set-action-listeners-behavior-to-a-choicebox-in-javafx
// to understand how to implement listeners for choicebox
} */

