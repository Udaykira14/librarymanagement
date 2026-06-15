package org.jsp.service;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/delete")
public class DeleteUser extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		HttpSession session = req.getSession();
		int id = (Integer) session.getAttribute("id");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root",
					"#UkiDB345");

			PreparedStatement preparestatement = connection
					.prepareStatement("update book set availability=-1 where availability=" + id);
			preparestatement.addBatch();
			preparestatement.addBatch("delete from rolebased where id=" + id);

			int[] result = preparestatement.executeBatch();

			if (result[1] == 1) {
//				RequestDispatcher dispatcher= req.getRequestDispatcher("returnBook");
//				dispatcher.forward(req, resp);
				resp.sendRedirect("Login.html");
			} else {
				out.println("mission impossible");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
