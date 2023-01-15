package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.User;

public class UserDao implements Dao<User> {
	DataSource dataSource;

	public UserDao() {
		dataSource = ServiceLocator.getInstance().getDataSource("practice_1");
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		String sql = "select user_id, username, password, email, first_name, last_name, area, job, created_time from user";
		try(Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			System.out.println("Successfully connected to database!");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				String firstName = rs.getString(5);
				String lastName = rs.getString(6);
				String area = rs.getString(7);
				String job = rs.getString(8);
				String createdAt = rs.getString(9);
				User user = new User(userId, username, password, email, firstName, lastName, area, job, createdAt);
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public User get(String username) {
		User user = null;
		String sql = "select user_id, username, password, email, first_name, last_name, area, job, created_time from user where username = ?";
		try(Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int userId = rs.getInt(1);
				// String username = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				String firstName = rs.getString(5);
				String lastName = rs.getString(6);
				String area = rs.getString(7);
				String job = rs.getString(8);
				String createdAt = rs.getString(9);
				user = new User(userId, username, password, email, firstName, lastName, area, job, createdAt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean add(User user) {
		int rowCount = 0;
		String sql = "insert into user(" 
				+ "username, password, email, first_name, last_name, area, job) "
				+ "values(?, ?, ?, ?, ?, ?, ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getFirst_name());
			ps.setString(5, user.getLast_name());
			ps.setString(6, user.getArea());
			ps.setString(7, user.getJob());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
