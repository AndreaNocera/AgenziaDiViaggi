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

<%@ page import = "home.helper.HelperHome" %>

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
		
		<script src = "common/Script/General.js" type = "text/javascript"></script>
		
		<link href = "common/css/General.css" type = "text/css" rel = "stylesheet">		
		<link href = "common/css/Login.css" type = "text/css" rel = "stylesheet">
		
			
  		
	</head>
	
	<body>
	
		<%
			HelperHome helper = HelperHome.getInstance();
		%>
	
		<%=
			helper.getLogo()
		%>		
		
		<div class = "panelMain" id = "panelLogin" align = "center">
		
			<p class = "avviso" id = "avviso"></p>
				
			<form>
			
				<p><input class = "text ui-widget-content ui-corner-all" id = "username" name = "username" type = "text" placeholder = "Username"/></p>
				
				<p><input class = "text ui-widget-content ui-corner-all" id = "password" name = "password" type = "password" placeholder = "Password"/></p>
				
				<p><button class = "buttonLock buttonIconLabel" id = "buttonLogin" type = "button">Login</button>
				
				<button class = "buttonOption buttonApriDialogResetPassword buttonIcon" id = "buttonResetPassword" type = "button" title = "Hai dimenticato la password?"></button></p>
			
			</form>
		
		</div>
		
		<div class = "dialogForm" id = "dialogResetPassword">
		
  			<p>Inserisci il tuo username.</p> 
  			<p>Verrà inviata al tuo indirizzo email, una <b>Password Provvisoria</b> con la quale accedere al servizio.</p> 
  			
  			<form align = "center">
  			
  				<fieldset>
  				
    				<input class = "text ui-widget-content ui-corner-all" id = "usernameResetPassword" type = "text" name = "usernameResetPassword" placeholder = "Username" />
  				
  				</fieldset>
  				
  			</form>
  			
		</div>
		
		<div class = "dialogMessaggio" id = "dialogMessaggioResetPasswordSuccesso" title = "Reimposta Password">
		
  			<p><span class = "ui-icon ui-icon-circle-check" style = "float: left; margin: 0 7px 50px 0;"></span>
    			La <b>Password Provvisoria</b> sarà inviata all'indirizzo email dell'utente specificato!
  			</p>
  			
		</div>			
		
		<script>
	
			function animazioneApertura() {
				
				var panelLogo = $( "#panelLogo" );
				var panelLogin = $( "#panelLogin" );
				
				panelLogo.hide();
				panelLogin.hide();
				
				panelLogo.show( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
					panelLogin.show( "drop", {direction : "left", easing: "swing"} );
				});	
				
			}
			
			function animazioneChiusura() {
				
				var panelLogo = $( "#panelLogo" );
				var panelLogin = $( "#panelLogin" );
				
				panelLogin.hide( "drop", {direction : "left", easing: "swing"}, function() {
					panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500});
				});
				
			}
			
			$(window).load(function() {
				
				animazioneApertura();
				
			});
			
			function goTo(address) {
				
				var panelLogo = $( "#panelLogo" );
				var panelLogin = $( "#panelLogin" );
				
				panelLogin.hide( "drop", {direction : "left", easing: "swing"}, function() {
					panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
						window.location = address;
					});
				});
				
			}
		
			$(function() {
				
				var panelLogin = $( "#panelLogin" );
				var username = $( "#username" );
				var password = $( "#password" );			
				var avviso = $( "#avviso" );
				var campi = $( [] ).add(username).add(password);
				
				function inizializzaCampi( targetCampi ) {
					targetCampi.removeClass( "ui-state-error" );
				}
				
				function mostraErroreCampi( targetCampi ) {
					targetCampi.addClass( "ui-state-error" );
				}
				
				function aggiornaAvviso( string ) {
		    	      avviso.text( string ).addClass( "ui-state-highlight" );
		    	}
				
				function controllaLunghezzaCampo( target, nomeCampo, min, max ) {
		    	      if ( target.val().length > max || target.val().length < min ) {
		    	    	  	target.addClass( "ui-state-error" );
		    	        	//aggiornaAvviso( "La lunghezza di " + nomeCampo + " deve essere compresa tra " + min + " e " + max + "." );
		    	        	panelLogin.effect( "shake" );
		    	        	return false;
		    	      } else {		    	    	  
		    	        return true;
		    	      }
		    	}
				
				function login( u, p ) {
					var campiValidi = true;
					
					campiValidi = campiValidi && controllaLunghezzaCampo(username, "Username", 3, 25);
					campiValidi = campiValidi && controllaLunghezzaCampo(password, "Password", 3, 25);
					
					if ( campiValidi ) {
						$.post("http://localhost:8080/WebVoyager/Login", {username: u, password : p})
						.done(function() {
							goTo("http://localhost:8080/WebVoyager/Home.jsp");
							/*panelLogin.hide( "drop", {direction : "left", easing: "swing"}, function() {
								panelLogo.hide( "drop", {direction : "up", easing: "easeOutBounce", duration: 1500}, function() {
									window.location = "http://localhost:8080/WebVoyager/Home.jsp";
								});
							});	*/											
						})
						.fail(function() {
							panelLogin.effect( "shake", function() {
								mostraErroreCampi( campi );
							});
							
						});
						
					}
					
				}
				
				$( "#buttonLogin" ).click( function() {
					inizializzaCampi(campi);
					var mUsername = username.val();
					var mPassword = password.val();
					login(mUsername, mPassword);
				});
				
				$( "#dialogResetPassword" ).dialog({    
					title: "Reimposta Password",
				    autoOpen: false,
				    modal: true,
				    draggable: false,
				    closeOnEscape: true,
				    height: 230,
				    width: 350,	
				    open: function() {
				    	$( "#usernameResetPassword" ).val("");
				    },
				    show: {
				    	effect: "blind"
				    },
				    hide: {
				    	effect: "blind"
				    },
				    buttons: {
					    "Ok": function() {
			   			    var mUsername = $( "#usernameResetPassword" ).val();
			   			          
			   			    $.post("http://localhost:8080/WebVoyager/ResetPassword", {username : mUsername})
			   			    .done(function() {
				   			    $( "#dialogMessaggioResetPasswordSuccesso" ).dialog( "open" );
			   			    })
			   			    .always(function() {
				   			    $( "#dialogResetPassword" ).dialog( "close" );
			   			    });    			          
					    },
					    "Annulla": function() {
			   			    $( this ).dialog( "close" );
					    }
				    }
				});
				
				$( "#buttonResetPassword" ).click(function() {
			        $( "#dialogResetPassword" ).dialog( "open" );
			        return false;
			    });
				
			});
	
		</script>
	
	</body>
	
</html>