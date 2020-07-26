$(document).ready(function(){
	bindDraggable();
});

function bindDraggable(){
	
	$(".draggable").draggable({  
		appendTo: 'body', 
		scroll : false, 
		containment: [ -140, 0, 150, 0], 
		axis: "x",
      	revert : 
      		function(event, ui) {
            	$(this).data("uiDraggable").originalPosition = {
                		top : 0,
                		left : 0
            		};
            	return !event;
           }, 
       start: 
    	   	function (event, ui) {
       			$('body').addClass("overflow");
    		}, 
       stop: 
    	   	function (event, ui) {
      			$('body').removeClass("overflow");
      			
      			// Calc element position.
      			var xpos   = ui.position.left;
      			var offset = $(this).offset().left;
      			var pos    = xpos + offset;
      			
      			var bpId = $(this).attr("editId");
      			
      			
      			
      			if (pos == -140) {
      				$(this).children('.edit-link').first().click();
      			}else if(pos == 150){
      			
      				var proceed = confirm("Delete?");
      				
      				if (!proceed) {
      					return;
					}
      				
      				deleteRC([{name:'editId', value:bpId}]);
      			}
       		}
});
}