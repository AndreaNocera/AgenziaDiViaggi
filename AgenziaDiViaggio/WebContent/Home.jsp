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
 * @author Giacomo Marciani (TEAM 9)
 * 
 */
 -->

<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1"%>

<%@ include file = "common/Head.jsp" %>
<%@ page import = "home.helper.HelperHome" %>
<%@ page import = "gestioneutenti.model.bean.UtenteBean" %>
<%@ page import = "java.util.Calendar" %>
<%@ page import = "java.util.GregorianCalendar" %>
<%@ page import = "gestioneutenti.model.bean.UtenteBean" %>
<%@ page import = "gestioneutenti.model.ruoli.Ruolo" %>
<%@ page import = "utils.swing.DateUtils" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
		
		<link href = "common/css/Home.css" type = "text/css" rel = "stylesheet">
		
		<jsp:useBean id = "utente" class = "gestioneutenti.model.bean.UtenteBean" scope = "session"></jsp:useBean>	
		
		<%
			HelperHome helper = HelperHome.getInstance();
		%>
  		
	</head>
	
	<script>
		
			$(function() {	
				
				var nome = $( "#dialogGestioneProfilo #nome" );
		    	var cognome = $( "#dialogGestioneProfilo #cognome" );
		    	var citta = $( "#dialogGestioneProfilo #citta" );
		    	var nascita = $( "#dialogGestioneProfilo #nascita" );
		    	var sesso = $( "#dialogGestioneProfilo #sesso" );
		    	var mail = $( "#dialogGestioneProfilo #mail" );
		    	var username = $( "#dialogGestioneProfilo #username" );
		    	var ruolo = $( "#dialogGestioneProfilo #ruolo" );
		    	var cambiaPassword = $( "#dialogGestioneProfilo #cambiaPassword" );
		    	var passwordVecchia = $( "#dialogGestioneProfilo #passwordVecchia" );
		    	var passwordNuova = $( "#dialogGestioneProfilo #passwordNuova" );
		    	var passwordNuovaConferma = $( "#dialogGestioneProfilo #passwordVecchia" );
		    	
		    	var avviso = $( "#dialogGestioneProfilo #avviso" );
		    	
		    	var campi = $( [] )
		    	.add(nome).add(cognome).add(citta).add(nascita).add(mail).add(username).add(password);
		    	
		    	function inizializzaCampi() {
		    		$( "#dialogGestioneProfilo #nome" ).val("<%=utente.getNome()%>");
		    		$( "#dialogGestioneProfilo #cognome" ).val("<%=utente.getCognome()%>");
		    		$( "#dialogGestioneProfilo #citta" ).val("<%=utente.getCitta()%>");
		    		$( "#dialogGestioneProfilo #nascita" ).datepicker("setDate", new Date(<%=utente.getNascita().get(Calendar.YEAR)%>,	<%=utente.getNascita().get(Calendar.MONTH)%>, <%=utente.getNascita().get(Calendar.DAY_OF_MONTH)%>));
		    		$( "#dialogGestioneProfilo #radioUomo" ).prop("checked", <%=(utente.getSesso().equals("Uomo"))%>);
		    		$( "#dialogGestioneProfilo #radioDonna" ).prop("checked", <%=(utente.getSesso().equals("Donna"))%>);
		    		$( "#dialogGestioneProfilo #sesso" ).buttonset("refresh");
		    		$( "#dialogGestioneProfilo #mail" ).val("<%=utente.getMail()%>");
		    		$( "#dialogGestioneProfilo #username" ).val("<%=utente.getUsername()%>");
		    		$( "#dialogGestioneProfilo #ruolo" ).val("<%=utente.getRuolo().asString()%>");
		    		$( "#dialogGestioneProfilo #checkCambiaPassword" ).removeAttr("checked").button("refresh");
		    		
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
		    			target1.addClass( "ui-state-error" );
		    			aggiornaAvviso( "La password inserita non è corretta.");
	    	        	return false;
		    		} else {
		    			return true;
		    		}
		    	}
		    	
		    	$( "#dialogGestioneProfilo #checkCambiaPassword" ).change(function(){
		    	    if ($( this ).is( ":checked" )){
		    	        $( "#dialogGestioneProfilo #passwordCorrente" ).prop("disabled", false);
		    	        $( "#dialogGestioneProfilo #passwordNuova" ).prop("disabled", false);
		    	        $( "#dialogGestioneProfilo #passwordNuovaConferma" ).prop("disabled", false);
		    	    } else {
		    	        $( "#dialogGestioneProfilo #passwordCorrente" ).prop("disabled", true);
		    	        $( "#dialogGestioneProfilo #passwordNuova" ).prop("disabled", true);
		    	        $( "#dialogGestioneProfilo #passwordNuovaConferma" ).prop("disabled", true);
		    	    }
		    	});
		    	
		    	function aggiornaProfilo() {
		    		
		    		$.post("http://localhost:8080/WebVoyager/GestioneProfilo", {
		    			nome : nome.val(),
		    			cognome : cognome.val(),
		    			citta : citta.val(),
		    			nascita : nascita.val(),
		    			sesso : $( "#sesso :radio:checked" ).attr("value"),
		    			mail : mail.val(),
		    			ruolo : "<%=utente.getRuolo().getId()%>",
		    			username : "<%=utente.getUsername()%>",
		    			password : passwordNuova.val()});
		    	}
				
				$( "#dialogGestioneProfilo" ).dialog({   
					title: "Gestione Profilo",
					modal: true,
				    autoOpen: false,			    
				    draggable: false,
				    closeOnEscape: true,
				    height: 515,
				    width: 515,	
				    open: function() {
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
					    	campiValidi = campiValidi && controllaPassword(passwordVecchia);
					    	campiValidi = campiValidi && controllaConfermaPassword(passwordNuova, passwordNuovaConferma);
					    	campiValidi = campiValidi && controllaLunghezzaCampo(passwordNuova, "password", 5, 20 ) /*&& controllaEspressioneRegolare(password, "password", regexpGenerale)*/;

					    	if(campiValidi) {
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
				
				$( "#buttonGestioneProfilo" ).click(function() {
			        $( "#dialogGestioneProfilo" ).dialog( "open" );
			        return false;
			    });
				
			});		
  			
  		</script>
	
	<body>
	
		<%=
			helper.getLogo()
		%>				
		
		<div class = "panelMain" id = "panelCompetenze" align = "center">
		
			<p class = "title" id = "titleHome">HOME</p>
			
			<%= 
				helper.getWelcome(utente.getNome()) 
			%>			
			
			<form name = "formCompetenze" action = "Home" method = "POST">
			
				<%=
					helper.getHomePanel(utente.getRuolo())
				%>
				
			</form>
			
		</div>	
		
		<div class = "dialogForm" id = "dialogGestioneProfilo"  align = "center">
			
			<p class = "datiValidi"></p>
		
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
    				<input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" placeholder = "Username" disabled/>    				
    				<input class = "text ui-widget-content ui-corner-all" id = "ruolo" type = "text" name = "ruolo" placeholder = "Ruolo" disabled/>
    				<p><input class = "checkbox" id = "checkCambiaPassword" type = "checkbox" /><label for = "checkCambiaPassword">Cambia Password</label></p> 
    				<p><input class = "text ui-widget-content ui-corner-all" id = "passwordCorrente" type = "password" name = "passwordCorrente" placeholder = "Password Corrente" disabled = "true"/></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "passwordNuova" type = "password" name = "passwordNuova" placeholder = "Nuova Password" disabled = "true"/></p>
    				<p><input class = "text ui-widget-content ui-corner-all" id = "passwordNuovaConferma" type = "password" name = "passwordNuovaConferma" placeholder = "Conferma Password" disabled = "true"/></p>
  				
  				</fieldset>
  				
  			</form>
  			
		</div>
		
		<div class = "dialogMessaggio" id = "dialogMessaggioGestioneProfiloSuccesso" title = "Gestione Profilo">
  			<p>
  				<span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			Il <b>tuo profilo</b> è stato correttamente aggiornato!
  			</p>
		</div>	
	
	</body>
	
</html>