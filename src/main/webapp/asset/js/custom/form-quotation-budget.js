$(document).ready(function(){
});
    /** variaveis para calcular valores total produto **/
    var quantity = 0;
    var unityPrice = 0;
    var totalPriceMaterial = 0; // valor final de cada material QTD * valor unt
    var totalFinalPriceMaterial=0; // soma dos totais (quantidade * valor unit)

    var numberRequestMaterial = $('#numberRequestMaterial').val()-1;
    
    /**  variaveis para calculos de pagamento **/
    var totalPrice = 0;   // total mostrado nas informações de pagamento 
    var percentage = 0; // percentual de desconto
    var totalFinalPrice = 0; // total mostrado nas informações de pagamento - desconto
    
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

            totalPriceMaterial = parseFloat(quantity) * parseFloat(unityPrice);
            $('#totalPriceMaterial'+i).val(totalPriceMaterial.toFixed(2).replace(".", ","));
            $('#totalPriceMaterial'+i).priceFormat({
                    prefix: 'R$ ',
                    centsSeparator: ',',
                    thousandsSeparator: ''
            }); 
            if (parseInt(i) === parseInt(numberRequestMaterial)) {
                totalFinalPriceMaterial = 0;
                for (var j = 0; j <= parseInt(numberRequestMaterial); j++) {
                    auxQuantity = $('#quantity'+j).val();
                    auxUnityPrice = $('#unityPrice'+j).val();
                        while (auxQuantity.indexOf(',') != -1)
                            auxQuantity = auxQuantity.replace(',', '.');
                        while (auxUnityPrice.indexOf(',') != -1)
                            auxUnityPrice = auxUnityPrice.replace(',', '.');

                        auxTotalPriceMaterial = parseFloat(auxQuantity) * parseFloat(auxUnityPrice);
                        totalFinalPriceMaterial = parseFloat(totalFinalPriceMaterial) + parseFloat(auxTotalPriceMaterial) ;
                        totalPrice = totalFinalPriceMaterial;
                };
                $('#totalFinalPriceMaterial').val(totalFinalPriceMaterial.toFixed(2).replace(".", ","));
                $('#totalFinalPriceMaterial').priceFormat({
                    prefix: 'R$ ',
                    centsSeparator: ',',
                    thousandsSeparator: ''
                });
                $('#totalPrice').val(totalPrice.toFixed(2).replace(".", ","));
                $('#totalPriceTwo').val(totalPrice.toFixed(2).replace(".", ","));
                $('#totalPriceThree').val(totalPrice.toFixed(2).replace(".", ","));
                }
            }  
    });     
};

function calculateTotalPriceMaterialTwo(i){
    quantity = $('#quantity'+i).val();
    unityPrice = $('#unityPrice'+i).val();

        while (quantity.indexOf(',') != -1)
            quantity = quantity.replace(',', '.');
        while (unityPrice.indexOf(',') != -1)
            unityPrice = unityPrice.replace(',', '.');

        totalPriceMaterial = parseFloat(quantity) * parseFloat(unityPrice);
        $('#totalPriceMaterial'+i).val(totalPriceMaterial.toFixed(2).replace(".", ","));
        $('#totalPriceMaterial'+i).priceFormat({
                prefix: 'R$ ',
                centsSeparator: ',',
                thousandsSeparator: ''
        }); 
        if (parseInt(i) === parseInt(numberRequestMaterial)) {
            totalFinalPriceMaterial = 0;
            for (var j = 0; j <= parseInt(numberRequestMaterial); j++) {
                auxQuantity = $('#quantity'+j).val();
                auxUnityPrice = $('#unityPrice'+j).val();
                    while (auxQuantity.indexOf(',') != -1)
                        auxQuantity = auxQuantity.replace(',', '.');
                    while (auxUnityPrice.indexOf(',') != -1)
                       auxUnityPrice = auxUnityPrice.replace(',', '.');

                        auxTotalPriceMaterial = parseFloat(auxQuantity) * parseFloat(auxUnityPrice);
                        totalFinalPriceMaterial = parseFloat(totalFinalPriceMaterial) + parseFloat(auxTotalPriceMaterial) ;
                        totalPrice = totalFinalPriceMaterial;
            };
            $('#totalFinalPriceMaterial').val(totalFinalPriceMaterial.toFixed(2).replace(".", ","));
            $('#totalFinalPriceMaterial').priceFormat({
                prefix: 'R$ ',
                centsSeparator: ',',
                thousandsSeparator: ''
            });
            $('#totalPrice').val(totalPrice.toFixed(2).replace(".", ","));
            $('#totalPriceTwo').val(totalPrice.toFixed(2).replace(".", ","));
            $('#totalPriceThree').val(totalPrice.toFixed(2).replace(".", ","));
        }
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

                    $('#dateFirstInstallment').attr('onblur', 'calculateValueParcelWithInput('+parcels+','+intervalDay+')');
                }else if (input == false &&  parcels > 0 && intervalDay > 0){
                    $('#dateInput').attr("readonly",true);
                    $('#dateFirstInstallment').attr("readonly",false);
                    $('#inputPrice').attr("readonly",true);
                    $('#dateLastInstallment').attr("readonly",true);

                     $('#dateFirstInstallment').attr('onblur', 'calculateSecondFormPayment('+parcels+','+intervalDay+')');
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
                    $('#dateInputTwo').attr("readonly",true);
                    $('#dateFirstInstallmentTwo').attr("readonly",true);
                    $('#dateLastInstallmentTwo').attr("readonly",true);
                    $('#inputPriceTwo').attr("readonly",true);
                    $('#expirationDateTwo').attr("readonly",false);

                    $('#discountPercentageTwo').attr('onblur', 'calculateFirstFormPaymentTwo('+intervalDay+')');

                }else if (input == true &&  parcels > 0 && intervalDay > 0){
                    $('#dateInputTwo').attr("readonly",false);
                    $('#dateFirstInstallmentTwo').attr("readonly",false);
                    $('#inputPriceTwo').attr("readonly",false);
                    $('#dateLastInstallmentTwo').attr("readonly",true);

                     $('#dateFirstInstallmentTwo').attr('onblur', 'calculateValueParcelWithInputTwo('+parcels+','+intervalDay+')');
                }else if (input == false &&  parcels > 0 && intervalDay > 0){
                    $('#dateInputTwo').attr("readonly",true);
                    $('#dateFirstInstallmentTwo').attr("readonly",false);
                    $('#inputPriceTwo').attr("readonly",true);
                    $('#dateLastInstallmentTwo').attr("readonly",true);

                    $('#dateFirstInstallmentTwo').attr('onblur', 'calculateSecondFormPaymentTwo('+parcels+','+intervalDay+')');
                }
           };
        },
        error: function () {
            alert("Ocorreu um erro no processamento dos dados.");
        }
    });   
});

$('#formPaymentThree').change(function(){
    formPaymentId = $('#formPaymentThree').val();
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
                    $('#dateInputThree').attr("readonly",true);
                    $('#dateFirstInstallmentThree').attr("readonly",true);
                    $('#dateLastInstallmentThree').attr("readonly",true);
                    $('#inputPriceThree').attr("readonly",true);
                    $('#expirationDateThree').attr("readonly",false);

                    $('#discountPercentageThree').attr('onblur', 'calculateFirstFormPaymentThree('+intervalDay+')');

                }else if (input == true &&  parcels > 0 && intervalDay > 0){
                    $('#dateInputThree').attr("readonly",false);
                    $('#dateFirstInstallmentThree').attr("readonly",false);
                    $('#inputPriceThree').attr("readonly",false);
                    $('#dateLastInstallmentThree').attr("readonly",true);

                     $('#dateFirstInstallmentThree').attr('onblur', 'calculateValueParcelWithInputThree('+parcels+','+intervalDay+')');
                }else if (input == false &&  parcels > 0 && intervalDay > 0){
                    $('#dateInputThree').attr("readonly",true);
                    $('#dateFirstInstallmentThree').attr("readonly",false);
                    $('#inputPriceThree').attr("readonly",true);
                    $('#dateLastInstallmentThree').attr("readonly",true);

                    $('#dateFirstInstallmentThree').attr('onblur', 'calculateSecondFormPaymentThree('+parcels+','+intervalDay+')');
                }
           };
        },
        error: function () {
            alert("Ocorreu um erro no processamento dos dados.");
        }
    });   
});


/** Calculo de valor final sem parcelas e entrada   **/
function calculateFirstFormPayment(intervalDay){
    percentage = $('#discountPercentage').val();
    if(totalPrice != 0 && percentage != '' ){
        percentage = $('#discountPercentage').val();
        totalFinalPrice =  parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100);
        $('#totalFinalPrice').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
    }else{
        $('#totalFinalPrice').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    }
};

function calculateFirstFormPaymentTwo(intervalDay){
    percentage = $('#discountPercentageTwo').val();
    if(totalPrice != 0 && percentage != '' ){
        percentage = $('#discountPercentageTwo').val();
        totalFinalPrice =  parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100);
        $('#totalFinalPriceTwo').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
    }else{
        $('#totalFinalPriceTwo').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    }
};

function calculateFirstFormPaymentThree(intervalDay){
    percentage = $('#discountPercentageThree').val();
    if(totalPrice != 0 && percentage != '' ){
        percentage = $('#discountPercentageThree').val();
        totalFinalPrice =  parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100);
        $('#totalFinalPriceThree').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
    }else{
        $('#totalFinalPriceThree').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    }
};

/** Calculo de valor final com parcela e sem entrada  **/
function calculateSecondFormPayment(parcel,intervalDay){
    dateFirstInstallment = $('#dateFirstInstallment').val();
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null){
        percentage = $('#discountPercentage').val();
        totalFinalPrice =  parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100);
        $('#totalFinalPrice').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));

        valueParcel = parseFloat(totalFinalPrice) / parseFloat(parcel);
        $('#sharePrice').val(parseFloat(valueParcel).toFixed(2).replace(".", ","));

        dateLastInstallment = "";
        if (intervalDay == 30 || intervalDay == 31) {
            dateLastInstallment =  moment(dateFirstInstallment,"DD-MM-YYYY").add(parseInt(parcel-1), 'months');
        }else{
            days = parseInt(parcel-1) * parseInt(intervalDay);
            dateLastInstallment =  moment(dateFirstInstallment,"DD-MM-YYYY").add(parseInt(days), 'days');
        }   
        dateLastInstallmentConvert = moment(dateLastInstallment).format('DD/MM/YYYY');    ;
        $('#dateLastInstallment').val(dateLastInstallmentConvert);
    }else{
        $('#totalFinalPrice').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    }
};

function calculateSecondFormPaymentTwo(parcel,intervalDay){
    dateFirstInstallment = $('#dateFirstInstallmentTwo').val();
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null){
        percentage = $('#discountPercentageTwo').val();
        totalFinalPrice =  parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100);
        $('#totalFinalPriceTwo').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));

        valueParcel = parseFloat(totalFinalPrice) / parseFloat(parcel);
        $('#sharePriceTwo').val(parseFloat(valueParcel).toFixed(2).replace(".", ","));

        dateLastInstallment = "";
        if (intervalDay == 30 || intervalDay == 31) {
            dateLastInstallment =  moment(dateFirstInstallment,"DD-MM-YYYY").add(parseInt(parcel-1), 'months');
        }else{
            days = parseInt(parcel-1) * parseInt(intervalDay);
            dateLastInstallment =  moment(dateFirstInstallment,"DD-MM-YYYY").add(parseInt(days), 'days');
        }   
        dateLastInstallmentConvert = moment(dateLastInstallment).format('DD/MM/YYYY');    ;
        $('#dateLastInstallmentTwo').val(dateLastInstallmentConvert);
    }else{
        $('#totalFinalPriceTwo').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    } 
};

function calculateSecondFormPaymentThree(parcel,intervalDay){
    dateFirstInstallment = $('#dateFirstInstallmentThree').val();
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null){
        percentage = $('#discountPercentageThree').val();
        totalFinalPrice =  parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100);
        $('#totalFinalPriceThree').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
        valueParcel = parseFloat(totalFinalPrice) / parseFloat(parcel);
        $('#sharePriceThree').val(parseFloat(valueParcel).toFixed(2).replace(".", ","));
        dateLastInstallment = "";
        if (intervalDay == 30 || intervalDay == 31) {
            dateLastInstallment =  moment(dateFirstInstallment,"DD-MM-YYYY").add(parseInt(parcel-1), 'months');
        }else{
            days = parseInt(parcel-1) * parseInt(intervalDay);
            dateLastInstallment =  moment(dateFirstInstallment,"DD-MM-YYYY").add(parseInt(days), 'days');
        }   
        dateLastInstallmentConvert = moment(dateLastInstallment).format('DD/MM/YYYY');    ;
        $('#dateLastInstallmentThree').val(dateLastInstallmentConvert);
    }else{
        $('#totalFinalPriceThree').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    }  
};

/** Calculo de valor final com parcela e  entrada  **/
function calculateValueParcelWithInput(parcel,intervalDay){
    inputValue = $('#inputPrice').val();
    dateFirstInstallment = $('#dateFirstInstallment').val();
    
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null && inputValue != ''){
        percentage = $('#discountPercentage').val();
        totalFinalPrice =  parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100);
        $('#totalFinalPrice').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
        
        totalPriceLessInput = parseFloat(totalFinalPrice) - parseFloat(inputValue.replace(".", ","));
       
        valueParcel = parseFloat(totalPriceLessInput) / parseFloat(parcel);
        $('#sharePrice').val(parseFloat(valueParcel).toFixed(2).replace(".", ","));

        dateLastInstallment = "";
        if (intervalDay == 30 || intervalDay == 31) {
            dateLastInstallment =  moment(dateFirstInstallment,"DD-MM-YYYY").add(parseInt(parcel-1), 'months');
        }else{
            days = parseInt(parcel-1) * parseInt(intervalDay);
            dateLastInstallment =  moment(dateFirstInstallment,"DD-MM-YYYY").add(parseInt(days), 'days');
        }   
        dateLastInstallmentConvert = moment(dateLastInstallment).format('DD/MM/YYYY');    ;
        $('#dateLastInstallment').val(dateLastInstallmentConvert);
    }else{
        $('#totalFinalPrice').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    } 
};

function calculateValueParcelWithInputTwo(parcel,intervalDay){
    inputValue = $('#inputPriceTwo').val();
    dateFirstInstallment = $('#dateFirstInstallmentTwo').val();
    
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null && inputValue != ''){
        percentage = $('#discountPercentageTwo').val();
        totalFinalPrice =  parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100);
        $('#totalFinalPriceTwo').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
        
        totalPriceLessInput = parseFloat(totalFinalPrice) - parseFloat(inputValue.replace(".", ","));
       
        valueParcel = parseFloat(totalPriceLessInput) / parseFloat(parcel);
        $('#sharePriceTwo').val(parseFloat(valueParcel).toFixed(2).replace(".", ","));

        dateLastInstallment = "";
        if (intervalDay == 30 || intervalDay == 31) {
            dateLastInstallment =  moment(dateFirstInstallment,"DD-MM-YYYY").add(parseInt(parcel-1), 'months');
        }else{
            days = parseInt(parcel-1) * parseInt(intervalDay);
            dateLastInstallment =  moment(dateFirstInstallment,"DD-MM-YYYY").add(parseInt(days), 'days');
        }   
        dateLastInstallmentConvert = moment(dateLastInstallment).format('DD/MM/YYYY');    ;
        $('#dateLastInstallmentTwo').val(dateLastInstallmentConvert);
    }else{
        $('#totalFinalPriceTwo').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    } 
};

function calculateValueParcelWithInputThree(parcel,intervalDay){
    inputValue = $('#inputPriceThree').val();
    dateFirstInstallment = $('#dateFirstInstallmentThree').val();
    
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null && inputValue != ''){
        percentage = $('#discountPercentageThree').val();
        totalFinalPrice =  parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100);
        $('#totalFinalPriceThree').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
        
        totalPriceLessInput = parseFloat(totalFinalPrice) - parseFloat(inputValue.replace(".", ","));
       
        valueParcel = parseFloat(totalPriceLessInput) / parseFloat(parcel);
        $('#sharePriceThree').val(parseFloat(valueParcel).toFixed(2).replace(".", ","));

        dateLastInstallment = "";
        if (intervalDay == 30 || intervalDay == 31) {
            dateLastInstallment =  moment(dateFirstInstallment,"DD-MM-YYYY").add(parseInt(parcel-1), 'months');
        }else{
            days = parseInt(parcel-1) * parseInt(intervalDay);
            dateLastInstallment =  moment(dateFirstInstallment,"DD-MM-YYYY").add(parseInt(days), 'days');
        }   
        dateLastInstallmentConvert = moment(dateLastInstallment).format('DD/MM/YYYY');    ;
        $('#dateLastInstallmentThree').val(dateLastInstallmentConvert);
    }else{
        $('#totalFinalPriceThree').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    } 
};

