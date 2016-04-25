/**
 * Created by vanessa on 20/04/2016.
 */
$('#type').change(function () {
    var type = $('#type').val();
    switch(type) {
        case "A":
            $('#phone').attr("readonly",true);
            $('#signatureType').attr("readonly",true);
            $('#typeService').attr("readonly",true);
            cleamInputTelephony();
            break;
        case "E":
            $('#phone').attr("readonly",true);
            $('#signatureType').attr("readonly",true);
            $('#typeService').attr("readonly",true);
            cleamInputTelephony();
            break;
        case "T":
            $('#phone').attr("readonly",false);
            $('#signatureType').attr("readonly",false);
            $('#typeService').attr("readonly",false);
            break;
    }
});

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

function cleamInputTelephony() {
    $('#phone').val("");
    $('#signatureType').val("");
    $('#typeService').val("");
}

$('#discount').blur(function () {
    var value = $('#value').val();
    var discount = $('#discount').val();

    value = value.replace('.','');
    value = value.replace(',', '.');

    discount = discount.replace('.','');
    discount = discount.replace(',', '.');

    var totalValue = parseFloat(value) - parseFloat(discount);
    $('#totalValue').val(totalValue.toFixed(2).replace(".", ","));
});