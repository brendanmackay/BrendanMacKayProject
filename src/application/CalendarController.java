package application;



import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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
import javafx.stage.Stage;


public class CalendarController {
	private Stage applicationStage;
	private Scene scene;
	private ArrayList<BasicCalendar> calendars = new ArrayList<BasicCalendar>();
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
	
	

    public void switchHomeScreen(ActionEvent event) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		applicationStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		applicationStage.setScene(scene);
		applicationStage.show();
		// code from https://www.youtube.com/watch?v=hcM-R-YOKkQ&ab_channel=BroCode
    }
    
    public void addNewCalendarInfo(ActionEvent event) throws IOException {
    	calendars.add(new BasicCalendar(calendarNameTextField.getText()));
    	switchHomeScreen(event);
    }
     
    
    @FXML
    void getNewCalendarInfo(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("CreateCalendar.fxml"));
    	rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    void editCalendarInfo(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("EditCalendar.fxml"));
    	rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    void individualCalendarView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("IndividualCalendarView.fxml"));
    	rootPane.getChildren().setAll(pane);
    	 // datePicker.setValue(LocalDate.now()); // from https://www.youtube.com/watch?v=9uubyM6oHAY&ab_channel=today%27sIT
    }
    
    @FXML
    void comparisonCalendarView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("ComparisonCalendarView.fxml"));
    	rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    void combinedCalendarView(ActionEvent event) throws IOException {
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

