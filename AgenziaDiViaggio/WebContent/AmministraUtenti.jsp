<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import = "gestioneutenti.model.Utente" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
		<title>Voyager Amministra Utenti</title>
		<link rel = "stylesheet" href = "Script/jquery-ui/css/ui-lightness/jquery-ui-1.10.3.custom.css" >
		<script src = "Script/jquery-ui/js/jquery-1.9.1.js"></script>
		<script src = "Script/jquery-ui/js/jquery-ui-1.10.3.custom.js"></script>
		
		<script>
  			$(function() {
    			$( document ).tooltip();
    			$( "button" ).button();
    			$( "button").css({width: 300});
  			});
  			
  		</script>
  		
  		<style>
  			body{
				font: 62.5% "Trebuchet MS", sans-serif;
				margin: 50px;
			}
			.title {
				margin-top: 2em;
			}
			.subtitle {
				margin-top: 2em;
			}
			
  			label {
    			display: inline-block;
    			width: 5em;
  			}
  		</style>
	</head>
	
	<body>
	
		<% Utente[] utenti = (Utente[]) request.getAttribute("utenti");%>
		
		<div align = "center">
			<h1 class = "title">Voyager Amministra Utenti</h1>
			<p><button id = "buttonNuovoUtente" name = "azione" value = "nuovoUtente" type = "submit">Nuovo</button>
			<button id = "buttonModificaUtente" name = "azione" value = "modificaUtente" type = "submit">Modifica</button>
			<button id = "buttonRimuoviUtente" name = "azione" value = "rimuoviUtente" type = "submit">Rimuovi</button></p>
		</div>			
	
	</body>
	
	<body>
	
	</body>
</html>