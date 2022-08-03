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
	
	public void initialize(URL url, ResourceBundle rb) {
		// datePickerHomeView.setValue(LocalDate.now());
		// Code fromhttps://www.youtube.com/watch?v=9uubyM6oHAY&ab_channel=today%27sIT
		
		calendarChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			    	listViewEventsHome.setItems(calendars.get(
			    			calendarChoiceBox.getSelectionModel().getSelectedIndex()).getEvents());
				});
		// Read https://www.tutorialspoint.com/example-to-set-action-listeners-behavior-to-a-choicebox-in-javafx
		// to understand how to implement listeners for choicebox
	}
	
	private Stage applicationStage;
	private Scene scene;
	
	private ObservableList<BasicCalendar> calendars = FXCollections.observableArrayList();
	
	// Nodes
	@FXML	private AnchorPane rootPane;
	@FXML	private TextField calendarNameTextField;
	@FXML	private TextField descriptionTextFieldIndividual;
	@FXML	private TextField descriptionTextField;
	@FXML	private DatePicker datePickerHomeView;
	@FXML	private DatePicker datePickerIndividualView;
	@FXML	private ListView<BasicEvent> listViewIndividual;
	@FXML	private ListView<BasicCalendar> listViewCalendarsHome;
	@FXML	private ListView<BasicEvent> listViewEventsHome;
	@FXML	private TextField newCalendarName;
	@FXML	private Label numberOfEventsLabel;
	@FXML	private ChoiceBox<BasicCalendar> calendarChoiceBox;
	@FXML	private Label errorLabel;
	
	
	
    @FXML
    void createNewCalendar(ActionEvent event) throws IOException {
    	if (newCalendarName.getText() == "") {
    		errorLabel.setText("Enter a Calendar Name");
    	}
    	else {
    		errorLabel.setText("");
    		calendars.add(new BasicCalendar(newCalendarName.getText()));
    		listViewCalendarsHome.setItems(calendars);
    		newCalendarName.setText("");
    		calendarChoiceBox.setItems(calendars);
    		calendarChoiceBox.getSelectionModel().select(calendars.size()-1);
    	}
    }
    
    public void switchHomeScreen(ActionEvent event) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		applicationStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		applicationStage.setScene(scene);
		applicationStage.show();
		// code from https://www.youtube.com/watch?v=hcM-R-YOKkQ&ab_channel=BroCode
    }
    
    @FXML
    void switchIndividualCalendarView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("IndividualCalendarView.fxml"));
    	rootPane.getChildren().setAll(pane);
    	// from https://www.youtube.com/watch?v=9uubyM6oHAY&ab_channel=today%27sIT
    }
    
    @FXML
    void addEvent(ActionEvent event) {
    	if (calendars.size() == 0) {
    		errorLabel.setText("Create a Calendar first");
    	}
    	else if (descriptionTextField.getText() == "") {
    		errorLabel.setText("Enter an event description");
    	}
    	else {
    		errorLabel.setText("");
	    	calendars.get(calendarChoiceBox.getSelectionModel().getSelectedIndex()).addEvents(
	    			new BasicEvent(datePickerHomeView.getValue(), descriptionTextField.getText()));
	    	listViewEventsHome.setItems(calendars.get(calendarChoiceBox.getSelectionModel().getSelectedIndex()).getEvents());
	    	descriptionTextField.setText(null); // code from code from https://www.youtube.com/watch?v=9uubyM6oHAY&ab_channel=today%27sIT
	    	numberOfEventsLabel.setText(Integer.toString(calendars.get(0).getEvents().size()));
    	}
    }
    
    
    public Stage getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage(Stage applicationStage) {
		this.applicationStage = applicationStage;
	}
}

