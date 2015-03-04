$(document).ready(function() {
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });
});

function actionChecked (i) {
    var status = $('#statusSolicitationRequest'+i).val();
    if (status === 'Delivered') {
        $('#statusSolicitationRequest'+i).val('');
    }
    if (status === '') {
        $('#statusSolicitationRequest'+i).val('Delivered');
    }
};

function actionStatusSolicitationRequest(){
    var status = $('#statusSolicitationRequest').val();
    if (status === 'Delivered') {
        $('#statusSolicitationRequest').val('');
    }
    if (status === '') {
        $('#statusSolicitationRequest').val('Delivered');
    }
};