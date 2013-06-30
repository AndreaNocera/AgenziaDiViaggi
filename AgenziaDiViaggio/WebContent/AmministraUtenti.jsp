<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
		<title>Voyager Gestisci Profilo</title>
		<link rel = "stylesheet" href = "Script/jquery-ui/css/ui-lightness/jquery-ui-1.10.3.custom.css" >
		<link rel = "stylesheet" href = "css/Main.css" >
		<script src = "Script/jquery-ui/js/jquery-1.9.1.js"></script>
		<script src = "Script/jquery-ui/js/jquery-ui-1.10.3.custom.js"></script>
		
		<script>
		
			window.onload = function() {
				
    			$( document ).tooltip();
    			
    			$( "button" ).button();
    			
    			$( "#buttonNuovo" ).button({
    				icons: { primary: "ui-icon-person" }
    			});
    			
    			$( "#buttonModifica" ).button({
    				icons: { primary: "ui-icon-pencil" }
    			}); 
    			
    			$( "#buttonRimuovi" ).button({
    				icons: { primary: "ui-icon-trash" }
    			});
    			
    			$( "#buttonCerca" ).button({
    				icons: { primary: "ui-icon-search" }
    			});
    			
    			$( "#selectable" ).selectable({
    			      stop: function() {
    			        var result = $( "#select-result" ).empty();
    			        $( ".ui-selected", this ).each(function() {
    			          var index = $( "#selectable li" ).index( this );
    			          result.append( " #" + ( index + 1 ) );
    			        });
    			      }
    			    });
    			
  			};
  			
  		</script>
	
	</head>
	
	<body>
	
		<div class = "panelLogo" align = "center">
				<img class = "logo" border = "0" src = "img/Voyager.png" >
		</div>
		
		<div class = "panelAmministrazioneUtenti" align = "center">
			<p class = "title">AMMINISTRAZIONE UTENTI</p>
			<div class = panelButton id = "panelButton" align = "center">
				<form name = "formAzioni" action = "AmministrazioneUtentiServlet" method = "GET">
					<p><input class = "text" id = "cerca" name = "cerca" type = "text" placeholder = "Cerca" />
					<button class = "buttonIconLabel" id = "buttonCerca" type = "submit" name = "action" value = "cerca">Cerca</button>
					<button class = "buttonIconLabel" id = "buttonNuovo" type = "submit" name = "action" value = "nuovo">Nuovo</button>
					<button class = "buttonIconLabel" id = "buttonModifica" type = "submit" name = "action" value = "modifica">Modifica</button>
					<button class = "buttonIconLabel" id = "buttonRimuovi" type = "submit" name = "action" value = "rimuovi">Rimuovi</button></p>
				</form>			
			</div>			
			
			<div class = "items" align = "center">
				<ol id = "selectable">
				<%  %>
					<li class = "ui-widget-content">Giacomo Marciani</li>
					<li class = "ui-widget-content">Giacomo Marciani</li>
					<li class = "ui-widget-content">Giacomo Marciani</li>
					<li class = "ui-widget-content">Giacomo Marciani</li>
					<li class = "ui-widget-content">Giacomo Marciani</li>
					<li class = "ui-widget-content">Giacomo Marciani</li>
					<li class = "ui-widget-content">Giacomo Marciani</li>
					<li class = "ui-widget-content">Giacomo Marciani</li>
					<li class = "ui-widget-content">Giacomo Marciani</li>
					<li class = "ui-widget-content">Giacomo Marciani</li>
					<li class = "ui-widget-content">Giacomo Marciani</li>
					<li class = "ui-widget-content">Giacomo Marciani</li>
				</ol>			
			</div>		
					
		</div>
	
	</body>

</html>