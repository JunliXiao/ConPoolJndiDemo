package conpool.jndi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormHandler")
public class FormHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Request header names: ");
		// 取得所有請求標頭名稱
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement().toString();
			// HttpServletRequest只有定義getHeader()，HttpServletResponse才有定義setHeader()
			String value = request.getHeader(name);
			System.out.println(name + ": " + value);
		}
		System.out.println();

		System.out.println("Request parameter values: ");
		// 指定請求內容的編碼方式，必須在讀取請求參數之前呼叫
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		System.out.println("URI: " + uri);
		String url = request.getRequestURL().toString();
		System.out.println("URL: " + url);
		String name = request.getParameter("name");
		System.out.println("name: " + name);
		String password = request.getParameter("password");
		System.out.println("password: " + password);
		String gender = request.getParameter("gender");
		System.out.println("gender: " + gender);
		String[] places = request.getParameterValues("place");
		System.out.print("places: ");
		Arrays.stream(places).forEach(place -> System.out.print(place + " "));
		System.out.println();
		String job = request.getParameter("job");
		System.out.println("job: " + job);
		String comment = request.getParameter("comment");
		System.out.println("comment: " + comment);
		System.out.println();
		
		System.out.println("Request parameter names: ");
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String param = paramNames.nextElement();
			System.out.println(param);
		}
		
		// 使用Chrome開發人員工具查看response headers可看到額外加入的header
		response.addHeader("test", "testHeader");
		// 設定輸出內容的編碼方式，必須在呼叫getWriter()前呼叫
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("FormHandle finished!");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
