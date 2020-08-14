//crud functions ..addClient, getClient, getAllClients,updateClient
//modal functions ..addmodal, editmodal, clientTable
//button disable function .. checkAddbuttonDisable , checkEditbuttonDisable

var clientName="";

function getClientUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/client";
}


//crud functions
function addClient(){
	var $form = $("#client-add-form");
	var json = toJson($form);
	var url = getClientUrl();
	console.log("addClient json form"+json);
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$('#add-client-modal').modal('toggle');
			successPopup("Added Successfully!!");
			getAllClients();  
		},
		error: handleAjaxError
	});
	return false;
}

function getClient(id){
	var url = getClientUrl() + "/" + id;
	console.log("getClient url"+url);
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			EditModal(data);   
		},
		error: handleAjaxError
	});	
}

function searchClient(){
	var url="";
	var type=$("#inputClientType").val();
	if(type==""){
			getAllClients();
	}else{
	if(type=="CLIENT"){

		 url= getClientUrl()+"/clients";
	}else{
	    url= getClientUrl()+"/customers";
	}
	// call api
	$.ajax({
		url: url,
		type: 'GET',
		success: function(response) {
	   		ClientTable(response);
	   	},
	   	error: handleAjaxError
	   });
    }
}

function getAllClients(){
	var url = getClientUrl();
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			ClientTable(data);  
		},
		error: handleAjaxError
	});
}

function updateClient(event){
	var id = $("#client-edit-form input[name=id]").val();	
	var url = getClientUrl() + "/" + id;
	var $form = $("#client-edit-form");
	var json = toJson($form);
	console.log("updateClient json form"+json);
	$.ajax({
		url: url,
		type: 'PUT',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$('#edit-client-modal').modal('toggle');
			successPopup("Client is updated");
			getAllClients();   
		},
		error: handleAjaxError
	});
	return false;
}


//modal display functions
function AddModal(){
	$("#add-client").prop('disabled', true);
	$( '#client-add-form' ).each(function(){
		this.reset();
	});
	$('#add-client-modal').modal('toggle');
}

function EditModal(data){
	$("#client-edit-form input[name=name]").val(data.name);	
	$("#client-edit-form input[name=id]").val(data.id);	
	$("#update-client").prop('disabled', true);
	clientName=data.name;
	$('#edit-client-modal').modal('toggle');
}

function ClientTable(data){
	var $tbody = $('#client-table').find('tbody');
	$tbody.empty();
	for(var i in data){
		var e = data[i];
		var buttonHtml = ' <button class="btn btn-dark btn-sm" onclick="getClient(' + e.id + ')">Edit</button>';
		var row = '<tr>'
		+ '<td>' + e.name + '</td>'
		+ '<td>'  + e.type + '</td>'
		+ '<td>' + buttonHtml + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}
}


//buttons disable functions
function checkAddButtonDisable(){
	var name= $('#client-add-form input[name=name]').val();
	if (name.length > 0) {
		$("#add-client").prop("disabled", false);
	}
	else {
		$("#add-client").prop("disabled", true);
	} 
}

function checkEditButtonDisable(){
	var name= $('#client-edit-form input[name=name]').val();
	if (name.length>0 && name!=clientName){
		$("#update-client").prop('disabled', false);
	}else {
		$("#update-client").prop("disabled", true);
	}
}


// init
function init(){
	getAllClients();
	$('#client-add-form input[name=name]').on("input",checkAddButtonDisable);
	$('#client-edit-form input[name=name]').on("input",checkEditButtonDisable);
	$('#add-client').click(addClient);
	$('#update-client').click(updateClient);
	$('#refresh-data').click(getAllClients);
	$('#search-data').click(searchClient);
	$('#add-data').click(AddModal);

}

$(document).ready(init);





