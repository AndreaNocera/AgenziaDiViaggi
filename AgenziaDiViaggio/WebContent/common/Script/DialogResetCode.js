$(function() {
  				
	$( document ).tooltip(); 
	
	$( "#dialogFormReimpostaPassword" ).dialog({    				
		autoOpen: false,
		height: 300,
		width: 350,
		modal: true,
		buttons: {
			"Ok": function() {
				var valid = true;
				var username = $( "#usernameResetCode" ).val();
				  
				$.get("http://localhost:8080/WebVoyager/ResetCode?username=" + username);
				  
				if ( valid ) {
					$( "#dialogMessaggioResetCodeSuccesso" ).dialog( "open" );
					$( this ).dialog( "close" );
				} else {
					$( "#dialogMessaggioResetCodeFallimento" ).dialog( "open" );
					$( this ).dialog( "close" );
				}
			},
			"Annulla": function() {
				$( this ).dialog( "close" );
			}
		}
	});
});