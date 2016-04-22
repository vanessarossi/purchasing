/**
 * Created by vanessa on 22/04/2016.
 */

$('#address').change(function () {
    $('#place').val("");
    var address = $('#address').val();
    $.ajax({
        type: "GET",
        url: getContextPath()+'conta/pesquisa/lugar/'+address+'/json',
        dataType: "json",
        beforeSend: function(){
        },
        success: function (result) {
            if (result != false) {
                var place = result;
                $('#place').val(place);
            }else{
                $('#place').val("");
            }
        },
        error: function () {
            alert("Erro, por favor tente novamente.")
        }
    });
});


function search() {
    var form  = $('#formSearch');
    var dados = form.serialize();
    $.ajax({
        url: getContextPath()+'conta/pesquisa/lancamentos/json',
        data: dados,
        dataType: "json",
        type: "POST",
        beforeSend: function(){
        },
        success: function (result) {
            if (result != false){
                var totalAccount = "0,00";
                for (var i = result.length - 1; i >= 0; i--) {
                    
                    var id = result[i]['id'];
                    var type = result[i]['type'] ;
                    var competence = result[i]['competence'];
                    var place = result[i]['place'];
                    var address = result[i]['address'];
                    var value = result[i]['value'].toFixed(2).replace(".", ",");
                    var discount = result[i]['discount'].toFixed(2).replace(".", ",");
                    var totalValue = result[i]['totalValue'].toFixed(2).replace(".", ",");

                    var row = "<tr>";
                    row += "<td>" + type + "</td>";
                    row += "<td>" + competence + "</td>";
                    row += "<td>" + place + "</td>";
                    row += "<td>" + address + "</td>";
                    row += "<td>" + value + "</td>";
                    row += "<td>" + discount + "</td>";
                    row += "<td>" + totalValue + "</td>";
                    row += "<td>";
                    row += '<a href='+getContextPath()+'conta/editar/'+id+' class="btn btn-default btn-xs" ><span class="fa fa-pencil-square-o"></span></a>';
                    row += "</td>";
                    row += "<td>";
                    row += '<a href='+getContextPath()+'conta/deletar/'+id+' class="btn btn-default btn-xs" href=""><span class="fa fa-trash-o"></span></a>';
                    row += "</td>";
                    row += "</tr>";

                    $('#postingAccountTable').append(row);


                    while (totalValue.indexOf(',') != -1)
                        totalValue = totalValue.replace(',', '.');
                    while (totalAccount.indexOf(',') != -1)
                        totalAccount = totalAccount.replace(',', '.');

                    var totalAccount = parseFloat(totalAccount) + parseFloat(totalValue);
                    $('#totalAccount').val(totalAccount.toFixed(2).replace(".", ","));
                }
            }
        },
        error: function () {
            alert("Erro, por favor tente novamente.")
        }
    });
}

