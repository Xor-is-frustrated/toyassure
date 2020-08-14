
function getInventoryUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/inventory";
}

function getClientUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/client";
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
		+ '<td>' + e.clientName + '</td>'
		+ '<td>' + e.clientSkuId + '</td>'
		+ '<td>' + e.availableQuantity + '</td>'
		+ '<td>' + e.allocatedQuantity + '</td>'
		+ '<td>' + e.fulfilledQuantity + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}
}

function getAllClients(){
    var  url= getClientUrl()+"/clients";
    $.ajax({
    		url: url,
    		type: 'GET',
    		success: function(response) {
    	   		displayClientDropDownList(response);
    	   	},
    	   	error: handleAjaxError
    	   });

}

function displayClientDropDownList(data){
    $('#clientSelect').empty();
//    $('#clientSelected').empty();
    var options = '<option value="" selected>Select Client</option>';
    $.each(data, function(index, value) {
        options += '<option value="' + value.name + '">' + value.name + '</option>';
    });
    $('#clientSelect').append(options);
//    $('#clientSelected').append(options);
}

function searchClient(){

    var url="";
	var nameOfClient=$("#clientSelect").val();
	if(nameOfClient==""){
			getAllInventories();
	}else{
	    url= getInventoryUrl()+"/client/"+nameOfClient;

	// call api
	$.ajax({
		url: url,
		type: 'GET',
		success: function(response) {
	   		inventoryTable(response);
	   	},
	   	error: handleAjaxError
	   });
}
}

function init(){
    getAllInventories();
    getAllClients();
    $('#search-data').click(searchClient);
    $('#refresh-data').click(getAllInventories);
}

$(document).ready(init);








