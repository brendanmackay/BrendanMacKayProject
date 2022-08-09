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


public class TypeViewController implements Initializable {
		
	// Initialize calendar data from database
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		calendarChoiceBox.setItems(calendars);
		eventTypeChoiceBox.getItems().addAll(types);
		eventTypeChoiceBox.getSelectionModel().selectFirst();
	}   
	
	// Nodes in Individual view fxml
	@FXML	private ChoiceBox<BasicCalendar> calendarChoiceBox;
	@FXML	private ListView<BasicEvent> listViewEvents;
	@FXML	private DatePicker datePicker;
	@FXML	private TextField descriptionTextField;
	@FXML	private Label currentCalendarLabel;
	@FXML	private Label errorLabel;
	@FXML	private ChoiceBox<BasicEvent> deleteEventChoiceBox;
	@FXML	private ChoiceBox<String> eventTypeChoiceBox;
	@FXML	private TextField typeBoxOne;
	@FXML	private TextField typeBoxTwo;
	
	// The types of events which can be added from the choiceBox
	String[] types = {"Basic", "Flight", "Work"};
	
	// get stage and scene to switch back to home screen
	private Stage applicationStage;
	private Scene scene;
	
	// Load from database textfile
	private CalendarList calendarList = new CalendarList("src\\application\\database.txt");
		
	//store data loaded from database into calendars instance of calendarList
	private ObservableList<BasicCalendar> calendars = calendarList.getCalendars();
	
	// create variable that keeps track of which calendar events are being displayed
	private BasicCalendar displayedCalendar = new BasicCalendar();
	
	
	
	/** Changes the events displayed in the GUI to the selected calendar 
     *  if a calendar in the choicebox has been selected.
     * @param event change/select calendar button pressed
     */
	@FXML
    void selectCalendar(ActionEvent event) {
    	if (calendarChoiceBox.getValue() == null);
    	else {
    		listViewEvents.setItems(calendars.get(calendarChoiceBox.getSelectionModel().getSelectedIndex()).getEvents());
    		deleteEventChoiceBox.setItems(calendars.get(calendarChoiceBox.getSelectionModel().getSelectedIndex()).getEvents());
    		displayedCalendar = calendars.get(calendarChoiceBox.getSelectionModel().getSelectedIndex());
    		currentCalendarLabel.setText("Events in: " + displayedCalendar.getName());
    	}
    }
	
	
	/** Adds a new BasicEvent to the selected BasicCalendar in the GUI.
     * Error messages are displayed if a calendar is not selected. A
     * event description is not given. A banned character is used.
     * @param event add event button pressed in GUI
     */
	@FXML
    void addEvent(ActionEvent event) {
    	if (calendars.size() == 0) {
    		errorLabel.setText("Create a Calendar first");
    	}
    	else if (descriptionTextField.getText() == "") {
    		errorLabel.setText("Enter an event description");
    	}
    	else if (descriptionTextField.getText().matches(CalendarList.bannedCharacters) 
    			|| typeBoxOne.getText().matches(CalendarList.bannedCharacters)
    			|| typeBoxTwo.getText().matches(CalendarList.bannedCharacters)) {
    		errorLabel.setText("The characters ; / ` ! < > cannot be used");
    	}
    	else if (listViewEvents.getItems() == null);
    	else if (displayedCalendar.getName() == null) {
    		errorLabel.setText("Choose a calendar to add the event");
    	}
    	
    	else if (eventTypeChoiceBox.getValue().equals("Flight")) {
    		if (datePicker.getValue() == null || typeBoxOne.getText() == null || typeBoxOne.getText() == "" 
    				|| typeBoxTwo.getText() == null || typeBoxTwo.getText() == "") {
    			errorLabel.setText("Enter date, departure, & arrival");
    		}
    		else {
	    		listViewEvents.getItems().add(new Flight(descriptionTextField.getText(), datePicker.getValue(),
	    				typeBoxOne.getText(), typeBoxTwo.getText()));
	    		refresh();
    		}
    	
    	}
    	else if (eventTypeChoiceBox.getValue().equals("Work")) {
    		if (datePicker.getValue() == null || typeBoxOne.getText() == null || typeBoxOne.getText() == "" 
    				|| typeBoxTwo.getText() == null || typeBoxTwo.getText() == "") {
    			errorLabel.setText("Enter date and shift times");
    		}
    		else {
	    		listViewEvents.getItems().add(new Work(descriptionTextField.getText(), datePicker.getValue(),
	    				typeBoxOne.getText(), typeBoxTwo.getText()));
	    		errorLabel.setText("");
	    		refresh();
    		}
    	}
    	else if (eventTypeChoiceBox.getValue().equals("Basic")) {
    		listViewEvents.getItems().add(new BasicEvent(descriptionTextField.getText(), datePicker.getValue()));
    		deleteEventChoiceBox.setItems(calendars.get(calendarChoiceBox.getSelectionModel().getSelectedIndex()).getEvents());
	    	refresh();
    	}
    }
	
	
	/** Refreshes nodes on the GUI when called.
	 */
	private void refresh() {
		errorLabel.setText("");
		descriptionTextField.setText("");
    	datePicker.setValue(null);
    	calendarList.saveDataBase();
    	typeBoxOne.setText("");
    	typeBoxTwo.setText("");
	}
	
	
	/** When this button is pressed the information for whatever event
	 * type has been selected will be displayed in the text boxes.
	 * @param event
	 */
	@FXML
	void displayPromptText(ActionEvent event) {
		if (eventTypeChoiceBox.getValue().equals("Basic")) {
			typeBoxOne.setPromptText("No extra properties");
			typeBoxTwo.setPromptText("for basic events");
		}
		if (eventTypeChoiceBox.getValue().equals("Flight")) {
			typeBoxOne.setPromptText("Departure Airport");
			typeBoxTwo.setPromptText("Arrival Airport");
		}
		if (eventTypeChoiceBox.getValue().equals("Work")) {
			typeBoxOne.setPromptText("Shift Start time");
			typeBoxTwo.setPromptText("Shift End time");
		}
	}
	
	
	/** Delete a BasicEvent from the selected BasicCalendar in the GUI
     * when the delete event button is pressed and an event is selected
     * @param event the delete event button is pressed in the GUI
     */
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
	
    /** Change the view to the main view.
     * The main view has a separate controller.
     * This code is from from https://www.youtube.com/watch?v=9uubyM6oHAY&ab_channel=today%27sIT
     * @param event  Switch to individual view button is pressed
     * @throws IOException
     */
	@FXML
	public void switchHomeScreen(ActionEvent event) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("/fxmlFiles/HomeScreen.fxml"));
		applicationStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		applicationStage.setScene(scene);
		applicationStage.show();
		// code from https://www.youtube.com/watch?v=hcM-R-YOKkQ&ab_channel=BroCode
    }
	
	

}
