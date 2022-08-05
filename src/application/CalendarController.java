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

// The Calendar Controller implements Initializable to initialize data
public class CalendarController implements Initializable {
	
	
	@Override	// Initialize calendar data from database
	public void initialize(URL location, ResourceBundle resources) {
		listViewCalendars.setItems(calendars);
		calendarChoiceBox.setItems(calendars);
		deleteCalendarChoiceBox.setItems(calendars);
	}   
	
	
	// Load from database textfile
	private CalendarList calendarList = new CalendarList("src\\application\\database.txt");
	
	
	//store data loaded from database into calendars instance of calendarList
	private ObservableList<BasicCalendar> calendars = calendarList.getCalendars();
	
	
	// create instance variable that keeps track of which calendar events are being displayed
	private BasicCalendar displayedCalendar = new BasicCalendar();
	
	
	// Nodes in main fxml file
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
	
	
	/** Creates a new BasicCalendar inside of ObservableList<BasicCalendar> if
	 * a name has been entered an no banned characters have been used.
	 * @param event create calendar button has been pushed in the GUI
	 * @throws IOException
	 */
    @FXML
    void createNewCalendar(ActionEvent event) throws IOException {
    	if (newCalendarName.getText() == "") {		// Must choose a name for the calendar
    		errorLabel.setText("Enter a Calendar Name");
    	}
    	else if (newCalendarName.getText().contains("`") || newCalendarName.getText().contains(";")
    			|| newCalendarName.getText().contains("/")) {			// Cannot use these characters 
    		errorLabel.setText("The characters ; / ` cannot be used");	// They are used in the database
    	}
    	else {		// Add the calendar to the calendar list and change various nodes in the GUI
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
    		calendarList.saveDataBase();		// Update the database with the new calendar
    	}
    }
    
    
    /** Changes the events displayed in the GUI to the selected calendar 
     *  if a calendar in the choicebox has been selected.
     * @param event change/select calendar button pressed
     */
    @FXML
    void selectCalendar(ActionEvent event) {
    	if (calendarChoiceBox.getValue() == null);	// A calendar must be selected in the choicebox
    	else {				// update the various nodes in the GUI to display the correct calendar
    		listViewEvents.setItems(calendars.get(calendarChoiceBox.getSelectionModel().
    				getSelectedIndex()).getEvents());
    		deleteEventChoiceBox.setItems(calendars.get(calendarChoiceBox.getSelectionModel().
    				getSelectedIndex()).getEvents());
    		displayedCalendar = calendars.get(calendarChoiceBox.getSelectionModel().
    				getSelectedIndex());
    		currentCalendarLabel.setText(displayedCalendar.getName());
    	}
    }
    

    /** Adds a new BasicEvent to the selected BasicCalendar in the GUI.
     * Error messages are displayed if a calendar is not selected. A
     * event description is not given. A banned character is used.
     * @param event add event button pressed in GUI
     */
    @FXML
    void addEvent(ActionEvent event) {
    	if (calendars.size() == 0) { 		// A calendar must be created before any events 
    		errorLabel.setText("Create a Calendar first");
    	}
    	else if (descriptionTextField.getText() == "") {	// a description must be given
    		errorLabel.setText("Enter an event description");
    	}
    	else if (descriptionTextField.getText().contains("`") || descriptionTextField.getText().contains(";")
    			|| descriptionTextField.getText().contains("/")) {		// Banned characters as they are 
    		errorLabel.setText("The characters ; / ` cannot be used");	// used in the database
    	}
    														// else if (listViewEvents.getItems() == null);
    	else if (displayedCalendar.getName() == null) {		// a calendar must be selected to add the events
    		errorLabel.setText("Choose a calendar to add the event");
    	}
    	else {				// Add the event, change the GUI, and save to the database
    		errorLabel.setText("");
    		listViewEvents.getItems().add(new BasicEvent(descriptionTextField.getText(), datePicker.getValue()));
    		deleteEventChoiceBox.setItems(calendars.get(calendarChoiceBox.getSelectionModel().
    				getSelectedIndex()).getEvents());
	    	descriptionTextField.setText("");
	    	datePicker.setValue(null);
	    	numberOfEventsLabel.setText(Integer.toString(calendars.get(0).getEvents().size()));
	    	calendarList.saveDataBase();
    	}
    }
    
    
    /** Delete a BasicEvent from the selected BasicCalendar in the GUI
     * when the delete event button is pressed and an event is selected
     * @param event the delete event button is pressed in the GUI
     */
    @FXML
    void deleteEvent(ActionEvent event) {
    	if (deleteEventChoiceBox.getItems().size() == 0);		// There must be events to delete
    	if (deleteEventChoiceBox.getValue() == null);		// An event needs to be selected in the choicebox
    	else {					// delete the event from the list, reset the choicebox, save to the database
    		listViewEvents.getItems().remove(deleteEventChoiceBox.getSelectionModel().getSelectedIndex());
    		deleteEventChoiceBox.setValue(null);
    		calendarList.saveDataBase();
    	}
    }
    
    
    /** Delete a BasicCalendar from the ObservableList of BasicCalendars'
     *  when a the delete button is pressed. There must be a calendar to delete
     *  and there must be a calendar selected in the delete calendar choicebox
     * @param event delete calendar button is pressed
     */
    @FXML
    void deleteCalendar(ActionEvent event) {
    	if (calendars.size() == 0);				// There must be a calendar
    	if (deleteCalendarChoiceBox.getValue() == null); // a calendar must be selected
    	else {			//If the deleted calendar is the calendar being displayed refresh events in GUI
    		if (deleteCalendarChoiceBox.getValue() == displayedCalendar) {
    			calendarChoiceBox.setValue(null);
    			listViewEvents.setItems(null);
    			currentCalendarLabel.setText(null);
    		}												// Refresh the calendars nodes in GUI
    		calendars.remove(deleteCalendarChoiceBox.getSelectionModel().getSelectedIndex());
    		calendarChoiceBox.setItems(calendars);
    		listViewCalendars.setItems(calendars);
    		deleteCalendarChoiceBox.setItems(calendars);
    		deleteCalendarChoiceBox.setValue(null);
    		calendarList.saveDataBase();
    	}
    }
    
    
    /** Change the view to an individual view where you can look at one BasicCalendar. 
     * This other view has its own FXML file and controller.
     * This code is from from https://www.youtube.com/watch?v=9uubyM6oHAY&ab_channel=today%27sIT
     * @param event  Switch to individual view button is pressed
     * @throws IOException
     */
    @FXML
    void switchIndividualCalendarView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("IndividualCalendarView.fxml"));
    	rootPane.getChildren().setAll(pane);		// set the rootpane to the new FXML file
    }
    
}
