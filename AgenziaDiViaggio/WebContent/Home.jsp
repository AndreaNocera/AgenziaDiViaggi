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
				    	/*
				    	alert( $( "#dialogGestioneProfilo #nome" ).val() +
				    			$( "#dialogGestioneProfilo #cognome" ).val() +
				    			$( "#dialogGestioneProfilo #citta" ).val() + 
				    			$( "#dialogGestioneProfilo #nascita" ).val() +
				    			$( "#dialogGestioneProfilo #sesso" ).val() +
				    			$( "#dialogGestioneProfilo #mail" ).val() +
				    			$( "#dialogGestioneProfilo #username" ).val() +
				    			$( "#dialogGestioneProfilo #ruolo" ).val() +
				    			$( "#dialogGestioneProfilo #passwordCorrente" ).val() +
				    			$( "#dialogGestioneProfilo #passwordNuova" ).val() +
				    			$( "#dialogGestioneProfilo #passwordNuovaConferma" ).val());*/
		   			          
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
    					<input type = "radio" id = "radioUomo" name = "sesso"/><label for = "radioUomo">Uomo</label>
    					<input type = "radio" id = "radioDonna" name = "sesso" /><label for = "radioDonna">Donna</label>
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