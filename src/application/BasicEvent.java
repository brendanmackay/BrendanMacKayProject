package application;

import java.time.LocalDate;

public class BasicEvent {

	
	// instance variables for the class
	private String description;
	private LocalDate date;
	
	
	/** The constructor for a basic event when only a description is given.
	 * The value is assigned to the description instance variable.
	 * @param description the description of the event.
	 */
	public BasicEvent (String description) {
		setDescription(description);
	}
	
	
	/** The constructor for a basic event when a description and date are given.
	 * The values are assigned to the two instance variable. If a date is given 
	 * but its value is null, then no date is assigned.
	 * @param description the description of the new BasicEvent.
	 * @param date the date of the new BasicEvent.
	 */
	public BasicEvent(String description, LocalDate date) {
		if (date != null) setDate(date);
		setDescription(description);
	}
	

	/** Gets the date instance variable from BasicEvent
	 * @return the date instance variable of type LocalDate
	 */
	public LocalDate getDate() {
		return date;
	}
	
	
	/** Sets the date instance variable for BasicEvent
	 * @param date the date instance variable of type LocalDate
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	/** Get the description instance variable in BasicEvent
	 * @return the description instance variable
	 */
	public String getDescription() {
		return description;
	}
	
	
	/** Set the description instance variable in BasicEvent
	 * @param description instance variable
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/** Returns a string of the basic event where it'll return
	 * only the description if the date is null and otherwise
	 * it will return the description and date.
	 */
	public String toString() {
		String string;
		if (date == null) {
			string = description;
		}
		else {
			string = description + " on " + date;
		}
		return string;
	}
}
