$(document).ready(function() {
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });
    fillTable();
});

function fillTable(){
    var oTable =  $('#tableProductSolicitation').dataTable({
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
        "sAjaxSource": getContextPath()+'solicitacao/paginar/produto/'+$('#idSolicitation').text(),
    });
};

function reproveSolicitation(solicitation) {
    $('#solicitationId').val(solicitation);
    $('#reproveSolicitation').modal('show');
};

function send() {
    var justification =  $('#justification').val();
    if (justification != null && justification != '') {
        $( "#formReproveSolicitation" ).submit();
    }
};
