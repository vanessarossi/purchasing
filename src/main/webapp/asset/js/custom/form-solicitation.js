$(document).ready(function(){
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });
    actionType();
    $('#confirmUpdate').hide();
});

function actionType(){
    var type = $('#type').val();
    switch(type) {
        case "Material":
            $('#divMaterial').show();
            $('#divService').hide();
            break;
        case "Service":
            $('#divService').show();
            $('#divMaterial').hide();
            break;
        case "MaterialService":
            $('#divMaterial').show();
            $('#divService').show();
            break;
    }
};

$('#type').change(function(){
    actionType();
});

$('#code').blur(function () {
    var id = $('#code').val();
    if (id != null && id != '') {
        $.ajax({
            url: getContextPath()+'produto/pesquisarPorCodigo/'+id,
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
                    var unit = result["unit"]["description"];
                    var product = description+" "+model+" "+mark;
                    $('#product').val(product);
                    $('#unit').val(unit);
                }else{
                    $('#product').val("Não foi encontrado um produto com este código");
                    $('#code').val("");
                    $('#quantity').val("");
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
        $('#quantity').val('');
        $('#unit').val('');
    }
});


function addProduct() {
    var product = $('#product').val();
    var code = $('#code').val();
    var quantity = $('#quantity').val();
    var unit = $('#unit').val();
    var counter = $('#counter').val();

    if (product != null && product != '') {
        var row = "<tr id='"+counter+"'>";
        row += "<input type='hidden' name='solicitation.solicitationRequests["+counter+"].product.id' id='product"+counter+"' value="+code+">";
        row += "<input type='hidden' name='solicitation.solicitationRequests["+counter+"].quantity' id='quantity"+counter+"' value="+quantity+">";
        row += "<td>" + code + "</td>";
        row += "<td>" + product + "</td>";
        row += "<td>" + quantity + "</td>";
        row += "<td>" + unit + "</td>";
        row += "<td>";
        row += '<a class="btn btn-default btn-xs" onclick="editProduct('+counter+',0)"><span class="fa fa-edit"></span></a>';
        row += "</td>";
        row += "<td>";
        row += '<a class="btn btn-default btn-xs" onclick="removeProduct('+counter+',0)"><span class="fa fa-trash-o"></span></a>';
        row += "</td>";
        row += "</tr>";
        $('#tableProductSolicitation').append(row);
        $('#product').val("");
        $('#code').val("");
        $('#quantity').val("");
        $('#unit').val("");
        $('#counter').val(parseInt(counter) + 1);
    }
};

function addProductWithId(codeSolicitationRequest) {
    var product = $('#product').val();
    var code = $('#code').val();
    var quantity = $('#quantity').val();
    var unit = $('#unit').val();
    var counter = $('#counter').val();

    if (product != null && product != '') {
        var row = "<tr id='"+counter+"'>";
        row += "<input type='hidden' name='solicitation.solicitationRequests["+counter+"].product.id' id='product"+counter+"' value="+code+">";
        row += "<input type='hidden' name='solicitation.solicitationRequests["+counter+"].quantity' id='quantity"+counter+"' value="+quantity+">";
        row += "<input type='hidden' name='solicitation.solicitationRequests["+counter+"].id' id='id"+counter+"' value="+codeSolicitationRequest+">";
        row += "<td>" + code + "</td>";
        row += "<td>" + product + "</td>";
        row += "<td>" + quantity + "</td>";
        row += "<td>" + unit + "</td>";
        row += "<td>";
        row += '<a class="btn btn-default btn-xs" onclick="editProduct('+counter+','+codeSolicitationRequest+')"><span class="fa fa-edit"></span></a>';
        row += "</td>";
        row += "<td>";
        row += '<a class="btn btn-default btn-xs" onclick="removeProduct('+counter+','+codeSolicitationRequest+')"><span class="fa fa-trash-o"></span></a>';
        row += "</td>";
        row += "</tr>";
        $('#tableProductSolicitation').append(row);
        $('#product').val("");
        $('#code').val("");
        $('#quantity').val("");
        $('#unit').val("");
        $('#counter').val(parseInt(counter) + 1);
    }
};

function removeProduct(index,code) {
    if (parseInt(code) === 0) {
        $('#'+index).remove();
    }else{
        $.ajax({
            url: getContextPath()+'solicitacao/remover/produto/'+code,
            type: "GET",
            dataType: 'json',
            beforeSend: function(){
            },
            success: function (result) {
                if (result == true){
                    $('#'+index).remove();
                };
            },
            error: function () {
                alert("Ocorreu um erro no processamento dos dados.");
            }
        });
    }
};

function editProduct(index,code){
    var product = $('#product'+index).val();
    var quantity = $('#quantity'+index).val();
    var idSolicitationRequest = code;
    $('#code').val(product);
    var id = $('#code').val();
    $.ajax({
        url: getContextPath()+'produto/pesquisarPorCodigo/'+id,
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
                var unit = result["unit"]["description"];
                var product = description+" "+model+" "+mark;
                $('#product').val(product);
                $('#quantity').val(quantity);
                $('#unit').val(unit);
                $('#'+index).remove();

                $('#addProduct').hide();
                $('#confirmUpdate').show();
                $('#confirmUpdate').attr('onclick', 'addProductWithId('+idSolicitationRequest+')');
            }else{
                $('#product').val("Não foi encontrado um produto com este código");
                $('#code').val("");
                $('#quantity').val("");
                $('#unit').val("");
            }
        },
        error: function () {
            alert("Ocorreu um erro no processamento dos dados.");
        }
    });
};

function confirmUpdate(codeSolicitationRequest) {
    if (codeSolicitationRequest == 0){
        addProduct();
    }else{
        addProductWithId(codeSolicitationRequest);
    }
};

function chooseProduct(code,product,unit){
    $('#product').val(product);
    $('#code').val(code);
    $('#unit').val(unit);
    $('#searchProduct').modal('hide');
    $('#quantity').focus();
};