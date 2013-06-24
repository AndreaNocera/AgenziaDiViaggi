<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import = "gestioneutenti.model.Utente" %>
<%@ page import = "gestioneutenti.model.ruoli.*" %>
<%@ page import = "gestioneutenti.model.competenze.*" %>
<%@ page import = "java.util.Calendar" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Voyager Home</title>
		<link rel = "stylesheet" href = "Script/jquery-ui/css/ui-lightness/jquery-ui-1.10.3.custom.css" >
		<script src = "Script/jquery-ui/js/jquery-1.9.1.js"></script>
		<script src = "Script/jquery-ui/js/jquery-ui-1.10.3.custom.js"></script>
		
		<script>
			window.onload = function() {
    			$( document ).tooltip();
    			
    			$( "button" ).button();
    			
    			$( "#button0" ).button({
    				icons: { primary: "ui-icon-power" }
    			});
    			
    			$( "#button1" ).button({
    				icons: { primary: "ui-icon-gear" }
    			});
    			
    			$( "#button2" ).button({
    				icons: { primary: "ui-icon-person" }
    			});
    			
    			$( "#button3" ).button({
    				icons: { primary: "ui-icon-note" }
    			});
    			
    			$( "#button4" ).button({
    				icons: { primary: "ui-icon-cart" }
    			});
    			
    			
  			};
  			
  		</script>
  		
  		<style>
  		
  			body{
				background: #fbfbfb;
				font: 62.5% "Trebuchet MS", sans-serif;
				margin: 50px;
			}
			
			.panelLogo {
				margin-top: 50px;
			}
			
			.panelCompetenze {
				background: #ffffff;
				border-radius: 10px;
				padding: 25px;
				margin-top: 25px;
				margin-left: 450px;
				margin-right: 450px;				
			}
			
			.title {
				font-size: 15px;
			}
			
			.welcomeMessage {
				padding-bottom: 10px;
			}
  			
  			button {
  				width: 300px;
  			}
  			
  		</style>
	</head>
	
	<body>
	
		<div class = "panelLogo" align = "center">
			<img class = "logo" border = "0" src = "img/Voyager.png" >
		</div>
	
	<% Utente utente = (Utente) request.getAttribute("utente");
	String WELCOME_MESSAGE_AM_PM = (Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM) ? "Buongiorno" : "Buonasera";%>
	
	<div class = "panelCompetenze" align = "center">
		<p class = "title">HOME</p>
		<% out.println("<p class = \"welcomeMessage\">" + WELCOME_MESSAGE_AM_PM + " " + utente.getDatiUtente().getNome() + "</p>"); %>
		<form name = "formCompetenze" action = "HomeServlet" method = "GET">
			<%Ruolo ruolo = utente.getRuolo();
			Competenza[] competenze = ruolo.getCompetenze();
			for (Competenza c : competenze) {
				out.println("<p><button id = \"button" + c.getId() + "\" name = \"competenza\" value = \"" + c.getId() + "\" type = \"submit\">" + c.asString() + "</button></p>");
			} %>
		</form>
	</div>			
	
	</body>
	
</html>