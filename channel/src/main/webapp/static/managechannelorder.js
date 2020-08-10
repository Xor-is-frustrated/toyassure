var clientName="";
var customerName="";
var channelOrderId="";
var orderStatus="";
var orderId=-1;
var orderedQuantity=0;
var sellingPrice=0;
var channelName="";
var fileData = [];
var errorData = [];
var processCount = 0;
var successCount =0;

function getAllExternalOrdersUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/order/external";
}

function getOrderUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/order";
}

function addOrderItemsUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/orderitem/channel";
}

function getOrderItemsUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/orderitem/order";
}


//crud functions
function addorder(){
	console.log("add order");
	clientName=$('#upload-order-form input[name=clientName]').val();
	customerName=$('#upload-order-form input[name=customerName]').val();
	channelOrderId=$('#upload-order-form input[name=channelOrderId]').val();
	channelName=$('#upload-order-form input[name=channelName]').val();
	var form={};
	form['clientName']=clientName;
	form['customerName']=customerName;
	form['channelOrderId']=channelOrderId;
	form['channelName']=channelName;

	var json = JSON.stringify(form);
	var url = getOrderUrl();

	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			orderId=response.id;
			channelName=response.channelName;
			clientName=response.clientName;
			customerName=response.customerName;
			channelOrderId=response.channelOrderId;
			orderStatus=response.status;
			$('#upload-order-modal').modal('toggle');
			console.log("addorder order id"+orderId);
			getOrderItemAndAddOrderItemModalOpen(orderId);
			$('#add-orderitems-modal').modal('toggle');
		},
		error: handleAjaxError
	});
	return false;
}


function getorder(id){
	var url = getOrderUrl()+"/"+id;
	console.log("get order")
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {

			clientName=data.clientName;
			customerName=data.customerName;
			channelOrderId=data.channelOrderId;
			orderStatus=data.status;
			orderId=data.id;
			console.log("get order"+data.clientName);
			console.log("get order"+data.customerName);
			console.log("get order"+data.channelOrderId);
		},
		error: handleAjaxError
	});
}

function getAllorders(){
	var url = getAllExternalOrdersUrl();
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			orderTable(data);  
		},
		error: handleAjaxError
	});
}

function allocateOrder(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	var url = baseUrl + "/api/orderitem/allocate/"+orderId;
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			getOrderItem(orderId); 
			getAllorders(); 
		},
		error: handleAjaxError
	});
}

function fulfillOrder(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	var url = baseUrl + "/api/orderitem/fulfill/"+orderId;
	var req = new XMLHttpRequest();
	req.open("GET", url, true);
	req.responseType = "blob";

	req.onload = function (event) {
		var blob = req.response;
		console.log(blob.size);
		var link=document.createElement('a');
		link.href=window.URL.createObjectURL(blob);
		link.download="Receipt.pdf";
		link.click();
		
	};

	req.send();
	getOrderItem(orderId);

	

}

function addorderItem(){
	var channelSkuId=$('#add-orderitem-form input[name=channel-skuid]').val();
	var orderedQuantity=$('#add-orderitem-form input[name=orderedQuantity]').val();
	var sellingPrice=$('#add-orderitem-form input[name=sellingPrice]').val();
	var form={};
	form['orderId']=orderId.toString();
	form['channelSkuId']=channelSkuId;
	form['orderedQuantity']=orderedQuantity;
	form['sellingPrice']=sellingPrice;


	var json = JSON.stringify(form);
	var url = addOrderItemsUrl();
	console.log("addorder json form"+json);
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {			
			getOrderItemAndAddOrderItemModalOpen();  
		},
		error: handleAjaxError
	});
	return false;
}

function getOrderItemAndAddOrderItemModalOpen(){
	getorder(orderId);
	var url = getOrderItemsUrl() + "/" + orderId;
	console.log("getOrderItemAndAddOrderItemModalOpen url"+url);
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			editItemsModal(data);   
		},
		error: handleAjaxError
	});	
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

function getOrderItemSingle(id){
	getorder(id);
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	var url= baseUrl + "/api/orderitem/"+id;
	console.log("getOrderItemSingle url"+url);
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			EditItemModal(data);   
		},
		error: handleAjaxError
	});	
}

function updateOrderItem(event){
	var id = $("#orderitem-edit-form input[name=id]").val();	
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	var url= baseUrl + "/api/orderitem/"+id;
	var $form = $("#orderitem-edit-form");
	var json = toJson($form);
	console.log("update order item json form"+json);
	$.ajax({
		url: url,
		type: 'PUT',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$('#edit-orderitem-modal').modal('toggle');
			successPopup("item is updated");
			getOrderItemAndAddOrderItemModalOpen();

		},
		error: handleAjaxError
	});
	return false;
}

function deleteOrderItem(id){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	var url= baseUrl + "/api/orderitem/"+id;

	$.ajax({
		url: url,
		type: 'DELETE',
		success: function(data) {
			getOrderItemAndAddOrderItemModalOpen();  
		},
		error: handleAjaxError
	});
}
//modal related functions


function editItemsModal(data){
	$('#channel-name').text("Channel: "+channelName);
	$('#client-name').text("Client: "+clientName);
	$('#customer-name').text("Customer: "+customerName);
	$('#channel-order-id').text("OrderId: "+channelOrderId);
	var $tbody = $('#orderitems-add-table').find('tbody');
	$tbody.empty();
	console.log("orderItemsaddTable" + data);
	for(var i in data){
		var e = data[i];
		console.log(e);
		var buttonHtml = ' <button class="btn btn-dark btn-sm" onclick="getOrderItemSingle(' + e.id + ')">Edit</button>';
		buttonHtml +=  ' <button class="btn btn-dark btn-sm" id="delete-item" onclick="deleteOrderItem(' + e.id + ')">Delete</button>'
		var row = '<tr>'
		+ '<td>' + e.productName + '</td>'
		+ '<td>'  + e.orderedQuantity + '</td>'
		+ '<td>'  + e.sellingPrice + '</td>'
		+ '<td>' + buttonHtml + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}
}

function EditItemModal(data){
	$("#orderitem-edit-form input[name=orderedQuantity]").val(data.orderedQuantity);	
	$("#orderitem-edit-form input[name=sellingPrice]").val(data.sellingPrice);	
	$("#orderitem-edit-form input[name=id]").val(data.id);	
	$("#orderitem-edit-form input[name=allocatedQuantity]").val(data.allocatedQuantity);	
	$("#orderitem-edit-form input[name=fulfilledQuantity]").val(data.fulfilledQuantity);	
	// $("#update-client").prop('disabled', true);
	orderedQuantity=data.orderedQuantity;
	sellingPrice=data.sellingPrice;
	$('#edit-orderitem-modal').modal('toggle');
}

function EditModal(data){
	console.log("edit modal"+data);
	$('#order-status').text("Status: "+orderStatus);
	$('#client-name').text("Client: "+clientName);
	$('#customer-name').text("Customer: "+customerName);
	$('#channel-order-id').text("OrderId: "+channelOrderId);
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
	if(orderStatus=='CREATED' || orderStatus=='ALLOCATED'){
		$('#allocate-order').prop('disabled',false);
		$('#fulfill-order').prop('disabled',true);

	}else{
		$('#allocate-order').prop('disabled',true);
		$('#fulfill-order').prop('disabled',false);
	}

	
}

function orderTable(data){
	var $tbody = $('#order-table').find('tbody');
	$tbody.empty();
	console.log("orderTable");
	for(var i in data){
		var e = data[i];

		var buttonHtml = ' <button class="btn btn-dark btn-sm" onclick="getOrderItemModalOpen(' + e.id + ')">View</button>';
		var row = '<tr>'
		+ '<td>' + e.clientName + '</td>'
		+ '<td>'  + e.customerName + '</td>'
		+ '<td>'  + e.channelOrderId + '</td>'
		+ '<td>'  + e.channelName + '</td>'
		+ '<td>'  + e.status + '</td>'
		+ '<td>' + buttonHtml + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}
}

function getOrderItemModalOpen(id){
	getOrderItem(id);
	$('#edit-order-modal').modal('toggle');
}

function uploadModal(){

	// $("#add-channel").prop('disabled', true);
	$( '#add-order-modal' ).each(function(){
		this.reset();
	});
	$('#upload-order-modal').modal('toggle');


}

// init
function init(){
	getAllorders();
	$('#upload-data').click(uploadModal);
	$('#refresh-data').click(getAllorders);
	$('#add-orderitem').click(addorderItem);
	$('#allocate-order').click(allocateOrder);
	$('#fulfill-order').click(fulfillOrder);
	$('#create-order').click(addorder);
	$('#update-orderitem').click(updateOrderItem);
	

}

$(document).ready(init);