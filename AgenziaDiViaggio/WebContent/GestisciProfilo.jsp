<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent
 * 
 * @name GestisciProfilo.jsp
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */
 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
		<title>Voyager Gestisci Profilo</title>
		<link rel = "stylesheet" href = "Script/jquery-ui/css/ui-lightness/jquery-ui-1.10.3.custom.css" >
		<link rel = "stylesheet" href = "css/Main.css" >
		<script src = "Script/jquery-ui/js/jquery-1.9.1.js"></script>
		<script src = "Script/jquery-ui/js/jquery-ui-1.10.3.custom.js"></script>
		
		<link href = "img/favicon.ico" rel = "icon" type = "image/x-icon" />
        <link href = "img/favicon.ico" rel = "shortcut icon" type = "image/x-icon" />
		
		<script>
		
			window.onload = function() {
				
    			$( document ).tooltip();
    			
    			$( "button" ).button();
    			
    			$( "#datepickerNascita" ).datepicker({
    				inline: true
    			});
    			
    			$( ".radioset" ).buttonset();
    			
    			$( "#checkCambiaPassword" ).button();
    			
    			$( "#buttonOk" ).button({
    				icons: { primary: "ui-icon-check" }
    			});
    			
    			$( "#buttonAnnulla" ).button({
    				icons: { primary: "ui-icon-close" }
    			});    			
    			
  			};
  			
  		</script>
  		
  		<style>
  		
  			
  			
  		</style>
	
	</head>
	
	<body>
	
		<div class = "panelLogo" align = "center">
				<img class = "logo" border = "0" src = "img/Voyager.png" >
		</div>
		
		<div class = "panelGestioneProfilo" align = "center">
			<p class = "title">GESTIONE PROFILO</p>
			
			<form name = "formGestioneProfilo" action = "GestioneProfiloServlet" method = "GET">
				<p><input class = "text" id = "nome" name = "nome" type = "text" placeholder = "Nome" /></p>
				<p><input class = "text" id = "cognome" name = "cognome" type = "text" placeholder = "Cognome" /></p>
				<p><input class = "text" id = "città" name = "città" type = "text" placeholder = "Città" /></p>
				<div class = "datepicker" id = "datepickerNascita"></div>
				<div class = "radioset" id = "radiosetSesso">
					<input class = "radio" type = "radio" id = "radioUomo" name = "radioSesso" value = "Uomo" checked = "checked" ><label class = "radioButtonLabel" for = "radioUomo">Uomo</label>
					<input class = "radio" type = "radio" id = "radioDonna" name = "radioSesso" value = "Donna" ><label class = "radioButtonLabel" for = "radioDonna">Donna</label>
				</div>
				<p><input class = "text" id = "mail" name = "mail" type = "text" placeholder = "Mail" /></p>
				<p class = "label" id = "ruolo" >Ruolo</p>
				<p class = "label" id = "username" >Username</p>
				<p><input class = "checkbox" id = "checkCambiaPassword" type = "checkbox"/><label class = "buttonLabel" for = "checkCambiaPassword">Cambia Password</label></p>
				<p><input class = "text" id = "password" name = "password" type = "password" placeholder = "Password" /></p>
				<p><input class = "text" id = "nuovaPassword" name = "nuovaPassword" type = "password" /></p>
				<p><input class = "text" id = "confermaNuovaPassword" name = "confermaNuovaPassword" type = "password" /></p>
				<p><button class = "buttonIconLabel" id = "buttonAnnulla" type = "submit" name = "action" value = "annulla">Annulla</button>
				<button class = "buttonIconLabel" id = "buttonOk" type = "submit" name = "action" value = "ok">Ok</button></p>
			</form>
			
		</div>
	
	</body>

</html>