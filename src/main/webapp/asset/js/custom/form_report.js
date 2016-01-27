$(document).ready(function() {
  $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
  });

  $('#formReportOne').hide();
  $('#formReportTwo').hide();
});

$('input[type="radio"]').on('change', function() {
   var typeReport = $('input[name=report]:checked').val();
   if(typeReport == 'financialManagementReport'){
   		$('#formReportOne').show();
  		$('#formReportTwo').hide();
  		
  		clearFormReportOne();
  		clearFormReportTwo();
   }if(typeReport == 'totalPurchasedProductClassificationReport'){
   		$('#formReportOne').show();
  		$('#formReportTwo').hide();
  		
  		clearFormReportOne();
  		clearFormReportTwo();
   }if(typeReport == 'totalPurchasedServiceTypeReport'){
   		$('#formReportOne').show();
  		$('#formReportTwo').hide();
  		
  		clearFormReportOne();
  		clearFormReportTwo();
   }if(typeReport == 'financialManagementByCostCenterReport'){
   		$('#formReportOne').show();
  		$('#formReportTwo').hide();
  		
  		clearFormReportOne();
  		clearFormReportTwo();
   }if(typeReport == 'purchaseOrderAndSolicitationReport'){
   		$('#formReportOne').show();
  		$('#formReportTwo').hide();
  		
  		clearFormReportOne();
  		clearFormReportTwo();
   }if (typeReport == 'purchaseOrderForSupplierAndExpirationDateReport'){
   		$('#formReportOne').hide();
  		$('#formReportTwo').show();

  		clearFormReportOne();
  		clearFormReportTwo();
   }
});

function clearFormReportOne() {
	$('#year').val('');
	$('#initialMonth').val('');
	$('#finalMonth').val('');
};

function clearFormReportTwo() {
	$('#supplier').val('');
	$('#expirationDate').val('');
};