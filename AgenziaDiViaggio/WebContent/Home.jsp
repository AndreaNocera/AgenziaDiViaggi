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
<%@ include file = "common/DialogGestioneProfilo.jsp" %>
<%@ page import = "home.helper.HelperHome" %>
<%@ page import = "gestioneutenti.model.bean.UtenteBean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
		
		<link href = "common/css/Home.css" type = "text/css" rel = "stylesheet">
		
		<%
			HelperHome helper = HelperHome.getInstance();	
			UtenteBean utenteBean = (UtenteBean) request.getAttribute("utente");
		%>
  		
	</head>
	
	<body>
	
		<div class = "panelLogo" id = "panelLogo" align = "center">
			<img class = "logo" id = "logoVoyager" border = "0" src = "common/img/Voyager.png" >
		</div>		
		
		<div class = "panelMain" id = "panelCompetenze" align = "center">
		
			<p class = "title" id = "titleHome">HOME</p>
			
			<%				
				out.println(helper.getWelcome(utenteBean.getNome()));
			%>			
			
			<form name = "formCompetenze" action = "Home" method = "GET">
			
				<%
					out.println(helper.getHomePanel(utenteBean.getRuolo()));
				%>
				
			</form>
			
		</div>	
	
	</body>
	
</html>