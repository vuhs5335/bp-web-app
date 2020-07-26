
$(document).ready(function(){
	
});

$(document).on('click', '.edit-link', function(){
	
	var bpId = $(this).attr("editId");	
	
	editRC([{name:'editId', value:bpId}]);
	
});

$(document).on('click', '.delete-link', function(){
	
	var proceed = confirm("Delete?");
		
	if (!proceed) {
		return;
	}
	
	var bpId = $(this).attr("editId");	
	
	deleteRC([{name:'editId', value:bpId}]);
});


function bindDraggable(){
	
}


