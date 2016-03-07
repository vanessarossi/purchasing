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

      addActionForm('formReportOne',getContextPath()+'relatorio/financeiro/gerencial');
   		$('#formReportOne').show();
  		$('#formReportTwo').hide();
  		clearFormReportOne();
  		clearFormReportTwo();
      
   }if(typeReport == 'totalPurchasedProductClassificationReport'){

      addActionForm('formReportOne',getContextPath()+'relatorio/relacao/compras/classificada/categoria/produto');
   		$('#formReportOne').show();
  		$('#formReportTwo').hide();
  		clearFormReportOne();
  		clearFormReportTwo();

   }if(typeReport == 'totalPurchasedProductClassificationByCostCenterReport'){

      addActionForm('formReportOne',getContextPath()+'relatorio/relacao/compras/classificada/categoria/produto/centro/custo');
      $('#formReportOne').show();
      $('#formReportTwo').hide();
      clearFormReportOne();
      clearFormReportTwo();

   }if(typeReport == 'totalPurchasedServiceTypeReport'){

      addActionForm('formReportOne',getContextPath()+'relatorio/relacao/compras/classificada/tipo/servico');
   		$('#formReportOne').show();
  		$('#formReportTwo').hide();
  		clearFormReportOne();
  		clearFormReportTwo();

   }if(typeReport == 'totalPurchasedServiceTypeByCostCenterReport'){

      addActionForm('formReportOne',getContextPath()+'relatorio/relacao/compras/classificada/tipo/servico/centro/custo');
      $('#formReportOne').show();
      $('#formReportTwo').hide();
      clearFormReportOne();
      clearFormReportTwo();

   }if(typeReport == 'financialManagementByCostCenterReport'){

      addActionForm('formReportOne',getContextPath()+'relatorio/financeiro/gerencial/centro/custo');
   		$('#formReportOne').show();
  		$('#formReportTwo').hide();
  		clearFormReportOne();
  		clearFormReportTwo();

   }if(typeReport == 'purchaseOrderAndSolicitationReport'){

      addActionForm('formReportOne',getContextPath()+'relatorio/relacao/solicitacao/ordem/compra');
   		$('#formReportOne').show();
  		$('#formReportTwo').hide();
  		clearFormReportOne();
  		clearFormReportTwo();

   }if (typeReport == 'purchaseOrderForSupplierAndExpirationDateReport'){

      addActionForm('formReportTwo',getContextPath()+'relatorio/relacao/ordem/fornecedor/vencimento');
   		$('#formReportOne').hide();
  		$('#formReportTwo').show();
  		clearFormReportOne();
  		clearFormReportTwo();

   }
});

function clearFormReportOne() {
	$('#year').val('');
	$('#initialMonth').val('');
	$('#lastMonth').val('');
};

function clearFormReportTwo() {
	$('#supplier').val('');
	$('#expirationDate').val('');
};

function addActionForm(idForm,url) {
  $('#'+idForm).attr('action',url);
};