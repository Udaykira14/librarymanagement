package org.jsp.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/borrow")
public class BorrowBook extends HttpServlet{
	Connection connection;
	PreparedStatement preparedStatement;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		int userId = (Integer) session.getAttribute("id");
		int bookId= Integer.parseInt(req.getParameter("bookId"));

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","#UkiDB345");
			
			
			preparedStatement=connection.prepareStatement("update book set availability=? where bid=?");
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, bookId);
			
			
			int result= preparedStatement.executeUpdate();
			
			if (result==1) {
				resp.sendRedirect("displayAllBooks");
				
			} else {
				resp.sendRedirect("displayAllBooks");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
