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

<%@ page import = "home.helper.HelperHome" %>
<%@ page import = "gestioneutenti.model.bean.UtenteBean" %>
<%@ page import = "gestioneutenti.model.ruoli.Ruolo" %>
<%@ page import = "gestioneutenti.model.ruoli.AbstractRuolo" %>
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
	
		<jsp:useBean id = "utente" class = "gestioneutenti.model.bean.UtenteBean" scope = "session"></jsp:useBean>	
	
		<%
			HelperHome helper = HelperHome.getInstance();
		%>
	
		<%=
			helper.getLogo()
		%>				
		
		<div class = "panelMain" id = "panelHome" align = "center">
		
			<p class = "title" id = "titleHome">HOME</p>
			
			<%= 
				helper.getWelcome(utente.getNome()) 
			%>			
			
			<%=
				helper.getHomePanel(utente.getRuolo())
			%>
			
		</div>	
		
		<div class = "dialogForm" id = "dialogGestioneProfilo" align = "center">
			
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
    				<p><input class = "text ui-widget-content ui-corner-all" id = "mail" type = "text" name = "mail" placeholder = "Mail"/>  
    				<input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" placeholder = "Username" readonly/>    				
    				<input class = "text ui-widget-content ui-corner-all" id = "ruolo" type = "text" name = "ruolo" placeholder = "Ruolo" readonly/>
    				<p><input class = "checkbox" id = "cambiaPassword" type = "checkbox"/><label for = "cambiaPassword">Cambia Password</label></p> 
    				<p><input class = "text ui-widget-content ui-corner-all" id = "password" type = "password" name = "password" placeholder = "Password" readonly/></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "passwordNuova" type = "password" name = "passwordNuova" placeholder = "Nuova Password" readonly/></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "passwordNuovaConferma" type = "password" name = "passwordNuovaConferma" placeholder = "Conferma Password" readonly/></p>
  				
  				</fieldset>
  				
  			</form>
  			
		</div>
		
		<div class = "dialogMessaggioReload" id = "dialogMessaggioGestioneProfiloSuccesso" title = "Gestione Profilo">
  			<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Il <b>tuo profilo</b> è stato correttamente aggiornato!
  			</p>
		</div>	
		
		<div class = "dialogMessaggio" id = "dialogMessaggioGestioneCatalogoFallimento" title = "Gestione Catalogo">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			La <b>Gestione del Catalogo</b> è una funzionalità Voyager non ancora implementata!
  			</p>
  			<p>Riprova al secondo appello!</p>
		</div>	
		
		<div class = "dialogMessaggio" id = "dialogMessaggioGestioneOffertaFallimento" title = "Gestione Offerta">
  			<p>
  				<span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			La <b>Gestione dell'Offerta</b> è una funzionalità Voyager non ancora implementata! 
  			</p>
  			<p>Riprova al secondo appello!</p>
		</div>		
		
		<script>
		
			function animazioneApertura() {
				
				var panelLogo = $( "#panelLogo" );
				var panelHome = $( "#panelHome" );
				
				panelLogo.hide();
				panelHome.hide();
				
				panelLogo.show( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
					panelHome.show( "drop", {direction : "left", easing: "swing"} );
				});	
				
			}
			
			function animazioneChiusura() {
				
				var panelLogo = $( "#panelLogo" );
				var panelHome = $( "#panelHome" );
				
				panelHome.hide( "drop", {direction : "left", easing: "swing"}, function() {
					panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500});
				});
				
			}
		
			$(window).load(function() {
				
				animazioneApertura();
				
			});
			
			function goTo(address) {
				
				var panelLogo = $( "#panelLogo" );
				var panelHome = $( "#panelHome" );
				
				panelHome.hide( "drop", {direction : "left", easing: "swing"}, function() {
					panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
						window.location = address;
					});
				});
				
			}
		
			$(function() {					
				
				var nome = $( "#dialogGestioneProfilo #nome" );
		    	var cognome = $( "#dialogGestioneProfilo #cognome" );
		    	var citta = $( "#dialogGestioneProfilo #citta" );
		    	var nascita = $( "#dialogGestioneProfilo #nascita" );
		    	var sesso = $( "#dialogGestioneProfilo #sesso" );
		    	var uomo = $( "#dialogGestioneProfilo #sesso #uomo" );
		    	var donna = $( "#dialogGestioneProfilo #sesso #donna" );
		    	var mail = $( "#dialogGestioneProfilo #mail" );
		    	var username = $( "#dialogGestioneProfilo #username" );
		    	var cambiaPassword = $( "#dialogGestioneProfilo #cambiaPassword" );
		    	var password = $( "#dialogGestioneProfilo #password" );
		    	var passwordNuova = $( "#dialogGestioneProfilo #passwordNuova" );
		    	var passwordNuovaConferma = $( "#dialogGestioneProfilo #passwordNuovaConferma" );
		    	
		    	var avviso = $( "#dialogGestioneProfilo #avviso" );
		    	
		    	var campi = $( [] )
		    	.add(nome).add(cognome).add(citta).add(nascita).add(mail).add(password).add(passwordNuova).add(passwordNuovaConferma);
		    	
		    	function inizializzaCampi() {	
		    		nome.val("<%=utente.getNome()%>");
		    		cognome.val("<%=utente.getCognome()%>");
		    		citta.val("<%=utente.getCitta()%>");
		    		nascita.val("<%=DateUtils.getStringFromGregorianCalendar(utente.getNascita())%>");
		    		uomo.prop("checked", <%=(utente.getSesso().equals("Uomo")) ? true : false%>);
		    		donna.prop("checked", <%=(utente.getSesso().equals("Donna")) ? true : false%>);
		    		sesso.buttonset( "refresh" );
		    		mail.val("<%=utente.getMail()%>");
		    		ruolo.val("<%=utente.getRuolo().asString()%>");
		    		username.val("<%=utente.getUsername()%>");
		    		cambiaPassword.prop("checked", false);
		    		cambiaPassword.button( "refresh" );
		    		
		    		campi.removeClass( "ui-state-error" );
			    	avviso.text( "" ).removeClass( "ui-state-highlight" );
		    	}
		    	
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
		    	
		    	function controllaConfermaPassword(target1, target2) {
		    		if ( target1.val() != target2.val() ) {
		    			target1.addClass( "ui-state-error" );
		    			target2.addClass( "ui-state-error" );
		    			aggiornaAvviso( "La nuova password deve essere confermata." );
	    	        	return false;
		    		} else {
		    			return true;
		    		}
		    	}
		    	
		    	function controllaPassword(target) {
		    		if ( target.val() != "<%=utente.getPassword()%>" ) {
		    			target.addClass( "ui-state-error" );
		    			aggiornaAvviso( "La password inserita non è corretta.");
	    	        	return false;
		    		} else {
		    			return true;
		    		}
		    	}
		    	
		    	cambiaPassword.change(function(){
		    	    if ($( this ).is( ":checked" )){
		    	        password.prop("readonly", false);
		    	        passwordNuova.prop("readonly", false);
		    	        passwordNuovaConferma.prop("readonly", false);
		    	    } else {
		    	    	password.prop("readonly", true);
		    	    	passwordNuova.prop("readonly", true);
		    	    	passwordNuovaConferma.prop("readonly", true);
		    	    }
		    	});
		    	
		    	function aggiornaProfilo() {
		    		
		    		var mPassword = null;
		    		
		    		if (cambiaPassword.is( ":checked" )) {
		    			mPassword = passwordNuova.val();
		    		} else {
		    			mPassword = "<%=utente.getPassword()%>";
		    		}
		    		
		    		$.post("http://localhost:8080/WebVoyager/GestioneProfilo", {
		    			nome : nome.val(),
		    			cognome : cognome.val(),
		    			citta : citta.val(),
		    			nascita : nascita.val(),
		    			sesso : $( "#dialogGestioneProfilo #sesso :radio:checked" ).attr("value"),
		    			mail : mail.val(),
		    			ruolo : "<%=utente.getRuolo().getId()%>",
		    			username : "<%=utente.getUsername()%>",
		    			password : mPassword});
		    	}
				
				$( "#dialogGestioneProfilo" ).dialog({   
					title: "Gestione Profilo",
					modal: true,
				    autoOpen: false,			    
				    draggable: false,
				    resizable: false,
				    closeOnEscape: true,
				    height: 515,
				    width: 515,	
				    open: function() {
				    	$( this ).load()
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
					    	
					    	if (cambiaPassword.is( ":checked" )) {
					    		campiValidi = campiValidi && controllaPassword(password);
						    	campiValidi = campiValidi && controllaConfermaPassword(passwordNuova, passwordNuovaConferma);
						    	campiValidi = campiValidi && controllaLunghezzaCampo(passwordNuova, "password", 5, 20 ) /*&& controllaEspressioneRegolare(password, "password", regexpGenerale)*/;
					    	}					    	

					    	if(campiValidi) {
					    		alert(nome.val()+cognome.val()+citta.val()+nascita.val()+mail.val()+username.val());
					    		aggiornaProfilo();	
					    		$( "#dialogMessaggioGestioneProfiloSuccesso" ).dialog( "open" );
					    		$( this ).dialog( "close" );
					    	}
					    },
					    "Annulla": function() {					    	
			   			    $( this ).dialog( "close" );
					    }
				    }
				});
				
				$( "#buttonAmministrazioneUtenti" ).click(function() {
			        goTo("http://localhost:8080/WebVoyager/AmministraUtenti.jsp");
			        return false;
			    });
				
				$( "#buttonGestioneCatalogo" ).click(function() {
			        $( "#dialogMessaggioGestioneCatalogoFallimento" ).dialog( "open" );
			        return false;
			    });
				
				$( "#buttonGestioneOfferta" ).click(function() {
			        $( "#dialogMessaggioGestioneOffertaFallimento" ).dialog( "open" );
			        return false;
			    });
				
				$( "#buttonGestioneProfilo" ).click(function() {
			        $( "#dialogGestioneProfilo" ).dialog( "open" );
			        return false;
			    });
				
				$( "#buttonLogout" ).click(function() {
					$.post("http://localhost:8080/WebVoyager/Logout");
					goTo("http://localhost:8080/WebVoyager");
					return false;
				});
				
			});		
  			
  		</script>
	
	</body>
	
</html>