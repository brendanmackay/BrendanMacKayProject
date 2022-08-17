package application;

import java.time.LocalDate;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CalendarList {
	
	// Instance variable that stores a list of calendars
	// This list is loaded and saved to the database 
	private ObservableList<BasicCalendar> calendars = FXCollections.observableArrayList();
	
	// Set the banned characters which are used in the database
	// The following are not banned *.[  ].* but used in the regex
	// From https://stackoverflow.com/questions/14392270/how-do-i-check-if-a-string-contains-a-list-of-characters
	public static final String bannedCharacters = ".*[`;/<!>#].*";
	
	
	/** This constructor loads the database into the CalendarList
	 *  taking as a parameter the filepath for the database text file.
	 * @param filePath database text file
	 */
	public CalendarList (String filePath) {
		FileIO fileIO = new FileIO(); 				// create object to use FileIO methods
		toCalendars(fileIO.readFile(filePath));		// read the database using the fileIO
	}												// toCalendars populates calendars
	
	
	/** This constructor loads a ObservableList of BasicCalendars
	 * 	into the calendars instance variable.
	 * @param calendars assigned to the instance variable
	 */
	public CalendarList ( ObservableList<BasicCalendar> calendars) {
		this.calendars = calendars;
	}
    
	
	/** Creates a string out of the calendars list which is stored in the database
	 *  with calendars, events, and so on, seperated by special characters which
	 *  are not allowed to be used in any events or calendar names/descriptions.
	 * @return	the string of calendars in its database form
	 */
	public String toText() {
		String text = ""; 
		
		for (BasicCalendar c : calendars) {
			text += c.getName() ;							// start with the calendar name
			for (BasicEvent e : c.getEvents()) {	
				text += "/" + e.getDescription() + ";" + e.getDate();
				if (e instanceof Flight) {					// If it's a flight add a flight
					text += "<" + ((Flight)e).getDepartureAirport() + ">" + ((Flight)e).getArrivalAirport();
				}
				if (e instanceof Work) {					// If its a instance of work add a work
					text += "!" + ((Work)e).getShiftStart() + ">" + ((Work)e).getShiftEnd();
				}
				if (e instanceof School) {					// If its a instance of school add a school
					text += "#" + ((School)e).getLocation() + ">" + ((School)e).getTime();
				}
				
				
					
			}								// separate events and descriptions with / and ;
			text += "`";					// put a ` before the next event
		}
		text = text.substring(0, text.length()-1);		// remove the last character `
		return text;
	}
	
	
	/** Takes the string created in the toText() method and populates a calendar
	 * 	with the calendar list stored in the string.
	 * @param string of calendars 
	 */
	public void toCalendars(String text) { 				// take inputted string from text file and populate calendars
		String[] calendarList = text.split("`");
		int counter = 0;
		for (String calendar : calendarList) {
			String[] eventList = calendar.split("/"); // split into events with first index being the calendar name
			calendars.add(new BasicCalendar(eventList[0])); 				// add name to a new calendar 
			eventList = Arrays.copyOfRange(eventList, 1, eventList.length); // delete name of calendar added
			for (String event : eventList) {
				String[] eventProperties = event.split(";");
				if (eventProperties.length == 1) { 		// if there is only 1 event property it is a name
					calendars.get(counter).addEvent(new BasicEvent(eventProperties[0]));
				}
				else if (eventProperties.length == 2) {			// If the length is 2 figure out the type
					if (eventProperties[1].contains("<")) {		// this is an event of type flight
						String[] dateAndFlights = eventProperties[1].split("<");
						String[] flights = dateAndFlights[1].split(">");
						calendars.get(counter).addEvent(new Flight(eventProperties[0], 
								LocalDate.parse(dateAndFlights[0]), flights[0], flights[1]));
					}
					else if (eventProperties[1].contains("!")) {	// this is a event of type work
						String[] dateAndShifts = eventProperties[1].split("!");
						String[] shifts = dateAndShifts[1].split(">");
						calendars.get(counter).addEvent(new Work(eventProperties[0], 
								LocalDate.parse(dateAndShifts[0]), shifts[0], shifts[1]));
					}
					else if (eventProperties[1].contains("#")) {
						String[] dateAndLocationTime = eventProperties[1].split("#");
						String[] locationAndTime = dateAndLocationTime[1].split(">");
						calendars.get(counter).addEvent(new School(eventProperties[0], 
								LocalDate.parse(dateAndLocationTime[0]), locationAndTime[0], locationAndTime[1]));
					}
					else if (eventProperties[1].equals("null")) { // if the date is null do not add date to event
						calendars.get(counter).addEvent(new BasicEvent(eventProperties[0]));
					}
					else { 								// otherwise add the date when constructing the new event
						calendars.get(counter).addEvent(new BasicEvent(eventProperties[0], LocalDate.parse(eventProperties[1])));
					}
				}
			}
			counter ++;
		}
	}

	
	/** Saves the calendarList to the database text file
	 */
	public void saveDataBase() {
		FileIO fileIO = new FileIO();									// use FileIO
		CalendarList calendarList = new CalendarList(calendars);		
		String data = calendarList.toText();							// use to textMethod to get string
		fileIO.writeFile(data, "src\\application\\database.txt");		// write the string to the database
	}

	
	public ObservableList<BasicCalendar> getCalendars() {
		return calendars;
	}


}
