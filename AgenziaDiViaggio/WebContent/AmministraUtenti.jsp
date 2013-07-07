<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent
 * 
 * @name AmministraUtenti.jsp
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */
 -->

<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1"%>

<%@ include file = "common/Head.jsp" %>
<%@ page import = "gestioneutenti.helper.HelperAmministraUtenti" %>

<%@ page import = "java.util.List" %>
<%@ page import = "java.util.Calendar" %>
<%@ page import = "gestioneutenti.model.bean.UtenteBean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
		
		<link href = "common/css/AmministraUtenti.css" type = "text/css" rel = "stylesheet">	
		
		<jsp:useBean id = "utenteSelezionato" class = "gestioneutenti.model.bean.UtenteBean" scope = "session"></jsp:useBean>
		
		
		<%
			HelperAmministraUtenti helper = HelperAmministraUtenti.getInstance();
			List<UtenteBean> utenti = (List<UtenteBean>) session.getAttribute("utenti");
			String htmlListaUtenti = helper.getListaUtenti(utenti);
		%>					
				
		<script>
		
			window.onload = function() {	
				
				var usernameUtenteSelezionato = null;
		    	
		    	function inizializzaCampiVuoti() {
		    		$( "#dialogNuovoUtente #nome" ).val("");
		    		$( "#dialogNuovoUtente #cognome" ).val("");
		    		$( "#dialogNuovoUtente #citta" ).val("");
		    		$( "#dialogNuovoUtente #nascita" ).datepicker("setDate", new Date(1990, 01, 1));
		    		$( "#dialogNuovoUtente #sesso input" ).removeAttr("checked");
		    		$( "#dialogNuovoUtente #sesso" ).buttonset("refresh");
		    		$( "#dialogNuovoUtente #mail" ).val("");
		    		$( "#dialogNuovoUtente #username" ).val("");
		    		$( "#dialogNuovoUtente #ruolo input" ).removeAttr("checked");
		    		$( "#dialogNuovoUtente #ruolo" ).buttonset("refresh");
		    		$( "#dialogNuovoUtente #checkGeneraPassword" ).removeAttr("checked").button("refresh");
		    		$( "#dialogNuovoUtente #password" ).val("");
		    	}
		    	
		    	$( "#dialogNuovoUtente #checkGeneraPassword" ).change(function(){
		    	    if ($( this ).is( ":checked" )){
		    	    	$( "#dialogNuovoUtente #password" ).val("Password generata");
		    	        $( "#dialogNuovoUtente #password" ).prop("disabled", true);
		    	    } else {
		    	    	$( "#dialogNuovoUtente #password" ).val("");
		    	        $( "#dialogNuovoUtente #password" ).prop("disabled", false);
		    	    }
		    	});
		    	
		    	$( "#dialogModificaUtente #checkGeneraPassword" ).change(function(){
		    	    if ($( this ).is( ":checked" )){
		    	    	$( "#dialogModificaUtente #password" ).val("Password generata");
		    	        $( "#dialogModificaUtente #password" ).prop("disabled", true);
		    	    } else {
		    	    	$( "#dialogModificaUtente #password" ).val("");
		    	        $( "#dialogModificaUtente #password" ).prop("disabled", false);
		    	    }
		    	});		    	
				
				$( "#dialogNuovoUtente" ).dialog({   
					title: "Nuovo Utente",
					modal: true,
				    autoOpen: false,			    
				    draggable: false,
				    closeOnEscape: true,
				    height: 515,
				    width: 515,	
				    open: function() {
				    	inizializzaCampiVuoti();
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
				
				$( "#buttonNuovo" ).click(function() {
			        $( "#dialogNuovoUtente" ).dialog( "open" );
			        return false;
			    });
				
				$( "#buttonModifica" ).click(function() {
					$.post("http://localhost:8080/WebVoyager/AmministraUtenti", {action: 2, username : usernameUtenteSelezionato});
					
			        return false;
			    });
				
				$( "#buttonRimuovi" ).click(function() {
					alert("rimuovi");
					return false;
			    });
				
				$( "#buttonCerca" ).click(function() {
					alert("cerca");
					return false;
			    });
				
				$( "#selectable" ).selectable({
				      stop: function() {
				    	    $( "#buttonModifica" ).button( "enable" );
				        	$( "#buttonRimuovi" ).button( "enable" );
				        $( ".ui-selected", this ).each(function() {				        	
				         	var index = $( "#selectable li" ).index( this );
				          	usernameUtenteSelezionato = $( this ).text();
				          	//getUtenteSelezionato(usernameUtenteSelezionato);
				        });
				      }
				});
				
				function getUtenteSelezionato(usernameUtenteSelezionato) {
					alert(usernameUtenteSelezionato);
					$.post("http://localhost:8080/WebVoyager/GetUtente", {username : usernameUtenteSelezionato})
	   			    .done(function() {
	   			    	alert("done");
	   			    })
	   			    .fail(function() {
	   			    	alert("failed");
	   			    });
				}
				
			};		
  			
  		</script>
	
	</head>
	
	<body>
		
		<%=
			helper.getLogo()
		%>
		
		<div class = "panelAmministrazioneUtenti" align = "center">
		
			<p class = "title">AMMINISTRAZIONE UTENTI</p>
			
			<div class = panelButton id = "panelButton" align = "center">
				
				<p><input class = "text ui-widget-content ui-corner-all" id = "cerca" type = "text" placeholder = "Cerca" />
				<button class = "buttonSearch buttonIconLabel" id = "buttonCerca" type = "button">Cerca</button>
				<button class = "buttonAdd buttonIconLabel" id = "buttonNuovo" type = "button">Nuovo</button>
				<button class = "buttonEdit buttonIconLabel" id = "buttonModifica" type = "button" disabled>Modifica</button>
				<button class = "buttonRemove buttonIconLabel" id = "buttonRimuovi" type = "button" disabled>Rimuovi</button></p>	
			
			</div>	
			
			<div class = "items" align = "center">
			
				<ol id = "selectable">
		
					<%= 
						htmlListaUtenti
					%>
					
				</ol>	
						
			</div>		
					
		</div>
		  
		<div class = "dialogForm" id = "dialogNuovoUtente"  align = "center">
			
			<p class = "datiValidi"></p>
		
  			<form>
  			
  				<fieldset>    				
    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "nome" type = "text" name = "nome" placeholder = "Nome"/>
    				<input class = "text ui-widget-content ui-corner-all" id = "cognome" type = "text" name = "cognome" placeholder = "Cognome"/></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "citta" type = "text" name = "citta" placeholder = "Città"/>
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
  			
		</div>		
		
		<div class = "dialogMessaggio" id = "dialogMessaggioNuovoUtenteSuccesso" title = "Gestione Utenti">
  			<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Il <b>nuovo utente</b> è stato correttamente creato!
  			</p>
		</div>	
		
		<!--  
		
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
  			
		</div>		
		
		<div class = "dialogMessaggio" id = "dialogMessaggioModificaUtenteSuccesso" title = "Gestione Utenti">
  			<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			L'<b>utente</b> è stato correttamente aggiornato!
  			</p>
		</div>-->
	
	</body>

</html>