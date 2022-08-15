package application;

import java.time.LocalDate;

public class School extends BasicEvent{
	
	// Instance variables for school class
	private String lectureLocation;
	private String lectureTime;

	

	/**
	 * one constructor which calls the parent constructor from BasicEvents
	 * and creates a new instance of the School Class.
	 * @param description the description of the school event
	 * @param date the date of the school event
	 * @param lectureLocation the location of the lecture
	 * @param lectureTime the time of the lecture
	 */
	public School(String description, LocalDate date, String lectureLocation, String lectureTime) {
		super(description, date);
		this.lectureLocation = lectureLocation;
		this.lectureTime = lectureTime;
		
	}

	/** Getter for the lecture location instance variable
	 * @return returns the lectureLocation instance variable
	 */
	public String getLectureLocation() {
		return lectureLocation;
	}

	/** Setter for the lecture location instance variable
	 * @param lectureLocation sets the lectureLocation instance variable
	 */
	public void setLectureLocation(String lectureLocation) {
		this.lectureLocation = lectureLocation;
	}

	/** Getter for the lecture time instance variable
	 * @return returns the lectureTime instance variable
	 */
	public String getLectureTime() {
		return lectureTime;
	}

	/** Setter for the lecture time instance variable
	 * @param lectureTime sets the lectureTime instance variable
	 */
	public void setLectureTime(String lectureTime) {
		this.lectureTime = lectureTime;
	}
}
