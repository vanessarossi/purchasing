$(document).ready(function(){
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });
});


$('#code').blur(function () {
    var id = $('#code').val().trim();
    if (id != null && id != '') {
        $.ajax({
            url: getContextPath()+'fornecedor/pesquisar/'+id+'/json',
            type: "GET",
            dataType: 'json',
            beforeSend: function(){
            },
            success: function (result) {
                if (result != false) {
                    var code  = result["id"];
                    var name  = result["person"]["name"];
                    $('#supplier').val(name);
                    $('#code').val(code);
                }else{
                    $('#supplier').val("Não foi encontrado o fornecedor com este código");
                    $('#code').val("");
                }
            },
            error: function () {
                alert("Ocorreu um erro no processamento dos dados.");
            }
        });
    }else{
        $('#supplier').val('');
        $('#code').val('');
    }
});


function chooseSupplier(code,name){
    $('#supplier').val(name);
    $('#code').val(code);
    $('#searchSupplier').modal('hide');
};

function toggleChevron(e) {
    $(e.target)
        .prev('.panel-heading')
        .find("i.indicator")
        .toggleClass('fa-angle-double-down fa-angle-double-up');
}
$('#accordion').on('hidden.bs.collapse', toggleChevron);
$('#accordion').on('shown.bs.collapse', toggleChevron);