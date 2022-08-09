package application;

import java.time.LocalDate;

public class Flight extends BasicEvent{
	
	// instance variables for Flight class
	private String departureAirport;
	private String arrivalAirport;
	
	/**
	 * One constructor which calls the parents constructor for basic events
	 * and also assigns values for depature airport and arrival airport.
	 * @param description the description of the flight
	 * @param date the date of the flight
	 * @param departureAirport the departure airport
	 * @param arrivalAirport the arrival airport
	 */
	public Flight(String description, LocalDate date, String departureAirport, String arrivalAirport) {
		super(description, date);
		this.departureAirport = departureAirport;
		this.arrivalAirport =  arrivalAirport;
	}

	
	/**
	 * The method which returns a string for the flight Class by calling the 
	 * toString of its parent Basic Events. This toString is displayed in 
	 * the GUI in listview.
	 */
	public String toString() {
		return "Flight: " + super.toString() + ". Departure Airport: " + departureAirport + 
				". Arrival Airport: " + arrivalAirport + ".";
	}

	/**
	 * Getter method for the departureAirport instance variable.
	 * @return the departure airport.
	 */
	public String getDepartureAirport() {
		return departureAirport;
	}

	/**
	 * Setter method for the depatureAirport instance variable.
	 * @param departureAirport the departure airport
	 */
	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	/**
	 * Getter method for the arrival airport instance variable.
	 * @return the arrival airport
	 */
	public String getArrivalAirport() {
		return arrivalAirport;
	}

	/** Setter method for the arrival airport instance variable
	 * @param arrivalAirport the arrival airport
	 */
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	
	
}
