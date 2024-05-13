package com.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

@WebServlet("/loginForm")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","root");
			
			PreparedStatement ps = con.prepareStatement("select * from register where username=? and password=?"); 
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs= ps.executeQuery();
			if(rs.next()) 
			{
				HttpSession session = req.getSession();
				session.setAttribute("session_name", rs.getString("firstname"));
				session.setAttribute("user_name", username);
				req.setAttribute("user_name", username);
				
				RequestDispatcher rd = req.getRequestDispatcher("/orderdetails.jsp");
				rd.include(req, resp);
			} else {
				out.print("<h3 style = 'color:red'> Username and password did not matched</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.include(req, resp);
			}
			
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			out.print("<h3 style = 'color:red'> "+e.getMessage()+" </h3>");
			
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.include(req, resp);
		}
	}

}
