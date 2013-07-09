/**
 * @project WebVoyager
 * 
 * @package WebContent/common/Script
 * 
 * @name General.js
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
	
	$( ".buttonOk" ).button({
		icons: { primary: "ui-icon-check" }
	});
	
	$( ".buttonCancel" ).button({
		icons: { primary: "ui-icon-close" }
	});
	
	$( ".buttonLock" ).button({
		icons: { primary: "ui-icon-locked" }
	});
	
	$( ".buttonUnlock" ).button({
		icons: { primary: "ui-icon-unlocked" }
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
	
	$( ".buttonSearch" ).button({
		icons: { primary: "ui-icon-search" }
	});
	
	$( ".dialogMessaggio" ).dialog({
		autoOpen: false,
		draggable: false,
		modal: true,
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
		modal: true,
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
				}, 2000);
			}
		}
	});
	
	$( ".dialogMessaggioErrore" ).dialog({
		autoOpen: true,
		draggable: false,
		modal: true,
		show: {
			effect: "shake"
		},
		hide: {
			effect: "blind"
		},
		buttons: {
			Ok: function() {
				$( this ).hide();
				parent.history.back();
		        return false;
			}
		}
	});
	
});