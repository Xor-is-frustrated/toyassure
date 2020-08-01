var clientName="";
var customerName="";
var channelOrderId="";
var orderStatus="";
var orderId=-1;
var channelName="INTERNAL";
var fileData = [];
var errorData = [];
var processCount = 0;
var successCount =0;

function getAllInternalOrdersUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/order/internal";
}

function getOrderUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/order/";
}

function addOrderItemsUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/orderitem/assure";
}

function getOrderItemsUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/orderitem/order";
}

//csv related functions
function processData(){
	var file = $('#orderFile')[0].files[0];
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
		getAllorders();
		var $file = $('#orderFile');
		$file.val('');
		$('#orderFileName').html("Choose File");
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
	row['orderId']=orderId;
	var json = JSON.stringify(row);
	console.log("upload rows json"+json);
	var url = addOrderItemsUrl();
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
	var $file = $('#orderFile');
	$file.val('');
	$('#orderFileName').html("Choose File");
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
	var $file = $('#orderFile');
	var fileName = $file.val();
	$('#process-data').prop('disabled',false);
	$("#download-errors").prop('disabled', true);
	$('#orderFileName').html(fileName);
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
function addorder(){
	clientName=$('#upload-order-form input[name=clientName]').val();
	customerName=$('#upload-order-form input[name=customerName]').val();
	channelOrderId=$('#upload-order-form input[name=channelOrderId]').val();
	var form={};
	form['clientName']=clientName;
	form['customerName']=customerName;
	form['channelOrderId']=channelOrderId;
	form['channelName']=channelName;

	var json = JSON.stringify(form);
	var url = getAllInternalOrdersUrl();

	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			console.log(response);
			orderId=response.id;
			console.log("addorder order id"+orderId);
			processData();  
		},
		error: handleAjaxError
	});
	return false;
}


function getorder(id){
	var url = getOrderUrl()+"/"+id;
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			clientName=data.clientName;
			customerName=data.customerName;
			channelOrderId=data.channelOrderId;
			orderStatus=data.status;
		},
		error: handleAjaxError
	});
}

function getAllorders(){
	var url = getAllInternalOrdersUrl();
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			orderTable(data);  
		},
		error: handleAjaxError
	});
}

function addorderItemCSV(){
	var $form = $("#order-add-form");
	var json = toJson($form);
	var url = getAllInternalOrdersUrl();
	console.log("addorder json form"+json);
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$('#add-order-modal').modal('toggle');
			successPopup("order/Customer is added");
			getAllorders();  
		},
		error: handleAjaxError
	});
	return false;
}


function getOrderItem(id){
	getorder(id);
	var url = getOrderItemsUrl() + "/" + id;
	console.log("getorderitem url"+url);
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			EditModal(data);   
		},
		error: handleAjaxError
	});	
}


//modal related functions
function uploadModal(){
	resetUploadDialog(); 	
	$("#download-errors").prop('disabled', true);
	$("#process-data").prop('disabled', true);
	$('#upload-order-modal').modal('toggle');
}

function EditModal(data){

	//display krna h
	$('#order-status').text("Status: "+orderStatus);
	$('#client-name').text("Client: "+clientName);
	$('#customer-name').text("Customer: "+customerName);
	$('#channel-order-id').text("Channel Order Id: "+channelOrderId);
	var $tbody = $('#orderitems-table').find('tbody');
	$tbody.empty();
	console.log("orderItemsTable" + data);
	for(var i in data){
		var e = data[i];
		console.log(e);
		var row = '<tr>'
		+ '<td>' + e.productName + '</td>'
		+ '<td>'  + e.orderedQuantity + '</td>'
		+ '<td>'  + e.allocatedQuantity + '</td>'
		+ '<td>'  + e.fulfilledQuantity + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}

	$('#edit-order-modal').modal('toggle');
}

function orderTable(data){
	var $tbody = $('#order-table').find('tbody');
	$tbody.empty();
	console.log("orderTable");
	for(var i in data){
		var e = data[i];

		var buttonHtml = ' <button class="btn btn-dark btn-sm" onclick="getOrderItem(' + e.id + ')">View</button>';
		var row = '<tr>'
		+ '<td>' + e.clientName + '</td>'
		+ '<td>'  + e.customerName + '</td>'
		+ '<td>'  + e.channelOrderId + '</td>'
		+ '<td>'  + e.status + '</td>'
		+ '<td>' + buttonHtml + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}
}


// init
function init(){
	getAllorders();
	
	$('#refresh-data').click(getAllorders);
	$('#upload-data').click(uploadModal);
	$('#process-data').click(addorder);
	$('#download-errors').click(downloadErrors);
	$('#orderFile').on('change', updateFileName);

}

$(document).ready(init);