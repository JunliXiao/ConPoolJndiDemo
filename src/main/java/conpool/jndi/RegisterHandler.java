package conpool.jndi;

import java.io.IOException;
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
		System.out.println("Http request received");
		System.out.println("Request parameter names: ");
		// 取得所有請求參數名稱
		Enumeration<String> paramterNames = request.getParameterNames();
		while (paramterNames.hasMoreElements()) {
			String name = paramterNames.nextElement().toString();
			String value = request.getParameter(name);
			System.out.println(name + ": " + value);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
