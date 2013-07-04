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

<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1"%>

<%@ include file = "common/Head.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset = ISO-8859-1">
		
		<link href = "common/css/Login.css" type = "text/css" rel = "stylesheet">
		
		<script>
		
  			$(function() {	
    			
    			$( "#dialogFormReimpostaPassword" ).dialog({    				
    			    autoOpen: false,
    			    height: 300,
    			    width: 350,
    			    modal: true,
    			    buttons: {
    			        "Ok": function() {
    			          var valid = true;
    			          var username = $( "#usernameResetCode" ).val();
    			          
    			          $.get("http://localhost:8080/WebVoyager/ResetCode?username=" + username);
    			          
    			          if ( valid ) {
    			           	$( "#dialogMessaggioResetCodeSuccesso" ).dialog( "open" );
    			            $( this ).dialog( "close" );
    			          } else {
    			        	$( "#dialogMessaggioResetCodeFallimento" ).dialog( "open" );
      			            $( this ).dialog( "close" );
    			          }
    			        },
    			        "Annulla": function() {
    			          $( this ).dialog( "close" );
    			        }
    			    }
    			});
    			
    			$( "#buttonReimpostaPassword" ).click(function() {
			        $( "#dialogFormReimpostaPassword" ).dialog( "open" );
			        return false;
			    });
    			
  			});
  			
  		</script>
  		
	</head>
	
	<body>
	
		<div class = "panelLogo" id = "panelLogo" align = "center">
			<img class = "logo" id = "logoVoyager" border = "0" src = "common/img/Voyager.png" >
		</div>
		
		<div class = "panelMain" id = "panelLogin" align = "center">		
				<form name = "formLogin" action = "Login" method = "GET">
					<p><input class = "text" id = "username" name = "username" type = "text" placeholder = "Username" title = "Inserisci il tuo username" /></p>
					<p><input class = "text" id = "password" name = "password" type = "password" placeholder = "Password" title = "Inserisci la tua password" /></p>
					<p><button class = "buttonLock buttonIconLabel" id = "buttonLogin" type = "submit" name = "action" value = "login" >Login</button>
					<button class = "buttonOption buttonIcon" id = "buttonReimpostaPassword" type = "submit" name = "action" value = "reset"  title = "Hai dimenticato la password?"></button></p>
				</form>				
		</div>
		
		<div class = "dialogForm" id = "dialogFormReimpostaPassword" title = "Reimposta Password">
  			<p>Inserisci il tuo username.</p> 
  			<form>
  				<fieldset>
    				<label for = "usernameResetCode">Username</label>
    				<input class = "text ui-widget-content ui-corner-all" id = "usernameResetCode" type = "text" name = "usernameResetCode" />
  				</fieldset>
  			</form>
		</div>
		
		<div class = "dialogMessaggio" id = "dialogMessaggioResetCodeSuccesso" title = "ResetCode">
  			<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Il ResetCode è stato correttamente inviato a <b>username</b>
  			</p>
		</div>
		
		<div class = "dialogMessaggio" id = "dialogMessaggioResetCodeFallimento" title = "ResetCode">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Oops! Il ResetCode non può essere inviato! Hai specificato uno <b>username non valido</b>
  			</p>
		</div>
		
		<div class = "dialogMessaggio" id = "dialogMessaggioLoginFallimento" title = "Login Errato">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Ooops! <b>NON</b> sei stato riconosciuto!
  			</p>
		</div>
	
	</body>
</html>