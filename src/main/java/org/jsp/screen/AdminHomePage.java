package org.jsp.screen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminHomepage")
public class AdminHomePage extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out= resp.getWriter();
		out.println("<html>");
		out.println("<head><link rel='stylesheet' href='AdminHP.css'>");
		out.println("</head>");
		
		out.println("<body>");
		
		out.println("<main align='center'>");
		out.println("<h1>Hello Admin.</h1>");
		out.println("<a href='addBookPage'><button>Add Book</button></a>");
		out.println("<a href='removeBookPage'><button>Remove Book.</button></a>");
		out.println("<a href='DisplayBookmarks.html'><button>Display Bookmarks</button></a>");
		out.println("<a href='displayAllBooks_Admin'><button>Display all books</button></a>");
		out.println("<a href='logout'><button>Logout</button></a>");

		out.println("</main>");
		
		
		
		
		
		out.println("</body>");
		out.println("</html>");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
