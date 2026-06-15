package org.jsp.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestBook")

public class RequestBook extends HttpServlet {
	Connection connection;
	PreparedStatement preparedStatement;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();

		int userId = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String author = req.getParameter("author");
		out.println("<html>");
		out.println("<body>");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "#UkiDB345");

			preparedStatement = connection.prepareStatement("insert into request_book values (?,?,?)");

			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, author);

			int result = preparedStatement.executeUpdate();
			if (result == 1) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("requestBookPage");
				out.println("<h1>Book Request sent to Admin</h1>");
				dispatcher.include(req, resp);
			} else {
				out.println("<h1>Failed to send Book Request to Admin</h1>");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		out.println("</body>");
		out.println("</html>");
	}
}
