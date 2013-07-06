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
		
		$( document ).ready(function() {			
			
			var nome = $( "#nome" );
	    	var cognome = $( "#cognome" );
	    	var citta = $( "#citta" );
	    	var nascita = $( "#nascita" );
	    	var sesso = $( "#sesso" );
	    	var sessoUomo = $( "#radioUomo" );
	    	var sessoDonna = $( "#radioDonna" );
	    	var mail = $( "#mail" );
	    	var username = $( "#username" );
	    	var ruolo = $( "#ruolo" );
	    	var passwordCorrente = $( "#passwordCorrente" );
	    	var passwordNuova = $( "#passwordNuova" );
	    	var passwordNuovaConferma = $( "#passwordNuovaConferma" );	    	
	    	
	    	var cambiaPassword = false;	 
	    	
	    	function inizializzaCampi() {
	    		nome.val("<%=utente.getNome()%>");
		    	cognome.val("<%=utente.getCognome()%>");
		    	citta.val("<%=utente.getCitta()%>");
		    	nascita.datepicker("setDate", new Date(<%=utente.getNascita().get(Calendar.YEAR)%>,	<%=utente.getNascita().get(Calendar.MONTH)%>, <%=utente.getNascita().get(Calendar.DAY_OF_MONTH)%>));
		    	sessoUomo.prop("checked", <%=(utente.getSesso().equals("Uomo"))%>);
		    	sessoDonna.prop("checked", <%=(utente.getSesso().equals("Donna"))%>);
		    	sesso.buttonset("refresh");
		    	mail.val("<%=utente.getMail()%>");
		    	username.val("<%=utente.getUsername()%>");
		    	ruolo.val("<%=utente.getRuolo().asString()%>");
	    	}
	    	
	    	$( "#checkCambiaPassword" ).change(function(){
	    	    if ($( this ).is( ":checked" )){
	    	        mCambiaPassword = true;
	    	        mPasswordCorrente.prop("disabled", false);
	    	        mPasswordNuova.prop("disabled", false);
	    	        mPasswordNuovaConferma.prop("disabled", false);
	    	    } else {
	    	        mCambiaPassword = false;
	    	        mPasswordCorrente.prop("disabled", true);
	    	        mPasswordNuova.prop("disabled", true);
	    	        mPasswordNuovaConferma.prop("disabled", true);
	    	    }
	    	});
			
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
		   			          
		   			    /*$.get("http://localhost:8080/WebVoyager/GestioneProfilo", 
		   			    		{nome : mNome.val(), 
		   			    		cognome : mCognome.val(), 
		   			    		citta : mCitta.val(), 
		   			    		sesso : sesso.val(),
		   			    		mail : mMail.val(),
		   			    		password : mPassword.val()
		   			    		})
		   			    .done(function() {
			   			    $( "#dialogMessaggioGestioneProfiloSuccesso" ).dialog( "open" );
		   			    })
		   			    .fail(function() {
			   			    $( "#dialogMessaggioGestioneProfiloFallimento" ).dialog( "open" );
		   			    })
		   			    .always(function() {
			   			    $( "#dialogGestioneProfilo" ).dialog( "close" );
		   			    });    */      
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
    					<input type = "radio" id = "radioUomo" name = "radio"/><label for = "radioUomo">Uomo</label>
    					<input type = "radio" id = "radioDonna" name = "radio" /><label for = "radioDonna">Donna</label>
  					</div>  				  				
    				<p><input class = "text ui-widget-content ui-corner-all" id = "mail" type = "text" name = "mail" placeholder = "Mail"/></p>  
    				<p><input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" readonly/>    				
    				<input class = "text ui-widget-content ui-corner-all" id = "ruolo" type = "text" name = "ruolo" readonly/></p>    	    				
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