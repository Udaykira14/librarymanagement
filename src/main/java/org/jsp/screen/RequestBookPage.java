package org.jsp.screen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/requestBookPage")
public class RequestBookPage extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int userId = (Integer) session.getAttribute("id");			


		PrintWriter out= resp.getWriter();
		out.println("<html>");
		out.println("<head><link rel='stylesheet' href='profile.css'><title>Request Book</title></head>");
		out.println("<body>");
		
		out.println("<main ><h1>Request Book Page.</h1>");
		out.println("<form action='requestBook' method='get'><fieldset>");
		out.println("<legend>Details</legend>");
		out.println("<input type=\"hidden\" name=\"id\" placeholder=\"enter user id\" value=\""+userId+"\"/>    <br>");
		out.println("<input type=\"text\" name=\"name\" placeholder=\"enter book name\" required /><br />");
		out.println("<input type=\"text\" name=\"author\" placeholder=\"enter author name\" required /><br />");
		out.println("<button>Request Book</button>");
		out.println("</fieldset></form></main>");
		
		
		out.println("<br>");
		out.println("<br>");
		
		out.println("<a href='userHomepage'><button>Home</button></a>");

		
		out.println("</body>");
		out.println("</html>");
			
			
			
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
