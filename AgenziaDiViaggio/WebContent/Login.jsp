<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
		<title>Voyager</title>
		<link rel = "stylesheet" href = "Script/jquery-ui/css/ui-lightness/jquery-ui-1.10.3.custom.css" >
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
  		
  		<style>
  		
  			body{
  				background: #fbfbfb;
				font: 62.5% "Trebuchet MS", sans-serif;
				margin: 50px;
			}
			
			.panelLogo {
				margin-top: 150px;
			}
			
			.panelLogin {
				background: #000000;
				border-radius: 10px;
				padding: 25px;
				margin-top: 25px;
				margin-left: 50px;
				margin-right: 50px;				
			}			  			
  			
  			input {
  				width: 200px;
  				border-radius: 5px;
  				padding: 5px;
  			}
  			
  			.buttonIconLabel {
  				width: 100px;
  				height: 30px;
  			}
  			
  			.buttonIcon {
  				width: 30px;
  				height: 30px;
  			}
  			
  		</style>
  		
	</head>
	
	<body>
	
		<div class = "panelLogo" align = "center">
			<img class = "logo" border = "0" src = "img/Voyager.png" >
		</div>
		
		<div class = "panelLogin" align = "center">
				<form name = "formLogin" action = "LoginServlet" method = "GET">
					<p><input id = "username" name = "username" type = "text" placeholder = "Username" title = "Inserisci il tuo username" /></p>
					<p><input id = "password" name = "password" type = "password" placeholder = "Password" title = "Inserisci la tua password" /></p>
					<p><button class = "buttonIconLabel" id = "buttonLogin" name = "action" value = "login" type = "submit">Login</button>
					<button class = "buttonIcon" id = "buttonReimpostaPassword" name = "action" value = "reset" type = "submit"> </button></p>
				</form>
		</div>
	
	</body>
</html>