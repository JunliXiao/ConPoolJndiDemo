package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Book;

public class BookDao implements Dao<Book> {
	DataSource dataSource;

	public BookDao() {
		dataSource = ServiceLocator.getInstance().getDataSource("jdbc/bookshop_jdbc");
	}

	@Override
	public List<Book> getAll() {
		List<Book> books = new ArrayList<>();
		String sql = "select isbn, book_name, price, author, publication_date, publisher_id from book;";
		try (Connection connection = dataSource.getConnection(); // Without dataSource, DriverManager is used
				PreparedStatement ps = connection.prepareStatement(sql)) {
			/*
			 * 當Statement關閉，ResultSet也會自動關閉，可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String isbn = rs.getString(1);
				String bookName = rs.getString(2);
				double price = rs.getDouble(3);
				String author = rs.getString(4);
				String publicationDate = rs.getString(5);
				String publisherId = rs.getString(6);
				Book book = new Book(isbn, bookName, price, author, publicationDate, publisherId, null);
				books.add(book);
			}
			return books;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public Book get(String id) {
		Book book = null;
		String sql = "select isbn, book_name, price, author, publication_date, publisher_id "
				+ "from book where isbn = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String isbn = rs.getString(1);
				String bookName = rs.getString(2);
				double price = rs.getDouble(3);
				String author = rs.getString(4);
				String publicationDate = rs.getString(5);
				String publisherId = rs.getString(6);
				book = new Book(isbn, bookName, price, author, publicationDate, publisherId, null);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return book;
	}

	@Override
	public boolean add(Book book) {
		int rowCount = 0;
		String sql = "insert into book(" 
				+ "isbn, book_name, price, author, publication_date, publisher_id) "
				+ "values(?, ?, ?, ?, ?, ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, book.getIsbn());
			ps.setString(2, book.getBookName());
			ps.setDouble(3, book.getPrice());
			ps.setString(4, book.getAuthor());
			ps.setObject(5, book.getPublicationDate());
			ps.setString(6, book.getPublisherId());
			rowCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean update(Book book) {
		int rowCount = 0;
		String sql = "update book "
				+ "set book_name = ?, price = ?, author = ?, publication_date = ?, publisher_id = ? "
				+ "where isbn = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, book.getBookName());
			ps.setDouble(2, book.getPrice());
			ps.setString(3, book.getAuthor());
			ps.setObject(4, book.getPublicationDate());
			ps.setString(5, book.getPublisherId());
			ps.setString(6, book.getIsbn());
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}

	@Override
	public boolean delete(String id) {
		int rowCount = 0;
		String sql = "delete from book where isbn = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, id);
			rowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}
}
