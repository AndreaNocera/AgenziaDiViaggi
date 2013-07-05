<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent
 * 
 * @name FallimentoLogin.jsp
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */
 -->

<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1" %>

<%@ include file = "common/Head.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset = ISO-8859-1">
		
		<link href = "common/css/Login.css" type = "text/css" rel = "stylesheet">
  		
	</head>
	
	<body>
		
		<div class = "dialogMessaggioErrore" id = "dialogMessaggioFallimentoLogin" title = "Login Errato">
		
  			<p><span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			<b>Ooops!</b> Non sei stato riconosciuto!
  			</p>
  			
		</div>
	
	</body>
	
</html>