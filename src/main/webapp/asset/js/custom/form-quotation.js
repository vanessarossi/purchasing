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
            url: getContextPath()+'produto/pesquisar/'+id+'/json',
            type: "GET",
            dataType: 'json',
            beforeSend: function(){
            },
            success: function (result) {
                if (result != false) {
                    var code  = result["id"];
                    var description = result["description"];
                    var model  = result["model"] == null ? '' : result["model"];
                    var mark = result["mark"] ==  null ? '' : result["mark"];
                    var product = description+" "+model+" "+mark;
                    var unit = result["unit"]["description"];

                    $('#product').val(product);
                    $('#unit').val(unit);
                }else{
                    $('#product').val("Não foi encontrado um produto com este código");
                    $('#code').val("");
                    $('#unit').val("");
                }
            },
            error: function () {
                alert("Ocorreu um erro no processamento dos dados.");
            }
        });
    }else{
        $('#product').val('');
        $('#code').val('');
        $('#unit').val('');
    }
});

function chooseProduct(code,product,unit){
    $('#product').val(product);
    $('#code').val(code);
    $('#unit').val(unit);
    $('#searchProduct').modal('hide');
};

function searchProductByProduct() {
    var product = $('#code').val().trim();
    if (product != null && product != '') {
      $.ajax({
            url: getContextPath() + 'solicitacao/pesquisar/pedido/produto/'+product+'/json',
            type: 'GET',
            dataType: 'json',
            beforeSend: function(){
            },
            success: function (result) {
                $('#materialTable > tbody >tr').remove();
                   if (result != false) {
                     for (var i = 0; i < result.length; i++) {
                            var solicitation = result[i]["solicitation"]["id"];
                            var costCenter = result[i]["solicitation"]["costCenter"]["description"];

                            var description = result[i]["product"]["description"];
                            var model = ((result[i]["product"]["model"] == null) ? '' : result[i]["product"]["model"]);
                            var mark = ((result[i]["product"]["mark"] == null) ? '' : result[i]["product"]["mark"]);
                            var unit = result[i]["product"]["unit"]["description"];
                            
                            var quantity = result[i]["quantity"];
                            
                            var product = description+" "+model+" "+mark;

                            var row = '<tr>';
                            row += "<td>"+solicitation+"</td>";
                            row += "<td>"+costCenter+"</td>";
                            row += "<td>"+product+"</td>";
                            row += "<td>"+quantity+"</td>";
                            row += "<td>"+unit+"</td>";
                            row += "<td>";
                            row += '<input type="hidden" name="solicitationRequests['+i+'].id"  value='+result[i]['id']+' />';
                            row += '<input type="checkbox" name="solicitationRequests['+i+'].addQuotation" id="addQuotation'+i+'"  onclick="actionChecked('+i+')"  value='+result[i]['addQuotation']+' />';
                            row += "</td>";
                            row += "</tr>";

                            $('#materialTable > tbody').append(row);
                        }
                   }else{
                       /** adicionar mensagem por falta de solicitaçnao **/
                   }
                $('#code').val("");
                $('#product').val("");
                $('#unit').val("");
            },
            error: function (result) {
                alert('Ocorreu um erro ao tentar realizar esta ação');
            }
        });
    }
};

function searchProductBySolicitation() {
    var solicitation = $('#solicitationCode').val().trim();
    if (solicitation != null && solicitation != '') {
      $.ajax({
            url: getContextPath() + 'solicitacao/pesquisar/pedido/solicitacao/'+solicitation+'/json',
            type: 'GET',
            dataType: 'json',
            beforeSend: function(){
            },
            success: function (result) {
                $('#materialTable > tbody >tr').remove();
                if (result != false) {
                    for (var i = 0; i < result.length; i++) {
                        var solicitation = result[i]["solicitation"]["id"];
                        var costCenter = result[i]["solicitation"]["costCenter"]["description"];

                        var description = result[i]["product"]["description"];
                        var model = ((result[i]["product"]["model"] == null) ? '' : result[i]["product"]["model"]);
                        var mark = ((result[i]["product"]["mark"] == null) ? '' : result[i]["product"]["mark"]);
                        var unit = result[i]["product"]["unit"]["description"];

                        var quantity = result[i]["quantity"];
                        

                        var product = description+" "+model+" "+mark;

                        var row = '<tr>';
                        row += "<td>"+solicitation+"</td>";
                        row += "<td>"+costCenter+"</td>";
                        row += "<td>"+product+"</td>";
                        row += "<td>"+quantity+"</td>";
                        row += "<td>"+unit+"</td>";
                        row += "<td>";
                        row += '<input type="hidden" name="solicitationRequests['+i+'].id"  value='+result[i]['id']+' />';
                        row += '<input type="checkbox" name="solicitationRequests['+i+'].addQuotation" id="addQuotation'+i+'"  onclick="actionChecked('+i+')"  value='+result[i]['addQuotation']+' />';
                        row += "</td>";
                        row += "</tr>";

                        $('#materialTable > tbody').append(row);
                    }  
                }else{
                    /** adicionar mensagem de falta de solicitação**/
                }
                $('#solicitationCode').val('');
            },
            error: function (result) {
                alert('Ocorreu um erro ao tentar realizar esta ação');
            }
        })
    }
};

function searchServiceBySolicitation() {
    var solicitation = $('#solicitationServiceCode').val().trim();

    if (solicitation != null && solicitation != ' ') {
      $.ajax({
            url: getContextPath() + 'solicitacao/pesquisar/pedido/servico/solicitacao/'+solicitation+'/json',
            type: 'GET',
            dataType: 'json',
            beforeSend: function(){
            },
            success: function (result) {
                $('#serviceTable > tbody >tr').remove();
                    if (result != false) {
                        var solicitation = result["solicitation"]["id"];
                        var type = result["service"]["typeService"]["description"];
                        var costCenter = result["solicitation"]["costCenter"]["description"];  
                        var description = result["service"]["description"];

                        var row = '<tr>';
                        row += "<td>"+solicitation+"</td>";
                        row += "<td>"+type+"</td>";
                        row += "<td>"+costCenter+"</td>";
                        row += "<td>"+description+"</td>";
                        row += "<td>";
                        row += '<input type="hidden" name="solicitationRequest.id"  value='+result['id']+' />';
                        row += '<input type="checkbox" name="solicitationRequest.addQuotation" id="addQuotation0"  onclick="actionChecked(0)"  value='+result['addQuotation']+' />';
                        row += "</td>";
                        row += "</tr>";

                        $('#serviceTable > tbody').append(row);
                    }else{
                        /** adicionar a mensagem que não possui solicitação **/
                    }
                $('#solicitationServiceCode').val('');
            },
            error: function (result) {
                alert('Ocorreu um erro ao tentar realizar esta ação');
            }
        })
    }
};

function actionChecked (i) {
  var addQuotation = $('#addQuotation'+i).val();
  if (addQuotation === 'true') {
    $('#addQuotation'+i).val(false);
  }
  if (addQuotation === 'false') {
    $('#addQuotation'+i).val(true);
  }
};








