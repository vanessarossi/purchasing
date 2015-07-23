$(document).ready(function(){
});
    /** variaveis para calcular valores total produto **/
    var quantity = 0;
    var unityPrice = 0;
    var totalPriceMaterial = 0; // valor final de cada material QTD * valor unt
    var totalFinalPriceMaterial=0; // soma dos totais (quantidade * valor unit)

    var numberRequestMaterial = $('#numberRequestMaterial').val()-1;
    var numberRequestService = $('#numberRequestService').val()-1;
    
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
        }
};
function calculateTotalPriceService(i){
    $('#unityPrice'+i).keypress(function(e) { 
        unityPrice = $('#unityPrice'+i).val();
        if (unityPrice != null && unityPrice.length >= 3) {
            while (unityPrice.indexOf(',') != -1)
                unityPrice = unityPrice.replace(',', '.');
            totalPriceService = parseFloat(unityPrice);
            $('#totalPriceService'+i).val(totalPriceService.toFixed(2).replace(".", ","));
            $('#totalPriceService'+i).priceFormat({
                    prefix: 'R$ ',
                    centsSeparator: ',',
                    thousandsSeparator: ''
            }); 
            console.log(i);
            console.log(numberRequestService);
            if (parseInt(i) === parseInt(numberRequestService)) {
                totalFinalPriceService = 0;
                for (var j = 0; j <= parseInt(numberRequestService); j++) {
                    auxUnityPrice = $('#unityPrice'+j).val();
                        while (auxUnityPrice.indexOf(',') != -1)
                            auxUnityPrice = auxUnityPrice.replace(',', '.');

                        auxTotalPriceService = parseFloat(auxUnityPrice);
                        totalFinalPriceService = parseFloat(totalFinalPriceService) + parseFloat(auxTotalPriceService) ;
                        totalPrice = totalFinalPriceService;
                };
                $('#totalFinalPriceService').val(totalFinalPriceService.toFixed(2).replace(".", ","));
                $('#totalFinalPriceService').priceFormat({
                    prefix: 'R$ ',
                    centsSeparator: ',',
                    thousandsSeparator: ''
                });
                $('#totalPrice').val(totalPrice.toFixed(2).replace(".", ","));
                $('#totalPriceTwo').val(totalPrice.toFixed(2).replace(".", ","));
                }
            }  
    });     
};
function calculateTotalPriceServiceTwo(i){
    unityPrice = $('#unityPrice'+i).val();
    if (unityPrice != null && unityPrice.length >= 3) {
        while (unityPrice.indexOf(',') != -1)
            unityPrice = unityPrice.replace(',', '.');
            totalPriceService = parseFloat(unityPrice);
            $('#totalPriceService'+i).val(totalPriceService.toFixed(2).replace(".", ","));
            $('#totalPriceService'+i).priceFormat({
                    prefix: 'R$ ',
                    centsSeparator: ',',
                    thousandsSeparator: ''
            }); 
        if (parseInt(i) === parseInt(numberRequestService)) {
            totalFinalPriceService = 0;
            for (var j = 0; j <= parseInt(numberRequestService); j++) {
            auxUnityPrice = $('#unityPrice'+j).val();
            while (auxUnityPrice.indexOf(',') != -1)
                auxUnityPrice = auxUnityPrice.replace(',', '.');

                auxTotalPriceService = parseFloat(auxUnityPrice);
                totalFinalPriceService = parseFloat(totalFinalPriceService) + parseFloat(auxTotalPriceService) ;
                totalPrice = totalFinalPriceService;
            };
            $('#totalFinalPriceService').val(totalFinalPriceService.toFixed(2).replace(".", ","));
            $('#totalFinalPriceService').priceFormat({
                prefix: 'R$ ',
                centsSeparator: ',',
                thousandsSeparator: ''
            });
            $('#totalPrice').val(totalPrice.toFixed(2).replace(".", ","));
            $('#totalPriceTwo').val(totalPrice.toFixed(2).replace(".", ","));
        }
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
					clearInputs();
                    $('#dateInput').attr("readonly",true);
                    $('#dateFirstInstallment').attr("readonly",true);
                    $('#dateLastInstallment').attr("readonly",true);
                    $('#inputPrice').attr("readonly",true);
                    $('#expirationDate').attr("readonly",false);
                    $('#freight').attr('onblur', 'calculateFirstFormPayment('+intervalDay+')');
                    totalPrice =  ($('#totalPrice').val() != "")? $('#totalPrice').val() : "0,00";
                     /** removendo atributo do calculo **/
                    $('#dateFirstInstallment').removeAttr("onblur");
                }else if (input == true &&  parcels > 0 && intervalDay > 0){
					clearInputs();
                    $('#dateInput').attr("readonly",false);
                    $('#dateFirstInstallment').attr("readonly",false);
                    $('#inputPrice').attr("readonly",false);
                    $('#dateLastInstallment').attr("readonly",true);
                    $('#dateFirstInstallment').attr('onblur', 'calculateValueParcelWithInput('+parcels+','+intervalDay+')');
                    totalPrice =  ($('#totalPrice').val() != "")? $('#totalPrice').val() : "0,00";
                    /** removendo atributo do calculo **/
                    $('#freight').removeAttr("onblur");
                }else if (input == false &&  parcels > 0 && intervalDay > 0){
					clearInputs();
                    $('#dateInput').attr("readonly",true);
                    $('#dateFirstInstallment').attr("readonly",false);
                    $('#inputPrice').attr("readonly",true);
                    $('#dateLastInstallment').attr("readonly",true);
                    $('#dateFirstInstallment').attr('onblur', 'calculateSecondFormPayment('+parcels+','+intervalDay+')');
                     totalPrice =  ($('#totalPrice').val() != "")? $('#totalPrice').val() : "0,00";
                     /** removendo atributo do calculo **/
                    $('#freight').removeAttr("onblur");
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
					clearInputsTwo();
                    $('#dateInputTwo').attr("readonly",true);
                    $('#dateFirstInstallmentTwo').attr("readonly",true);
                    $('#dateLastInstallmentTwo').attr("readonly",true);
                    $('#inputPriceTwo').attr("readonly",true);
                    $('#expirationDateTwo').attr("readonly",false);

                    $('#freightTwo').attr('onblur', 'calculateFirstFormPaymentTwo('+intervalDay+')');
                    totalPrice =  ($('#totalPriceTwo').val() != "")? $('#totalPriceTwo').val() : "0,00";
                     /** removendo atributo do calculo **/
                    $('#dateFirstInstallmentwo').removeAttr("onblur");
                }else if (input == true &&  parcels > 0 && intervalDay > 0){
					clearInputsTwo();
                    $('#dateInputTwo').attr("readonly",false);
                    $('#dateFirstInstallmentTwo').attr("readonly",false);
                    $('#inputPriceTwo').attr("readonly",false);
                    $('#dateLastInstallmentTwo').attr("readonly",true);

                     $('#dateFirstInstallmentTwo').attr('onblur', 'calculateValueParcelWithInputTwo('+parcels+','+intervalDay+')');
                     totalPrice =  ($('#totalPriceTwo').val() != "")? $('#totalPriceTwo').val() : "0,00";
                     /** removendo atributo do calculo **/
                    $('#freightTwo').removeAttr("onblur");
                }else if (input == false &&  parcels > 0 && intervalDay > 0){
					clearInputsTwo();
                    $('#dateInputTwo').attr("readonly",true);
                    $('#dateFirstInstallmentTwo').attr("readonly",false);
                    $('#inputPriceTwo').attr("readonly",true);
                    $('#dateLastInstallmentTwo').attr("readonly",true);

                    $('#dateFirstInstallmentTwo').attr('onblur', 'calculateSecondFormPaymentTwo('+parcels+','+intervalDay+')');
                    totalPrice =  ($('#totalPriceTwo').val() != "")? $('#totalPriceTwo').val() : "0,00";
                    /** removendo atributo do calculo **/
                    $('#freightTwo').removeAttr("onblur");
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
    percentage = $('#discountPercentage').val().replace(',', '.');
    freight = $('#freight').val().replace(',', '.');
    totalPrice = $('#totalPrice').val().replace(',', '.');
    if(totalPrice != 0 && percentage != '' && freight != 0 && freight != ''){
        while (totalPrice.indexOf(',') != -1)
                totalPrice = totalPrice.replace(',', '.');        
        totalFinalPrice =  (parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100))+parseFloat(freight);
        $('#totalFinalPrice').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
    }else{
        $('#totalFinalPrice').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    }
};
function calculateFirstFormPaymentTwo(intervalDay){
    percentage = $('#discountPercentageTwo').val();
    freight = $('#freightTwo').val().replace(',', '.');
    totalPrice = $('#totalPriceTwo').val().replace(',', '.');
    if(totalPrice != 0 && percentage != '' && freight != 0 && freight != ''){
        while (totalPrice.indexOf(',') != -1)
                totalPrice = totalPrice.replace(',', '.');  
        percentage = $('#discountPercentageTwo').val().replace(',', '.');
        totalFinalPrice =  (parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100))+parseFloat(freight);
        $('#totalFinalPriceTwo').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
    }else{
        $('#totalFinalPriceTwo').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    }
};
/** Calculo de valor final com parcela e sem entrada  **/
function calculateSecondFormPayment(parcel,intervalDay){
    dateFirstInstallment = $('#dateFirstInstallment').val();
    freight = $('#freight').val().replace(',', '.');
    percentage = $('#discountPercentage').val().replace(',', '.');
    totalPrice = $('#totalPrice').val().replace(',', '.');
    while (totalPrice.indexOf(',') != -1)
        totalPrice = totalPrice.replace(',', '.');  
    
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null && freight != 0 && freight != ''){
        totalFinalPrice =  (parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100))+parseFloat(freight);
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
    percentage = $('#discountPercentageTwo').val().replace(',', '.');
    freight = $('#freightTwo').val().replace(',', '.');
    totalPrice = $('#totalPriceTwo').val().replace(',', '.');
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null){
        while (totalPrice.indexOf(',') != -1)
            totalPrice = totalPrice.replace(',', '.');  
       
        totalFinalPrice =  (parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100))+parseFloat(freight);
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
/** Calculo de valor final com parcela e  entrada  **/
function calculateValueParcelWithInput(parcel,intervalDay){
    inputValue = $('#inputPrice').val();
    dateFirstInstallment = $('#dateFirstInstallment').val();
    percentage = $('#discountPercentage').val().replace(',', '.');
    freight = $('#freight').val().replace(',', '.');
    totalPrice = $('#totalPrice').val().replace(',', '.');
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null && inputValue != ''){
        while (totalPrice.indexOf(',') != -1)
            totalPrice = totalPrice.replace(',', '.');  
        
        totalFinalPrice =  (parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100))+parseFloat(freight);
        $('#totalFinalPrice').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
        
        totalPriceLessInput = parseFloat(totalFinalPrice) - parseFloat(inputValue.replace(",", "."));
       
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
    percentage = $('#discountPercentageTwo').val().replace(',', '.');
    freight = $('#freightTwo').val().replace(',', '.');
    totalPrice = $('#totalPriceTwo').val().replace(',', '.');
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null && inputValue != ''){
        while (totalPrice.indexOf(',') != -1)
            totalPrice = totalPrice.replace(',', '.');  
        
        totalFinalPrice =  (parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100))+parseFloat(freight);
        $('#totalFinalPriceTwo').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
        
        totalPriceLessInput = parseFloat(totalFinalPrice) - parseFloat(inputValue.replace(",", "."));
       
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
function clearInputs(){
	$('#dateInput').val("");
	$('#dateFirstInstallment').val("");
	$('#dateLastInstallment').val("");
	$('#inputPrice').val("");
	$('#sharePrice').val("");
	$('#expirationDate').val("");
    $('#freight').val("");
    $('#discountPercentage').val("");
};
function clearInputsTwo(){
	$('#dateInputTwo').val("");
	$('#dateFirstInstallmentTwo').val("");
	$('#dateLastInstallmentTwo').val("");
	$('#inputPriceTwo').val("");
	$('#sharePriceTwo').val("");
	$('#expirationDateTwo').val("");
    $('#freightTwo').val("");
    $('#discountPercentageTwo').val("");
};

