
function getOrderUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/orderitem";
}


//BUTTON ACTIONS
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

var id=-1;
var order_open=false;
var inventoryQuantity=0;
var price=0;
var leave_page=false;
var button_delete=false;

function purchase(event)
{
	
			successPopup("Order Created");
			order_open=false;
			if(id!=-1 && order_open==false){
				$("#receipt").prop('disabled', false);
				$("#add-order").prop('disabled', true);

				$('[id=edit-item]').slice(0).prop("disabled", true);
				$('[id=delete-item]').slice(0).prop("disabled", true);
				
			}
			$('#purchase').prop('disabled',true);
    		
    			$("#inputSellingPrice").prop('disabled', true);
				$("#inputBarcode").prop('disabled', true);
				$("#inputQuantity").prop('disabled', true);

			$("#order-id").text("");
			$("#channel-name").text("");
			$("#client-name").text("");
			$("#customer-name").text("");
			$("#receipt").prop('disabled', true);
				$("#inputSellingPrice").prop('disabled', true);
				$("#inputBarcode").prop('disabled', true);
				$("#inputQuantity").prop('disabled', true);
				$('#purchase').prop('disabled',true);
				displayOrderList([]);	

}



function receipt(event)
{
	var baseUrl = $("meta[name=baseUrl]").attr("content");

	var url = baseUrl+"/api/order/receipt/"+id;
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

}

function addOrder(event){
	//Set the values to update
	var $form = $("#order-form");
	var json = toJson($form);
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	var url = baseUrl + "/api/orderitem/channel";
	// var url = getOrderUrl();

	console.log(json);
	console.log("in add order");
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$( '#order-form' ).each(function(){
   			 this.reset();
				});
			$("#add-order").prop("disabled", true);
			getOrderList();  
		},
		error: handleAjaxError
	});

	return false;
}

function updateOrder(event){
	$('#edit-order-modal').modal('toggle');
	//Get the ID
	var id = $("#order-edit-form input[name=id]").val();	
	var url = getOrderUrl() + "/" + id;
	console.log(url);
	//Set the values to update
	var $form = $("#order-edit-form");
	var json = toJson($form);

	$.ajax({
		url: url,
		type: 'PUT',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			getOrderList();   
		},
		error: handleAjaxError
	});

	return false;
}


function getOrderList(){
	var url = getOrderUrl() + "/order/" + id;
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			console.log(data);
			if(order_open==false){
				$("#receipt").prop('disabled', false);
				$("#add-order").prop('disabled', true);

				$('[id=edit-item]').slice(0).prop("disabled", true);
				$('[id=delete-item]').slice(0).prop("disabled", true);
				
				$('#purchase').prop('disabled',true);
    		
    			$("#inputSellingPrice").prop('disabled', true);
				$("#inputBarcode").prop('disabled', true);
				$("#inputQuantity").prop('disabled', true);
			}
			else{
				$("#inputSellingPrice").prop('disabled', false);
				$("#inputBarcode").prop('disabled', false);
				$("#inputQuantity").prop('disabled', false);
			}
			displayOrderList(data);  
		},
		error: handleAjaxError
	});
}

function deleteOrder(id){
	var url = getOrderUrl() + "/" + id;

	$.ajax({
		url: url,
		type: 'DELETE',
		success: function(data) {
			getOrderList();  
		},
		error: handleAjaxError
	});
}

// FILE UPLOAD METHODS
var fileData = [];
var errorData = [];
var processCount = 0;


function processData(){
	var file = $('#OrderFile')[0].files[0];
	readFileData(file, readFileDataCallback);
}

function readFileDataCallback(results){
	fileData = results.data;
	uploadRows();
}

function uploadRows(){
	//Update progress
	updateUploadDialog();
	//If everything processed then return
	if(processCount==fileData.length){
		return;
	}
	
	//Process next row
	var row = fileData[processCount];
	processCount++;
	
	var json = JSON.stringify(row);
	var url = getOrderUrl();

	//Make ajax call
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			uploadRows();  
		},
		error: function(response){
			row.error=response.responseText
			errorData.push(row);
			uploadRows();
		}
	});

}

function downloadErrors(){
	writeFileData(errorData);
}

//UI DISPLAY METHODS

function displayOrderList(data){
	var $thead = $('#order-table').find('thead');
	var $tbody = $('#order-table').find('tbody');

	$tbody.empty();
	$thead.empty();
	if(data.length==0){
		$("#purchase").prop('disabled', true);
		$("#total-amount").text("");
		return false;
	}
	if(order_open==true)
		$("#purchase").prop('disabled', false);
	else
		$("#purchase").prop('disabled', true);
	var total=0;
	var row = '<tr>'
		+ '<th scope="col">Product</th>'
		+ '<th scope="col">Quantity</th>'
		+ '<th scope="col">Price</th>'
		+ '<th scope="col">Amount</th>'
		+ '<th scope="col">Actions</th>'
		+ '</tr>';
		$thead.append(row);

	for(var i in data){
		
		var e = data[i];
		console.log(e);
		total+=e.orderedQuantity*e.sellingPrice;
		var buttonHtml ='<button class="btn btn-dark btn-sm" id="edit-item" onclick="displayEditOrder(' + e.id + ')">Edit</button>'
		buttonHtml +=  ' <button class="btn btn-dark btn-sm" id="delete-item" onclick="deleteOrder(' + e.id + ')">Delete</button>'
		var row = '<tr>'

		+ '<td>' + e.productName + '</td>'
		+ '<td> '  + e.orderedQuantity + '</td>'
		+ '<td> '  +  e.sellingPrice.toFixed(2) + '</td>'
		+ '<td> '  +  e.orderedQuantity*e.sellingPrice + '</td>'
		+ '<td>' + buttonHtml + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}
	$("#total-amount").text("Total: "+total.toFixed(2));
	if(order_open==false){
		$('[id=edit-item]').slice(0).prop("disabled", true);
		$('[id=delete-item]').slice(0).prop("disabled", true);
	}
}

function displayEditOrder(id){
	var url = getOrderUrl() + "/" + id;
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			displayOrder(data);   
		},
		error: handleAjaxError
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
	//Update counts	
	updateUploadDialog();
}

function updateUploadDialog(){
	$('#rowCount').html("" + fileData.length);
	$('#processCount').html("" + processCount);
	$('#errorCount').html("" + errorData.length);
}

function updateFileName(){
	var $file = $('#orderFile');
	var fileName = $file.val();
	$('#orderFileName').html(fileName);
}

function displayUploadData(){
	resetUploadDialog(); 	
	$('#upload-order-modal').modal('toggle');
}

function displayOrder(data){
	
	$("#order-edit-form input[name=orderedQuantity]").val(data.orderedQuantity);
	$("#order-edit-form input[name=channelSkuId]").val(data.channelSkuId);		
	$("#order-edit-form input[name=id]").val(data.id);		
	$("#order-edit-form input[name=orderId]").val(id);
	$("#order-edit-form input[name=sellingPrice]").val(data.sellingPrice);
	$("#update-order").prop("disabled", true);
	inventoryQuantity=data.orderedQuantity;
	price=data.sellingPrice;
	$('#edit-order-modal').modal('toggle');
}

function checkToCreateOrder(){
	if(id!=-1 && order_open== true){
		$('#discard-order-modal').modal('toggle');
	}
	else{
		createOrderModalToggle();
	}
}

function createOrderModalToggle(){
	// $("#add-channel").prop('disabled', true);
	$( '#add-order-modal' ).each(function(){
		this.reset();
	});
	$('#upload-order-modal').modal('toggle');

}

function createOrder(){
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
	var baseUrl = $("meta[name=baseUrl]").attr("content");
	var url = baseUrl+"/api/order";
	$.ajax({
		url: url,
		type: 'POST',
		data:json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			console.log(response);
			id=response.id;
			orderId=response.id;
			channelName=response.channelName;
			clientName=response.clientName;
			customerName=response.customerName;
			channelOrderId=response.channelOrderId;
			orderStatus=response.status;
			order_open=true;
				$("#order-form input[name=orderId]").val(id);
				$("#receipt").prop('disabled', true);
				$("#inputSellingPrice").prop('disabled', false);
				$("#inputBarcode").prop('disabled', false);
				$("#inputQuantity").prop('disabled', false);
				

			$('#upload-order-modal').modal('toggle');
			$("#order-id").text("Order: "+channelOrderId);
			$("#channel-name").text("Channel: "+channelName);
		$("#client-name").text("Client: "+clientName);
		$("#customer-name").text("Customer: "+customerName);
			$("#total-amount").text("");
			getOrderList();  
		},
		error: handleAjaxError
	});

	return false;
}

function deleteWholeOrder(createOrder){
	var baseUrl = $("meta[name=baseUrl]").attr("content");
	var url = baseUrl+"/api/order/"+id;

	$.ajax({
		url: url,
		type: 'DELETE',
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			console.log(response);
			id=-1;
			order_open=false;
			if(leave_page==false && button_delete==false)
				createOrderModalToggle();
			$("#order-id").text("");
			$("#channel-name").text("");
			$("#client-name").text("");
			$("#customer-name").text("");
			$("#receipt").prop('disabled', true);
				$("#inputSellingPrice").prop('disabled', true);
				$("#inputBarcode").prop('disabled', true);
				$("#inputQuantity").prop('disabled', true);
				$('#purchase').prop('disabled',true);
				displayOrderList([]);

		},
		error: handleAjaxError
	});

}


function discardOrder(){
	$('#discard-order-modal').modal('toggle');
	deleteWholeOrder(createOrder);
	
}

function orderDelete(){
	button_delete=true;
	deleteWholeOrder(createOrder);
}

function searchOrder(){
	var $tbody = $('#order-search-table').find('tbody');
	
	$tbody.empty();
	$('#order_id').text('');
	$('#search-by-id').prop('disabled',true);

	$('#search-order-modal').modal('toggle');
}

function toJsonDate($form){
	var serialized = $form.serializeArray();
	
	var dateobj1 = new Date(serialized[0]['value']); 
	var dateobj2 = new Date(serialized[1]['value']); 
	dateobj2.setDate(dateobj2.getDate() + 1);
  	
	
   var date1 = dateobj1.toISOString(); 
   var date2 = dateobj2.toISOString(); 
   
   	var i=0;
	var s = '';
	var data = {};
	for(s in serialized){
		if(i==0){
		data[serialized[s]['name']] = date1;
		}
		else{
			data[serialized[s]['name']] = date2;
		}
		i=i+1;
	}
	return data;
}

function searchByDate(){
		
	var $form = $("#order-search-form-date");
	var json = toJsonDate($form);
	json = JSON.stringify(json);

	var baseUrl = $("meta[name=baseUrl]").attr("content")
	var url = baseUrl + "/api/order/date" ;
	console.log(url);
	console.log(json);
	

	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			console.log("searchbydate "+response);
			getOrderSearchList(response);   
		},
		error: handleAjaxError
	});

	return false;

}

function searchById(){
	var id = $("#order-search-form-id input[name=order_id]").val();	
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	var url = baseUrl + "/api/order/" + id;
	console.log(url);
	
	

	$.ajax({
		url: url,
		type: 'GET',
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			getOrderSearchList(response);   
		},
		error: handleAjaxError
	});

	return false;
}

function getorder(){

	
}
function populateOrder(){
	
	var orderid=$('input[name="tableradio"]:checked').val();
	if(orderid==undefined){
		$("#error-message").text("Select one order!!");

	    $('#error-alert').modal('toggle');
	    setTimeout(function() {
	        $('#error-alert').modal('toggle');
	    }, 3000);
		return false;
	}
	console.log("populate order mein oredr id"+orderid)
	$("#search-order-modal").modal('toggle');
	id=orderid;
	var orderstatus="OPEN";
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	var url = baseUrl + "/api/order/" + id;
	$.ajax({
		url: url,
		type: 'GET',
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			console.log("populate order response status"+ response.status);

			 if(response.status=="CLOSE"){

			 	orderstatus="CLOSE";
			 	order_open=false;
			 	console.log("yes");

			 }
			 else{
			 	order_open=true;
			 }
		},
		error: handleAjaxError
	});
	$("#order-id").text("Order: "+channelOrderId);
	$("#channel-name").text("Channel: "+channelName);
	$("#client-name").text("Client: "+clientName);
	$("#customer-name").text("Customer: "+customerName);
	$("#order-form input[name=orderId]").val(id);
	getOrderList();
	
}

function getOrderSearchList(data){

	var $tbody = $('#order-search-table').find('tbody');
	console.log(data);
	$tbody.empty();

		if(Array.isArray(data)==false){
			var e = data;
			var date= e.datetime.month+" "+e.datetime.dayOfMonth+" "+e.datetime.year;
			var s=e.status;
			var buttonHtml ='<button class="btn btn-dark btn-sm"  onclick="populateOrder(' + e.id + ','+"'"+e.status+"'"+')">Select</button>'
			var row = '<tr>'

			+ '<td> <div class="radio"> <label> <input type="radio" id="tableradio" name="tableradio" value="'+e.id+'"> ' + e.id + '</label></div></td>'
			+ '<td> <div class="radiotext"><label for="express">'+date+'</label></div></td>'
			+ '<td> <div class="radiotext"><label for="express">'+e.status+'</label></div></td>'
			+ '<td> <div class="radiotext"><label for="express">'+e.itemsCount+'</label></div></td>'
			+ '</tr>';
			$tbody.append(row);
		}
		else{
			for(var i in data){
				var e = data[i];
				var date= e.datetime.month+" "+e.datetime.dayOfMonth+" "+e.datetime.year;
				var buttonHtml ='<button class="btn btn-dark btn-sm"  onclick="populateOrder(' + e.id + ')">Select</button>'
				var row = '<tr>'

			+ '<td> <div class="radio"> <label> <input type="radio" id="tableradio" name="tableradio" value="'+e.id+'"> ' + e.id + '</label></div></td>'
			+ '<td> <div class="radiotext"><label for="express">'+date+'</label></div></td>'
			+ '<td> <div class="radiotext"><label for="express">'+e.status+'</label></div></td>'
			+ '<td> <div class="radiotext"><label for="express">'+e.itemsCount+'</label></div></td>'
			+ '</tr>';
				$tbody.append(row);
			}
		}

		

		
	
	
}



//INITIALIZATION CODE
function init(){
	$('#add-order').click(addOrder);
	$('#update-order').click(updateOrder);
	$('#refresh-data').click(getOrderList);
	$('#upload-data').click(displayUploadData);
	$('#purchase').click(purchase);
	$('#delete-order').click(orderDelete);
	$('#receipt').click(receipt);
	$('#search-order').click(searchOrder);
	$('#search-by-id').click(searchById);
	$('#search-by-date').click(searchByDate);
	$('#create-order').click(checkToCreateOrder);
	$('#create-orderpojo').click(createOrder);
	$('#process-data').click(processData);
	$('#download-errors').click(downloadErrors);
	$('#discard-order').click(discardOrder);
	$('#populate-search').click(populateOrder);
	$('#orderFile').on('change', updateFileName);
	

}

function checkNumeric(){
	var value=$('#order-form input[name=sellingPrice]').val();
  if(( value.length ==   0) || ($.isNumeric(value))) 
 	{
 		
 		 var value=$('#order-form input[name=orderedQuantity]').val();
		  if(( value.length ==   0) || (Math.floor(value) == (value) && $.isNumeric(value))) 
		 	{
		 		return true;
		 		
		 	}
 		
 	}
 	return false;
  
}

function checkEditForm(){
	var value=$('#order-edit-form input[name=orderedQuantity]').val();
  if(( value.length ==   0) || (Math.floor(value) == (value) && $.isNumeric(value))) 
 	{
 		var value=$('#order-edit-form input[name=sellingPrice]').val();
	  if(( value.length ==   0) || ( $.isNumeric(value))) 
	 	{
	 		return true;
	 	}
 	}
 	return false;

}

$(document).ready(init);

$('#order-form input[name=channelSkuId],#order-form input[name=sellingPrice],#order-form input[name=orderedQuantity]').on("input",function(){

  if ($('#order-form input[name=channelSkuId]').val().length   >   0   &&
        $('#order-form input[name=orderedQuantity]').val().length  >   0   &&
        $('#order-form input[name=sellingPrice]').val().length  >   0  && checkNumeric()) {
        $("#add-order").prop("disabled", false);
    }
    else {
        $("#add-order").prop("disabled", true);
    }
});


$('#order-edit-form input[name=orderedQuantity],#order-edit-form input[name=sellingPrice]  ').on("input",function(){
  	
	var quantity=$('#order-edit-form input[name=orderedQuantity] ').val();
	var sp=$('#order-edit-form input[name=sellingPrice] ').val();

    if (quantity.length  >   0  && sp.length>0 && checkEditForm() && ( quantity!=inventoryQuantity || sp !=price) ){
        $("#update-order").prop("disabled", false);
    }
    else {
        $("#update-order").prop("disabled", true);
    }
  
});


$('#order-form input[name=sellingPrice]').on("input",function(){
	var value=$('#order-form input[name=sellingPrice]').val();
  if(( value.length ==   0) || ($.isNumeric(value))) 
 	{
 		$("#check-price").text("");
 		 
 		
 	}
 	else{
 		$("#check-price").text("Please enter Numeric value");
 		$("#add-order").prop("disabled", true);
 		
 	}
  
});

$('#order-form input[name=orderedQuantity]').on("input",function(){
	var value=$('#order-form input[name=orderedQuantity]').val();
  if(( value.length ==   0) || (Math.floor(value) == (value) && $.isNumeric(value))) 
 	{
 		$("#check-quantity").text("");
 		
 	}
 	else{
 		$("#check-quantity").text("Please enter Integer value");
 		$("#add-order").prop("disabled", true);
 	}
  
});

$('#order-edit-form input[name=orderedQuantity]').on("input",function(){
	var value=$('#order-edit-form input[name=orderedQuantity]').val();
  if(( value.length ==   0) || (Math.floor(value) == (value) && $.isNumeric(value))) 
 	{
 		$("#check-edit-quantity").text("");
 		
 	}
 	else{
 		$("#check-edit-quantity").text("Please enter Integer value");
 		$("#update-order").prop("disabled", true);
 	}
  
});


$('#order-edit-form input[name=sellingPrice]').on("input",function(){
	var value=$('#order-edit-form input[name=sellingPrice]').val();
  if(( value.length ==   0) || ( $.isNumeric(value))) 
 	{
 		$("#check-edit-sellingPrice").text("");
 		
 	}
 	else{
 		$("#check-edit-sellingPrice").text("Please enter Numeric value");
 		$("#update-order").prop("disabled", true);
 	}
  
});










