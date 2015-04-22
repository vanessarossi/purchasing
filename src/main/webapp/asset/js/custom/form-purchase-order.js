$(document).ready(function(){
	 fillTable();
});

$('#helpPlace').change(function(){
	helpPlace = $('#helpPlace').val();

	switch (helpPlace) {
	    case "0":
	        clear();
	        break;
	    case "1":
	        fillAdministrativeOffice();
	        break;
	    case "2":
	       fillDiagnosticCenter();
	        break;
	    case "3":
	        fillDrugstore();
	        break;
	    case "4":
	        fillOptica();
	        break;
	    case "5":
	        fillReferenceCenter();
	        break;
	    case "6":
	        fillHospital();
	        break;
	    case "7":
	        fillDepartmentOfOccupationalHealth();
	        break;
	    case "8":
	        fillP
	        break;
	   case "9":
	        fillSpecialtyCenter();
	        break;
	}
});

function fillAdministrativeOffice () {
	$('#street').val("Rua Francisquinho Dias");
	$('#neighborhood').val("Centro");
	$('#number').val("583");
	$('#receptorName').val();
}
function fillDiagnosticCenter () {
	$('#street').val("Rua Silvia Jardim");
	$('#neighborhood').val("Centro");
	$('#number').val("260/268");
	$('#receptorName').val("");
}
function fillDrugstore () {
	$('#street').val("Rua Campos Salles");
	$('#neighborhood').val("Centro");
	$('#number').val("491");
	$('#receptorName').val("");
}
function fillOptica () {
	$('#street').val("Rua Campos Salles");
	$('#neighborhood').val("Centro");
	$('#number').val("491");
	$('#receptorName').val("");
}
function fillReferenceCenter () {
	$('#street').val("Rua Coronel Alípio Dias");
	$('#neighborhood').val("Centro");
	$('#number').val("885");
	$('#receptorName').val("");
}
function fillHospital () {
	$('#street').val("Rua Alexandre Carlos de Melo");
	$('#neighborhood').val("Jardim Aeroporto");
	$('#number').val("118");
	$('#receptorName').val("");
}
function fillDepartmentOfOccupationalHealth() {
	$('#street').val("Rua Carlos Botelho");
	$('#neighborhood').val("Centro");
	$('#number').val("593");
	$('#receptorName').val("");
}
function fillPreventiveMedicine () {
	$('#street').val("Rua Rui Barbosa");
	$('#neighborhood').val("Centro");
	$('#number').val("109");
	$('#receptorName').val("");
}
function fillSpecialtyCenter () {
	$('#street').val("Rua Leôncio D’Ávilla Riberiro");
	$('#neighborhood').val("Vila Brasil");
	$('#number').val("11");
	$('#receptorName').val("");
}
function clear () {
	$('#street').val("");
	$('#neighborhood').val("");
	$('#number').val("");
	$('#receptorName').val("");
}

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
                    totalPrice =  ($('#totalPrice').val() != "")? $('#totalPrice').val() : "0,00";
                }else if (input == true &&  parcels > 0 && intervalDay > 0){
                    $('#dateInput').attr("readonly",false);
                    $('#dateFirstInstallment').attr("readonly",false);
                    $('#inputPrice').attr("readonly",false);
                    $('#dateLastInstallment').attr("readonly",true);

                    $('#dateFirstInstallment').attr('onblur', 'calculateValueParcelWithInput('+parcels+','+intervalDay+')');
                    totalPrice =  ($('#totalPrice').val() != "")? $('#totalPrice').val() : "0,00";
                }else if (input == false &&  parcels > 0 && intervalDay > 0){
                    $('#dateInput').attr("readonly",true);
                    $('#dateFirstInstallment').attr("readonly",false);
                    $('#inputPrice').attr("readonly",true);
                    $('#dateLastInstallment').attr("readonly",true);

                     $('#dateFirstInstallment').attr('onblur', 'calculateSecondFormPayment('+parcels+','+intervalDay+')');
                     totalPrice =  ($('#totalPrice').val() != "")? $('#totalPrice').val() : "0,00";
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
        while (totalPrice.indexOf(',') != -1)
                totalPrice = totalPrice.replace(',', '.');        
        percentage = $('#discountPercentage').val();
        totalFinalPrice =  parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100);
        $('#totalFinalPrice').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
    }else{
        $('#totalFinalPrice').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    }
};

/** Calculo de valor final com parcela e sem entrada  **/
function calculateSecondFormPayment(parcel,intervalDay){
    dateFirstInstallment = $('#dateFirstInstallment').val();
    while (totalPrice.indexOf(',') != -1)
        totalPrice = totalPrice.replace(',', '.');  
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

/** Calculo de valor final com parcela e  entrada  **/
function calculateValueParcelWithInput(parcel,intervalDay){
    inputValue = $('#inputPrice').val();
    dateFirstInstallment = $('#dateFirstInstallment').val();
    
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null && inputValue != ''){
        while (totalPrice.indexOf(',') != -1)
            totalPrice = totalPrice.replace(',', '.');  
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

function fillTable(){
 var oTable =  $('#orderRequestProductTable').dataTable({
    "oLanguage": {
      "sUrl": getContextPathDataTable()
    },
    "bAutoWidth":true,
    "bLengthChange": false,
    "bPaginate": true,
    "bFilter": true,
    "bSort": false,
    "bInfo": true,
    "processing": true,
    "bJQueryUI": false,
    "sPaginationType": "full_numbers",
    "iDisplayLength":  10,
    "aLengthMenu": [[5, 10, 100], [5,10, 100]],
  });
};