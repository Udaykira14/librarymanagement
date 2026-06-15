package org.jsp.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/displayAllBooks_Admin")
public class DisplayAllBooksAdmin extends HttpServlet{
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		int userId=Integer.parseInt(req.getParameter("userId"));
//		String name = req.getParameter("name");

		PrintWriter out=resp.getWriter();
		
		out.println("<html>");
		out.println("<head><link rel='stylesheet' href='books.css'></head>");
		out.println("<body>");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","#UkiDB345");
			
			preparedStatement=connection.prepareStatement("select * from book");
			
			resultSet =preparedStatement.executeQuery();
//			out.println("<h1>Books Bank  id>><mark>"+userId+"</mark></h1>");
			out.println("<h2>Books in Library</h2>");
			if (resultSet.next()) {
				out.println("<table border='2px'>");
				out.println("<thead><th>id</th>  <th>Title</th>  <th>Author</th>  <th>Cost</th>  <th>Availability</th>  </thead>");
				do {
					int bookId=resultSet.getInt(1);
					out.println("<tr><td>"+bookId+"</td> <td>"+resultSet.getString(2)+"</td> <td>"+resultSet.getString(3)+"</td> <td>"+resultSet.getInt(4)+"</td> <td>"+(resultSet.getInt(5)==-1?"<mark>Not Borrowed</mark>":"<mark style='background-color: lightblue;'>"+resultSet.getInt(5)+"</mark>")+" </td> </tr>");
					
				} while (resultSet.next());
				out.println("</table>");
			} else {
				out.println("<h1>Books n_a</h1>");
			}

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("<a href='removeBookPage'><button>Remove Book</button></a>");
		out.println("<a href='addBookPage'><button>Add Book</button></a>");
		out.println("<a href='adminHomepage'><button>Home</button></a>");
		out.println("<a href='Login.html'><button>go to Login</button></a>");

		out.println("</body>");
		out.println("</html>");
		
	}
	 
}
