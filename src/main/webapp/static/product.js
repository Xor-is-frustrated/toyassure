var clientName="";
var name="";
var brandId="";
var mrp="";
var description="";
var fileData = [];
var errorData = [];
var processCount = 0;
var successCount =0;

function getProductUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "/api/product";
}

//csv related functions
function processData(){
	var file = $('#productFile')[0].files[0];
	clientName=$('#upload-product-form input[name=clientName]').val();
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
		getAllProducts();
		var $file = $('#productFile');
		$file.val('');
		$('#productFileName').html("Choose File");
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
	var url = getProductUrl();

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
	var $file = $('#productFile');
	$file.val('');
	$('#productFileName').html("Choose File");
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
	var $file = $('#productFile');
	var fileName = $file.val();
	$('#process-data').prop('disabled',false);
	$("#download-errors").prop('disabled', true);
	$('#productFileName').html(fileName);
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
function addProductCSV(){
	var $form = $("#product-add-form");
	var json = toJson($form);
	var url = getProductUrl();
	console.log("addProduct json form"+json);
	$.ajax({
		url: url,
		type: 'POST',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$('#add-Product-modal').modal('toggle');
			successPopup("Product/Customer is added");
			getAllProducts();  
		},
		error: handleAjaxError
	});
	return false;
}

function getProduct(id){
	var url = getProductUrl() + "/" + id;
	console.log("getProduct url"+url);
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			EditModal(data);   
		},
		error: handleAjaxError
	});	
}

function getAllProducts(){
	var url = getProductUrl();
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			productTable(data);  
		},
		error: handleAjaxError
	});
}

function updateProduct(event){
	var id = $("#product-edit-form input[name=id]").val();	
	var url = getProductUrl() + "/" + id;
	var $form = $("#product-edit-form");
	var json = toJson($form);
	console.log("updateProduct json form"+json);
	
	$.ajax({
		url: url,
		type: 'PUT',
		data: json,
		headers: {
			'Content-Type': 'application/json'
		},	   
		success: function(response) {
			$('#edit-product-modal').modal('toggle');
			console.log("modal toggled in update product");
			successPopup("Product is updated");
			getAllProducts();   
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
	$('#upload-product-modal').modal('toggle');
}

function EditModal(data){

	$("#product-edit-form input[name=name]").val(data.name);	
	$("#product-edit-form input[name=clientName]").val(data.clientName);	
	$("#product-edit-form input[name=mrp]").val(data.mrp);	
	$("#product-edit-form input[name=description]").val(data.description);	
	$("#product-edit-form input[name=clientSkuId]").val(data.clientSkuId);	
	$("#product-edit-form input[name=brandId]").val(data.brandId);	
	$("#product-edit-form input[name=id]").val(data.id);	
	$("#update-product").prop('disabled', true);
	clientName=data.clientName;
	name=data.name;
	mrp=data.mrp;
	description=data.description;
	brandId=data.brandId;
	$('#edit-product-modal').modal('toggle');
	console.log("modal toggled in edit modal product");
}

function productTable(data){
	var $tbody = $('#product-table').find('tbody');
	$tbody.empty();
	console.log("productTable");
	for(var i in data){
		var e = data[i];
				
		var buttonHtml = ' <button class="btn btn-dark btn-sm" onclick="getProduct(' + e.id + ')">Edit</button>';
		var row = '<tr>'
		+ '<td>' + e.name + '</td>'
		+ '<td>'  + e.brandId + '</td>'
		+ '<td>'  + e.mrp + '</td>'
		+ '<td>'  + e.clientName + '</td>'
		+ '<td>'  + e.clientSkuId + '</td>'
		+ '<td>'  + e.description + '</td>'
		+ '<td>' + buttonHtml + '</td>'
		+ '</tr>';
		$tbody.append(row);
	}
}

function checkEditButtonDisable(){
	var formName= $('#product-edit-form input[name=name]').val();
	var formBrandId=$('#product-edit-form input[name=brandId]').val();
	var formMrp= $('#product-edit-form input[name=mrp]').val();
	var formDescription=$('#product-edit-form input[name=description]').val();

	if( (formMrp.length>0 && formName.length>0 && formBrandId.length>0) && 
		(formMrp!=mrp || formName!=name || formBrandId!=brandId || formDescription!=description) &&	($.isNumeric(formMrp)) ){
		$("#update-product").prop('disabled', false);
	}else{
		$("#update-product").prop('disabled', true);
	}

}

function checkNumericForMrp(){
	var formMrp= $('#product-edit-form input[name=mrp]').val();
	if(formMrp.length==0 || ($.isNumeric(formMrp))){
		$("#check-edit-mrp").text("");
	}
	else{
		$("#check-edit-mrp").text("Please enter Numeric value");
		$("#update-product").prop("disabled", true);
	}
}
// init
function init(){
	getAllProducts();

	$('#product-edit-form input[name=name],#product-edit-form input[name=mrp],#product-edit-form input[name=description],#product-edit-form input[name=brandId]').on("input",checkEditButtonDisable);
	$('#product-edit-form input[name=mrp]').on("input",checkNumericForMrp);

	$('#update-product').click(updateProduct);
	$('#refresh-data').click(getAllProducts);
	$('#upload-data').click(uploadModal);
	$('#process-data').click(processData);
	$('#download-errors').click(downloadErrors);
	$('#productFile').on('change', updateFileName);

}

$(document).ready(init);