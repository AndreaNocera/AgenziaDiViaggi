<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
		<title>Voyager</title>
		<link rel = "stylesheet" href = "Script/jquery-ui/css/ui-lightness/jquery-ui-1.10.3.custom.css" >
		<link rel = "stylesheet" href = "css/Main.css" >
		<link rel = "stylesheet" href = "css/Login.css" >
		<script src = "Script/jquery-ui/js/jquery-1.9.1.js"></script>
		<script src = "Script/jquery-ui/js/jquery-ui-1.10.3.custom.js"></script>
		
		<script>
		
  			$(function() {
  				
    			$( document ).tooltip();
    			
    			$( "button" ).button();
    			
    			$( "#buttonLogin" ).button({
    				icons: { primary: "ui-icon-locked" }
    			});
    			
    			$( "#buttonReimpostaPassword" ).button({
    				icons: { primary: "ui-icon-gear" }
    			});
    			
  			});
  			
  		</script>
  		
	</head>
	
	<body>
	
		<div class = "panelLogo" id = "panelLogo" align = "center">
			<img class = "logo" id = "logoVoyager" border = "0" src = "img/Voyager.png" >
		</div>
		
		<div class = "panelMain" id = "panelLogin" align = "center">		
				<form name = "formLogin" action = "LoginServlet" method = "GET">
					<p><input class = "text" id = "username" name = "username" type = "text" placeholder = "Username" title = "Inserisci il tuo username" /></p>
					<p><input class = "text" id = "password" name = "password" type = "password" placeholder = "Password" title = "Inserisci la tua password" /></p>
					<p><button class = "buttonIconLabel" id = "buttonLogin" type = "submit" name = "action" value = "login" >Login</button>
					<button class = "buttonIcon" id = "buttonReimpostaPassword" type = "submit" name = "action" value = "reset"  title = "Hai dimenticato la password?"></button></p>
				</form>				
		</div>
	
	</body>
</html>