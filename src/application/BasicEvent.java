package application;

import java.time.LocalDate;

public class BasicEvent {

	private String description;
	private LocalDate date;
	
	public BasicEvent (String description) {
		setDescription(description);
	}
	
	public BasicEvent(String description, LocalDate date) {
		if (date != null) setDate(date);
		setDescription(description);
	}
	


	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
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
