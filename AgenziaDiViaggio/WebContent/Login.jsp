<!-- 
/**
 * @project WebVoyager
 * 
 * @package WebContent
 * 
 * @name Login.jsp
 *
 * @description
 *
 * @author Giacomo Marciani
 * 
 */
 -->

<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1" pageEncoding = "ISO-8859-1" %>

<%@ page import = "webvoyager.helper.TemplateViewer" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset = ISO-8859-1">
				
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
	
		<div class = "panelLogoLogin" id = "panelLogo" align = "center">
		
			<%=
				templateViewer.getLogo()
			%>
			
		</div>		
		
		<div class = "panelMainLogin" id = "panelLogin" align = "center">
			
				<p><input class = "text ui-widget-content ui-corner-all" id = "username" name = "username" type = "text" placeholder = "Username"/></p>
				
				<p><input class = "text ui-widget-content ui-corner-all" id = "password" name = "password" type = "password" placeholder = "Password"/></p>
				
				<p><button class = "buttonLogin buttonIconLabel" id = "buttonLogin" type = "submit">Login</button>
				
				<button class = "buttonOption buttonIcon" id = "buttonResetPassword" type = "button" title = "Hai dimenticato la password?"></button></p>
		
		</div>	
		
		<div class = "dialog dialogForm popup" id = "dialogResetPassword">
		
  			<p>Inserisci il tuo username.</p> 
  			<p>Verrà inviata al tuo indirizzo email, una <b>Password Provvisoria</b> con la quale accedere al servizio.</p> 
  			
  			<form align = "center">
  			
  				<fieldset class = "ui-dialog-content">
  				
    				<input class = "text ui-widget-content ui-corner-all" id = "username" type = "text" name = "username" placeholder = "Username" />
  				
  				</fieldset>
  				
  			</form>
  			
		</div>	
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioFallimentoLogin" title = "Login">
		
  			<p><span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			<b>Ooops!</b> Qualcosa è andato storto durante il Login! Riprova più tardi.
  			</p>
  			
		</div>
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioSuccessoResetPassword" title = "Reimposta Password">
		
  			<p><span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			La <b>Password Provvisoria</b> sarà inviata all'indirizzo email dell'utente specificato!
  			</p>
  			
		</div>			
		
		<div class = "dialog dialogMessaggio popup" id = "messaggioFallimentoResetPassword" title = "Reimposta Password">
		
  			<p><span class = "ui-icon ui-icon-alert" style = "float: left; margin: 0 7px 50px 0;"></span>
    			<b>Ooops!</b> Qualcosa è andato storto durante l'impostazione della Password Provvisoria! Riprova più tardi.
  			</p>
  			
		</div>
		
		<script>
		
			var panelLogo = $( "#panelLogo" );
			var panelLogin = $( "#panelLogin" );
			
			var username = $( "#panelLogin #username" );
			var password = $( "#panelLogin #password" );
			
			var campi = $( [] ).add(username).add(password);
			
			var buttonLogin = $( "#panelLogin #buttonLogin" );
			var buttonResetPassword = $( "#panelLogin #buttonResetPassword" );
			
			var dialogResetPassword = $( "#dialogResetPassword" );
			var usernameResetPassword = $ ( "#dialogResetPassword #username" );
			
			var messaggioFallimentoLogin = $( "#messaggioFallimentoLogin" );
			var messaggioSuccessoResetPassword = $( "#messaggioSuccessoResetPassword" );
			var messaggioFallimentoResetPassword = $( "#messaggioFallimentoResetPassword" );
	
			function animazioneApertura() {					
				panelLogo.hide();
				panelLogin.hide();
				
				panelLogo.show( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
					panelLogin.show( "drop", {direction : "left", easing: "swing"} );
				});					
			}
			
			function animazioneChiusura() {				
				panelLogin.hide( "drop", {direction : "left", easing: "swing"}, function() {
					panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500});
				});				
			}
			
			function getCookie(cookieName) {
				var cookieValue = document.cookie;
				var cookieStart = cookieValue.indexOf(" " + cookieName + "=");
				
				if (cookieStart == -1) {
					cookieStart = cookieValue.indexOf(cookieName + "=");
				}
				
				if (cookieStart == -1) {
					cookieValue = null;
				} else {
					cookieStart = cookieValue.indexOf("=", cookieStart) + 1;
					
					var cookieEnd = cookieValue.indexOf(";", cookieStart);
					
					if (cookieEnd == -1) {
						cookieEnd = cookieValue.length;
					}
					
					cookieValue = unescape(cookieValue.substring(cookieStart,cookieEnd));
				}
				
				return cookieValue;
			}
			
			function checkLoginCookie() {
				var cookieUsername = getCookie("VoyagerUsername");
				var cookiePassword = getCookie("VoyagerPassword");
				if (cookieUsername != null && cookieUsername != "" && cookiePassword != null && cookiePassword != "") {
					username.val(cookieUsername);
					password.val(cookiePassword);
				}
			}
			
			$( window ).load(function() {				
				animazioneApertura();	
			});
			
			$( document ).ready(function() {
				checkLoginCookie();
			});
			
			function goTo(address) {				
				panelLogin.hide( "drop", {direction : "left", easing: "swing"}, function() {
					panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
						window.location = address;
					});
				});				
			}			
			
			buttonLogin.click( function() {				
				var mUsername = username.val();
				var mPassword = password.val();
				
				rimuoviErroreCampi(campi);
				login(mUsername, mPassword);				
			});
			
			buttonResetPassword.click(function() {				
		        dialogResetPassword.dialog( "open" );
		        return false;		        
		    });
			
			campi.keypress(function(event){
				if(event.keyCode == '13'){
					buttonLogin.click();
					return false;
				}			 
			});
				
			function rimuoviErroreCampi( targetCampi ) {				
				targetCampi.removeClass( "ui-state-error" );				
			}
			
			function mostraErroreCampi( targetCampi ) {				
				targetCampi.addClass( "ui-state-error" );				
			}
			
			function controllaLunghezzaCampo( targetCampo, targetParent, min, max ) {				
	    	      if ( targetCampo.val().length > max || targetCampo.val().length < min ) {	    	    	  
	    	    	  	targetCampo.addClass( "ui-state-error" );		 
	    	    	  	targetParent.effect( "shake" );
	    	        	return false;	    	        	
	    	      } else {		    	    	  
	    	        return true;	    	        
	    	      }	    	      
	    	}
			
			function login() {
				var campiValidi = true;
				
				campiValidi = campiValidi && controllaLunghezzaCampo(username, panelLogin, 3, 25);
				campiValidi = campiValidi && controllaLunghezzaCampo(password, panelLogin, 3, 25);
				
				if ( campiValidi ) {
					var mUsername = username.val();
					var mPassword = password.val();
					
					$.post("http://localhost:8080/WebVoyager/Login", {username: mUsername, password : mPassword})
					.done(function() {
						goTo("http://localhost:8080/WebVoyager/Home.jsp");										
					})
					.fail(function(data) {
						if (data.status == 401) {
							panelLogin.effect( "shake", function() {
								mostraErroreCampi( campi );
							});
						} else {
							messaggioFallimentoLogin.dialog( "open" );
						}						
					});
					
				}				
			}			
			
			function resetPassword() {		
				var campiValidi = true;
				
				campiValidi = campiValidi && controllaLunghezzaCampo(usernameResetPassword, dialogResetPassword.parent("div"), 3, 25);
				
				if ( campiValidi ) {
					var mUsername = usernameResetPassword.val();
					
					$.post("http://localhost:8080/WebVoyager/ResetPassword", {username : mUsername})
	   			    .done(function() {
		   			    messaggioSuccessoResetPassword.dialog( "open" );
		   			 	dialogResetPassword.dialog( "close" );
	   			    })
	   			    .fail(function(data) {
	   			    	if (data.status == 401) {
	   			    		dialogResetPassword.parent( "div" ).effect( "shake", function() {
								mostraErroreCampi( usernameResetPassword );
							});
	   			    	} else {
	   			    		messaggioFallimentoResetPassword.dialog( "open" );
	   			    	}
	   			    	
	   			    }); 
				} 				
			}			
			
			dialogResetPassword.dialog({    
				title: "Reimposta Password",
			    autoOpen: false,
			    modal: true,
			    draggable: false,
			    resizable: false,
			    closeOnEscape: true,
			    height: "225",
			    width: "350",	
			    open: function() {
			    	rimuoviErroreCampi(usernameResetPassword);
			    	usernameResetPassword.val("");
			    },
			    show: {
			    	effect: "blind"
			    },
			    hide: {
			    	effect: "blind"
			    },
			    buttons: {
				    "Ok": function() {				    	
				    	resetPassword();       			          
				    },
				    "Annulla": function() {				    	
		   			    $( this ).dialog( "close" );		   			    
				    }
			    }
			});
	
		</script>
	
	</body>
	
</html>