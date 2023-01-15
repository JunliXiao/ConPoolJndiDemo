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

@WebServlet("/login")
public class LogInHandler extends HttpServlet {
	
//	private static final long serialVersionUID = 1L;
//	Gson gson = new Gson();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Dao<User> dao = new UserDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = dao.get(username);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
//		out.print(gson.toJson(user));
		out.println("收到登入資料...");
		
		if(user != null) {
			if(user.getPassword().compareTo(password) == 0) {
				out.println("Succcessfully logged in");
			} else {
				out.println("Failed to logged in");
			}
			out.println(user.getPassword());
  			out.println(password);
				
		} else {
			out.println("Wrong username; no such user exists.");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
