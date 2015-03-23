$(document).ready(function() {
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });
});

$("#text").keydown(function (event) {
    var text = $('#text').val();
    $('#tableSearchSupplier > tbody >tr').remove();
    if (text.length >= 4) {
        $.ajax({
            url: getContextPath() + 'fornecedor/pesquisar/json',
            type: 'POST',
            dataType: 'json',
            data: {text: text},
            beforeSend: function(){
            },
            success: function (result) {
                for (var i = 0; i < result.length; i++) {

                    var code = result[i]["id"];
                    var name = result[i]["person"]["name"];
                    var companyName = (result[i]["person"]["companyName"] == null) ? " " : result[i]["person"]["companyName"];

                    var row = '<tr onclick="chooseSupplier('+code+','+"'"+name+"'"+')">';
                    row += "<td>"+code+"</td>";
                    row += "<td>"+name+"</td>";
                    row += "<td>"+companyName+"</td>";
                    row += "</tr>";

                    $('#tableSearchSupplier > tbody').append(row);
                }
            },
            error: function (result) {
                alert('Ocorreu um erro ao tentar realizar esta ação');
            }
        })
    }
});

$('#searchSupplier').on('hide.bs.modal', function (e) {
    $('#tableSearchSupplier > tbody > tr').remove();
    $('#text').val('');
});