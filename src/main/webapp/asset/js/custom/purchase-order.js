$(document).ready(function() {
  $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
  });
  	oTable =  $('#purchasingOrderTable').dataTable({
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
      "sAjaxSource": getContextPath()+'ordemCompra/paginar',
    });
});

$('#status').change(function () {
  status = $('#status').val();
   if(status != ''){
     filtrar();
    }else{
		if(oTable != null)oTable.fnDestroy();
		$('#purchasingOrderTable').dataTable({
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
		      "sAjaxSource": getContextPath()+'ordemCompra/paginar',
		});
	}
});

function filtrar () {
    if(oTable != null)oTable.fnDestroy();
    status = $('#status').val();
	$('#purchasingOrderTable').dataTable({
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
	      "sAjaxSource": getContextPath()+'ordemCompra/paginar/filtro/'+status,
	});
}