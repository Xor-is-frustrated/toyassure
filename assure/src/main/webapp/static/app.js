
//HELPER METHOD
function toJson($form){
    var serialized = $form.serializeArray();
    console.log(serialized);
    var s = '';
    var data = {};
    for(s in serialized){
        data[serialized[s]['name']] = serialized[s]['value']
    }
    var json = JSON.stringify(data);
    return json;
}




function handleAjaxError(response){
   var response = JSON.parse(response.responseText);
    $("#error-message").text(response.message);

    $('#error-alert').modal('toggle');
    setTimeout(function() {
        $('#error-alert').modal('toggle');
    }, 3000);
   }

function successPopup(response){
   
    $("#success-message").text(response);
    $('#success-alert').modal('toggle');
    setTimeout(function() {
        $('#success-alert').modal('toggle');
    }, 1000);
    
}

function readFileData(file, callback){
    var config = {
        header: true,
        delimiter: ",",
        skipEmptyLines: "greedy",
        complete: function(results) {
            callback(results);
        }   
    }
    Papa.parse(file, config);
}


function writeFileData(arr){
    var config = {
        quoteChar: '',
        escapeChar: '',
        delimiter: "\t"
    };
    
    var data = Papa.unparse(arr, config);
    var blob = new Blob([data], {type: 'text/tsv;charset=utf-8;'});
    var fileUrl =  null;

    if (navigator.msSaveBlob) {
        fileUrl = navigator.msSaveBlob(blob, 'download.tsv');
    } else {
        fileUrl = window.URL.createObjectURL(blob);
    }
    var tempLink = document.createElement('a');
    tempLink.href = fileUrl;
    tempLink.setAttribute('download', 'download.tsv');
    tempLink.click(); 
}
