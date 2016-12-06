$(document).ready(function() {
});


function validNumber(num) {
    var er = /[^0-9]/;
    er.lastIndex = 0;
    var campo = num;
    if (er.test(campo.value)) {
        campo.value = "";
    }
}


$('#confered').click(function(){
	var tax_document = $("#taxDocument").val();
    var observation = $('#observation').val();
        if(tax_document != null && tax_document != '' && observation != null && observation != ''){
            $('#confirmReceptionForm').attr('action', '/purchasing/ordemCompra/recepcao/conferir');
            $('#confirmReceptionForm').submit();
        }else{
            alert("Existem informações obrigatórias a serem preenchidas (Ex: Número da nota ou observação de recepção).");
        }
    
});

$('#finalize').click(function(){
	var tax_document = $("#taxDocument").val();
    	if(tax_document != null && tax_document != '' && tax_document != ' '){
    		$('#confirmReceptionForm').attr('action', '/purchasing/ordemCompra/recepcao/finalizar');
    		$('#confirmReceptionForm').submit();
    	}else{
    		alert("Informe o número da nota fiscal.");
    	}	
});

function calculateTotalPriceMaterial(i,numberRequestMaterial){
    quantity = $('#quantity'+i).val();
    unityPrice = $('#unityPrice'+i).val();
	numberRequestMaterial = numberRequestMaterial;
	orderRequestId = $('#orderRequestId'+i).val();

        while (quantity.indexOf(',') != -1)
            quantity = quantity.replace(',', '.');
        while (unityPrice.indexOf(',') != -1)
            unityPrice = unityPrice.replace(',', '.');
		
		validateQuantity(orderRequestId,quantity,i);
		
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

           var totalOrder = $('#totalPrice').val();
           if (totalOrder.replace(",", ".") != totalFinalPriceMaterial.toFixed(2)){
                insertTextFinal("Sua recepção está divergente ao pedido, se estiver de acordo com a nota, apenas confira.");
                $('#confered').show();
                $('#finalize').hide();
           }else{
                insertTextFinal("Sua recepção está igual ao pedido, caso as informações de pagamento estejam corretas com a nota, finalize a recepção.");
                $('#confered').show();
                $('#finalize').show();
           }
      	}
};

function calculateTotalPriceService(i,numberRequestService){
    price = $('#price'+i).val();
    numberRequestService = numberRequestService;
    orderRequestId = $('#orderRequestService'+i).val();

    while (price.indexOf(',') != -1)
            price = price.replace(',', '.');

    validatePrice(orderRequestId,price,i);    

        if (parseInt(i) === parseInt(numberRequestService)) {
            totalFinalPriceService = 0;
            for (var j = 0; j <= parseInt(numberRequestService); j++) {
                    servicePrice = $('#price'+j).val();
                    while (servicePrice.indexOf(',') != -1)
                        servicePrice = servicePrice.replace(',', '.');
                        totalFinalPriceService = parseFloat(totalFinalPriceService) + parseFloat(servicePrice) ;
                        totalPrice = totalFinalPriceService;
            };
            $('#totalFinalPriceService').val(totalFinalPriceService.toFixed(2).replace(".", ","));
            $('#totalFinalPriceService').priceFormat({
                prefix: 'R$ ',
                centsSeparator: ',',
                thousandsSeparator: ''
            });

           var totalOrder = $('#totalPrice').val();
        
           if (totalOrder.replace(",", ".") != totalFinalPriceService.toFixed(2)){
                insertTextFinal("Sua recepção está divergente ao pedido, se estiver de acordo com a nota, apenas confira.");
                $('#confered').show();
                $('#finalize').hide();
            }else{
                insertTextFinal("Sua recepção está igual ao pedido, caso as informações de pagamento estejam corretas com a nota, finalize a recepção.");
                $('#confered').show();
                $('#finalize').show();
            }
        }
}

function enableImcompletePrice(i){
    $('#price'+i).val("");
    $('#serviceComplete'+i).prop('checked', false); 
}

function desableImcompletePrice(i,numberRequestService){
    var price = $('#priceService'+i).val().replace(",", ".");
    $('#price'+i).val("");
    $('#price'+i).val(parseFloat(price).toFixed(2).replace(".", ","));
    $('#serviceImcomplete'+i).prop('checked', false);

    calculateTotalPriceService(i,numberRequestService);
}

function insertText(index, message) {
    document.getElementById('check'+index).innerHTML = message;
}

function insertTextFinal(message){
    $('#resultMessage').text(message);
}

function validateQuantity(orderRequestId,quantity,i){
    $.ajax({
        type: "GET",
        url: getContextPath()+'ordemCompra/quantidade/receber/'+orderRequestId+'/json',
        dataType: "json",
        beforeSend: function(){
        },
        success: function (result) {
			if(quantity > result){
				$('#quantity'+i).val("");
				$('#totalPriceMaterial'+i).val("");
                insertText(i,"Recepção superior ao pedido");
				//$('#modalErrorReception').modal('show');
			}else if(quantity < result){
                insertText(i,"Recepção inferior ao pedido");
				//$('#modalErrorQuantityLess').modal('show');
			}else if(quantity == result){
                insertText(i,"");
            }
        },
        error: function () {
        }
    });
};

function validatePrice(orderRequestId,price,i){
  $.ajax({
        type: "GET",
        url: getContextPath()+'ordemCompra/valor/receber/'+orderRequestId+'/json',
        dataType: "json",
        beforeSend: function(){
        },
        success: function (result) {
            if(price > result){
                $('#price'+i).val("");
                $('#totalFinalPriceService').val("");
                //insertText(i,"Recepção superior ao pedido");
            }else if(price < result){
                //insertText(i,"Recepção inferior ao pedido");
            }else if(price == result){
                //insertText(i,"");
            }
        },
        error: function () {
        }
    });  
}