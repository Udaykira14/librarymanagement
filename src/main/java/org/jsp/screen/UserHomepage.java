package org.jsp.screen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/userHomepage")
public class UserHomepage extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out= resp.getWriter();
		HttpSession session=req.getSession();
		
		String name = (String)session.getAttribute("name");
		int userId= (Integer)session.getAttribute("id");
		
		out.println("<html>");
		out.println("<body style='background-color: aqua;'>");
		
		
		out.println("<div class='container'>");

		out.println("<h1>Welcome, <i>" + name + "</i><br>User ID : " + userId + "</h1>");

		out.println("<a href='profile'><button class='btn'>Profile</button></a>");

		out.println("<a href='displayAllBooks'><button class='btn'>Display All Books</button></a>");

		out.println("<a href='displayBorrowedBooks'><button class='btn'>Display Borrowed Books</button></a>");
		
		out.println("<a href='requestBookPage'><button>Request Book</button></a>");

		out.println("<a href='logout'><button class='btn logout'>Logout</button></a>");

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
}
