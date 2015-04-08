$(document).ready(function () {
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });
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
				totalPrice = parseFloat(totalPrice) + parseFloat($('#total'+i+j).val());
			}
		}
		$('#totalPrice'+i).val(totalPrice.toFixed(2).replace(".", ","));
		totalPrice = 0;
	}	
}
