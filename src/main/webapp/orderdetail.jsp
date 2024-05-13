<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>order detail</title>
</head>
<body>
<h3> Welcome : ${user_name}... your order details below!!</h3>


<form method="post">
	<%
     String username = (String) request.getAttribute("user_name");
    %>
<table border="4">
   <tr>
        <td>username</td>
        <td>order_id</td>
        <td>order_date</td>
        <td>item</td>
        <td>amount</td>
   </tr>
  
   <%
   class Order {
		int order_id;
   }
   try
   {
       	Class.forName("com.mysql.cj.jdbc.Driver");
       	String query="select * from order_detail where username=\'" + username + "\'";
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","root");
       	Statement stmt=con.createStatement();
       	ResultSet rs=stmt.executeQuery(query);
       	
       	ArrayList<Order> orders = new ArrayList<Order>();
       	while(rs.next())
       	{
       		String usname = rs.getString(1);
       		int order_id = rs.getInt(2);
       		Order o = new Order();
       		o.order_id = order_id;
       		orders.add(o);
       	}
       	
       	for (Order o : orders)
       	{
   %>
           <tr>
           	<td><%Integer.toString(o.order_id); %></td>
           </tr>

   <%
       	}
   %>
   </table>
   <%
        rs.close();
        stmt.close();
        con.close();
   }
   catch(Exception e)
   {
        e.printStackTrace();
   }
   %>
</form>
</body>
</html>