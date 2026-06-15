package org.jsp.auth;

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

@WebServlet("/signup")
public class SignUp extends HttpServlet{
	Connection c;
	PreparedStatement pS;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String role=req.getParameter("role");
		long number= Long.parseLong(req.getParameter("number"));
		
		
		PrintWriter pout= resp.getWriter();
		pout.println("<html>");
		pout.println("<body>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			c= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","#UkiDB345");
			
			pS=c.prepareStatement("Insert into rolebased values(?,?,?,?,?,?)");
			pS.setString(1, name);
			pS.setString(2, email);
			pS.setString(3, password);
			pS.setString(4, role);
			pS.setLong(5, number);
			pS.setInt(6, id);
			
			int done=pS.executeUpdate();
			
			if (done==1) {
				pout.println("SignUp Successful");
				RequestDispatcher dispatcher= req.getRequestDispatcher("Login.html");
				dispatcher.forward(req, resp);
			} else {
				pout.println("<h1>User Already Exists</h1>");
				RequestDispatcher dispatcher= req.getRequestDispatcher("SignUp.html");
				dispatcher.include(req, resp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pS!=null) {
					pS.close();
				}
				if (c!=null) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		pout.println("</html>");
		pout.println("</body>");
		
		
	}
}
