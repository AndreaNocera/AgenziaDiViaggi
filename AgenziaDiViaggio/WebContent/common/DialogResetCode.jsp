<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent/common
 * 
 * @name DialogResetCode.jsp
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
			
			$( "#dialogReimpostaPassword" ).dialog({    				
				    autoOpen: false,
				    draggable: false,
				    height: 200,
				    width: 350,
				    modal: true,
				    buttons: {
					    "Ok": function() {
		   			    var mUsername = $( "#usernameResetCode" ).val();
		   			          
		   			    $.get("http://localhost:8080/WebVoyager/ResetCode", {username : mUsername})
		   			    .done(function() {
			   			    $( "#dialogMessaggioReimpostaPasswordSuccesso" ).dialog( "open" );
		   			    })
		   			    .fail(function() {
			   			    $( "#dialogMessaggioReimpostaPasswordFallimento" ).dialog( "open" );
		   			    })
		   			    .always(function() {
			   			    $( "#dialogReimpostaPassword" ).dialog( "close" );
		   			    });    			          
					    },
					    "Annulla": function() {
		   			    $( this ).dialog( "close" );
					    }
				    }
			});
			
			$( ".buttonApriDialogReimpostaPassword" ).click(function() {
		        $( "#dialogReimpostaPassword" ).dialog( "open" );
		        return false;
		    });
			
		});
	
	</script>
	
	</head>
	
	<body>
	
		<div class = "dialogForm" id = "dialogReimpostaPassword" title = "Reimposta Password">
  			<p>Inserisci il tuo username.</p> 
  			<form>
  				<fieldset>
    				<label for = "usernameResetCode">Username</label>
    				<input class = "text ui-widget-content ui-corner-all" id = "usernameResetCode" type = "text" name = "usernameResetCode" />
  				</fieldset>
  			</form>
		</div>
		
		<div class = "dialogMessaggio" id = "dialogMessaggioReimpostaPasswordSuccesso" title = "ResetCode">
  			<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Il <b>Reset Code</b> è stato correttamente inviato!
  			</p>
		</div>
		
		<div class = "dialogMessaggio" id = "dialogMessaggioReimpostaPasswordFallimento" title = "ResetCode">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Oops! Il ResetCode non può essere inviato! Hai specificato uno <b>username non valido</b>
  			</p>
		</div>
		
		
	
	</body>

</html>