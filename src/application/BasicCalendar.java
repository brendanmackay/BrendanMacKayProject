package application;

import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class BasicCalendar {
	private ObservableList<BasicEvent> events = FXCollections.observableArrayList();
	private String name;
	
	public BasicCalendar(String name){
		setName(name);
	}

	public String toString() {
		return "Name: " + name; 
		
	}

	public void addEvents(BasicEvent event) {
		events.add(event);
	}

	public ObservableList<BasicEvent> getEvents() {
		return events;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
