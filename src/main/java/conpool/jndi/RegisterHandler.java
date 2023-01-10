package conpool.jndi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 取得所有請求參數名稱
		Enumeration<String> paramterNames = request.getParameterNames();
		
		out.println("收到請求參數...");
		out.println("Request parameter names: ");
		while (paramterNames.hasMoreElements()) {
			String name = paramterNames.nextElement().toString();
			String value = request.getParameter(name);
			out.println(name + ": " + value);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
