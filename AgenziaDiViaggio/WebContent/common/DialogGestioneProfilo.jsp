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
				    height: 400,
				    width: 350,
				    modal: true,
				    buttons: {
					    "Ok": function() {
			   			    var mUsername = $( "#username" ).val();
			   			          
			   			    $.get("http://localhost:8080/WebVoyager/ResetCode", {username : mUsername})
			   			    .done(function() {
				   			    $( "#dialogMessaggioGestioneProfiloSuccesso" ).dialog( "open" );
			   			    })
			   			    .fail(function() {
				   			    $( "#dialogMessaggioGestioneProfiloFallimento" ).dialog( "open" );
			   			    })
			   			    .always(function() {
				   			    $( "#dialogGestioneProfilo" ).dialog( "close" );
			   			    });    			          
					    },
					    "Annulla": function() {
			   			    $( this ).dialog( "close" );
					    }
				    }
			});
			
			$( ".buttonApriDialogGestioneProfilo" ).click(function() {
		        $( "#dialogGestioneProfilo" ).dialog( "open" );
		        return false;
		    });
			
		});
	
	</script>
	
	</head>
	
	<body>
	
		<div class = "dialogForm" id = "dialogGestioneProfilo" title = "Gestione Profilo">
  			<p>Da qui puoi modificare i dettagli del tuo profilo.</p> 
  			<form>
  				<fieldset>
    				<label for = "nome">Nome</label>
    				<label for = "cognome">Cognome</label>
    				<label for = "citta">Città</label>
    				<label for = "nascita">Nascita</label>
    				<label for = "sesso">Sesso</label>
    				<label for = "mail">Mail</label>
    				<label for = "ruolo">Ruolo</label>
    				<label for = "username">Username</label>
    				<label for = "password">Password</label>
    				
    				<input class = "text ui-widget-content ui-corner-all" id = "nome" type = "text" name = "nome" />
    				<input class = "text ui-widget-content ui-corner-all" id = "cognome" type = "text" name = "cognome" />
    				<input class = "text ui-widget-content ui-corner-all" id = "citta" type = "text" name = "citta" />
    				<input class = "text ui-widget-content ui-corner-all" id = "nascita" type = "text" name = "nascita" />
    				<input class = "text ui-widget-content ui-corner-all" id = "sesso" type = "text" name = "sesso" />
    				<input class = "text ui-widget-content ui-corner-all" id = "mail" type = "text" name = "mail" />
    				<input class = "text ui-widget-content ui-corner-all" id = "ruolo" type = "text" name = "ruolo" />
    				<input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" />
    				<input class = "text ui-widget-content ui-corner-all" id = "password" type = "text" name = "password" />
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