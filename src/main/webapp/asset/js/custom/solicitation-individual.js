$(document).ready(function() {
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });

    fillTable();
});

function fillTable(){
    var oTable =  $('#solicitationTable').dataTable({
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
};

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