package org.jsp.screen;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/removeBookPage")
public class RemoveBookPage extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out= resp.getWriter();
		out.println("<html>");
		out.println("<head><link rel='stylesheet' href=''>");
		
		out.println("<title>Remove Book Page</title>");
		out.println("</head>");
		
		out.println("<body>");
		
		out.println("<main ><h1>Remove Book.</h1>");
		out.println("<form action='removeBook' method='post'><fieldset>");
		out.println("<legend>Remove a Book</legend>");
		out.println("<input type=\"number\" name=\"id\" placeholder=\"enter book id\" min=\"1\" max=\"99999\" value=\"555\" required><br>");
		out.println("<button>Remove</button>");
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
