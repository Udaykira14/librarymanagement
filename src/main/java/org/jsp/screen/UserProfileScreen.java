package org.jsp.screen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/profile")
public class UserProfileScreen extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int id = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String email = (String) session.getAttribute("email");
		String password = (String) session.getAttribute("password");
		long number = (Long) session.getAttribute("number");

		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><link rel='stylesheet' href='profile.css'></head>");
		out.println("<body>");

		out.println("<div class='profile-container'>");

		out.println("<form action='update' method='post'>");
		out.println("<input type='hidden' name='id' value='" + id + "'>");
		out.println("<br>");
		out.println("<input type='text' name='name' value='" + name + "' required>");
		out.println("<br>");
		out.println("<input type='email' name='email' value='" + email + "' required>");
		out.println("<br>");
		out.println("<input type='password' name='password' value='" + password + "' required>");
		out.println("<br>");
		out.println("<input type='tel' name='number' value='" + number + "' required>");
		out.println("<br>");
		out.println("<button>Update</button>");

		out.println("</form>");
		out.println("<div class='actions'>");
		out.println("<a href='delete'><button class='delete-btn'>Delete user</button></a>");
		out.println("<a href='userHomepage'><button class='home-btn'>Home.</button></a>");
		out.println("</div>");

		out.println("</div>");

		out.println("</body></html>");
	}
}
