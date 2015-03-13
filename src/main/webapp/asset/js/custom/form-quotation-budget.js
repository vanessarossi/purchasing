$(document).ready(function(){
});

    var totalUnitPriceMaterial = 0;
    var finalPriceMaterial = 0;
    var quantity = 0;
    var unityPrice = 0;
    var totalMaterial = $('#totalMaterial').val() - 1;
    var totalPrice = 0;
    var totalFinalPrice = 0;
    var percentage = 0;

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

function calculateTotalPriceMaterial(i){
    $('#unityPrice'+i).keypress(function(e) { 
        quantity = $('#quantity'+i).val();
        unityPrice = $('#unityPrice'+i).val();
        if (unityPrice != " "  && unityPrice != null && unityPrice.length >= 3) {
            while (quantity.indexOf(',') != -1)
                quantity = quantity.replace(',', '.');
            while (unityPrice.indexOf(',') != -1)
                unityPrice = unityPrice.replace(',', '.');

            totalPrice = parseFloat(quantity) * parseFloat(unityPrice);
            $('#totalPrice'+i).val(totalPrice.toFixed(2).replace(".", ","));
            $('#totalPrice'+i).priceFormat({
                    prefix: 'R$ ',
                    centsSeparator: ',',
                    thousandsSeparator: ''
            });        
            if (parseInt(i) === parseInt(totalMaterial)) {
                for (var j = 0; j <= parseInt(totalMaterial); j++) {
                    auxQuantity = $('#quantity'+j).val();
                    auxUnityPrice = $('#unityPrice'+j).val();
                        while (auxQuantity.indexOf(',') != -1)
                            auxQuantity = auxQuantity.replace(',', '.');
                        while (unityPrice.indexOf(',') != -1)
                            auxUnityPrice = auxUnityPrice.replace(',', '.');

                        auxFinalPrice = parseFloat(auxQuantity) * parseFloat(auxUnityPrice);
                        
                        totalUnitPriceMaterial = parseFloat(totalUnitPriceMaterial) + parseFloat(auxUnityPrice);
                        finalPriceMaterial = parseFloat(finalPriceMaterial) + parseFloat(auxFinalPrice) ;
                };
                $('#totalUnitPriceMaterial').val(totalUnitPriceMaterial.toFixed(2).replace(".", ","));
                $('#totalUnitPriceMaterial').priceFormat({
                    prefix: 'R$ ',
                    centsSeparator: ',',
                    thousandsSeparator: ''
                });
                $('#finalPriceMaterial').val(finalPriceMaterial.toFixed(2).replace(".", ","));
                $('#finalPriceMaterial').priceFormat({
                    prefix: 'R$ ',
                    centsSeparator: ',',
                    thousandsSeparator: ''
                });
                $('#totalPrice').val(finalPriceMaterial.toFixed(2).replace(".", ","));
                $('#totalPriceTwo').val(finalPriceMaterial.toFixed(2).replace(".", ","));
                $('#totalPriceThree').val(finalPriceMaterial.toFixed(2).replace(".", ","));
                }
            }  
    });     
};

$('#formPayment').change(function(){
    formPaymentId = $('#formPayment').val();
    $.ajax({
        type: "GET",
        url: getContextPath()+'cotacao/pesquisar/detalhes/pagamento/'+formPaymentId+'/json',
        dataType: "json",
        beforeSend: function(){
        },
        success: function (result) {
           if (result != false) {
                var input = result["input"];
                var parcels = result["parcels"];
                var intervalDay = result["intervalDay"];

                if (input == false &&  parcels == 0 && (intervalDay == 0 || intervalDay > 0)){
                    $('#dateInput').attr("readonly",true);
                    $('#dateFirstInstallment').attr("readonly",true);
                    $('#dateLastInstallment').attr("readonly",true);
                    $('#inputPrice').attr("readonly",true);
                    $('#expirationDate').attr("readonly",false);

                    $('#discountPercentage').attr('onblur', 'calculateFirstFormPayment('+intervalDay+')');

                }else if (input == true &&  parcels > 0 && intervalDay > 0){
                    $('#dateInput').attr("readonly",false);
                    $('#dateFirstInstallment').attr("readonly",false);
                    $('#inputPrice').attr("readonly",false);
                    $('#dateLastInstallment').attr("readonly",true);

                    $('#discountPercentage').attr('onblur', 'calculateFirstFormPayment('+intervalDay+')');
                }else if (input == false &&  parcels > 0 && intervalDay > 0){
                    $('#dateInput').attr("readonly",true);
                    $('#dateFirstInstallment').attr("readonly",false);
                    $('#inputPrice').attr("readonly",true);
                    $('#dateLastInstallment').attr("readonly",true);

                    $('#discountPercentage').attr('onblur', 'calculateFirstFormPayment('+intervalDay+')');
                }
           };
        },
        error: function () {
            alert("Ocorreu um erro no processamento dos dados.");
        }
    });   
});

$('#formPaymentTwo').change(function(){
    formPaymentId = $('#formPaymentTwo').val();
});

$('#formPaymentThree').change(function(){
    formPaymentId = $('#formPaymentThree').val();
});


/** Calculo de valor final sem parcelas e entrada   **/
function calculateFirstFormPayment(intervalDay){
    if( finalPriceMaterial != 0 && percentage != '' ){
        percentage = $('#discountPercentage').val();
        totalFinalPrice =  parseFloat(finalPriceMaterial) - ((parseFloat(finalPriceMaterial) * parseFloat(percentage))/100);
        $('#totalFinalPrice').val(totalFinalPrice);
    }else{
        $('#totalFinalPrice').val("");
    }
};

function calculateFirstFormPaymentTwo(intervalDay){
    if( finalPriceMaterial != 0 && percentage != '' ){
        percentage = $('#discountPercentageTwo').val();
        totalFinalPrice =  parseFloat(finalPriceMaterial) - ((parseFloat(finalPriceMaterial) * parseFloat(percentage))/100);
        $('#totalFinalPriceTwo').val(totalFinalPrice);
    }else{
        $('#totalFinalPriceTwo').val("");
    }
};

function calculateFirstFormPaymentThree(intervalDay){
    if( finalPriceMaterial != 0 && percentage != '' ){
        percentage = $('#discountPercentageThree').val();
        totalFinalPrice =  parseFloat(finalPriceMaterial) - ((parseFloat(finalPriceMaterial) * parseFloat(percentage))/100);
        $('#totalFinalPriceThree').val(totalFinalPrice);
    }else{
        $('#totalFinalPriceThree').val("");
    }
};

/** Calculo de valor final com parcela e sem entrada  **/
function calculateSecondFormPayment(parcel,intervalDay){
    dateFirstInstallment = $('#dateFirstInstallment').val();
    if( finalPriceMaterial != 0 && percentage != '' && dateFirstInstallment != ''){
        
        percentage = $('#discountPercentage').val();
        finalPriceMaterial =  parseFloat(finalPriceMaterial) - ((parseFloat(finalPriceMaterial) * parseFloat(percentage))/100);
        $('#totalFinalPrice').val(finalPriceMaterial);

        valueParcel = parseFloat(totalFinalPrice) / parseFloat(parcel);
        $('#sharePrice').val(valueParcel);

    }else{
        $('#totalFinalPrice').val("");
    }
};

function calculateSecondFormPaymentTwo(parcel,intervalDay){
};

function calculateSecondFormPaymentThree(parcel,intervalDay){
};

/** Calculo de valor final com parcela e  entrada  **/
function calculateValueParcelWithInput(input,parcel,intervalDay){
};

function calculateValueParcelWithInputTwo(input,parcel,intervalDay){
};

function calculateValueParcelWithInputThree(input,parcel,intervalDay){
};

