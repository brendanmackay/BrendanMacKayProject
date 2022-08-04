package application;

import java.time.LocalDate;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CalendarList {
	private ObservableList<BasicCalendar> calendars = FXCollections.observableArrayList();
	
	
	CalendarList (String filePath) {
		FileIO fileIO = new FileIO();
		toCalendars(fileIO.readFile(filePath));
	}
	
	
	CalendarList ( ObservableList<BasicCalendar> calendars) {
		this.calendars = calendars;
	}
    
	
	public String toText() {
		String text = "";
		for (BasicCalendar c : calendars) {
			text += c.getName() ;
			for (BasicEvent e : c.getEvents()) {
				text += "/" + e.getDescription() + ";" + e.getDate();
			}
			text += "`";
		}
		text = text.substring(0, text.length()-1);
		return text;	
	}
	
	public void toCalendars(String text) { // take inputted string from text file and populate calendars
		String[] calendarList = text.split("`");
		int counter = 0;
		for (String calendar : calendarList) {
			String[] eventList = calendar.split("/"); // split into events with first index being the calendar name
			calendars.add(new BasicCalendar(eventList[0])); // add name to a new calendar 
			eventList = Arrays.copyOfRange(eventList, 1, eventList.length); // delete name of calendar added
			for (String event : eventList) {
				String[] eventProperties = event.split(";");
				if (eventProperties.length == 1) { // events need a name so if there is only 1 event property it is a name
					calendars.get(counter).addEvents(new BasicEvent(eventProperties[0]));
				}
				else if (eventProperties[1].equals("null")) { // if the data is null do not add to events
					calendars.get(counter).addEvents(new BasicEvent(eventProperties[0]));
				}
				else { // otherwise add the date when constructing the new event
					calendars.get(counter).addEvents(new BasicEvent(eventProperties[0], LocalDate.parse(eventProperties[1])));
				} 
			}
			counter ++;
		}
	}

	public void saveDataBase() {
		FileIO fileIO = new FileIO();
		CalendarList calendarList = new CalendarList(calendars);
		String data = calendarList.toText();
		fileIO.writeFile(data, "src\\application\\database.txt");
	}

	public void bannedCharacters() {
		
	}
	public ObservableList<BasicCalendar> getCalendars() {
		return calendars;
	}

	public void setCalendars(ObservableList<BasicCalendar> calendars) {
		this.calendars = calendars;
	}
	
}
