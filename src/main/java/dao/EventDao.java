package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Event;

public class EventDao implements Dao<Event>{
	DataSource dataSource;
	
	public EventDao() {
		dataSource = ServiceLocator.getInstance().getDataSource("TGA105");
	}
	
	@Override
	public List<Event> getAll() {
		List<Event> events = new ArrayList<>();
		String sql = "SELECT * FROM EVENT";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int eventId = rs.getInt(1);
				String eventDate = rs.getString(2);
				String interval = rs.getString(3);
				String eventName = rs.getString(4);
				String eventAbbreviation = rs.getString(5);
				String teachers = rs.getString(6);
				int hours = rs.getInt(7);
				String classroom = rs.getString(8);
				Event event = new Event(eventId, eventDate, interval, eventName, eventAbbreviation, teachers, hours, classroom);
				events.add(event);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return events;
	}

	@Override
	public Event get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Event obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Event obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}