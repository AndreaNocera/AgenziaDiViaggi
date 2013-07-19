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

<%@ page import = "webvoyager.marciani.model.bean.UtenteBean" %>
<%@ page import = "webvoyager.marciani.model.ruoli.Ruolo" %>
<%@ page import = "webvoyager.marciani.helper.TemplateViewer" %>

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
		
		<script src = "common/Script/Voyager.js" type = "text/javascript"></script>		
		<link href = "common/css/Voyager.css" type = "text/css" rel = "stylesheet">				
	
	</head>
	
	<body>								
				
		<%
			TemplateViewer templateViewer = TemplateViewer.getInstance();
		%>	
		
		<div class = "panelLogo" id = "panelLogo" align = "center">
		
			<%=
					templateViewer.getLogo()
			%>
			
		</div>		
		
		<div class = "panelAmministrazioneUtenti" id = "panelAmministrazioneUtenti" align = "center">
		
			<p class = "title">AMMINISTRAZIONE UTENTI</p>
			
			<div class = panelButton id = "panelButton" align = "center">
				
				<p><input class = "text ui-widget-content ui-corner-all" id = "cerca" type = "text" placeholder = "Cerca" />
				<button class = "buttonAdd buttonIconLabel" id = "buttonNuovo" type = "button">Nuovo</button>
				<button class = "buttonEdit buttonIconLabel" id = "buttonModifica" type = "button" disabled>Modifica</button>
				<button class = "buttonRemove buttonIconLabel" id = "buttonRimuovi" type = "button" disabled>Rimuovi</button></p>	
			
			</div>	
			
			<div class = "items" align = "center">
			
				<ol id = "selectable">
					
				</ol>	
						
			</div>		
					
		</div>		
		  
		<div class = "dialog dialogForm popup" id = "dialogNuovoUtente"  align = "center">
			
			<p class = "avviso" id = "avviso"></p>
		
  			<form>
  			
  				<fieldset class = "ui-dialog-content">    				
    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "nome" type = "text" name = "nome" placeholder = "Nome"/>
    				<input class = "text ui-widget-content ui-corner-all" id = "cognome" type = "text" name = "cognome" placeholder = "Cognome"/></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "citta" type = "text" name = "citta" placeholder = "Città"/>
    				<input class = "datepicker text ui-widget-content ui-corner-all " id = "nascita" type = "text" name = "nascita"/></p>  
    				<div class = "radio" id = "sesso">
    					<input type = "radio" id = "uomo" name = "sesso" value = "Uomo"/><label for = "uomo">Uomo</label>
    					<input type = "radio" id = "donna" name = "sesso" value = "Donna"/><label for = "donna">Donna</label>
  					</div>  				  				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "mail" type = "text" name = "mail" placeholder = "Mail"/>  
    				<input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" placeholder = "Username"/>    				
    				<div class = "radio" id = "ruolo">					
    					<input type = "radio" id = "cliente" name = "ruolo" value = "4"/><label for = "cliente">Cliente</label>
    					<input type = "radio" id = "venditore" name = "ruolo" value = "3"/><label for = "venditore">Venditore</label>
    					<input type = "radio" id = "progettista" name = "ruolo" value = "2"/><label for = "progettista">Progettista</label>
    					<input type = "radio" id = "promotore" name = "ruolo" value = "1"/><label for = "promotore">Promotore</label>
    					<input type = "radio" id = "amministratore" name = "ruolo" value = "0"/><label for = "amministratore">Amministratore</label>
  					</div> 	    				
    				<p><button class = "button" id = "buttonGeneraPassword" type = "button">Genera Password</button></p> 
    				<p><input class = "text ui-widget-content ui-corner-all" id = "password" type = "password" name = "password" placeholder = "Password"/></p>
  				
  				</fieldset>
  				
  			</form>
  			
		</div>		
		 
		<div class = "dialog dialogForm popup" id = "dialogModificaUtente"  align = "center">
			
			<p class = "avviso" id = "avviso"></p>
		
  			<form>
  			
  				<fieldset class = "ui-dialog-content">    				
    				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "nome" type = "text" name = "nome" placeholder = "Nome"/>
    				<input class = "text ui-widget-content ui-corner-all" id = "cognome" type = "text" name = "cognome" placeholder = "Cognome"/></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "citta" type = "text" name = "citta" placeholder = "Città"/>
    				<input class = "datepicker text ui-widget-content ui-corner-all " id = "nascita" type = "text" name = "nascita"/></p>  
    				<div class = "radio" id = "sesso">
    					<input type = "radio" id = "uomo" name = "sesso" value = "Uomo"/><label for = "uomo">Uomo</label>
    					<input type = "radio" id = "donna" name = "sesso" value = "Donna"/><label for = "donna">Donna</label>
  					</div>  				  				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "mail" type = "text" name = "mail" placeholder = "Mail"/>  
    				<input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" placeholder = "Username"/>    				
    				<div class = "radio" id = "ruolo">    					
    					<input type = "radio" id = "cliente" name = "ruolo" value = "4"/><label for = "cliente">Cliente</label>
    					<input type = "radio" id = "venditore" name = "ruolo" value = "3"/><label for = "venditore">Venditore</label>
    					<input type = "radio" id = "progettista" name = "ruolo" value = "2"/><label for = "progettista">Progettista</label>
    					<input type = "radio" id = "promotore" name = "ruolo" value = "1"/><label for = "promotore">Promotore</label>
    					<input type = "radio" id = "amministratore" name = "ruolo" value = "0"/><label for = "amministratore">Amministratore</label>
  					</div> 	    				
    				<p><button class = "button" id = "buttonGeneraPassword" type = "button">Genera Password</button></p> 
    				<p><input class = "text ui-widget-content ui-corner-all" id = "password" type = "password" name = "password" placeholder = "Password"/></p>
  				
  				</fieldset>
  				
  			</form>  			
  			
		</div>	
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioSuccessoNuovoUtente" title = "Amministrazione Utenti">
  			<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Il <b>nuovo utente</b> è stato correttamente creato!
  			</p>
		</div>	
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioFallimentoNuovoUtente" title = "Amministrazione Utenti">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			<b>Oops!</b> Qualcosa è andato storto durante la creazione del nuovo utente! Riprova più tardi.
  			</p>
		</div>
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioSuccessoModificaUtente" title = "Amministrazione Utenti">
  				<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			L'<b>utente</b> è stato correttamente aggiornato!
  				</p>
		</div>
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioFallimentoModificaUtente" title = "Amministrazione Utenti">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			<b>Oops!</b> Qualcosa è andato storto durante la modifica dell'utente! Riprova più tardi.
  			</p>
		</div>	
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioSuccessoRimuoviUtente" title = "Amministrazione Utenti">
  				<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			L'<b>utente</b> è stato correttamente rimosso!
  				</p>
		</div>
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioFallimentoRimuoviUtente" title = "Amministrazione Utenti">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			<b>Oops!</b> Qualcosa è andato storto durante la rimozione dell'utente! Riprova più tardi.
  			</p>
		</div>
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioFallimentoCaricaUtenti" title = "Amministrazione Utenti">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			<b>Oops!</b> Qualcosa è andato storto durante il caricamento degli utenti! Riprova più tardi.
  			</p>
		</div>		
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioFallimentoGeneraPassword" title = "Amministrazione Utenti">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			<b>Oops!</b> Qualcosa è andato storto durante la generazione della password! Riprova più tardi.
  			</p>
		</div>
		
		<script>
		
			var panelLogo = $( "#panelLogo" );
			var panelAmministrazioneUtenti = $( "#panelAmministrazioneUtenti" );
			
			var cerca = $( "#panelButton #cerca" );
			var buttonNuovo = $( "#panelButton #buttonNuovo" );
			var buttonModifica = $( "#panelButton #buttonModifica" );
			var buttonRimuovi = $( "#panelButton #buttonRimuovi" );
			
			var dialogNuovoUtente = $( "#dialogNuovoUtente" );
			var dialogModificaUtente = $( "#dialogModificaUtente" );
			
			var messaggioFallimentoCaricaUtenti = $( "#messaggioFallimentoCaricaUtenti" );		
			
			var usernameUtenteSelezionato = null;
			
			function animazioneApertura() {				
				panelLogo.hide();
				panelAmministrazioneUtenti.hide();
				
				panelLogo.show( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
					panelAmministrazioneUtenti.show( "drop", {direction : "left", easing: "swing"} );
				});					
			}
			
			function animazioneChiusura() {				
				panelAmministrazioneUtenti.hide( "drop", {direction : "left", easing: "swing"}, function() {
					panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500});
				});				
			}
			
			function creaListaUtenti() {
				$( "#selectable" ).selectable({
			        stop: function() {
			    	    buttonModifica.button( "enable" );
			        	buttonRimuovi.button( "enable" );
			        $( ".ui-selected", this ).each(function() {			
			          	usernameUtenteSelezionato = $( this ).text();
			        });
			      }
				});
			}
			
			function caricaListaUtenti() {
				var mQuery = cerca.val();
				$.post("http://localhost:8080/WebVoyager/GetUtenti", {query : mQuery})
				.done(function(data) {
					$( "#selectable" ).empty();
					var listaUsername = data.split(":");
					for (var count in listaUsername) {
						var username = listaUsername[count];
						if ( username == usernameUtenteSelezionato ) {
							$( "#selectable" ).append( "<li class = \"ui-widget-content ui-selected\">" + username + "</li>" );
						} else {
							$( "#selectable" ).append( "<li class = \"ui-widget-content\">" + username + "</li>" );
						}						
					}
				})
				.fail(function(data) {
					messaggioFallimentoCaricaUtenti.dialog( "open" );
				});
			}							
		
			$(window).load(function() {				
				animazioneApertura();
				creaListaUtenti();
				caricaListaUtenti();
				setInterval(function() {
					caricaListaUtenti();
				}, 5000);
			});			
			
			function goTo(address) {				
				panelAmministrazioneUtenti.hide( "drop", {direction : "left", easing: "swing"}, function() {
					panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
						window.location = address;
					});
				});				
			}	
			
			panelLogo.click(function() {
				goTo("http://localhost:8080/WebVoyager/Home.jsp");
			});		
			
			$(function() {
				
				cerca.keyup(function() {
					caricaListaUtenti();
				});
				
			});
			
			$(function() {
				
				var nome = $( "#dialogNuovoUtente #nome" );
		    	var cognome = $( "#dialogNuovoUtente #cognome" );
		    	var citta = $( "#dialogNuovoUtente #citta" );
		    	var nascita = $( "#dialogNuovoUtente #nascita" );
		    	var sesso = $( "#dialogNuovoUtente #sesso" );
		    	var mail = $( "#dialogNuovoUtente #mail" );
		    	var username = $( "#dialogNuovoUtente #username" );
		    	var ruolo = $( "#dialogNuovoUtente #ruolo" );
		    	var buttonGeneraPassword = $( "#dialogNuovoUtente #buttonGeneraPassword" );
		    	var password = $( "#dialogNuovoUtente #password" );		    	
		    	
		    	var campi = $( [] )
		    	.add(nome).add(cognome).add(citta).add(nascita).add(mail).add(username).add(password);
		    	
		    	var avviso = $( "#dialogNuovoUtente #avviso" );
		    	
		    	var messaggioSuccessoNuovoUtente = $( "#messaggioSuccessoNuovoUtente" );
		    	var messaggioFallimentoNuovoUtente = $( "#messaggioFallimentoNuovoUtente" );
		    	var messaggioFallimentoGeneraPassword = $( "#messaggioFallimentoGeneraPassword" );
		    	
		    	buttonNuovo.click(function() {
					dialogNuovoUtente.dialog( "open" );
			        return false;
			    });		    	
				
				function mostraErroreCampi( targetCampi ) {				
					targetCampi.addClass( "ui-state-error" );				
				}	
				
				function rimuoviErroreCampi( targetCampi ) {				
					targetCampi.removeClass( "ui-state-error" );				
				}
		    	
		    	function aggiornaAvviso( string ) {
		    	      avviso.text( string ).addClass( "ui-state-highlight" );
		    	}
		    	
		    	function rimuoviAvviso() {
		    		avviso.text( "" ).removeClass( "ui-state-highlight" );
		    	}    	
		    	
		    	function controllaLunghezzaCampo( targetCampo, nomeCampo, targetParent, min, max ) {
		    	      if ( targetCampo.val().length > max || targetCampo.val().length < min ) {
		    	    	 	targetCampo.addClass( "ui-state-error" );
		    	    	 	targetParent.effect( "shake" );
		    	        	aggiornaAvviso( "La lunghezza di " + nomeCampo + " deve essere compresa tra " + min + " e " + max + "." );
		    	        	return false;
		    	      } else {		    	    	  
		    	        return true;
		    	      }
		    	}
		    	
		    	function controllaEspressioneRegolare(targetCampo, nomeCampo, targetParent, regexp) {
		    		if(!regexp.test(targetCampo.value())) {
		    			targetCampo.addClass( "ui-state-error" );
		    			targetParent.effect( "shake" );
		    			aggiornaAvviso("Campo " + nomeCampo + " non valido.");
		    			return false;
		    		} else {
		    			return true;
		    		}
		    	}
		    	
		    	function generaPassword() {
					$.post("http://localhost:8080/WebVoyager/GeneraPassword")
					.done(function(data) {
						password.val(data);
					})
					.fail(function(data) {
						messaggioFallimentoGeneraPassword.dialog( "open" );
						return null;
					});
				}
				
				buttonGeneraPassword.click(function(){
					generaPassword();
		    	});
				
				function inizializzaDialog() {
					nome.val("");
					cognome.val("");
					citta.val("");
					nascita.val("01/01/1990");
					$( "#uomo" ).prop("checked", true);
					sesso.buttonset( "refresh" );
					mail.val("");
					username.val("");
					$( "#cliente" ).prop("checked", true);
					ruolo.buttonset( "refresh" );
					password.val("");

		    		rimuoviErroreCampi(campi);
		    		rimuoviAvviso();
		    	}
		    	
		    	function inserisciNuovoUtente() {
		    		//var regexpGenerale = /^[a-z]([0-9a-z_])+$/i;
			    	//var regexpData = /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/i;
			    	//var regexpMail = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
			    					    	
			    	var campiValidi = true;
			    	
			    	campiValidi = campiValidi && controllaLunghezzaCampo(nome, "Nome", dialogNuovoUtente.parent("div"), 1, 20 )/* && controllaEspressioneRegolare(nome, "Nome", dialogNuovoUtente.parent("div"), regexpGenerale)*/;
			    	campiValidi = campiValidi && controllaLunghezzaCampo(cognome, "Cognome", dialogNuovoUtente.parent("div"), 1, 20 )/* && controllaEspressioneRegolare(cognome, "Cognome", dialogNuovoUtente.parent("div"), regexpGenerale)*/;
			    	campiValidi = campiValidi && controllaLunghezzaCampo(citta, "Città", dialogNuovoUtente.parent("div"), 1, 20 )/* && controllaEspressioneRegolare(citta, "Città", dialogNuovoUtente.parent("div"), regexpGenerale)*/;
			    	campiValidi = campiValidi && controllaLunghezzaCampo(nascita, "Nascita", dialogNuovoUtente.parent("div"), 8, 10 ) /*&& controllaEspressioneRegolare(nascita, "Nascita", dialogNuovoUtente.parent("div"), regexpData)*/;
			    	campiValidi = campiValidi && controllaLunghezzaCampo(mail, "Mail", dialogNuovoUtente.parent("div"), 5, 40 ) /*&& controllaEspressioneRegolare(mail, "Mail", dialogNuovoUtente.parent("div"), regexpMail)*/;
			    	campiValidi = campiValidi && controllaLunghezzaCampo(username, "Username", dialogNuovoUtente.parent("div"), 3, 20 )/* && controllaEspressioneRegolare(username, "Username", dialogNuovoUtente.parent("div"), regexpGenerale)*/;
			    	campiValidi = campiValidi && controllaLunghezzaCampo(password, "Password", dialogNuovoUtente.parent("div"), 3, 20 )/* && controllaEspressioneRegolare(password, "Password", dialogNuovoUtente.parent("div"), regexpGenerale)*/;
			    	
			    	if(campiValidi) {
			    		$.post("http://localhost:8080/WebVoyager/NuovoUtente", {
			    			nome : nome.val(),
			    			cognome : cognome.val(),
			    			citta : citta.val(),
			    			nascita : nascita.val(),
			    			sesso : $( "#dialogNuovoUtente #sesso :radio:checked" ).attr("value"),
			    			mail : mail.val(),
			    			ruolo : $( "#dialogNuovoUtente #ruolo :radio:checked" ).attr("value"),
			    			username : username.val(),
			    			password : password.val()})
				    	.done(function() {
				    		messaggioSuccessoNuovoUtente.dialog( "open" );
				    		dialogNuovoUtente.dialog( "close" );
				    	})
				    	.fail(function(data) {
				    		if (data.status == 409) {
		    					mostraErroreCampi(username);
		    					aggiornaAvviso( "Utente esistente." );
		    					dialogNuovoUtente.parent( "div" ).effect( "shake" );
		    				} else {
		    					messaggioFallimentoNuovoUtente.dialog( "open" );
			    				dialogNuovoUtente.dialog( "close" );
		    				}
				    	});
			    	}		    		
				}
		    	
				dialogNuovoUtente.dialog({   
					title: "Nuovo Utente",
					modal: true,
				    autoOpen: false,			    
				    draggable: false,
				    resizable: false,
				    closeOnEscape: true,
				    height: "auto",
				    width: "auto",	
				    open: function() {
				    	inizializzaDialog();
				    },
				    show: {
				    	effect: "blind"
				    },
				    hide: {
				    	effect:"blind"
				    },
				    buttons: {
					    "Ok": function() { 
					    	rimuoviErroreCampi(campi);
					    	rimuoviAvviso();
					    	inserisciNuovoUtente();
					    },
					    "Annulla": function() {					    	
			   			    $( this ).dialog( "close" );
					    }
				    }
				});
				
			});
			
			$(function() {
				
				var nome = $( "#dialogModificaUtente #nome" );
		    	var cognome = $( "#dialogModificaUtente #cognome" );
		    	var citta = $( "#dialogModificaUtente #citta" );
		    	var nascita = $( "#dialogModificaUtente #nascita" );
		    	var sesso = $( "#dialogModificaUtente #sesso" );
		    	var mail = $( "#dialogModificaUtente #mail" );
		    	var username = $( "#dialogModificaUtente #username" );
		    	var ruolo = $( "#dialogModificaUtente #ruolo" );
		    	var buttonGeneraPassword = $( "#dialogModificaUtente #buttonGeneraPassword" );
		    	var password = $( "#dialogModificaUtente #password" );		    	
		    	
		    	var campi = $( [] )
		    	.add(nome).add(cognome).add(citta).add(nascita).add(mail).add(username).add(password);
				
		    	var avviso = $( "#dialogModificaUtente #avviso" );
		    	
				var messaggioSuccessoModificaUtente = $( "#messaggioSuccessoModificaUtente" );
				var messaggioFallimentoModificaUtente = $( "#messaggioFallimentoModificaUtente" );
				var messaggioFallimentoGeneraPassword = $( "#messaggioFallimentoGeneraPassword" );
				
				buttonModifica.click(function() {
					$.post("http://localhost:8080/WebVoyager/GetUtente", {username : usernameUtenteSelezionato});
					dialogModificaUtente.dialog( "open" );
			        return false;
			    });
				
				function mostraErroreCampi( targetCampi ) {				
					targetCampi.addClass( "ui-state-error" );				
				}	
				
				function rimuoviErroreCampi( targetCampi ) {				
					targetCampi.removeClass( "ui-state-error" );				
				}
		    	
		    	function aggiornaAvviso( string ) {
		    	      avviso.text( string ).addClass( "ui-state-highlight" );
		    	}
		    	
		    	function rimuoviAvviso() {
		    		avviso.text( "" ).removeClass( "ui-state-highlight" );
		    	}    	
		    	
		    	function controllaLunghezzaCampo( targetCampo, nomeCampo, targetParent, min, max ) {
		    	      if ( targetCampo.val().length > max || targetCampo.val().length < min ) {
		    	    	 	targetCampo.addClass( "ui-state-error" );
		    	    	 	targetParent.effect( "shake" );
		    	        	aggiornaAvviso( "La lunghezza di " + nomeCampo + " deve essere compresa tra " + min + " e " + max + "." );
		    	        	return false;
		    	      } else {		    	    	  
		    	        return true;
		    	      }
		    	}
		    	
		    	function controllaEspressioneRegolare(targetCampo, nomeCampo, targetParent, regexp) {
		    		if(!regexp.test(targetCampo.value())) {
		    			targetCampo.addClass( "ui-state-error" );
		    			targetParent.effect( "shake" );
		    			aggiornaAvviso("Campo " + nomeCampo + " non valido.");
		    			return false;
		    		} else {
		    			return true;
		    		}
		    	}
		    	
		    	function generaPassword() {
					$.post("http://localhost:8080/WebVoyager/GeneraPassword")
					.done(function(data) {
						password.val(data);
					})
					.fail(function(data) {
						messaggioFallimentoGeneraPassword.dialog( "open" );
						return null;
					});
				}
				
				buttonGeneraPassword.click(function(){
					generaPassword();
		    	});				
				
				function inizializzaDialog() {
					
					var mUsername = usernameUtenteSelezionato;
		    		
		    		$.post("http://localhost:8080/WebVoyager/GetUtente", {username : mUsername})
		    		.done(function(data) {
		    			var jsonData = data.split( ":" );
		    			nome.val(jsonData[0]);
		    			cognome.val(jsonData[1]);
		    			citta.val(jsonData[2]);
		    			nascita.val(jsonData[3]);
		    			$("#sesso #" + jsonData[4].toLowerCase()).prop("checked", true);
		    			sesso.buttonset( "refresh" );
		    			mail.val(jsonData[5]);
		    			username.val(jsonData[6]);	
		    			$("#ruolo #" + jsonData[7].toLowerCase()).prop("checked", true);
		    			ruolo.buttonset( "refresh" );
		    			password.val(jsonData[8]);
		    		})
		    		.fail(function() {
		    			messaggioFallimentoModificaUtente.dialog( "open" );
		    			dialogModificaUtente.dialog( "close" );
		    		});

		    		rimuoviErroreCampi(campi);
		    		rimuoviAvviso();
		    	}
		    	
		    	function modificaUtente() {
		    		
		    		//var regexpGenerale = /^[a-z]([0-9a-z_])+$/i;
			    	//var regexpData = /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/i;
			    	//var regexpMail = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
			    					    	
			    	var campiValidi = true;
			    	
			    	campiValidi = campiValidi && controllaLunghezzaCampo(nome, "Nome", dialogModificaUtente.parent("div"), 1, 20 )/* && controllaEspressioneRegolare(nome, "Nome", dialogModificaUtente.parent("div"), regexpGenerale)*/;
			    	campiValidi = campiValidi && controllaLunghezzaCampo(cognome, "Cognome", dialogModificaUtente.parent("div"), 1, 20 )/* && controllaEspressioneRegolare(cognome, "Cognome", dialogModificaUtente.parent("div"), regexpGenerale)*/;
			    	campiValidi = campiValidi && controllaLunghezzaCampo(citta, "Città", dialogModificaUtente.parent("div"), 1, 20 )/* && controllaEspressioneRegolare(citta, "Città", dialogModificaUtente.parent("div"), regexpGenerale)*/;
			    	campiValidi = campiValidi && controllaLunghezzaCampo(nascita, "Nascita", dialogModificaUtente.parent("div"), 8, 10 ) /*&& controllaEspressioneRegolare(nascita, "Nascita", dialogModificaUtente.parent("div"), regexpData)*/;
			    	campiValidi = campiValidi && controllaLunghezzaCampo(mail, "Mail", dialogModificaUtente.parent("div"), 5, 40 ) /*&& controllaEspressioneRegolare(mail, "Mail", dialogModificaUtente.parent("div"), regexpMail)*/;
			    	campiValidi = campiValidi && controllaLunghezzaCampo(username, "Username", dialogModificaUtente.parent("div"), 3, 20 )/* && controllaEspressioneRegolare(username, "Username", dialogModificaUtente.parent("div"), regexpGenerale)*/;
			    	campiValidi = campiValidi && controllaLunghezzaCampo(password, "Password", dialogModificaUtente.parent("div"), 3, 20 )/* && controllaEspressioneRegolare(password, "Password", dialogModificaUtente.parent("div"), regexpGenerale)*/;
			    	
			    	if(campiValidi) {
			    		$.post("http://localhost:8080/WebVoyager/ModificaUtente", {
			    			nome : nome.val(),
			    			cognome : cognome.val(),
			    			citta : citta.val(),
			    			nascita : nascita.val(),
			    			sesso : $( "#dialogNuovoUtente #sesso :radio:checked" ).attr("value"),
			    			mail : mail.val(),
			    			ruolo : $( "#dialogNuovoUtente #ruolo :radio:checked" ).attr("value"),
			    			username : username.val(),
			    			password : password.val()})
				    	.done(function() {
				    		messaggioSuccessoModificaUtente.dialog( "open" );
				    		dialogModificaUtente.dialog( "close" );
				    	})
				    	.fail(function(data) {
				    		if (data.status == 409) {
		    					mostraErroreCampi(username);
		    					aggiornaAvviso( "Utente esistente." );
		    					dialogModificaUtente.parent( "div" ).effect( "shake" );
		    				} else {
		    					messaggioFallimentoModificaUtente.dialog( "open" );
			    				dialogModificaUtente.dialog( "close" );
		    				}
				    	});
			    	}
		    	}
				
				dialogModificaUtente.dialog({   
					title: "Modifica Utente",
					modal: true,
				    autoOpen: false,			    
				    draggable: false,
				    resizable: false,
				    closeOnEscape: true,
				    height: "auto",
				    width: "auto",	
				    open: function() {				    		
				    	inizializzaDialog();
				    },
				    show: {
				    	effect: "blind"
				    },
				    hide: {
				    	effect:"blind"
				    },
				    buttons: {
					    "Ok": function() { 
					    	rimuoviErroreCampi(campi);
					    	rimuoviAvviso();
					    	modificaUtente();				    	
					    },
					    "Annulla": function() {					    	
			   			    $( this ).dialog( "close" );
					    }
				    }
				});			
				
			});	
				
			$(function() {			
				
				var messaggioSuccessoRimuoviUtente = $( "#messaggioSuccessoRimuoviUtente" );
				var messaggioFallimentoRimuoviUtente = $( "#messaggioFallimentoRimuoviUtente" );
				
				function rimuoviUtente() {
					$.post("http://localhost:8080/WebVoyager/RimuoviUtente", {username : usernameUtenteSelezionato})
					.done(function() {
						messaggioSuccessoRimuoviUtente.dialog( "open" );
						caricaListaUtenti();
					}).fail(function(data) {
						messaggioFallimentoRimuoviUtente.dialog( "open" );
					});
				}
				
				buttonRimuovi.click(function() {
					rimuoviUtente();
					return false;
			    });
				
			});						
  			
  		</script>
	
	</body>

</html>