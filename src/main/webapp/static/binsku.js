var clientName="";
var binId="";
var quantity=0;
var totalBins=0;
var fileData = [];
var errorData = [];
var processCount = 0;
var successCount =0;

function getBinUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/bin";
}

function getBinSkuUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/binsku";
}

//csv related functions
function processData(){
	var file = $('#binskuFile')[0].files[0];
	clientName=$('#upload-binsku-form input[name=clientName]').val();
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
		getAllBinSkus();
		var $file = $('#binskuFile');
		$file.val('');
		$('#binskuFileName').html("Choose File");
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
	var json = JSON.stringify(row);
	console.log("upload rows json"+json);
	var url = getBinSkuUrl();

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
	var $file = $('#binskuFile');
	$file.val('');
	$('#binskuFileName').html("Choose File");
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
	var $file = $('#binskuFile');
	var fileName = $file.val();
	$('#process-data').prop('disabled',false);
	$("#download-errors").prop('disabled', true);
	$('#binskuFileName').html(fileName);
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
function addBin(){
	var url = getBinUrl();
	$.ajax({
		url: url,
		type: 'POST',
		success: function(data) {
			successPopup("Bin is created");
			getAllBins();  
		},
		error: handleAjaxError
	});	
}

function getAllBins(){
	var url = getBinUrl();
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			totalBins= data.length; 
			$('#total-bins').text('Total Bins: '+totalBins);
		},
		error: handleAjaxError
	});	
}

function getBinSku(id){
	var url = getBinSkuUrl() + "/" + id;
	console.log("getBinSku url"+url);
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			EditModal(data);   
		},
		error: handleAjaxError
	});	
}

function getAllBinSkus(){
	var url = getBinSkuUrl();
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			binSkuTable(data);  
		},
		error: handleAjaxError
	});
}

function updateBinSku(event){
	var id = $("#binsku-edit-form input[name=id]").val();	
	var url = getBinSkuUrl() + "/" + id;
	var $form = $("#binsku-edit-form");
	var json = toJson($form);
	console.log("updateBinSku json form"+json);
	
	$.ajax({
		url: url,
		type: 'PUT',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$('#edit-binsku-modal').modal('toggle');
			console.log("modal toggled in update binsku");
			successPopup("Binsku is updated");
			getAllBinSkus();   
		},
		error: handleAjaxError
	});
	
	return false;
}


//modal related functions
function uploadModal(){
	resetUploadDialog(); 	
	$("#download-errors").prop('disabled', true);
	$("#process-data").prop('disabled', true);
	$('#upload-binsku-modal').modal('toggle');
}

function EditModal(data){

	

	$("#binsku-edit-form input[name=quantity]").val(data.quantity);			
	$("#binsku-edit-form input[name=id]").val(data.id);		
	$("#update-binsku").prop('disabled', true);
	clientName=data.clientName;
	quantity=data.quantity;
	$('#edit-binsku-modal').modal('toggle');
	console.log("modal toggled in edit modal binsku");
}

function binSkuTable(data){
	var $tbody = $('#binsku-table').find('tbody');
	$tbody.empty();
	console.log("binSkuTable");
	for(var i in data){
		var e = data[i];
				
		var buttonHtml = ' <button class="btn btn-dark btn-sm" onclick="getBinSku(' + e.id + ')">Edit</button>';
		var row = '<tr>'
		
		+ '<td>'  + e.productName + '</td>'
		+ '<td>'  + e.clientName + '</td>'
		+ '<td>'  + e.clientSkuId + '</td>'
		+ '<td>'  + e.binId + '</td>'
		+ '<td>'  + e.quantity + '</td>'
		+ '<td>' + buttonHtml + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}
}

function checkEditButtonDisable(){
	var formQuantity= $('#binsku-edit-form input[name=quantity]').val();
	
	if((formQuantity.length>0) && ( formQuantity!=quantity) &&($.isNumeric(formQuantity) && Math.floor(formQuantity) == (formQuantity)) ){
		$("#update-binsku").prop('disabled', false);
	}else{
		$("#update-binsku").prop('disabled', true);
	}

}

function checkNumericForQuantity(){
	var formQuantity= $('#binsku-edit-form input[name=quantity]').val();
	if(formQuantity.length==0 || ($.isNumeric(formMrp) && Math.floor(formQuantity) == (formQuantity))){
		$("#check-edit-quantity").text("");
	}
	else{
		$("#check-edit-quantity").text("Please enter Numeric value");
		$("#update-binsku").prop("disabled", true);
	}
}
// init
function init(){
	getAllBinSkus();
	getAllBins();
	$('#binsku-edit-form input[name=quantity]').on("input",checkEditButtonDisable);
	$('#binsku-edit-form input[name=quantity]').on("input",checkNumericForQuantity);
	$('#update-binsku').click(updateBinSku);
	$('#refresh-data').click(getAllBinSkus);
	$('#upload-data').click(uploadModal);
	$('#process-data').click(processData);
	$('#add-bin').click(addBin);
	$('#download-errors').click(downloadErrors);
	$('#binskuFile').on('change', updateFileName);


}

$(document).ready(init);