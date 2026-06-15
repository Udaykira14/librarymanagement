package org.jsp.screen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addBookPage")
public class AddBookPage extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out= resp.getWriter();
		out.println("<html>");
		out.println("<head><link rel='stylesheet' href=''>");
		
		out.println("<title>Add Book</title>");
		out.println("</head>");
		
		out.println("<body>");
		
		out.println("<main ><h1>Add Book Page</h1>");
		out.println("<form action='addBook' method='post'><fieldset>");
		out.println("<legend>Details</legend>");
		out.println("<input type=\"number\" name=\"id\" placeholder=\"enter book id\" min=\"1\" max=\"99999\" value=\"555\"/>    <br>");
		out.println("<input type=\"text\" name=\"name\" placeholder=\"enter book name\" required value=\"sherlock holmes\"/><br />");
		out.println("<input type=\"text\" name=\"author\" placeholder=\"enter author name\" required value=\"lock\"/><br />");
		out.println("<input type=\"number\" name=\"cost\" placeholder=\"enter cost of book\" required value=\"689\"/><br />");
		out.println("<button>Add Book</button>");
		out.println("</fieldset></form></main>");
		
		
		
		out.println("<a href='adminHomepage'><button>Home</button></a>");

		
		out.println("</body>");
		out.println("</html>");
			
			
			
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
