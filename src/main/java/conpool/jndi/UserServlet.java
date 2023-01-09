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
import dao.UserDao;
import model.Book;
import model.User;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson _gson = new Gson();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Dao<User> dao = new UserDao();
		List<User> users = dao.getAll();

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(_gson.toJson(users));
		System.out.println("username: " + request.getParameter("username"));
		System.out.println("password: " + request.getParameter("password"));

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}