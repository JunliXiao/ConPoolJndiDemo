package dao;

import java.sql.Connection;
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
		// String sql = ...
		try(Connection connection = dataSource.getConnection();) {
			System.out.println("Successfully connected to database!");
			
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
