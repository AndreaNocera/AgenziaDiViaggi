<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent
 * 
 * @name Login.jsp
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */
 -->

<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1" %>

<%@ include file = "common/Head.jsp" %>
<%@ include file = "common/DialogResetPassword.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset = ISO-8859-1">
		
		<link href = "common/css/Login.css" type = "text/css" rel = "stylesheet">
  		
	</head>
	
	<body>
	
		<div class = "panelLogo" id = "panelLogo" align = "center">
		
			<img class = "logo" id = "logoVoyager" border = "0" src = "common/img/Voyager.png">
			
		</div>
		
		<div class = "panelMain" id = "panelLogin" align = "center">
				
				<form id = "formLogin" name = "formLogin" action = "Login" method = "POST">
				
					<p><input class = "text ui-widget-content ui-corner-all" id = "username" name = "username" type = "text" placeholder = "Username"/></p>
					<p><input class = "text ui-widget-content ui-corner-all" id = "password" name = "password" type = "password" placeholder = "Password"/></p>
					<p><button class = "buttonLock buttonIconLabel" id = "buttonLogin" type = "submit">Login</button>
					<button class = "buttonOption buttonApriDialogResetPassword buttonIcon" id = "buttonResetPassword" type = "button" title = "Hai dimenticato la password?"></button></p>
				
				</form>
		
		</div>
	
	</body>
	
</html>