<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent
 * 
 * @name Home.jsp
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */
 -->

<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1"%>

<%@ include file = "common/Head.jsp" %>

<%@ page import = "gestioneutenti.model.bean.UtenteBean" %>
<%@ page import = "gestioneutenti.model.ruoli.*" %>
<%@ page import = "gestioneutenti.model.competenze.*" %>
<%@ page import = "java.util.Calendar" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
		
		<link href = "common/css/Home.css" type = "text/css" rel = "stylesheet">
  		
	</head>
	
	<body>
	
		<div class = "panelLogo" id = "panelLogo" align = "center">
			<img class = "logo" id = "logoVoyager" border = "0" src = "common/img/Voyager.png" >
		</div>
	
		<% 
			UtenteBean utenteBean = (UtenteBean) request.getAttribute("utente");		
			String WELCOME_MESSAGE_AM_PM = (Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM) ? "Buongiorno" : "Buonasera";
		%>
		
		<div class = "panelMain" id = "panelCompetenze" align = "center">
			<p class = "title" id = "titleHome">HOME</p>
			
			<% 
				out.println("<p class = \"subtitle\" id = \"subtitleWelcomeMessage\">" + WELCOME_MESSAGE_AM_PM + " " + utenteBean.getNome() + "</p>"); 
			%>
			
			<form name = "formCompetenze" action = "Home" method = "GET">
			
				<%
					Ruolo ruolo = utenteBean.getRuolo();
					Competenza[] competenze = ruolo.getCompetenze();
					for (Competenza c : competenze) {
						out.println("<p><button class = \"buttonCompetenza buttonIconLabelExtraLarge\" id = \"button" + c.asCompactString() + "\" name = \"competenza\" value = \"" + c.getId() + "\" type = \"submit\">" + c.asString() + "</button></p>");
					}
				%>
				
			</form>
			
		</div>	
	
	</body>
	
</html>