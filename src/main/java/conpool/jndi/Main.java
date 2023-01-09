package conpool.jndi;

import java.util.List;

import dao.BookDao;
import dao.Dao;
import model.Book;

public class Main {

	public static void main(String[] args) {

		Dao<Book> dao = new BookDao();
		List<Book> books = dao.getAll();
		System.out.println(books);
	}

}
