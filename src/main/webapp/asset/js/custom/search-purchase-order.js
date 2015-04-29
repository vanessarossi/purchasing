$(document).ready(function () {
	fillTable();
});

$('#code').blur(function () {
    var id = $('#code').val().trim();
    if (id != null && id != '') {
        $.ajax({
            url: getContextPath() + 'fornecedor/pesquisar/' + id + '/json',
            type: "GET",
            dataType: 'json',
            beforeSend: function () {
            },
            success: function (result) {
                if (result != false) {
                    var code = result["id"];
                    var name = result["person"]["name"];
                    $('#code').val(code);
                    $('#supplier').val(name);
                } else {
                    $('#supplier').val("Não foi encontrado um produto com este código");
                    $('#code').val("");

                }
            },
            error: function () {
                alert("Ocorreu um erro no processamento dos dados.");
            }
        });
    } else {
        $('#code').val(" ");
        $('#supplier').val(" ");
    }
});

function chooseSupplier(code, supplier) {
    $('#code').val(code);
    $('#supplier').val(supplier);
    $('#searchSupplier').modal('hide');
};

function fillTable(){
 var oTable =  $('#searchPurchaseOrderTable').dataTable({
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