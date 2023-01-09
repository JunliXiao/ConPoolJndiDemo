package conpool.jndi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dao.EventDao;
import model.Event;

@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		Dao<Event> dao = new EventDao();
		
		List<Event> events = dao.getAll();
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter(); 
		
	    out.print("<HTML>");
	    out.print("<HEAD><TITLE>Hello JNDI</TITLE></HEAD>");
	    out.print("<BODY>");
	    out.print("<H1>Hello JNDI!</H1>");
	    out.print("<p>ConPoolJndiDemo專案已經啟動</p></hr<");
	    out.println("<p>-----total " + events.size() + " event(s)-----</p>");
	    out.print("<ul>");
	    for (Event event : events) {
			out.print("<li>" + event.toString() + "</li>");
		}
	    out.println("</ul></BODY></HTML>");		

	}

}
