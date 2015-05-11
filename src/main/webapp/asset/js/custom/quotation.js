$(document).ready(function() {
	$('#cancellQuotationForm').hide();	
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
	$('#cancellQuotationForm').show();	
}

function cancelForm(){
	$('#quotation').val("");
	$('#cancellQuotationForm').hide();	
}