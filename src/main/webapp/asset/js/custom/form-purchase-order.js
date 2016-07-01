$(document).ready(function(){
	 fillTable();
});

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
	        fillPreventiveMedicine();
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
	$('#receptorName').val("Alessandro - Olivia");
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
function clearInputs(){
    $('#dateInput').val("");
    $('#dateFirstInstallment').val("");
    $('#dateLastInstallment').val("");
    $('#inputPrice').val("");
    $('#sharePrice').val("");
    $('#expirationDate').val("");
    $('#freight').val("");
    $('#discountPercentage').val("");
}
$('#formPayment').change(function(){
    formPaymentId = $('#formPayment').val();
    if (formPaymentId != '') {
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
                clearInputs();

                if (input == false &&  parcels == 0 && (intervalDay == 0 || intervalDay > 0)){
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
                    $('#dateInput').attr("readonly",false);
                    $('#dateFirstInstallment').attr("readonly",false);
                    $('#inputPrice').attr("readonly",false);
                    $('#dateLastInstallment').attr("readonly",true);
                    $('#dateFirstInstallment').attr('onblur', 'calculateValueParcelWithInput('+parcels+','+intervalDay+')');
                    totalPrice =  ($('#totalPrice').val() != "")? $('#totalPrice').val() : "0,00";
                    /** removendo atributo do calculo **/
                    $('#freight').removeAttr("onblur");
                }else if (input == false &&  parcels > 0 && intervalDay > 0){
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
    };  
});
/** Calculo de valor final sem parcelas e entrada   **/
function calculateFirstFormPayment(intervalDay){
    percentage = $('#discountPercentage').val().replace(',', '.');
    freight = $('#freight').val().replace(',', '.');
    totalPrice = $('#totalPrice').val().replace(',', '.');
    if(totalPrice != 0 && percentage != null && freight != null){
        while (totalPrice.indexOf(',') != -1)
                totalPrice = totalPrice.replace(',', '.');
        totalFinalPrice =  (parseFloat(totalPrice) - ((parseFloat(totalPrice) * parseFloat(percentage))/100))+parseFloat(freight);
        $('#totalFinalPrice').val(parseFloat(totalFinalPrice).toFixed(2).replace(".", ","));
    }else{
        $('#totalFinalPrice').val(parseFloat(totalPrice).toFixed(2).replace(".", ","));
    }
};
/** Calculo de valor final com parcela e sem entrada  **/
function calculateSecondFormPayment(parcel,intervalDay){
    dateFirstInstallment = $('#dateFirstInstallment').val();
    freight = $('#freight').val().replace(',', '.');
    percentage = $('#discountPercentage').val().replace(',', '.');
    totalPrice = $('#totalPrice').val().replace(',', '.');
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null && freight != null){
        while (totalPrice.indexOf(',') != -1)
        totalPrice = totalPrice.replace(',', '.');  

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
/** Calculo de valor final com parcela e  entrada  **/
function calculateValueParcelWithInput(parcel,intervalDay){
    inputValue = $('#inputPrice').val();
    dateFirstInstallment = $('#dateFirstInstallment').val();
    percentage = $('#discountPercentage').val().replace(',', '.');
    freight = $('#freight').val().replace(',', '.');
    totalPrice = $('#totalPrice').val().replace(',', '.');
    if(totalPrice != 0 && percentage != null && dateFirstInstallment != null && inputValue != null && freight != null){
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
function unlockInputs(){
    $('#dateInput').attr("readonly",false);
    $('#dateFirstInstallment').attr("readonly",false);
    $('#dateLastInstallment').attr("readonly",false);
    $('#expirationDate').attr("readonly",false);
};