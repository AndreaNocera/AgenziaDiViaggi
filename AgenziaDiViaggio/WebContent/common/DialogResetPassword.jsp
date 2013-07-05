<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent/common
 * 
 * @name DialogResetPassword.jsp
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
			
			$( "#dialogResetPassword" ).dialog({    				
				    autoOpen: false,
				    draggable: false,
				    height: 200,
				    width: 350,
				    modal: true,
				    show: {
				    	effect: "blind"
				    },
				    hide: {
				    	effect: "blind"
				    },
				    buttons: {
					    "Ok": function() {
			   			    var mUsername = $( "#usernameResetPassword" ).val();
			   			          
			   			    $.post("http://localhost:8080/WebVoyager/ResetPassword", {username : mUsername})
			   			    .done(function() {
				   			    $( "#dialogMessaggioResetPasswordSuccesso" ).dialog( "open" );
			   			    })
			   			    .always(function() {
				   			    $( "#dialogResetPassword" ).dialog( "close" );
			   			    });    			          
					    },
					    "Annulla": function() {
			   			    $( this ).dialog( "close" );
					    }
				    }
			});
			
			$( ".buttonApriDialogResetPassword" ).click(function() {
		        $( "#dialogResetPassword" ).dialog( "open" );
		        return false;
		    });
			
		});
	
	</script>
	
	</head>
	
	<body>
	
		<div class = "dialogForm" id = "dialogResetPassword" title = "Reimposta Password">
		
  			<p>Inserisci il tuo username.</p> 
  			
  			<form>
  			
  				<fieldset>
  				
    				<label for = "usernameResetPassword">Username</label>
    				<input class = "text ui-widget-content ui-corner-all" id = "usernameResetPassword" type = "text" name = "usernameResetPassword" />
  				
  				</fieldset>
  				
  			</form>
  			
		</div>
		
		<div class = "dialogMessaggio" id = "dialogMessaggioResetPasswordSuccesso" title = "Reimposta Password">
		
  			<p><span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			La <b>Password Provvisoria</b> sarà inviata all'indirizzo email dell'utente specificato!
  			</p>
  			
		</div>	
	
	</body>

</html>