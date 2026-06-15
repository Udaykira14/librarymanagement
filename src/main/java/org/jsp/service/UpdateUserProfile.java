package org.jsp.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/update")
public class UpdateUserProfile extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id= Integer.parseInt(req.getParameter("id"));
		String name= req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		long number =Long.parseLong(req.getParameter("number"));
		
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","#UkiDB345");
			
			PreparedStatement preparedStatement= connection.prepareStatement("UPDATE ROLEBASED set name=?, email=?, password=?, number=? WHERE ID=?");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			preparedStatement.setLong(4, number);
			preparedStatement.setInt(5, id);
			
			int result=preparedStatement.executeUpdate();
			if (result==1) {
				
//				creating a session object which pass data through attributes 
				HttpSession session = req.getSession();
				session.setAttribute("name", name);
				session.setAttribute("email", email);
				session.setAttribute("password", password);
				session.setAttribute("number", number);
				session.setAttribute("id", id);
				
				
				resp.sendRedirect("profile");
			} else {
				resp.sendRedirect("profile");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
