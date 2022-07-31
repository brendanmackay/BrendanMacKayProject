package application;

import java.time.LocalDate;

public class BasicEvent {

	private String description;
	private LocalDate date;
	
	public BasicEvent(LocalDate date, String description) {
		setDate(date);
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
		return "On " + this.date + " do " + description;
	}
}
