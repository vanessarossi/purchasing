$(document).ready(function() {
});

$('#confered').click(function(){
	$('#confirmReceptionForm').attr('action', '/purchasing/ordemCompra/recepcao/conferir');
	$('#confirmReceptionForm').submit();
});

$('#finalize').click(function(){
	$('#confirmReceptionForm').attr('action', '/purchasing/ordemCompra/recepcao/finalizar');
	$('#confirmReceptionForm').submit();
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
      	}
		
};

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
				$('#modalErrorReception').modal('show');
			}
			if(quantity < result){
				$('#modalErrorQuantityLess').modal('show');
			}
        },
        error: function () {
        }
    });
};