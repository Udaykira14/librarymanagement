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

@WebServlet("/addBook")
public class AddBook extends HttpServlet{
	Connection connection;
	PreparedStatement preparedStatement;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out= resp.getWriter();
		String name= req.getParameter("name");
		String author=req.getParameter("author");
		int id=Integer.parseInt(req.getParameter("id"));
		int cost=Integer.parseInt(req.getParameter("cost"));
		int available=-1;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","#UkiDB345");
			
			preparedStatement=connection.prepareStatement("insert into book values(?,?,?,?,?)");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, author);
			preparedStatement.setInt(4, cost);
			preparedStatement.setInt(5, available);
			int result=preparedStatement.executeUpdate();
			
			if (result==1) {
				RequestDispatcher dispatcher= req.getRequestDispatcher("addBookPage");
				out.println("<h1>New Book Added</h1>");
				dispatcher.include(req, resp);
			}
			else {
				RequestDispatcher dispatcher= req.getRequestDispatcher("addBookPage");
				out.println("<h1>Book Not Added</h1>");
				dispatcher.include(req, resp);
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement!=null) {
					preparedStatement.close();
				}
				if (connection!=null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
