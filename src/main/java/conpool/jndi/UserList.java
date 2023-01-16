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
import dao.UserDaoImpl;
import model.Book;
import model.User;

@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson _gson = new Gson();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		Dao<User> dao = new UserDaoImpl();
		List<User> users = dao.getAll();
		
		if (request.getParameter("id") == null) {
			out.print(_gson.toJson(users));
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			for (User user : users) {
				if(user.getUser_id() == id) {
					out.print(_gson.toJson(user));
				}
			}
		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
