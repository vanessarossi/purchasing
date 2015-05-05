$(document).ready(function() {
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });

	oTable =  $('#solicitationTable').dataTable({
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
	        "sAjaxSource": getContextPath()+'solicitacao/paginar/individual',
	});
});

$('#status').change(function () {
  status = $('#status').val();
   if(status != ''){
     filtrar();
	 console.log("filtro");
    }else{
		if(oTable != null)oTable.fnDestroy();
		$('#solicitationTable').dataTable({
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
		   "sAjaxSource": getContextPath()+'solicitacao/paginar/individual',
		});
	}
});

function filtrar () {
   if(oTable != null)oTable.fnDestroy();
   status = $('#status').val();
	$('#solicitationTable').dataTable({
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
	   "sAjaxSource": getContextPath()+'solicitacao/paginar/individual/filtro/'+status,
	});
}

function cancelSolicitation(solicitation) {
    $('#solicitationId').val(solicitation);
    $('#cancelSolicitation').modal('show');
};

function send() {
    var justification =  $('#justification').val();
    if (justification != null && justification != '') {
        $( "#cancelSolicitationForm" ).submit();
    }
};