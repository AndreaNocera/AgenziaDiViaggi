<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent
 * 
 * @name AmministraUtenti.jsp
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */
 -->

<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1"%>

<%@ include file = "common/Head.jsp" %>

<%@ page import = "java.util.List" %>
<%@ page import = "gestioneutenti.model.bean.UtenteBean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
		
		<link href = "common/css/AmministraUtenti.css" type = "text/css" rel = "stylesheet">
				
		<script>
		
			window.onload = function() {
    			
    			$( "#selectable" ).selectable({
    			      stop: function() {
    			        $( ".ui-selected", this ).each(function() {
    			          var index = $( "#selectable li" ).index( this );
    			        });
    			      }
    			    });
    			
  			};
  			
  		</script>
	
	</head>
	
	<body>
	
		<div class = "panelLogo" align = "center">
				<img class = "logo" border = "0" src = "common/img/Voyager.png" >
		</div>
		
		<div class = "panelAmministrazioneUtenti" align = "center">
			<p class = "title">AMMINISTRAZIONE UTENTI</p>
			<div class = panelButton id = "panelButton" align = "center">
				<form name = "formAzioni" action = "AmministrazioneUtentiServlet" method = "GET">
					<p><input class = "text" id = "cerca" name = "cerca" type = "text" placeholder = "Cerca" />
					<button class = "buttonSearch buttonIconLabel" id = "buttonCerca" type = "submit" name = "action" value = "cerca">Cerca</button>
					<button class = "buttonAdd buttonIconLabel" id = "buttonNuovo" type = "submit" name = "action" value = "nuovo">Nuovo</button>
					<button class = "buttonEdit buttonIconLabel" id = "buttonModifica" type = "submit" name = "action" value = "modifica">Modifica</button>
					<button class = "buttonRemove buttonIconLabel" id = "buttonRimuovi" type = "submit" name = "action" value = "rimuovi">Rimuovi</button></p>
				</form>			
			</div>		
			
			<% 
				List<UtenteBean> utenti = (List<UtenteBean>) request.getAttribute("utenti");		
			%>	
			
			<div class = "items" align = "center">
				<ol id = "selectable">
		
					<%  
						for (UtenteBean utente : utenti) {
							out.println("<li class = \"ui-widget-content\">" + 
								utente.getNome() + " " + 
								utente.getCognome() + " " + 
								utente.getRuolo().asString() + " " + "(" + 
								utente.getUsername() + ")" +
								"</li>");
						}
					%>
					
				</ol>			
			</div>		
					
		</div>
	
	</body>

</html>