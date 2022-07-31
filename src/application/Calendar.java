package application;

import java.util.ArrayList;


public class Calendar {
	private int nums;
	private ArrayList<Events> events;

	public ArrayList<Events> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Events> events) {
		this.events = events;
	}
}
