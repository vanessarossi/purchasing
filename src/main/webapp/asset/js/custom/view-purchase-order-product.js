$(document).ready(function() {
  $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
  });
  $('#reprovePurchaseOrderForm').hide();
  fillTable();
});

function fillTable(){
 var oTable =  $('#orderRequestProductTable').dataTable({
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
  });
};

function reprove() {
  $('#reprovePurchaseOrderForm').show();
}