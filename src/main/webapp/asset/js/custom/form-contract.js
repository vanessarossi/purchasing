$(document).ready(function() {
    $('#renewalForm').hide();

    $(".date").mask("00/00/0000");
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
                    var code = result["id"];
                    var name = result["person"]["name"];
                    $('#code').val(code);
    				$('#supplier').val(name);
                }else{
                    $('#supplier').val("Não foi encontrado um produto com este código");
                    $('#code').val("");

                }
            },
            error: function () {
                alert("Ocorreu um erro no processamento dos dados.");
            }
        });
    }else{
        $('#code').val(" ");
    	$('#supplier').val(" ");
    }
});

function chooseSupplier(code,supplier){
    $('#code').val(code);
    $('#supplier').val(supplier);
    $('#searchSupplier').modal('hide');
};

function renewal(){
    $('#renewalForm').show();
    $('#contractForm').hide();
};

function cancelRenewal(){
    $('#renewalForm').hide();
    $('#contractForm').show();
};

function confirmDetele(id) {
    $('#code').val(id);
    $('#modalConfirm').modal('show');
};

$('#btn-confirm').click(function(){
    var id = $('#code').val();
        $.ajax({
            type: "GET",
            url: getContextPath()+'contrato/renovacao/deletar/'+id,
            dataType: "json",
            beforeSend: function(){
            },
            success: function (result) {
              $('#modalConfirm').modal('hide');
              $('#modalSuccess').modal('show');
              setTimeout(function () {
                       $("#modalSuccess").modal('hide');
                   }, 1050)

            },
            error: function () {
              $('#modalConfirm').modal('hide');
              $('#modalError').modal('show');
              setTimeout(function () {
                    $("#modalError").modal('hide');
              }, 1050)
            }
        });
});

$('#btn-cancel').click(function(){
    $('#modalConfirm').modal('hide');
});