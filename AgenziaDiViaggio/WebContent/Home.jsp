<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent
 * 
 * @name Home.jsp
 *
 * @description
 *
 * @author Giacomo Marciani
 * 
 */
 -->

<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1"%>

<%@ page import = "webvoyager.model.bean.UtenteBean" %>
<%@ page import = "webvoyager.helper.TemplateViewer" %>

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
	
		<jsp:useBean id = "utente" class = "webvoyager.model.bean.UtenteBean" scope = "session"></jsp:useBean>	
	
		<%
			TemplateViewer templateViewer = TemplateViewer.getInstance();
		%>
	
		<div class = "panelLogo" id = "panelLogo" align = "center">
		
			<%=
				templateViewer.getLogo()
			%>
			
		</div>						
		
		<div class = "panelMain" id = "panelHome" align = "center">
		
			<p class = "title" id = "titleHome">HOME</p>
			
			<%= 
				templateViewer.getWelcome(utente.getNome()) 
			%>			
			
			<%=
				templateViewer.getHomePanel(utente.getRuolo())
			%>
			
		</div>	
		
		<div class = "dialog dialogForm popup" id = "dialogGestioneProfilo" align = "center">
			
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
	    			<input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" placeholder = "Username" readonly/>    				
	    			<p><input class = "text ui-widget-content ui-corner-all" id = "passwordNuova" type = "password" name = "passwordNuova" placeholder = "Nuova Password"/>
	    			<input class = "text ui-widget-content ui-corner-all" id = "passwordNuovaConferma" type = "password" name = "passwordNuovaConferma" placeholder = "Conferma Password" readonly/></p>	
	    			<p><input class = "text ui-widget-content ui-corner-all" id = "password" type = "password" name = "password" placeholder = "Password"/></p>
    				
  				</fieldset>
  				
  			</form>
  			
		</div>	
		
		<div class = "dialog dialogMessaggioReload popup" id = "messaggioSuccessoGestioneProfilo" title = "Gestione Profilo">
  			<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Il <b>tuo profilo</b> è stato correttamente aggiornato!
  			</p>
		</div>	
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioFallimentoGestioneProfilo" title = "Gestione Profilo">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			<b>Ooops!</b> Qualcosa è andato storto durante la gestione del tuo profilo! Riprova più tardi.
  			</p>
		</div>	
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioFallimentoGestioneCatalogo" title = "Gestione Catalogo">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			La <b>Gestione del Catalogo</b> è una funzionalità Voyager non ancora implementata!
  			</p>
  			<p>Riprova al secondo appello!</p>
		</div>		
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioFallimentoGestioneOfferta" title = "Gestione Offerta">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			La <b>Gestione dell'Offerta</b> è una funzionalità Voyager non ancora implementata! 
  			</p>
  			<p>Riprova al secondo appello!</p>
		</div>		
		
		<script>
		
			var panelLogo = $( "#panelLogo" );
			var panelHome = $( "#panelHome" );
		
			var buttonAmministrazioneUtenti = $( "#panelHome #buttonAmministrazioneUtenti" );
			var buttonGestioneCatalogo = $( "#panelHome #buttonGestioneCatalogo" );
			var buttonGestioneOfferta = $( "#panelHome #buttonGestioneOfferta" );
			var buttonGestioneProfilo = $( "#panelHome #buttonGestioneProfilo" );
			var buttonLogout = $( "#panelHome #buttonLogout" );
			
			var dialogGestioneProfilo = $( "#dialogGestioneProfilo" );
			
			var avviso = $( "#dialogGestioneProfilo #avviso" );
			var nome = $( "#dialogGestioneProfilo #nome" );
	    	var cognome = $( "#dialogGestioneProfilo #cognome" );
	    	var citta = $( "#dialogGestioneProfilo #citta" );
	    	var nascita = $( "#dialogGestioneProfilo #nascita" );
	    	var sesso = $( "#dialogGestioneProfilo #sesso" );
	    	var uomo = $( "#dialogGestioneProfilo #sesso #uomo" );
	    	var donna = $( "#dialogGestioneProfilo #sesso #donna" );
	    	var mail = $( "#dialogGestioneProfilo #mail" );
	    	var username = $( "#dialogGestioneProfilo #username" );
	    	var password = $( "#dialogGestioneProfilo #password" );
	    	var passwordNuova = $( "#dialogGestioneProfilo #passwordNuova" );
	    	var passwordNuovaConferma = $( "#dialogGestioneProfilo #passwordNuovaConferma" );
	    	
	    	var cambiaPassword = false;
	    	
	    	var campi = $( [] )
	    	.add(nome).add(cognome).add(citta).add(nascita).add(mail).add(password).add(passwordNuova).add(passwordNuovaConferma);
	    	
	    	var messaggioSuccessoGestioneProfilo = $( "#messaggioSuccessoGestioneProfilo" );
			var messaggioFallimentoGestioneProfilo = $( "#messaggioFallimentoGestioneProfilo" );
			var messaggioFallimentoGestioneCatalogo = $( "#messaggioFallimentoGestioneCatalogo" );
			var messaggioFallimentoGestioneOfferta = $( "#messaggioFallimentoGestioneOfferta" );	    	
		
			function animazioneApertura() {					
				panelLogo.hide();
				panelHome.hide();
				
				panelLogo.show( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
					panelHome.show( "drop", {direction : "left", easing: "swing"} );
				});					
			}
			
			function animazioneChiusura() {					
				panelHome.hide( "drop", {direction : "left", easing: "swing"}, function() {
					panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500});
				});				
			}
		
			$(window).load(function() {				
				animazioneApertura();				
			});
			
			function goTo(address) {				
				panelHome.hide( "drop", {direction : "left", easing: "swing"}, function() {
					panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
						window.location = address;
					});
				});				
			}
			
			buttonAmministrazioneUtenti.click(function() {
		        goTo("http://localhost:8080/WebVoyager/AmministraUtenti.jsp");
		        return false;
		    });
			
			buttonGestioneCatalogo.click(function() {
		        messaggioFallimentoGestioneCatalogo.dialog( "open" );
		        return false;
		    });
			
			buttonGestioneOfferta.click(function() {
		        messaggioFallimentoGestioneOfferta.dialog( "open" );
		        return false;
		    });
			
			buttonGestioneProfilo.click(function() {
		        dialogGestioneProfilo.dialog( "open" );
		        return false;
		    });
			
			buttonLogout.click(function() {
				$.post("http://localhost:8080/WebVoyager/Logout");
				goTo("http://localhost:8080/WebVoyager");
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
	    	
	    	passwordNuova.keyup(function(){
	    	    if (passwordNuova.val() == ""){
	    	    	cambiaPassword = false;
	    	    	passwordNuovaConferma.val("");
	    	        passwordNuovaConferma.prop("readonly", true);
	    	    } else {
	    	    	cambiaPassword = true;
	    	    	passwordNuovaConferma.val("");
	    	        passwordNuovaConferma.prop("readonly", false);
	    	    }
	    	});
	    	
	    	function controllaConfermaPassword() {
	    		if ( passwordNuova.val() != passwordNuovaConferma.val() ) {
	    			passwordNuova.addClass( "ui-state-error" );
	    			passwordNuovaConferma.addClass( "ui-state-error" );
	    			dialogGestioneProfilo.parent( "div" ).effect( "shake" );
	    			aggiornaAvviso( "La nuova password deve essere confermata." );
    	        	return false;
	    		} else {
	    			return true;
	    		}
	    	}    
	    	
	    	function inizializzaDialog() {
	    		caricaDatiUtente();
	    		rimuoviErroreCampi(campi);
	    		rimuoviAvviso();
	    	}
	    	
	    	function caricaDatiUtente() {
	    		var mUsername = "<%=utente.getUsername()%>";
	    		
	    		$.post("http://localhost:8080/WebVoyager/GetUtente", {username : mUsername})
	    		.done(function(data) {
	    			var jsonData = data.split( ":" );
	    			nome.val(jsonData[0]);
	    			cognome.val(jsonData[1]);
	    			citta.val(jsonData[2]);
	    			nascita.val(jsonData[3]);
	    			$("#" + jsonData[4].toLowerCase()).prop("checked", true);
	    			sesso.buttonset( "refresh" );
	    			mail.val(jsonData[5]);
	    			username.val(jsonData[6]);	    			
	    		})
	    		.fail(function() {
	    			messaggioFallimentoGestioneProfilo.dialog( "open" );
	    			dialogGestioneProfilo.dialog( "close" );
	    		});
	    	}	    		    	
	    	
	    	function aggiornaProfilo() {
	    		//var regexpGenerale = /^[a-z]([0-9a-z_])+$/i;
		    	//var regexpData = /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/i;
		    	//var regexpMail = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
		    					    	
		    	var campiValidi = true;
		    	
		    	campiValidi = campiValidi && controllaLunghezzaCampo(nome, "Nome", dialogGestioneProfilo.parent("div"), 1, 20 ) /*&& controllaEspressioneRegolare(nome, "Nome", dialogGestioneProfilo.parent("div"), regexpGenerale)*/;
		    	campiValidi = campiValidi && controllaLunghezzaCampo(cognome, "Cognome", dialogGestioneProfilo.parent("div"), 1, 20 ) /*&& controllaEspressioneRegolare(cognome, "Cognome", dialogGestioneProfilo.parent("div"), regexpGenerale)*/;
		    	campiValidi = campiValidi && controllaLunghezzaCampo(citta, "Città", dialogGestioneProfilo.parent("div"), 1, 20 ) /*&& controllaEspressioneRegolare(citta, "Città", dialogGestioneProfilo.parent("div"), regexpGenerale)*/;
		    	campiValidi = campiValidi && controllaLunghezzaCampo(nascita, "Nascita", dialogGestioneProfilo.parent("div"), 8, 10 ) /*&& controllaEspressioneRegolare(nascita, "Nascita", dialogGestioneProfilo.parent("div"), regexpData)*/;
		    	campiValidi = campiValidi && controllaLunghezzaCampo(mail, "Mail", dialogGestioneProfilo.parent("div"), 5, 40 ) /*&& controllaEspressioneRegolare(mail, "Mail", dialogGestioneProfilo.parent("div"), regexpMail)*/;
		    	
		    	if (cambiaPassword) {
		    		campiValidi = campiValidi && controllaLunghezzaCampo(passwordNuova, "Password", dialogGestioneProfilo.parent("div"), 3, 20 ) /*&& controllaEspressioneRegolare(password, "password", regexpGenerale)*/;
			    	campiValidi = campiValidi && controllaConfermaPassword();
		    	}					    	

		    	if(campiValidi) {	
		    		var mPassword = null;
		    		
		    		if (cambiaPassword) {
		    			mPassword = passwordNuova.val();
		    		} else {
		    			mPassword = "<%=utente.getPassword()%>";
		    		}
		    		
		    		$.post("http://localhost:8080/WebVoyager/GestioneProfilo", {
		    			username: "<%=utente.getUsername()%>",
		    			password: password.val(),
		    			nome : nome.val(),
		    			cognome : cognome.val(),
		    			citta : citta.val(),
		    			nascita : nascita.val(),
		    			sesso : $( "#dialogGestioneProfilo #sesso :radio:checked" ).attr("value"),
		    			mail : mail.val(),
		    			nPassword : mPassword})
		    			.done(function() {
		    				messaggioSuccessoGestioneProfilo.dialog( "open" );
		    				dialogGestioneProfilo.dialog( "close" );
		    			}).fail(function(data) {
		    				if (data.status == 401) {
		    					mostraErroreCampi(password);
		    					dialogGestioneProfilo.parent( "div" ).effect( "shake" );
		    				} else {
		    					alert(data.status);
		    					messaggioFallimentoGestioneProfilo.dialog( "open" );
			    				dialogGestioneProfilo.dialog( "close" );
		    				}		    				
		    			});		    		
		    	}	    		
	    	}
			
			dialogGestioneProfilo.dialog({   
				title: "Gestione Profilo",
				modal: true,
			    autoOpen: false,			    
			    draggable: false,
			    resizable: false,
			    closeOnEscape: true,
			    height: "415",
			    width: "500",	
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
				    	aggiornaProfilo();			    	
				    },
				    "Annulla": function() {					    	
		   			    $( this ).dialog( "close" );
				    }
			    }
			});
			
				
  			
  		</script>
	
	</body>
	
</html>