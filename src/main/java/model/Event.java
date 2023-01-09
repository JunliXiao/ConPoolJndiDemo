package model;

public class Event {
	private int eventId;
	private String eventDate;
	private String interval;
	private String eventName;
	private String eventAbbreviation;
	private String teachers;
	private int hours ;
	private String classroom;
	
	public Event(int eventID, String eventDate, String interval, String eventName, String eventAbbreviation,
			String teachers, int hours, String classroom) {
		this.eventId = eventID;
		this.eventDate = eventDate;
		this.interval = interval;
		this.eventName = eventName;
		this.eventAbbreviation = eventAbbreviation;
		this.teachers = teachers;
		this.hours = hours;
		this.classroom = classroom;
	}
	
	@Override
	public String toString() {
		String template = "課程 ID: %d, 課程日期: %s, 時段: %s, 課程名稱: %s, 課程別稱: %s, 授課教師: %s, 時數: %d, 教室: %s";
		return String.format(template, eventId, eventDate, interval, eventName, eventAbbreviation, teachers, hours, classroom);
	}

	public int getEventID() {
		return eventId;
	}

	public String getEventDate() {
		return eventDate;
	}

	public String getInterval() {
		return interval;
	}

	public String getEventName() {
		return eventName;
	}

	public String getEventAbbreviation() {
		return eventAbbreviation;
	}

	public String getTeachers() {
		return teachers;
	}

	public int getHours() {
		return hours;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setEventID(int eventID) {
		this.eventId = eventID;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void setEventAbbreviation(String eventAbbreviation) {
		this.eventAbbreviation = eventAbbreviation;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

}