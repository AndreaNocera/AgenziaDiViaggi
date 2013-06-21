<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<form name="actionForm" action="LoginServlet" method ="GET">
<table>
<tr><td>Username: </td><td><input type="text" name="username"/></td></tr>
<tr><td>Password: </td><td><input type="password" name="password"/></td></tr>
<tr><td><input type="submit" value="submit"> </td></tr>
</table>
</form>
</body>
</html>