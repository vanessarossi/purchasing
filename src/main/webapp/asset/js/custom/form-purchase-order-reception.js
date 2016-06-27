$(document).ready(function() {
});

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
           console.log(totalOrder);
           console.log(totalFinalPriceMaterial);
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