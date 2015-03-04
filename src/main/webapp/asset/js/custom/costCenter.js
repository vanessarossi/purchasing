$(document).ready(function() {
  $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
  });
  fillTable();
});

function fillTable(){
 var oTable =  $('#costCenterTable').dataTable({
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
    "aLengthMenu": [[10, 100, -1], [10, 100, "All"]],
    "bProcessing": false,
    "bServerSide": true,
    "sAjaxSource": getContextPath()+'centroCusto/paginar',
  });
};

function edit(id) {
	$.ajax({
    type: "GET",
    url: getContextPath()+'centroCusto/pesquisar/'+id+'/json',
    dataType: "json",
    beforeSend: function(){
    },
    success: function (result) {
        $('#newCostCenter').modal('show');
    	var description = result["description"];
        var code = result["code"];
        var company = result["company"]["id"];
    	var id = result["id"];
        $('#code').val(code);
        $('#description').val(description);
        $('#id').val(id);
        $('#company option[value="3"]').attr('selected', 'selected');
    },
    error: function () {
      alert("Ocorreu um erro no processamento dos dados.");
    }
  });
};

$('#newCostCenter').on('show.bs.modal', function (e) {
    $.ajax({
    type: "POST",
    url: getContextPath()+'centroCusto/empresas/json',
    dataType: "json",
    beforeSend: function(){
    },
    success: function (options) {
        var select = document.getElementById("company"); 
       for (var i = 0; i < options.length; i++) {
            var option = options[i]["companyName"];
            var valueOption = options[i]["id"];
            var element = document.createElement("option");
            element.textContent = option;
            element.value = valueOption;
            select.appendChild(element);
       };
    },
    error: function () {
      alert("Ocorreu um erro no processamento dos dados.");
    }
  });           
});

function confirmDetele(id) {
    $('#code').val(id);
    $('#modalConfirm').modal('show');
};

$('#btn-confirm').click(function(){
    var id = $('#code').val();
        $.ajax({
            type: "GET",
            url: getContextPath()+'centroCusto/deletar/'+id,
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
