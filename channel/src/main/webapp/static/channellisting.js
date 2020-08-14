var clientName="";
var channelName="";
var fileData = [];
var errorData = [];
var processCount = 0;
var successCount =0;

function getChannellistingUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/channellisting";
}

function getOrderUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/orderitem";
}


//csv related functions
function processData(){
	var file = $('#channellistingFile')[0].files[0];
	clientName=$('#clientSelected').val();
	channelName=$('#channelSelected').val();
	$('#process-data').prop('disabled',true);
	//Reset various counts
	processCount = 0;
	fileData = [];
	errorData = [];
	successCount=0;
	readFileData(file, readFileDataCallback);
}

function readFileDataCallback(results){
	fileData = results.data;
	console.log("readFileDataCallback");
	console.log("the length of  the file is");
	console.log(fileData.length);
	if(fileData.length>5000)
	{
		alert("File limit is 5000");
		return ;
	}
	
	uploadRows();
}

function uploadRows(){
	updateUploadDialog();
	
	//If everything processed then return
	if(processCount==fileData.length){
		getAllChannellistings();
		var $file = $('#channellistingFile');
		$file.val('');
//		$('#inputClientName').val('');
//        $('#inputChannelName').val('');
		$('#channellistingFileName').html("Choose File");
		if(errorData.length>0){
			$("#download-errors").prop('disabled', false);
		}
		else{
			$("#download-errors").prop('disabled', true);
			
		}

		
		return;
	}
	
	//Process next row
	var row = fileData[processCount];
	processCount++;
	row['clientName']=clientName;
	row['channelName']=channelName;
	var json = JSON.stringify(row);
	console.log("upload rows json"+json);
	var url = getChannellistingUrl();

	//Make ajax call
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			successCount++;
			uploadRows();  
		},
		error: function(response){
			row.error=response.responseText
			errorData.push(row);
			uploadRows();
		}
	});

}

function resetUploadDialog(){
	//Reset file name
	var $file = $('#channellistingFile');
	$file.val('');
	$('#channellistingFileName').html("Choose File");
	$('#inputClientName').val('');
	$('#inputChannelName').val('');



	//Reset various counts
	processCount = 0;
	fileData = [];
	errorData = [];
	successCount=0;
	//Update counts	
	updateUploadDialog();
}

function updateUploadDialog(){
	$('#rowCount').html("" + fileData.length);
	$("#errorCount").css("color", "black");
	if(errorData.length>0){
		$("#errorCount").css("color", "red");
	}
	$('#successCount').html("" + successCount);
	$('#errorCount').html("" + errorData.length);
}

function updateFileName(){
	var $file = $('#channellistingFile');
	var fileName = $file.val();
	$('#process-data').prop('disabled',false);
	$("#download-errors").prop('disabled', true);
	$('#channellistingFileName').html(fileName);
	processCount = 0;
	successCount=0;
	fileData = [];
	errorData = [];
	$('#rowCount').html("" + fileData.length);
	$('#successCount').html("" + successCount);
	$("#errorCount").css("color", "black");
	$('#errorCount').html("" + errorData.length);
}

function downloadErrors(){
	writeFileData(errorData);
}


//crud functions
function addChannellistingCSV(){
	var $form = $("#channellisting-add-form");
	var json = toJson($form);
	var url = getChannellistingUrl();
	console.log("addchannellisting json form"+json);
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$('#add-channellisting-modal').modal('toggle');
			successPopup("channellisting/Customer is added");
			getAllChannellistings();  
		},
		error: handleAjaxError
	});
	return false;
}

function getChannellisting(id){
	var url = getChannellistingUrl() + "/" + id;
	console.log("getchannellisting url"+url);
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			EditModal(data);   
		},
		error: handleAjaxError
	});	
}

function getAllChannellistings(){
	var url = getChannellistingUrl();
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			channellistingTable(data);  
		},
		error: handleAjaxError
	});
}


//modal related functions
function uploadModal(){
	resetUploadDialog(); 	
	$("#download-errors").prop('disabled', true);
	$("#process-data").prop('disabled', true);
	$('#upload-channellisting-modal').modal('toggle');
}



function channellistingTable(data){
	var $tbody = $('#channellisting-table').find('tbody');
	$tbody.empty();
	console.log("channellistingTable");
	for(var i in data){
		var e = data[i];
				
		// var buttonHtml = ' <button class="btn btn-dark btn-sm" onclick="getchannellisting(' + e.id + ')">Edit</button>';
		var row = '<tr>'
		+ '<td>' + e.channelName + '</td>'
		+ '<td>'  + e.clientName + '</td>'
		+ '<td>'  + e.channelSkuId + '</td>'
		+ '<td>'  + e.clientSkuId + '</td>'
		+ '<td>'  + e.productName + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}
}

function getAllChannels(){
	var url = getOrderUrl()+"/channels" ;
	$.ajax({
        url: url,
        type: 'GET',
        success: function(data) {
            displayChannelDropDownList(data);
        },
        error: handleAjaxError
	});
}

function getAllClients(){
    var url = getOrderUrl() +"/clients";
    	$.ajax({
            url: url,
            type: 'GET',
            success: function(data) {
                displayClientDropDownList(data);
            },
            error: handleAjaxError
    	});
}



function displayClientDropDownList(data){
//    $('#clientSelect').empty();
    $('#clientSelected').empty();
    var options = '<option value="" selected>Select Client</option>';
    $.each(data, function(index, value) {
        options += '<option value="' + value.name + '">' + value.name + '</option>';
    });
//    $('#clientSelect').append(options);
    $('#clientSelected').append(options);
}

function displayChannelDropDownList(data){
    $('#channelSelect').empty();
    $('#channelSelected').empty();
    var options = '<option value="" selected>Select Channel</option>';
    $.each(data, function(index, value) {
        if(value.name!="INTERNAL"){
            options += '<option value="' + value.name + '">' + value.name + '</option>';
        }
    });
    $('#channelSelect').append(options);
    $('#channelSelected').append(options);
}

function searchChannelListings(){
    var channelname=$('#channelSelect').val();
    if(channelname==""){
        getAllChannellistings();
    }
else{
    var url=getChannellistingUrl()+"/channel/"+channelname;
    console.log("channel listing search "+url);
    $.ajax({
                url: url,
                type: 'GET',
                success: function(data) {
                    channellistingTable(data);
                },
                error: handleAjaxError
        	});
}
}


// init
function init(){
	getAllChannellistings();
	getAllChannels();
	getAllClients();
	$('#refresh-data').click(getAllChannellistings);
	$('#upload-data').click(uploadModal);
	$('#process-data').click(processData);
	$('#search-data').click(searchChannelListings);
	$('#download-errors').click(downloadErrors);
	$('#channellistingFile').on('change', updateFileName);

}

$(document).ready(init);