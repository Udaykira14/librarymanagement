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

@WebServlet("/removeBook")
public class RemoveBook extends HttpServlet{
	Connection connection;
	PreparedStatement preparedStatement;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out =resp.getWriter();
		int id=Integer.parseInt(req.getParameter("id"));
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","#UkiDB345");
			
			preparedStatement=connection.prepareStatement("delete from book where bid=?");
			preparedStatement.setInt(1, id);
			
			int result=preparedStatement.executeUpdate();
			
			if (result==1) {
				RequestDispatcher dispatcher= req.getRequestDispatcher("removeBookPage");
				out.println("<h1>Book Deleted</h1>");
				dispatcher.include(req, resp);			
			} else {
				out.println("<h1>Book Not Deleted</h1>");
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
}
