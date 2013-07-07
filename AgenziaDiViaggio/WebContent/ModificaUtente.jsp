<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent
 * 
 * @name FallimentoInserimentoUtente.jsp
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
		
		<jsp:useBean id = "utenteSelezionato" class = "gestioneutenti.model.bean.UtenteBean" scope = "session"></jsp:useBean>
				
		<script>
		
			window.onload = function() {	
		    	
		    	$( "#dialogModificaUtente #checkGeneraPassword" ).change(function(){
		    	    if ($( this ).is( ":checked" )){
		    	    	$( "#dialogModificaUtente #password" ).val("Password generata");
		    	        $( "#dialogModificaUtente #password" ).prop("disabled", true);
		    	    } else {
		    	    	$( "#dialogModificaUtente #password" ).val("");
		    	        $( "#dialogModificaUtente #password" ).prop("disabled", false);
		    	    }
		    	});	
				
				$( "#dialogModificaUtente" ).dialog({   
					title: "Modifica Utente",
					modal: true,
				    autoOpen: true,			    
				    draggable: false,
				    closeOnEscape: false,
				    height: 515,
				    width: 515,	
				    open: function() {
				    	
				    },
				    show: {
				    	effect: "blind"
				    },
				    hide: {
				    	effect:"blind"
				    },
				    buttons: {
					    "Ok": function() {
			   			    //      
					    },
					    "Annulla": function() {					    	
			   			    $( this ).dialog( "close" );
					    }
				    }
				});
				
			};		
  			
  		</script>
  		
	</head>
	
	<body>
		
		<div class = "dialogForm" id = "dialogModificaUtente"  align = "center">
			
			<p class = "datiValidi"></p>
		
  			<form>
  			
  				<fieldset>    				
    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "nome" type = "text" name = "nome" placeholder = "Nome" value = "<jsp:getProperty name = "utenteSelezionato" property = "nome"/>"/>
    				<input class = "text ui-widget-content ui-corner-all" id = "cognome" type = "text" name = "cognome" placeholder = "Cognome" value = "<jsp:getProperty name = "utenteSelezionato" property = "cognome"/>"/></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "citta" type = "text" name = "citta" placeholder = "Città" value = "<jsp:getProperty name = "utenteSelezionato" property = "citta"/>"/>
    				<input class = "datepicker text ui-widget-content ui-corner-all " id = "nascita" type = "text" name = "nascita"/></p>  
    				<div class = "radio" id = "sesso">
    					<input type = "radio" id = "radioUomo" name = "sesso"/><label for = "radioUomo">Uomo</label>
    					<input type = "radio" id = "radioDonna" name = "sesso" /><label for = "radioDonna">Donna</label>
  					</div>  				  				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "mail" type = "text" name = "mail" placeholder = "Mail"/>  
    				<input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" placeholder = "Username"/>    				
    				<div class = "radio" id = "ruolo">    					
    					<input type = "radio" id = "radioCliente" name = "ruolo" /><label for = "radioCliente">Cliente</label>
    					<input type = "radio" id = "radioVenditore" name = "ruolo" /><label for = "radioVenditore">Venditore</label>
    					<input type = "radio" id = "radioProgettista" name = "ruolo" /><label for = "radioProgettista">Progettista</label>
    					<input type = "radio" id = "radioPromotore" name = "ruolo" /><label for = "radioPromotore">Promotore</label>
    					<input type = "radio" id = "radioAmministratore" name = "ruolo"/><label for = "radioAmministratore">Amministratore</label>
  					</div> 	    				
    				<p><input class = "checkbox" id = "checkGeneraPassword" type = "checkbox" /><label for = "checkGeneraPassword">Genera Password</label></p> 
    				<p><input class = "text ui-widget-content ui-corner-all" id = "password" type = "password" name = "password" placeholder = "Password"/></p>
  				
  				</fieldset>
  				
  			</form>
  			
  			<div class = "dialogMessaggio" id = "dialogMessaggioModificaUtenteSuccesso" title = "Gestione Utenti">
  			<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			L'<b>utente</b> è stato correttamente aggiornato!
  			</p>
		</div>
  			
		</div>
	
	</body>
	
</html>