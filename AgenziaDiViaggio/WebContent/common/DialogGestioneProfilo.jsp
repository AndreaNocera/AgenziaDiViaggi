<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent/common
 * 
 * @name DialogGestioneProfilo.jsp
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */
 -->

<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
	<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
	
	<script>
	
		$(function() {	
			
			$( ".dialogMessaggio" ).dialog({
				autoOpen: false,
				draggable: false,
				modal: true,
				buttons: {
					Ok: function() {
						$( this ).dialog( "close" );
					}
				}
			});
			
			$( "#dialogGestioneProfilo" ).dialog({    				
				    autoOpen: false,
				    draggable: false,
				    height: 500,
				    width: 600,
				    modal: true,
				    buttons: {
					    "Ok": function() {
					    	/*
					    	var mNome = $( "#nome" ).val();
					    	var mCognome = $( "#nome" ).val();
					    	var mCitta = $( "#nome" ).val();
					    	var mNascita = $( "#nome" ).val();
					    	var mSesso = $( "#nome" ).val();
					    	var mMail = $( "#nome" ).val();
					    	var mRuolo = $( "#nome" ).val();
					    	var mUsername = $( "#nome" ).val();
					    	var mPassword = $( "#nome" ).val();
			   			          
			   			    $.get("http://localhost:8080/WebVoyager/GestioneProfilo", {username : mUsername})
			   			    .done(function() {
				   			    $( "#dialogMessaggioGestioneProfiloSuccesso" ).dialog( "open" );
			   			    })
			   			    .fail(function() {
				   			    $( "#dialogMessaggioGestioneProfiloFallimento" ).dialog( "open" );
			   			    })
			   			    .always(function() {
				   			    $( "#dialogGestioneProfilo" ).dialog( "close" );
			   			    });  */  			          
					    },
					    "Annulla": function() {
			   			    $( this ).dialog( "close" );
					    }
				    }
			});
			
			$( "#buttonGestioneProfilo" ).click(function() {
		        $( "#dialogGestioneProfilo" ).dialog( "open" );
		        return false;
		    });
			
		});
	
	</script>
	
	</head>
	
	<body>
	
		<div class = "dialogForm" id = "dialogGestioneProfilo" title = "Gestione Profilo">
		
  			<form>
  			
  				<fieldset>    				
    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "nome" type = "text" name = "nome" placeholder = "Nome"/>
    				<input class = "text ui-widget-content ui-corner-all" id = "cognome" type = "text" name = "cognome" placeholder = "Cognome"/></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "citta" type = "text" name = "citta" placeholder = "Città"/></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "nascita" type = "text" name = "nascita" /></p>    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "sesso" type = "text" name = "sesso" /></p>    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "mail" type = "text" name = "mail" placeholder = "Mail"/></p>    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "ruolo" type = "text" name = "ruolo" /></p>    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" /></p>    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "password" type = "text" name = "password" placeholder = "Password"/></p>
  				
  				</fieldset>
  				
  			</form>
  			
		</div>
		
		<div class = "dialogMessaggio" id = "dialogMessaggioGestioneProfiloSuccesso" title = "Gestione Profilo">
  			<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Il <b>tuo profilo</b> è stato correttamente aggiornato!
  			</p>
		</div>
		
		<div class = "dialogMessaggio" id = "dialogMessaggioGestioneProfiloFallimento" title = "Gestione Profilo">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Oops! <b>NON</b> è stato possibile aggiornare il tuo profilo.
  			</p>
		</div>		
	
	</body>

</html>