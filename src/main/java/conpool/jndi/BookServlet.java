package conpool.jndi;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.BookDao;
import dao.Dao;
import model.Book;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson _gson = new Gson();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao<Book> dao = new BookDao();

		// 取得所有資料
		List<Book> books = dao.getAll();
//		System.out.println("-----total " + books.size() + " book(s)-----");
//		for (Book book : books) {
//			System.out.println(book.toString());
//			System.out.println();
//		}
		System.out.println("--------------------------------------------");

		// 取得指定ID資料
//		Book book = dao.get("9780596009205");
//		System.out.println("Book Info:\n" + book);
//		System.out.println("--------------------------------------------");

		// 新增一筆資料
//		Book bookInsert = new Book("9781484226766", "Python Unit Test Automation", 731, "Ashwin Pajankar",
//				LocalDate.of(2017, Month.JUNE, 12), "P001");
//		System.out.println(dao.add(bookInsert) ? "Insert Success" : "Insert Failed");
//		System.out.println("--------------------------------------------");

		// 修改一筆資料
//		Book bookUpdate = new Book("9780596009205", "Head First Java", 1000, "Kathy Sierra",
//				LocalDate.of(2005, Month.FEBRUARY, 19), "P001");
//		System.out.println(dao.update(bookUpdate) ? "Update Success" : "Update Failed");
//		System.out.println("--------------------------------------------");

		// 刪除指定ID資料
//		System.out.println(dao.delete("9781484226766") ? "Delete Success" : "Delete Failed");
//		System.out.println("--------------------------------------------");
//		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.print(_gson.toJson(books));
//		out.print("<HTML>");
//		out.print("<HEAD><TITLE>Hello JNDI</TITLE></HEAD>");
//		out.print("<BODY>");
//		out.print("<H1>Hello JNDI!</H1>");
//		out.print("<p>ConPoolJndiDemo專案已經啟動</p></hr<");
//		out.println("<p>-----total " + books.size() + " book(s)-----</p>");
//		out.print("<ul>");
//		for (Book book : books) {
//			out.print("<li>" + book.toString() + "</li>");
//		}
//		out.println("</ul></BODY></HTML>");

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
