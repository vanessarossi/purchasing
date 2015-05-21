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
    $('#place').val("Sede Administrativa");
	$('#street').val("Rua Francisquinho Dias");
	$('#neighborhood').val("Centro");
	$('#city').val("São José do Rio Pardo - SP");
	$('#zipCode').val("13720-000");
	$('#number').val("583");
	$('#receptorName').val("Eloisa - Everton");
}
function fillDiagnosticCenter () {
    $('#place').val("Centro de Diagnóstico");
	$('#street').val("Rua Silvia Jardim");
	$('#neighborhood').val("Centro");
	$('#city').val("São José do Rio Pardo - SP");
	$('#zipCode').val("13720-000");
	$('#number').val("260/268");
	$('#receptorName').val("Flavia - Fernando Prevital - Liliane");
}
function fillDrugstore () {
    $('#place').val("Farmácia");
	$('#street').val("Rua Campos Salles");
	$('#neighborhood').val("Centro");
	$('#city').val("São José do Rio Pardo - SP");
	$('#zipCode').val("13720-000");
	$('#number').val("491");
	$('#receptorName').val("Michel - Eliana");
}
function fillOptica () {
    $('#place').val("Optica");
	$('#street').val("Rua Campos Salles");
	$('#neighborhood').val("Centro");
	$('#city').val("São José do Rio Pardo - SP");
	$('#zipCode').val("13720-000");
	$('#number').val("491");
	$('#receptorName').val("Ana Claudia");
}
function fillReferenceCenter () {
    $('#place').val("Centro de Referencia");
	$('#street').val("Rua Coronel Alípio Dias");
	$('#neighborhood').val("Centro");
	$('#city').val("São José do Rio Pardo - SP");
	$('#zipCode').val("13720-000");
	$('#number').val("885");
	$('#receptorName').val("Alessandro - Natalie");
}
function fillHospital () {
    $('#place').val("Hospital");
	$('#street').val("Rua Alexandre Carlos de Melo");
	$('#neighborhood').val("Jardim Aeroporto");
	$('#city').val("São José do Rio Pardo - SP");
	$('#zipCode').val("13720-000");
	$('#number').val("118");
	$('#receptorName').val("Everton - Eloisa");
}
function fillDepartmentOfOccupationalHealth() {
    $('#place').val("Departamento de Saúde Ocupacional");
	$('#street').val("Rua Carlos Botelho");
	$('#city').val("São José do Rio Pardo - SP");
	$('#zipCode').val("13720-000");
	$('#neighborhood').val("Centro");
	$('#number').val("593");
	$('#receptorName').val("Maria Tinti - Bruno");
}
function fillPreventiveMedicine () {
    $('#place').val("Medicina Preventiva");
	$('#street').val("Rua Rui Barbosa");
	$('#neighborhood').val("Centro");
	$('#city').val("São José do Rio Pardo - SP");
	$('#zipCode').val("13720-000");
	$('#number').val("109");
	$('#receptorName').val("Sueli - Regiane");
}
function fillSpecialtyCenter () {
    $('#place').val("Centro de Especialidades");
	$('#street').val("Rua Leôncio D’Ávilla Riberiro");
	$('#neighborhood').val("Vila Brasil");
	$('#city').val("São José do Rio Pardo - SP");
	$('#zipCode').val("13720-000");
	$('#number').val("11");
	$('#receptorName').val("Fernanda - Regiane");
}
function clear () {
    $('#place').val("");
	$('#street').val("");
	$('#neighborhood').val("");
	$('#city').val("");
	$('#zipCode').val("");
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

function unlockInputs(){
    $('#dateInput').attr("readonly",false);
    $('#dateFirstInstallment').attr("readonly",false);
    $('#dateLastInstallment').attr("readonly",false);
    $('#expirationDate').attr("readonly",false);
}