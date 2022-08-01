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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CalendarController {
	private Stage applicationStage;
	private Scene scene;
	private ObservableList<BasicCalendar> calendars = FXCollections.observableArrayList();
	private BasicCalendar oneCalendar = new BasicCalendar("SKIING");
	
	@FXML
	private AnchorPane rootPane;
	
	@FXML
	TextField calendarNameTextField;

	@FXML
	TextField descriptionTextFieldIndividual;
	
	@FXML
	DatePicker datePickerIndividualView;
	
	@FXML
	ListView<BasicEvent> listViewIndividual;
	
	@FXML
	ListView<BasicCalendar> listViewCalendarsHome;
	
	@FXML
	VBox calendarNamesVBox;
	
	@FXML
	TextField newCalendarName;
    
    @FXML
    void addNewCalendar(ActionEvent event) throws IOException {
    	calendars.add(new BasicCalendar(newCalendarName.getText()));
    	listViewCalendarsHome.setItems(calendars);
    	newCalendarName.setText(null);
    }
    
    public void switchHomeScreen(ActionEvent event) throws IOException {
    	System.out.println(calendars);
	    Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		applicationStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		applicationStage.setScene(scene);
		applicationStage.show();
		System.out.println(calendars);
		// code from https://www.youtube.com/watch?v=hcM-R-YOKkQ&ab_channel=BroCode
    }
    
    @FXML
    void switchEditCalendarView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("EditCalendarView.fxml"));
    	rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    void switchIndividualCalendarView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("IndividualCalendarView.fxml"));
    	rootPane.getChildren().setAll(pane);
    	
    	 // datePicker.setValue(LocalDate.now()); // from https://www.youtube.com/watch?v=9uubyM6oHAY&ab_channel=today%27sIT
    }
    
    @FXML
    void switchComparisonCalendarView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("ComparisonCalendarView.fxml"));
    	rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    void switchCombinedCalendarView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("CombinedCalendarView.fxml"));
    	rootPane.getChildren().setAll(pane);
    }
    
    /**
     * code from from https://www.youtube.com/watch?v=9uubyM6oHAY&ab_channel=today%27sIT
     */
    private void refresh() {
    	datePickerIndividualView.setValue(LocalDate.now());
    	descriptionTextFieldIndividual.setText(null);
    }
    
    
    @FXML
    void addEvent(ActionEvent event) {
    	oneCalendar.addEvents(new BasicEvent(datePickerIndividualView.getValue(), descriptionTextFieldIndividual.getText()));
    	listViewIndividual.setItems(oneCalendar.getEvents());
    	refresh(); // code from code from from https://www.youtube.com/watch?v=9uubyM6oHAY&ab_channel=today%27sIT
    }
    
    public Stage getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage(Stage applicationStage) {
		this.applicationStage = applicationStage;
	}
}

