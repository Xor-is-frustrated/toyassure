//crud functions ..addchannel, getchannel, getAllchannels,updatechannel
//modal functions ..addmodal, editmodal, channelTable
//button disable function .. checkAddbuttonDisable , checkEditbuttonDisable

var channelName="";

function getChannelUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/channel";
}


//crud functions
function addChannel(){
	var $form = $("#channel-add-form");
	var json = toJson($form);
	var url = getChannelUrl();
	console.log("addchannel json form"+json);
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$('#add-channel-modal').modal('toggle');
			successPopup("Channel is added");
			getAllChannels();  
		},
		error: handleAjaxError
	});
	return false;
}

function getChannel(id){
	var url = getChannelUrl() + "/" + id;
	console.log("getchannel url"+url);
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			EditModal(data);   
		},
		error: handleAjaxError
	});	
}

function getAllChannels(){
	var url = getChannelUrl();
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			channelTable(data);  
		},
		error: handleAjaxError
	});
}

function updateChannel(event){
	var id = $("#channel-edit-form input[name=id]").val();	
	var url = getChannelUrl() + "/" + id;
	var $form = $("#channel-edit-form");
	var json = toJson($form);
	console.log("updatechannel json form"+json);
	$.ajax({
		url: url,
		type: 'PUT',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$('#edit-channel-modal').modal('toggle');
			successPopup("channel is updated");
			getAllChannels();   
		},
		error: handleAjaxError
	});
	return false;
}


//modal display functions
function AddModal(){
	$("#add-channel").prop('disabled', true);
	$( '#channel-add-form' ).each(function(){
		this.reset();
	});
	$('#add-channel-modal').modal('toggle');
}

function EditModal(data){
	$("#channel-edit-form input[name=name]").val(data.name);	
	$("#channel-edit-form input[name=id]").val(data.id);	
	$("#update-channel").prop('disabled', true);
	channelName=data.name;
	$('#edit-channel-modal').modal('toggle');
}

function channelTable(data){
	var $tbody = $('#channel-table').find('tbody');
	$tbody.empty();
	for(var i in data){
		var e = data[i];
		var buttonHtml = ' <button class="btn btn-dark btn-sm" onclick="getChannel(' + e.id + ')">Edit</button>';
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
	var name= $('#channel-add-form input[name=name]').val();
	if (name.length > 0) {
		$("#add-channel").prop("disabled", false);
	}
	else {
		$("#add-channel").prop("disabled", true);
	} 
}

function checkEditButtonDisable(){
	var name= $('#channel-edit-form input[name=name]').val();
	if (name.length>0 && name!=channelName){
		$("#update-channel").prop('disabled', false);
	}else {
		$("#update-channel").prop("disabled", true);
	}
}


function searchChannel(){
var url="";
	var type=$("#inputChannelType").val();
	if(type==""){
			getAllChannels();
	}else{
	if(type=="SELF"){

		 url= getChannelUrl()+"/self";
	}else{
	    url= getChannelUrl()+"/channel";
	}
	// call api
	$.ajax({
		url: url,
		type: 'GET',
		success: function(response) {
	   		channelTable(response);
	   	},
	   	error: handleAjaxError
	   });
    }

}

// init
function init(){
	getAllChannels();
	$('#channel-add-form input[name=name]').on("input",checkAddButtonDisable);
	$('#channel-edit-form input[name=name]').on("input",checkEditButtonDisable);
	$('#add-channel').click(addChannel);
	$('#update-channel').click(updateChannel);
	$('#refresh-data').click(getAllChannels);
	$('#add-data').click(AddModal);
	$('#search-data').click(searchChannel);

}

$(document).ready(init);





