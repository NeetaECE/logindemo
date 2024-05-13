package com.assignment;

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

@WebServlet("/regForm")
public class RegisterServlet extends HttpServlet {

	void Validate(String tableColumn, String exceptionMsg) throws Exception
	{
		if (tableColumn == "") {
			throw new Exception(exceptionMsg);
		}
	}
	
	void ExceptionHandling(HttpServletRequest req, HttpServletResponse resp, Exception e)
	{
		try {
			PrintWriter out = resp.getWriter();
			e.printStackTrace();
			resp.setContentType("text/html");
			out.print("<h3 style='color:red'> "+e.getMessage()+" </h3>");
			
			RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
			rd.include(req, resp);
		}
		catch (Exception ex) {
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try 
		{
			Validate(firstname, "firstname cannot be empty");
			Validate(lastname, "lastname cannot be empty");
			Validate(username, "username cannot be empty");
			Validate(password, "password cannot be empty");
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","root");
			
			PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?)");
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, username);
			ps.setString(4, password);
			
			int count = ps.executeUpdate();
			if(count>0) 
			{
				RequestDispatcher rd = req.getRequestDispatcher("/success.jsp");
				rd.include(req, resp);
			}
			else
			{
				throw new Exception("User not registered due to some error");
			}
			
		}
		catch (SQLException sqlExp)
		{
			ExceptionHandling(req, resp, new Exception("username already exist"));
		}
		catch (Exception e)
		{
			ExceptionHandling(req, resp, e);
		}
		
	}
}
