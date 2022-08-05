package application;

import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class BasicCalendar {
	
	// instance variables for the Basic Calendar Class
	private ObservableList<BasicEvent> events = FXCollections.observableArrayList();
	private String name;
	
	
	/** The trivial/default constructor which assigns no instance variables
	 */
	public BasicCalendar() {
	}
	
	
	/** Contructor which assigns a String to the name
	 * @param name the name assigned to the BasicCalendar
	 */
	public BasicCalendar(String name){
		this.name = name;
	}

	
	/**Overides the toString method returning the calendar name
	 */
	public String toString() {
		return name; 
	}
	
	
	/** Adds the BasicEvent assigned as a parameter to the list of events instance variable
	 * @param event the BasicEvent to be added to the list of events
	 */
	public void addEvent(BasicEvent event) {
		events.add(event);
	}
	
	
	/** Returns the ObservableList of BasicEvents instance variable
	 * @return	the ObservableList<BasicEvents> instance variable
	 */
	public ObservableList<BasicEvent> getEvents() {
		return events;
	}

	
	/** Returns the name instance variable which is of type String
	 * @return  The name instance variable of type String
	 */
	public String getName() {
		return name;
	}
	
	
	/**Assigns the instance variable name the parameter value
	 * @param name assigns the parameter String to the instance Variable name
	 */
	
	
	/*
	public void setName(String name) {
		this.name = name;
	}
	*/
}
