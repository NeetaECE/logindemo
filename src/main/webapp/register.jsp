<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>register</title>
</head>
<h1>Registration Form</h1>
<body>
	<form action="regForm" method ="post">
		First Name: <input type="text" name ="firstname"/><br/> <br/>
		Last Name: <input type="text" name ="lastname"/><br/> <br/>
		Username: <input type="text" name ="username"/><br/> <br/>
		Password: <input type="password" name ="password"/><br/> <br/>
		<input type ="submit" value="Register"/>
	</form>
</body>
</html>