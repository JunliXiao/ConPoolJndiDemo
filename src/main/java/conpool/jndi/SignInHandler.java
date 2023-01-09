package conpool.jndi;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/SignInHandler")
public class SignInHandler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Dao<Book> dao = new BookDao();
		List<Book> books = dao.getAll();
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(books));
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
