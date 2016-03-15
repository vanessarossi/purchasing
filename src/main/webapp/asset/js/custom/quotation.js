$(document).ready(function() {	   
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });
	
	oTable =  $('#quotationTable').dataTable({
	        "oLanguage": {
	            "sUrl": getContextPathDataTable()
	        },
	        "bAutoWidth":true,
	        "bLengthChange": false,
	        "bPaginate": true,
	        "bFilter": true,
	        "bSort": false,
	        "bInfo": true,
	        "processing": true,
	        "bJQueryUI": false,
	        "sPaginationType": "full_numbers",
	        "iDisplayLength":  10,
	        "aLengthMenu": [[5, 10, 100], [5,10, 100]],
	        "bProcessing": false,
	        "bServerSide": true,
	        "sAjaxSource": getContextPath()+'cotacao/paginar',
	});	
});

$('#status').change(function () {
  status = $('#status').val();
   if(status != ''){
     filtrar();
    }else{
		if(oTable != null)oTable.fnDestroy();
	$('#quotationTable').dataTable({
			"oLanguage": {
			 "sUrl": getContextPathDataTable()
		},
		"bAutoWidth":true,
		"bLengthChange": false,
		"bPaginate": true,
		"bFilter": true,
		"bSort": false,
		"bInfo": true,
		"processing": true,
		"bJQueryUI": false,
		"sPaginationType": "full_numbers",
		"iDisplayLength":  10,
		"aLengthMenu": [[5, 10, 100], [5,10, 100]],
		"bProcessing": false,
		"bServerSide": true,
		"sAjaxSource": getContextPath()+'cotacao/paginar',
	});
	}
});

function filtrar () {
    if(oTable != null)oTable.fnDestroy();
    status = $('#status').val();
 	$('#quotationTable').dataTable({
 			"oLanguage": {
 			 "sUrl": getContextPathDataTable()
 		},
 		"bAutoWidth":true,
 		"bLengthChange": false,
 		"bPaginate": true,
 		"bFilter": true,
 		"bSort": false,
 		"bInfo": true,
 		"processing": true,
 		"bJQueryUI": false,
 		"sPaginationType": "full_numbers",
 		"iDisplayLength":  10,
 		"aLengthMenu": [[5, 10, 100], [5,10, 100]],
 		"bProcessing": false,
 		"bServerSide": true,
 		"sAjaxSource": getContextPath()+'cotacao/paginar/filtro/'+status,
 	});
}

function openFormCancellation(idQuotation){
	$('#quotation').val(idQuotation);
	$('#viewCancellForm').modal('show');	
}

function viewQuick(id){ 
   $.ajax({
        url: getContextPath()+'cotacao/visualizacao/rapida/'+id+'/json',
        type: "GET",
        dataType: 'json',
        beforeSend: function(){
        },
        success: function (result) {
        	var code  = result["id"];
        	var user = result['user']['name'];
        	var type = result['type'];
        	
        	$('#quotationCode').val(code);
        	$('#quotationUser').val(user);

        	if(type === 'Service'){
        		$('#productType').hide();
        		$('#productTable').hide();
        		$('#serviceTable').show();
        		$('#serviceType').show();

			    $.ajax({
			        url: getContextPath()+'cotacao/pesquisa/pedido/visualizacao/rapida/'+id+'/json',
			        type: "GET",
			        dataType: 'json',
			        beforeSend: function(){
			        },
			        success: function (result) {
			 
			        },
			        error: function () {
			            alert("Ocorreu um erro no processamento dos dados.");
			        }
			    });			
        	}
        	if(type === 'Material'){
        		$('#productType').show();
        		$('#productTable').show();
        		$('#serviceTable').hide();
        		$('#serviceType').hide();

        		$.ajax({
			        url: getContextPath()+'cotacao/pesquisa/pedido/visualizacao/rapida/'+id+'/json',
			        type: "GET",
			        dataType: 'json',
			        beforeSend: function(){
			        },
			        success: function (result) {
			 			
			 			for (var i = 0; i < result.length; i++) {
		                  	var code = result[i]['product']['id'];
	
		                  	var description = result[i]['product']['description'];
		                  	var model = ((result[i]['product']['model'] == null) ? '' : result[i]['product']['model']);
		                  	var mark = ((result[i]['product']['mark'] == null) ? '' : result[i]['product']['mark']);
		                  	
		                  	var quantity = result[i]['quantity'];

			                var row = '<tr>';
			                row += "<td>"+code+"</td>";
			                row += "<td>"+description+"</td>";
			                row += "<td>"+model+"</td>";
			                row += "<td>"+mark+"</td>";
			                row += "<td>"+quantity+"</td>";
			                row += "</tr>";

		                  	$('#productTable > tbody').append(row);
              			}

			        },
			        error: function () {
			            alert("Ocorreu um erro no processamento dos dados.");
			        }
			    });		
            }
            $('#viewQuick').modal('show');
        },
        error: function () {
            alert("Ocorreu um erro no processamento dos dados.");
        }
    }); 	
};

$('#viewQuick').on('hide.bs.modal', function (e) {
    $('#productTable > tbody > tr').remove();
    $('#serviceTable > tbody > tr').remove();
});

$('#viewCancellForm').on('hide.bs.modal', function (e) {
	$('#quotation').val("");
  	$('#justification').val("");
});