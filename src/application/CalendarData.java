package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CalendarData {
	private final ObservableList<BasicCalendar> calendars = FXCollections.observableArrayList();
    
	public ObservableList<BasicCalendar> getCalendarList() {
        return calendars ;
    }
	
	
}
