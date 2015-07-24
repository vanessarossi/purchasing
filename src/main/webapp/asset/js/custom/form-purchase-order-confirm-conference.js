$(document).ready(function(){
});
function clearInputs(){
    $('#dateInput').val("");
    $('#dateFirstInstallment').val("");
    $('#dateLastInstallment').val("");
    $('#inputPrice').val("");
    $('#sharePrice').val("");
    $('#expirationDate').val("");
    $('#freight').val("");
    $('#discountPercentage').val("");
};
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