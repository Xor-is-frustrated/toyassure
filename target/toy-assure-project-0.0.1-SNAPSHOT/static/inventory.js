
function getInventoryUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/inventory/";
}


function getAllInventories(){
	var url = getInventoryUrl();
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			inventoryTable(data);  
		},
		error: handleAjaxError
	});
}


function inventoryTable(data){
	var $tbody = $('#inventory-table').find('tbody');
	$tbody.empty();
	console.log(data);
	for(var i in data){
		var e = data[i];
		
		var row = '<tr>'
		+ '<td>' + e.productName + '</td>'
		+ '<td>' + e.availableQuantity + '</td>'
		+ '<td>' + e.allocatedQuantity + '</td>'
		+ '<td>' + e.fulfilledQuantity + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}
}


$(document).ready(getAllInventories);








