$(function() {
	
	$( ".dialogMessaggioErrore" ).dialog({
		autoOpen: true,
		draggable: false,
		modal: true,
		buttons: {
			Ok: function() {
				parent.history.back();
		        return false;
			}
		}
	});
	
});