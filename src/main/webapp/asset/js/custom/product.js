$(document).ready(function() {
  $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
  });
  fillTable();
});

function fillTable(){
 var oTable =  $('#productTable').dataTable({
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
    "sAjaxSource": getContextPath()+'produto/paginar',
  });
};

function viewProduct(product) {
    $.ajax({
      type: "GET",
      url: getContextPath()+'produto/pesquisar/'+product+'/json',
      dataType: "json",
      beforeSend: function(){
      },
      success: function (result) {
        clearModal();
        var description = result["description"];
        var mark = result["mark"];
        var model = result["model"];
        var observation = result["observation"];
        var unit = result["unit"]["description"];
        var category = result["category"]["description"];
        var minimumStock = result["minimumStock"];
        var barCode = result["barCode"];

        $('#description').text(description);
        $('#mark').text(mark);
        $('#model').text(model);
        $('#unit').text(unit);
        $('#category').text(category);
        $('#observation').text(observation);
        $('#minimumStock').text(minimumStock);
        $('#barCode').text(barCode);
        $('#viewProduct').modal('show');
      },
      error: function () {
          alert("Ocorreu um erro no processamento dos dados.");
      }
    })
};

function clearModal() {
  $('#description').text("");
  $('#mark').text("");
  $('#model').text("");
  $('#unit').text("");
  $('#category').text("");
  $('#minimumStock').text("");
  $('#barCode').text("");
  $('#observation').text("");
}

function confirmDetele(id) {
    $('#code').val(id);
    $('#modalConfirm').modal('show');
};

$('#btn-confirm').click(function(){
    var id = $('#code').val();
        $.ajax({
            type: "GET",
            url: getContextPath()+'produto/deletar/'+id,
            dataType: "json",
            beforeSend: function(){
            },
            success: function (result) {
              $('#modalConfirm').modal('hide');
              $('#modalSuccess').modal('show');
              setTimeout(function () {
                       $("#modalSuccess").modal('hide');
                        location.reload();
                   }, 1050)

            },
            error: function () {
              $('#modalConfirm').modal('hide');
              $('#modalError').modal('show');
              setTimeout(function () {
                    $("#modalError").modal('hide');
                     location.reload();
              }, 1050)
            }
        });
});

$('#btn-cancel').click(function(){
    $('#modalConfirm').modal('hide');
});
