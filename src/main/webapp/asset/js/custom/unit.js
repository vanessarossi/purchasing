$(document).ready(function() {
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });
    fillTable();
});

function fillTable(){
    var oTable =  $('#unitTable').dataTable({
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
        "sAjaxSource": getContextPath()+'unidade/paginar',
    });
};

function edit(id) {
    $.ajax({
        type: "GET",
        url: getContextPath()+'unidade/pesquisar/'+id+'/json',
        dataType: "json",
        beforeSend: function(){
        },
        success: function (result) {
            var description = result["description"];
            var id = result["id"];
            $('#description').val(description);
            $('#id').val(id);
            $('#newUnit').modal('show');
        },
        error: function () {
            alert("Ocorreu um erro no processamento dos dados.");
        }
    });
};

$('#newUnit').on('hide.bs.modal', function (e) {
    $('#description').val("");
    $('#id').val("");       
});

function confirmDetele(id) {
    $('#code').val(id);
    $('#modalConfirm').modal('show');
};

$('#btn-confirm').click(function(){
    var id = $('#code').val();
        $.ajax({
            type: "GET",
            url: getContextPath()+'unidade/deletar/'+id,
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