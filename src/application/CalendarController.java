package application;



import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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


public class CalendarController {
	private Stage applicationStage;
	private Scene scene;
	private ObservableList<BasicCalendar> calendars = FXCollections.observableArrayList();
	
	@FXML
	private AnchorPane rootPane;
	
	@FXML
	TextField calendarNameTextField;

	@FXML
	TextField descriptionTextFieldIndividual;
	
	@FXML
	TextField descriptionTextFieldHome;
	
	@FXML
	DatePicker datePickerHomeView;
	
	@FXML
	DatePicker datePickerIndividualView;
	
	@FXML
	ListView<BasicEvent> listViewIndividual;
	
	@FXML
	ListView<BasicCalendar> listViewCalendarsHome;
	
	@FXML
	ListView<BasicEvent> listViewEventsHome;
	
	@FXML
	TextField newCalendarName;
	
	@FXML
	Label numberOfEventsLabel;
	
	@FXML
	ChoiceBox<String> calendarChoiceBox;
    
    @FXML
    void addNewCalendar(ActionEvent event) throws IOException {
    	if (newCalendarName.getText() == null);
    	else if (newCalendarName.getText() == "");
    	else {
    		calendars.add(new BasicCalendar(newCalendarName.getText()));
    		listViewCalendarsHome.setItems(calendars);
    		newCalendarName.setText(null);
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
    
    private void refresh() { // code from https://www.youtube.com/watch?v=9uubyM6oHAY&ab_channel=today%27sIT
    	datePickerHomeView.setValue(LocalDate.now());
    	descriptionTextFieldHome.setText(null);
    }
    
    @FXML
    void addEvent(ActionEvent event) {
    	calendars.get(0).addEvents(new BasicEvent(datePickerHomeView.getValue(), descriptionTextFieldHome.getText()));
    	listViewEventsHome.setItems(calendars.get(0).getEvents());
    	refresh(); // code from code from https://www.youtube.com/watch?v=9uubyM6oHAY&ab_channel=today%27sIT
    	numberOfEventsLabel.setText(Integer.toString(calendars.get(0).getEvents().size()));
    }
    
    public Stage getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage(Stage applicationStage) {
		this.applicationStage = applicationStage;
	}
}

