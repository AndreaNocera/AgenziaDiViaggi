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
		
		<link href = "img/favicon.ico" rel = "icon" type = "image/x-icon" />
        <link href = "img/favicon.ico" rel = "shortcut icon" type = "image/x-icon" />
		
		<script>
		
  			$(function() {
  				
    			$( document ).tooltip();    			
    			
    			$( ".dialogMessaggio" ).dialog({
    				autoOpen: false,
    			    modal: true,
    			    buttons: {
    			      Ok: function() {
    			        $( this ).dialog( "close" );
    			      }
    			    }
    			});    			
    			
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
    			          //verifica eccezioni
    			          
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
    			
    			$( "button" ).button();    			
    			
    			$( "#buttonLogin" ).button({
    				icons: { primary: "ui-icon-locked" }
    			});
    			
    			$( "#buttonReimpostaPassword" ).button({
    				icons: { primary: "ui-icon-gear" }
    			})
    			.click(function() {
			        $( "#dialogFormReimpostaPassword" ).dialog( "open" );
			        return false;
			    });
    			
  			});
  			
  		</script>
  		
	</head>
	
	<body>
	
		<div class = "panelLogo" id = "panelLogo" align = "center">
			<img class = "logo" id = "logoVoyager" border = "0" src = "img/Voyager.png" >
		</div>
		
		<div class = "panelMain" id = "panelLogin" align = "center">		
				<form name = "formLogin" action = "Login" method = "GET">
					<p><input class = "text" id = "username" name = "username" type = "text" placeholder = "Username" title = "Inserisci il tuo username" /></p>
					<p><input class = "text" id = "password" name = "password" type = "password" placeholder = "Password" title = "Inserisci la tua password" /></p>
					<p><button class = "buttonIconLabel" id = "buttonLogin" type = "submit" name = "action" value = "login" >Login</button>
					<button class = "buttonIcon" id = "buttonReimpostaPassword" type = "submit" name = "action" value = "reset"  title = "Hai dimenticato la password?"></button></p>
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