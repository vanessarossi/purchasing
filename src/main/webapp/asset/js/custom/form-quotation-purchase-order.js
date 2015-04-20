$(document).ready(function () {
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });
    $('#initialPurchasingOrderJustificationForm').hide();
    $('#divJustification').hide();
});

function checkedRequest(budgetIndex,itemRequest) {
	/** 
		budgetIndex = index for
		ItemRequest = Material = product.id / Service = service.id
	**/
	var valueChecked = $('#request'+budgetIndex+itemRequest).val();
	var totalIndexBudget = $('#totalBudget').val();
	var totalItemBudget = $('#totalItem').val();

	if (valueChecked === 'true') {
		$('#request'+budgetIndex+itemRequest).val('');
		calculateTotalPriceChecked(itemRequest,totalItemBudget);
	} if (valueChecked === '') {
		validateChecked(budgetIndex,itemRequest,totalIndexBudget)
		calculateTotalPriceChecked(itemRequest,totalItemBudget);
	};
}

function validateChecked(budgetIndex,itemRequest,totalIndexBudget){
	var result;
	for (var i = 0; i <= (totalIndexBudget - 1); i++) {
		var valueChecked = $('#request'+i+itemRequest).val();
		if (valueChecked === 'true') {
			result = 'false';
			break;
		}else{
			result = 'true';
		}
	}
	if (result === 'false') {
		$('#request'+budgetIndex+itemRequest).val('');
		$('#request'+budgetIndex+itemRequest).prop('checked', false);
	}else{
		$('#request'+budgetIndex+itemRequest).val(true);	
	}
}

function calculateTotalPriceChecked(totalIndexBudget,totalItemBudget){
	totalPrice = 0;
	for (var i = 0; i <= (totalIndexBudget - 1); i++) {
		for (var j = 0; j <= (totalItemBudget - 1); j++) {
			if ($('.'+i+j).val() === 'true') {
				valueTotal = $('#total'+i+j).val().replace(',', '.');
				totalPrice = parseFloat(totalPrice) + parseFloat(valueTotal);
			}
		}
		$('#totalPriceChoise'+i).val(totalPrice.toFixed(2).replace(".", ","));
		totalPrice = 0;
	}	
}

function validateSingleBudgetChoose(budgetIndex,totalIndexBudget,link){
	var totalPrice = $('#totalPrice'+budgetIndex).val();
	var result = false;
	for (var i = 0 ; i <= (totalIndexBudget-1); i++) {
		otherTotalPrice = $('#totalPrice'+i).val();
		if (totalIndexBudget == 1 || (budgetIndex != i && parseFloat(totalPrice) > parseFloat(otherTotalPrice))) {
			var valueChoised = $('input[name="purchaseOrders['+budgetIndex+'].orderRequests[0].budgetQuotation.budget.id"]').val();
			$('#exclusive').attr("name","exclusive");
			$('#justification').attr("name","justification");
			$('#budgetChoised').val(valueChoised);
			$('#initialPurchasingOrderJustificationForm').show();
			result = true;	
			break;
		}else{
			result = false;
		}
	}
	if (result == false) {
		window.location = link;
	};
}

function validateTotalBudgetChoose(totalIndexBudget){
	quantity=0;
	for (var i = 0 ; i <= (totalIndexBudget-1); i++) {
		if ($('#totalPriceChoise'+i).val() != '0,00' && $('#totalPriceChoise'+i).val() != '') {
			quantity += parseInt(1);
		};
	}
	if (quantity > 1) {
		$('#initialPurchasingOrderForm').submit();
	};
}