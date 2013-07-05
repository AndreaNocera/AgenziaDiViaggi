$(function() {
	
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