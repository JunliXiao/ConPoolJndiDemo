package conpool.jndi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dao.UserDao;
import model.User;

@WebServlet("/register")
public class RegisterHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Dao<User> dao = new UserDao();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 取得所有請求參數名稱
//		Enumeration<String> paramterNames = request.getParameterNames();
		
		out.println("收到註冊資料...");
		out.println("Request parameter names: ");
//		while (paramterNames.hasMoreElements()) {
//			String name = paramterNames.nextElement().toString();
//			String value = request.getParameter(name);
//			out.println(name + ": " + value);
//		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String email = request.getParameter("email");
		String job = request.getParameter("job");
		String area = request.getParameter("area");
		out.println("username: " + username);
		out.println("password: " + password);
		out.println("lastName: " + lastName);
		out.println("firstName: " + firstName);
		out.println("email: " + email);
		out.println("job: " + job);
		out.println("area: " + area);
		out.println();
		User userToRegister = new User(username, password, email, firstName, lastName, area, job);
		out.println(dao.add(userToRegister) ? "Successfully registered" : "Failed to register");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
