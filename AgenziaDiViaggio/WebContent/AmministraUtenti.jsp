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
 * @author Giacomo Marciani
 * 
 */
 -->

<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1"%>

<%@ page import = "gestioneutenti.helper.HelperAmministraUtenti" %>
<%@ page import = "gestioneutenti.controller.ControllerAmministraUtenti" %>
<%@ page import = "gestioneutenti.model.ruoli.Ruolo" %>
<%@ page import = "java.util.List" %>
<%@ page import = "gestioneutenti.model.bean.UtenteBean" %>
<%@ page import = "utils.DateUtils" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
				
		<title>Voyager</title>	
		
		<link href = "common/img/favicon.ico" type = "image/x-icon" rel = "icon"/>
		<link href = "common/img/favicon.ico" type = "image/x-icon" rel = "shortcut icon"/>	
			
		<script src = "common/Script/jquery-ui/js/jquery-1.9.1.js" type = "text/javascript"></script>
		<script src = "common/Script/jquery-ui/js/jquery-ui-1.10.3.custom.js" type = "text/javascript"></script>
		<link  href = "common/Script/jquery-ui/css/ui-lightness/jquery-ui-1.10.3.custom.css" type = "text/css" rel = "stylesheet">	
		
		<script src = "common/Script/General.js" type = "text/javascript"></script>
		
		<link href = "common/css/General.css" type = "text/css" rel = "stylesheet">				
	
	</head>
	
	<body>	
	
		<jsp:useBean id = "utenteSelezionato" class = "gestioneutenti.model.bean.UtenteBean" scope = "session"></jsp:useBean>							
				
		<%
				ControllerAmministraUtenti controller = ControllerAmministraUtenti.getInstance();
														
				HelperAmministraUtenti helper = HelperAmministraUtenti.getInstance();
														
				List<UtenteBean> utenti = controller.getUtenti();
														
				String htmlListaUtenti = helper.getListaUtenti(utenti);
		%>	
		
		<%=
			helper.getLogo()
		%>
		
		<div class = "panelAmministrazioneUtenti" id = "panelAmministrazioneUtenti" align = "center">
		
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
			
			<p class = "avviso" id = "avviso"></p>
		
  			<form>
  			
  				<fieldset>    				
    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "nome" type = "text" name = "nome" placeholder = "Nome"/>
    				<input class = "text ui-widget-content ui-corner-all" id = "cognome" type = "text" name = "cognome" placeholder = "Cognome"/></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "citta" type = "text" name = "citta" placeholder = "Città"/>
    				<input class = "datepicker text ui-widget-content ui-corner-all " id = "nascita" type = "text" name = "nascita" value = "1990/01/01"/></p>  
    				<div class = "radio" id = "sesso">
    					<input type = "radio" id = "uomo" name = "sesso" value = "Uomo" checked = "checked"/><label for = "uomo">Uomo</label>
    					<input type = "radio" id = "donna" name = "sesso" value = "Donna"/><label for = "donna">Donna</label>
  					</div>  				  				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "mail" type = "text" name = "mail" placeholder = "Mail"/>  
    				<input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" placeholder = "Username"/>    				
    				<div class = "radio" id = "ruolo">					
    					<input type = "radio" id = "cliente" name = "ruolo" value = "<%=Ruolo.CLIENTE%>" checked = "checked"/><label for = "cliente">Cliente</label>
    					<input type = "radio" id = "venditore" name = "ruolo" value = "<%=Ruolo.VENDITORE%>"/><label for = "venditore">Venditore</label>
    					<input type = "radio" id = "progettista" name = "ruolo" value = "<%=Ruolo.PROGETTISTA%>"/><label for = "progettista">Progettista</label>
    					<input type = "radio" id = "promotore" name = "ruolo" value = "<%=Ruolo.PROMOTORE%>"/><label for = "promotore">Promotore</label>
    					<input type = "radio" id = "amministratore" name = "ruolo" value = "<%=Ruolo.AMMINISTRATORE%>"/><label for = "amministratore">Amministratore</label>
  					</div> 	    				
    				<p><input class = "checkbox" id = "generaPassword" type = "checkbox" /><label for = "generaPassword">Genera Password</label></p> 
    				<p><input class = "text ui-widget-content ui-corner-all" id = "password" type = "password" name = "password" placeholder = "Password"/></p>
  				
  				</fieldset>
  				
  			</form>
  			
		</div>
		 
		<div class = "dialogForm" id = "dialogModificaUtente"  align = "center">
			
			<p class = "avviso" id = "avviso"></p>
		
  			<form>
  			
  				<fieldset>    				
    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "nome" type = "text" name = "nome" placeholder = "Nome"/>
    				<input class = "text ui-widget-content ui-corner-all" id = "cognome" type = "text" name = "cognome" placeholder = "Cognome"/></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "citta" type = "text" name = "citta" placeholder = "Città"/>
    				<input class = "datepicker text ui-widget-content ui-corner-all " id = "nascita" type = "text" name = "nascita"/></p>  
    				<div class = "radio" id = "sesso">
    					<input type = "radio" id = "uomo" name = "sesso" value = "Uomo"/><label for = "uomo">Uomo</label>
    					<input type = "radio" id = "donna" name = "sesso" value = "Donna"/><label for = "donna">Donna</label>
  					</div>  				  				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "mail" type = "text" name = "mail" placeholder = "Mail""/>  
    				<input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" placeholder = "Username"/>    				
    				<div class = "radio" id = "ruolo">    					
    					<input type = "radio" id = "cliente" name = "ruolo" value = "<%=Ruolo.CLIENTE%>"/><label for = "cliente">Cliente</label>
    					<input type = "radio" id = "venditore" name = "ruolo" value = "<%=Ruolo.VENDITORE%>"/><label for = "venditore">Venditore</label>
    					<input type = "radio" id = "progettista" name = "ruolo" value = "<%=Ruolo.PROGETTISTA%>"/><label for = "progettista">Progettista</label>
    					<input type = "radio" id = "promotore" name = "ruolo" value = "<%=Ruolo.PROMOTORE%>"/><label for = "promotore">Promotore</label>
    					<input type = "radio" id = "amministratore" name = "ruolo" value = "<%=Ruolo.AMMINISTRATORE%>"/><label for = "amministratore">Amministratore</label>
  					</div> 	    				
    				<p><input class = "checkbox" id = "generaPassword" type = "checkbox" /><label for = "generaPassword">Genera Password</label></p> 
    				<p><input class = "text ui-widget-content ui-corner-all" id = "password" type = "password" name = "password" placeholder = "Password"/></p>
  				
  				</fieldset>
  				
  			</form>  			
  			
		</div>
		
		<div class = "dialogMessaggioReload" id = "dialogMessaggioNuovoUtenteSuccesso" title = "Gestione Utenti">
  			<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Il <b>nuovo utente</b> è stato correttamente creato!
  			</p>
		</div>	
		
		<div class = "dialogMessaggioReload" id = "dialogMessaggioModificaUtenteSuccesso" title = "Gestione Utenti">
  				<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			L'<b>utente</b> è stato correttamente aggiornato!
  				</p>
		</div>
		
		<div class = "dialogMessaggioReload" id = "dialogMessaggioRimuoviUtenteSuccesso" title = "Gestione Utenti">
  				<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			L'<b>utente</b> è stato correttamente rimosso!
  				</p>
		</div>
		
		<script>
		
			function animazioneApertura() {
				
				var panelLogo = $( "#panelLogo" );
				var panelAmministrazioneUtenti = $( "#panelAmministrazioneUtenti" );
				
				panelLogo.hide();
				panelAmministrazioneUtenti.hide();
				
				panelLogo.show( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
					panelAmministrazioneUtenti.show( "drop", {direction : "left", easing: "swing"} );
				});	
				
			}
			
			function animazioneChiusura() {
				
				var panelLogo = $( "#panelLogo" );
				var panelAmministrazioneUtenti = $( "#panelAmministrazioneUtenti" );
				
				panelAmministrazioneUtenti.hide( "drop", {direction : "left", easing: "swing"}, function() {
					panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500});
				});
				
			}
		
			$(window).load(function() {
				
				animazioneApertura();
				
			});
			
			function goTo(address) {
				
				var panelLogo = $( "#panelLogo" );
				var panelAmministrazioneUtenti = $( "#panelAmministrazioneUtenti" );
				
				panelAmministrazioneUtenti.hide( "drop", {direction : "left", easing: "swing"}, function() {
					panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
						window.location = address;
					});
				});
				
			}		
		
			window.onload = function() {	
								
				var usernameUtenteSelezionato = null;
				
				$( "#panelLogo" ).click(function() {
					goTo("http://localhost:8080/WebVoyager/Home.jsp");
				});
				
				$( "#buttonNuovo" ).click(function() {
					$( "#dialogNuovoUtente" ).dialog( "open" );
			        return false;
			    });
				
				$( "#buttonModifica" ).click(function() {
					$.post("http://localhost:8080/WebVoyager/GetUtente", {username : usernameUtenteSelezionato});
					$( "#dialogModificaUtente" ).dialog( "open" );
			        return false;
			    });
				
				$( "#buttonRimuovi" ).click(function() {
					$.post("http://localhost:8080/WebVoyager/RimuoviUtente", {username : usernameUtenteSelezionato})
					.always(function() {
						$( "#dialogMessaggioRimuoviUtenteSuccesso" ).dialog( "open" );
					});
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
				        });
				      }
				});
				
			};	
		
  			$(function() {	
				
				var nome = $( "#dialogNuovoUtente #nome" );
		    	var cognome = $( "#dialogNuovoUtente #cognome" );
		    	var citta = $( "#dialogNuovoUtente #citta" );
		    	var nascita = $( "#dialogNuovoUtente #nascita" );
		    	var sesso = $( "#dialogNuovoUtente #sesso" );
		    	var mail = $( "#dialogNuovoUtente #mail" );
		    	var username = $( "#dialogNuovoUtente #username" );
		    	var ruolo = $( "#dialogNuovoUtente #ruolo" );
		    	var generaPassword = $( "#dialogNuovoUtente #generaPassword" );
		    	var password = $( "#dialogNuovoUtente #password" );
		    	
		    	var avviso = $( "#dialogNuovoUtente #avviso" );
		    	
		    	var campi = $( [] )
		    	.add(nome).add(cognome).add(citta).add(nascita).add(mail).add(username).add(password);
		    	
		    	function inizializzaCampi() {
		    		nome.val("");
		    		cognome.val("");
		    		citta.val("");
		    		nascita.datepicker("setDate", new Date(1990, 01, 1));
		    		$( "#dialogNuovoUtente #sesso #uomo" ).prop("checked", true);
		    		sesso.buttonset("refresh");
		    		mail.val("");
		    		username.val("");
		    		$( "#dialogNuovoUtente #ruolo #cliente" ).prop("checked", true);
		    		ruolo.buttonset("refresh");
		    		generaPassword.prop("checked", false).button("refresh");
		    		password.val("");
		    		
		    		campi.removeClass( "ui-state-error" );
			    	avviso.text( "" ).removeClass( "ui-state-highlight" );
		    	}
		    		    	
		    	generaPassword.change(function(){
		    	    if ($( this ).is( ":checked" )){
		    	    	password.val("Password generata");
		    	    	password.prop("disabled", true);
		    	    } else {
		    	    	password.val("");
		    	    	password.prop("disabled", false);
		    	    }
		    	});
		    	
		    	function aggiornaAvviso( string ) {
		    	      avviso.text( string ).addClass( "ui-state-highlight" );
		    	}
		    	
		    	function controllaLunghezzaCampo( target, nomeCampo, min, max ) {
		    	      if ( target.val().length > max || target.val().length < min ) {
		    	    	  	target.addClass( "ui-state-error" );
		    	        	aggiornaAvviso( "La lunghezza di " + nomeCampo + " deve essere compresa tra " + min + " e " + max + "." );
		    	        	return false;
		    	      } else {		    	    	  
		    	        return true;
		    	      }
		    	}
		    	
		    	function controllaEspressioneRegolare(target, nomeCampo, regexp) {
		    		if(!regexp.test(target.value())) {
		    			target.addClass( "ui-state-error" );
		    			aggiornaAvviso("Campo " + nomeCampo + "non valido.");
		    			return false;
		    		} else {
		    			return true;
		    		}
		    	}
		    	
		    	function inserisciNuovoUtente() {
		    		
		    		$.post("http://localhost:8080/WebVoyager/NuovoUtente", {
		    			nome : nome.val(),
		    			cognome : cognome.val(),
		    			citta : citta.val(),
		    			nascita : nascita.val(),
		    			sesso : $( "#dialogNuovoUtente #sesso :radio:checked" ).attr("value"),
		    			mail : mail.val(),
		    			ruolo : $( "#dialogNuovoUtente #ruolo :radio:checked" ).attr("value"),
		    			username : username.val(),
		    			password : password.val()});
		    	}
				
				$( "#dialogNuovoUtente" ).dialog({   
					title: "Nuovo Utente",
					modal: true,
				    autoOpen: false,			    
				    draggable: false,
				    closeOnEscape: true,
				    height: 515,
				    width: 515,	
				    open: function() {
				    	inizializzaCampi();
				    },
				    close: function() {
				    	inizializzaCampi();
				    },
				    show: {
				    	effect: "blind"
				    },
				    hide: {
				    	effect:"blind"
				    },
				    buttons: {
					    "Ok": function() { 
					    	
					    	campi.removeClass( "ui-state-error" );
					    	
					    	var regexpGenerale = /^[a-z]([0-9a-z_])+$/i;
					    	//var regexpData = /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/i;
					    	//var regexpMail = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
					    					    	
					    	var campiValidi = true;
					    	
					    	campiValidi = campiValidi && controllaLunghezzaCampo(nome, "nome", 1, 20 ) /*&& controllaEspressioneRegolare(nome, "nome", regexpGenerale)*/;
					    	campiValidi = campiValidi && controllaLunghezzaCampo(cognome, "cognome", 1, 20 ) /*&& controllaEspressioneRegolare(cognome, "cognome", regexpGenerale)*/;
					    	campiValidi = campiValidi && controllaLunghezzaCampo(citta, "città", 1, 20 ) /*&& controllaEspressioneRegolare(citta, "città", regexpGenerale)*/;
					    	//campiValidi = campiValidi && controllaLunghezzaCampo($( "#dialogNuovoUtente #nascita", "nascita", 1, 20 )) && controllaEspressioneRegolare("#dialogNuovoUtente #nascita", "nascita", regexpData);
					    	campiValidi = campiValidi && controllaLunghezzaCampo( mail, "mail", 6, 80 ) /*&& controllaEspressioneRegolare("#dialogNuovoUtente #mail", "mail", regexpMail)*/;
					    	campiValidi = campiValidi && controllaLunghezzaCampo(username, "username", 5, 20 ) /*&& controllaEspressioneRegolare(username, "username", regexpGenerale)*/;
					    	campiValidi = campiValidi && controllaLunghezzaCampo(password, "password", 5, 20 ) /*&& controllaEspressioneRegolare(password, "password", regexpGenerale)*/;

					    	if(campiValidi) {
					    		inserisciNuovoUtente();	
					    		$( "#dialogMessaggioNuovoUtenteSuccesso" ).dialog( "open" );
					    		$( this ).dialog( "close" );
					    	}
					    },
					    "Annulla": function() {					    	
			   			    $( this ).dialog( "close" );
					    }
				    }
				});
				
			});	
		
  			$(function() {	
				
				var mNome = $( "#dialogModificaUtente #nome" );
		    	var mCognome = $( "#dialogModificaUtente #cognome" );
		    	var mCitta = $( "#dialogModificaUtente #citta" );
		    	var mNascita = $( "#dialogModificaUtente #nascita" );
		    	var mSesso = $( "#dialogModificaUtente #sesso" );
		    	var mMail = $( "#dialogModificaUtente #mail" );
		    	var mUsername = $( "#dialogModificaUtente #username" );
		    	var mRuolo = $( "#dialogModificaUtente #ruolo" );
		    	var mGeneraPassword = $( "#dialogModificaUtente #generaPassword" );
		    	var mPassword = $( "#dialogModificaUtente #password" );
		    	
		    	var mAvviso = $( "#dialogModificaUtente #avviso" );
		    	
		    	var mCampi = $( [] )
		    	.add(mNome).add(mCognome).add(mCitta).add(mNascita).add(mMail).add(mUsername).add(mPassword);
		    	
		    	function inizializzaCampi() {		    		
		    		mCampi.removeClass( "ui-state-error" );		    		
			    	mAvviso.text( "" ).removeClass( "ui-state-highlight" );
		    	}		    	
		    	
		    	function aggiornaAvviso( string ) {
		    	      mAvviso.text( string ).addClass( "ui-state-highlight" );
		    	}
		    	
		    	function controllaLunghezzaCampo( target, nomeCampo, min, max ) {
		    	      if ( target.val().length > max || target.val().length < min ) {
		    	    	  	target.addClass( "ui-state-error" );
		    	        	aggiornaAvviso( "La lunghezza di " + nomeCampo + " deve essere compresa tra " + min + " e " + max + "." );
		    	        	return false;
		    	      } else {		    	    	  
		    	        return true;
		    	      }
		    	}
		    	
		    	function controllaEspressioneRegolare(target, nomeCampo, regexp) {
		    		if(!regexp.test(target.value())) {
		    			target.addClass( "ui-state-error" );
		    			aggiornaAvviso("Campo " + nomeCampo + "non valido.");
		    			return false;
		    		} else {
		    			return true;
		    		}
		    	}
		    	
		    	mGeneraPassword.change(function(){
		    	    if ($( this ).is( ":checked" )){
		    	    	mPassword.val("Password generata");
		    	    	mPassword.prop("readonly", true);
		    	    } else {
		    	    	mPassword.val("");
		    	    	mPassword.prop("readonly", false);
		    	    }
		    	});
		    	
		    	function modificaUtente() {
		    		
		    		$.post("http://localhost:8080/WebVoyager/ModificaUtente", {
		    			nome : mNome.val(),
		    			cognome : mCognome.val(),
		    			citta : mCitta.val(),
		    			nascita : mNascita.val(),
		    			sesso : $( "#dialogModificaUtente #sesso :radio:checked" ).attr("value"),
		    			mail : mMail.val(),
		    			ruolo : $( "#dialogModificaUtente #ruolo :radio:checked" ).attr("value"),
		    			username : mUsername.val(),
		    			password : mPassword.val()});
		    	}
				
				$( "#dialogModificaUtente" ).dialog({   
					title: "Modifica Utente",
					modal: true,
				    autoOpen: false,			    
				    draggable: false,
				    resizable: false,
				    closeOnEscape: true,
				    height: 515,
				    width: 515,	
				    open: function() {				    	
				    	$( this ).load("ModificaUtente.jsp #dialogModificaUtente");	
				    	inizializzaCampi();
				    },
				    show: {
				    	effect: "blind"
				    },
				    hide: {
				    	effect:"blind"
				    },
				    buttons: {
					    "Ok": function() { 
					    	
					    	mCampi.removeClass( "ui-state-error" );
					    	
					    	var regexpGenerale = /^[a-z]([0-9a-z_])+$/i;
					    	//var regexpData = /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/i;
					    	//var regexpMail = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
					    					    	
					    	var campiValidi = true;
					    	
					    	campiValidi = campiValidi && controllaLunghezzaCampo(nome, "nome", 1, 20 ) /*&& controllaEspressioneRegolare(nome, "nome", regexpGenerale)*/;
					    	campiValidi = campiValidi && controllaLunghezzaCampo(cognome, "cognome", 1, 20 ) /*&& controllaEspressioneRegolare(cognome, "cognome", regexpGenerale)*/;
					    	campiValidi = campiValidi && controllaLunghezzaCampo(citta, "città", 1, 20 ) /*&& controllaEspressioneRegolare(citta, "città", regexpGenerale)*/;
					    	//campiValidi = campiValidi && controllaLunghezzaCampo($( "#dialogNuovoUtente #nascita", "nascita", 1, 20 )) && controllaEspressioneRegolare("#dialogNuovoUtente #nascita", "nascita", regexpData);
					    	campiValidi = campiValidi && controllaLunghezzaCampo( mail, "mail", 6, 80 ) /*&& controllaEspressioneRegolare("#dialogNuovoUtente #mail", "mail", regexpMail)*/;
					    	campiValidi = campiValidi && controllaLunghezzaCampo(username, "username", 5, 20 ) /*&& controllaEspressioneRegolare(username, "username", regexpGenerale)*/;
					    	campiValidi = campiValidi && controllaLunghezzaCampo(password, "password", 5, 20 ) /*&& controllaEspressioneRegolare(password, "password", regexpGenerale)*/;

					    	if(campiValidi) {
					    		modificaUtente();	
					    		$( "#dialogMessaggioModificaUtenteSuccesso" ).dialog( "open" );
					    		$( this ).dialog( "close" );
					    	}
					    },
					    "Annulla": function() {					    	
			   			    $( this ).dialog( "close" );
					    }
				    }
				});
				
			});		
  			
  		</script>
	
	</body>

</html>