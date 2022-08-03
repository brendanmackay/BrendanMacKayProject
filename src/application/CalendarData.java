package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CalendarData {
	private final ObservableList<BasicCalendar> calendars = FXCollections.observableArrayList();
    
	public ObservableList<String> getCalendarNames() {
		ObservableList<String> nameList =  FXCollections.observableArrayList();
		for (BasicCalendar c : calendars) {
			nameList.add(c.getName());
			
		}
		return nameList;
	}
	
	
	
	public ObservableList<BasicCalendar> getCalendarList() {
        return calendars ;
    }
}
