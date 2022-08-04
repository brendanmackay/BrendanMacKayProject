package application;

import java.time.LocalDate;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CalendarList {
	private ObservableList<BasicCalendar> calendars = FXCollections.observableArrayList();
    
	CalendarList(ObservableList<BasicCalendar> calendars){
		this.setCalendars(calendars);
	}
	
	public String toText() {
		String text = "";
		for (BasicCalendar c : calendars) {
			text += c.getName() ;
			for (BasicEvent e : c.getEvents()) {
				text += "/" + e.getDescription() + ":" + e.getDate();
			}
			text += "+";
		}
		text = text.substring(0, text.length()-1);
		return text;	
	}
	
	public void toCalendars(String text) {
		String[] calendarList = text.split("\\+");
		int counter = 0;
		for (String calendar : calendarList) {
			String[] eventList = calendar.split("/"); // split into events with first index being the calendar name
			calendars.add(new BasicCalendar(eventList[0])); // add name to a new calendar 
			eventList = Arrays.copyOfRange(eventList, 1, eventList.length); // delete name of calendar added
			for (String event : eventList) {
				String[] eventProperties = event.split(":");
				if (eventProperties.length == 1) { // events need a name so if there is only 1 event property it is a name
					calendars.get(counter).addEvents(new BasicEvent(eventProperties[0]));
				}
				else if (eventProperties.length == 2) { // if the length is 2 then the second property is a date
					calendars.get(counter).addEvents(new BasicEvent(eventProperties[0], LocalDate.parse(eventProperties[1])));
				} 
			}
			counter ++;
		}
	}


	/* public ObservableList<BasicCalendar> getCalendarList() {
        return getCalendars() ;
    } */

	public ObservableList<BasicCalendar> getCalendars() {
		return calendars;
	}

	public void setCalendars(ObservableList<BasicCalendar> calendars) {
		this.calendars = calendars;
	}
	
	
	
	/*	public static void main(String[] args) {
	ObservableList<BasicCalendar> calendars = FXCollections.observableArrayList();
	

	BasicCalendar c1 = new BasicCalendar("SKI");
	BasicEvent e11 = new BasicEvent("Breck", LocalDate.now());
	BasicEvent e12 = new BasicEvent("Copper", LocalDate.now());
	c1.addEvents(e11);
	c1.addEvents(e12);
	BasicCalendar c2 = new BasicCalendar("Climb");
	BasicEvent e21 = new BasicEvent("Squamish", LocalDate.now());
	BasicEvent e22 = new BasicEvent("Canmore", LocalDate.now());
	BasicCalendar c3 = new BasicCalendar("Surf");
	c2.addEvents(e21);
	c2.addEvents(e22);
	calendars.add(c1);
	calendars.add(c2);
	calendars.add(c3);
	
	CalendarList calendarList = new CalendarList(calendars); 
	System.out.println(calendarList.toText()); 
	
	
	
	CalendarList calendarList = new CalendarList(calendars);
	calendarList.toCalendars("SKI/Breck:2022-08-03/Copper:2022-08-03+Climb/Squamish:2022-08-03/Canmore:2022-08-03+Surf/Australia");
	
}	*/
}
