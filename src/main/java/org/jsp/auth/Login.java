package org.jsp.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet{
	Connection c;
	PreparedStatement pS;
	ResultSet rS;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password= req.getParameter("password");
		String role=req.getParameter("role");
		
		PrintWriter pout= resp.getWriter();
		pout.println("<html>");
		pout.println("<body>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			c= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","#UkiDB345");
			
			pS=c.prepareStatement("select * from rolebased where email=?");
			pS.setString(1, email);
			
			rS=pS.executeQuery();
			
			if (rS.next()) {
				do {
					if (rS.getString("password").equals(password)) {
						if (rS.getString("role").equals("user") && role.equals("user")) {
//							passing id and name from database
							String userName=rS.getString("name");
							String userEmail=rS.getString("email");
							String userPassword=rS.getString("password");
							int userId=rS.getInt("id");
							long userNumber = rS.getLong("number");
							
//							creating a session object which pass data through attributes 
							HttpSession session = req.getSession();
							session.setAttribute("name", userName);
							session.setAttribute("email", userEmail);
							session.setAttribute("password", userPassword);
							session.setAttribute("number", userNumber);
							session.setAttribute("id", userId);
							RequestDispatcher dispatcher= req.getRequestDispatcher("userHomepage");
							dispatcher.forward(req, resp);
						} else if (rS.getString("role").equals("admin") && role.equals("admin")) {
							String userName=rS.getString("name");
							String userEmail=rS.getString("email");
							String userPassword=rS.getString("password");
							int userId=rS.getInt("id");
							long userNumber = rS.getLong("number");
							
//							creating a session object which pass data through attributes 
							HttpSession session = req.getSession();
							session.setAttribute("name", userName);
							session.setAttribute("email", userEmail);
							session.setAttribute("password", userPassword);
							session.setAttribute("number", userNumber);
							session.setAttribute("id", userId);
							RequestDispatcher dispatcher= req.getRequestDispatcher("adminHomepage");
							dispatcher.forward(req, resp);
						}else{
							
							RequestDispatcher dispatcher= req.getRequestDispatcher("Login.html");
							pout.println("<h1>Role mismatch</h1>");
							dispatcher.include(req, resp);
						}
						
						
					} else {
						RequestDispatcher dispatcher= req.getRequestDispatcher("Login.html");
						pout.println("<h1>Password mismatch</h1>");
						dispatcher.include(req, resp);
					}
				} while (rS.next());
			}else {
				RequestDispatcher dispatcher= req.getRequestDispatcher("Login.html");
				pout.println("<h1>User not found</h1>");
				dispatcher.include(req, resp);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rS!=null) {
					rS.close();
				}
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
