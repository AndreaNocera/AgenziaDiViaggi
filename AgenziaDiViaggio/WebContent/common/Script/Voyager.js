/**
 * @project WebVoyager
 * 
 * @package WebContent/common/Script
 * 
 * @name Voyager.js
 *
 * @description
 *
 * @author Giacomo Marciani
 * 
 */

$(function() {

	$( document ).tooltip();
	
	$( "button" ).button();	
	
	$( ".checkbox" ).button();
	
	$( ".radio" ).buttonset();
	
	$( ".selectable" ).selectable();
	
	$( ".datepicker" ).datepicker( {
		show: {
			effect: "blind"
		},
		hide: {
			effect: "blind"
		}
	});
	
	$( ".buttonLogin" ).button({
		icons: { primary: "ui-icon-locked" }
	});
	
	$( ".buttonOption" ).button({
		icons: { primary: "ui-icon-gear" }
	});
	
	$( "#buttonAmministrazioneUtenti" ).button({
		icons: { primary: "ui-icon-person" }
	});
	
	$( "#buttonGestioneCatalogo" ).button({
		icons: { primary: "ui-icon-note" }
	});
	
	$( "#buttonGestioneOfferta" ).button({
		icons: { primary: "ui-icon-cart" }
	});
	
	$( "#buttonGestioneProfilo" ).button({
		icons: { primary: "ui-icon-gear" }
	});
	
	$( "#buttonLogout" ).button({
		icons: { primary: "ui-icon-power" }
	});	
	
	$( ".buttonAdd" ).button({
		icons: { primary: "ui-icon-plus" }
	});
	
	$( ".buttonEdit" ).button({
		icons: { primary: "ui-icon-pencil" }
	});
	
	$( ".buttonRemove" ).button({
		icons: { primary: "ui-icon-trash" }
	});
	
	$( ".dialog" ).keypress(function(event) {				
		if(event.keyCode == '13'){	
			event.preventDefault();
		}
	});
	
	$( ".dialogMessaggio" ).dialog({
		autoOpen: false,
		draggable: false,
		resizable: false,
		modal: true,
		height: "200",
	    width: "250",
		show: {
			effect: "blind"
		},
		hide: {
			effect: "blind"
		},
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
			}
		}
	}); 
	
	$( ".dialogMessaggioReload" ).dialog({
		autoOpen: false,
		draggable: false,
		resizable: false,
		modal: true,
		height: "200",
	    width: "250",
		show: {
			effect: "blind"
		},
		hide: {
			effect: "blind"
		},
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
				setTimeout(function() {
					location.reload();
				}, 1250);
			}
		}
	});
	
});