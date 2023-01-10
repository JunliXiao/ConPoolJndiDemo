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
	public User get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
