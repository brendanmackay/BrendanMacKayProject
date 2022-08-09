package application;

import java.time.LocalDate;

public class Work extends BasicEvent {

	// Instance variables for Work class
	private String shiftStart;
	private String shiftEnd;
	
	/**
	 * one constructor which calls the parent constructor from BasicEvents
	 * and creates a new instance of the Work Class.
	 * @param description the description of the work
	 * @param date the date of the work
	 * @param shiftStart the start of the shift
	 * @param shiftEnd the end of the shift
	 */
	public Work(String description, LocalDate date, String shiftStart, String shiftEnd) {
		super(description, date);
		this.setShiftEnd(shiftEnd);
		this.setShiftStart(shiftStart);
	}


	/**
	 * Getter for the shift end instance variable
	 * @return shift end instance variable
	 */
	public String getShiftEnd() {
		return shiftEnd;
	}

	
	/**
	 * Setter for the shift end instance variable
	 * @param shiftEnd the end of the shift
	 */
	public void setShiftEnd(String shiftEnd) {
		this.shiftEnd = shiftEnd;
	}

	
	/**
	 * Getter for the shift end instance variable
	 * @return the shift end instance variable
	 */
	public String getShiftStart() {
		return shiftStart;
	}

	
	/**
	 * Setter for the shift start instance variable
	 * @param shiftStart the start of the shift
	 */
	public void setShiftStart(String shiftStart) {
		this.shiftStart = shiftStart;
	}
	
	/**
	 * The method which returns a string for the Work Class by calling the 
	 * toString of its parent Basic Events. This toString is displayed in 
	 * the GUI in listview.
	 */
	public String toString() {
		return "Work: " + super.toString() + ". Shift Start: " + shiftStart + ". Shift End: " + shiftEnd + ".";
	}
	

}
