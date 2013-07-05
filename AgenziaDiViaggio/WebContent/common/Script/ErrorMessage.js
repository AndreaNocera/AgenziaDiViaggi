/*$(function() {
	
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
	
});*/


(function( $ ) {
	 
    $.fn.messaggioErrore = function() {
    	
    	return this.dialog({
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
 
    };
 
}( jQuery ));

(function( $ ) {
	 
	$.widget("ui.errorDialog", {  
		
		_create: function() {
			
			return this.dialog({
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
			
		}
		  
	});  
 
}( jQuery ));
