$(document).ready(function() {
  var code = $('#code').val();
  fillTable(code);
});

function fillTable(code){
 var oTable =  $('#informationPurchaseTable').dataTable({
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
    "sAjaxSource": getContextPath()+'produto/paginar/informacao/pagamento/'+code,
  });
};