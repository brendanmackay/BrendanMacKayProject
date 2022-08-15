package application;

import java.time.LocalDate;

public class School extends BasicEvent{
	
	// Instance variables for school class
	private String location;
	private String time;


	/**
	 * one constructor which calls the parent constructor from BasicEvents
	 * and creates a new instance of the School Class.
	 * @param description the description of the school event
	 * @param date the date of the school event
	 * @param lectureLocation the location of the lecture
	 * @param lectureTime the time of the lecture
	 */
	public School(String description, LocalDate date, String location, String time) {
		super(description, date);
		this.location = location;
		this.time = time;
		
	}

	
	/**
	 * The method which returns a string for the school Class by calling the 
	 * toString of its parent Basic Events. This toString is displayed in 
	 * the GUI in listview.
	 */
	public String toString() {
		return "School: " + super.toString() + " at " + time + ". Location: " + location + 
				".";
	}
	
	/** Getter for the lecture location instance variable
	 * @return returns the lectureLocation instance variable
	 */
	public String getLocation() {
		return location;
	}

	/** Setter for the lecture location instance variable
	 * @param lectureLocation sets the lectureLocation instance variable
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/** Getter for the lecture time instance variable
	 * @return returns the lectureTime instance variable
	 */
	public String getTime() {
		return time;
	}

	/** Setter for the lecture time instance variable
	 * @param lectureTime sets the lectureTime instance variable
	 */
	public void setTime(String time) {
		this.time = time;
	}
}
