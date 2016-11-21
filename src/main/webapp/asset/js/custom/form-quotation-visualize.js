$(document).ready(function () {
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });
});

function toggleChevron(e) {
    $(e.target)
        .prev('.panel-heading')
        .find("i.indicator")
        .toggleClass('fa-angle-double-down fa-angle-double-up');
}
$('#accordion').on('hidden.bs.collapse', toggleChevron);
$('#accordion').on('shown.bs.collapse', toggleChevron);

function viewDetailBudget(id,type){
    if (id != null && id != '') {
        $.ajax({
            url: getContextPath()+'cotacao/pesquisa/produto/orcamento/'+id+'/json',
            type: "GET",
            dataType: 'json',
            beforeSend: function(){
            },
            success: function (result) {
                if (result != false && type === 'Material') {
                    for (var i = 0; i < result.length; i++) {
						var description = result[i]["product"]["description"];
						var model = ((result[i]["product"]["model"] == null) ? '' : result[i]["product"]["model"]);
                        var mark = ((result[i]["product"]["mark"] == null) ? '' : result[i]["product"]["mark"]);
						
						var product = description +" "+ model +" "+ mark;
                        var quantity =   result[i]["quantity"];
                        var unit = result[i]["product"]["unit"]["description"];
                        var unityPrice = result[i]["unityPrice"];
                        var totalPrice = result[i]["totalPrice"];

                        var row = "<tr>";
                        row += "<td>"+product+"</td>";
                        row += "<td>"+quantity+"</td>";
                        row += "<td>"+unit+"</td>";
                        row += "<td>"+unityPrice.toFixed(4).replace(".", ",")+"</td>";
                        row += "<td>"+totalPrice.toFixed(2).replace(".", ",")+"</td>";
                        row += "</tr>";

                        $('#bugetQuotationMaterialTable > tbody').append(row);
					}
                    $('#priceBudget').modal('show'); 
                }
                if(result != false && type === 'Service'){
                   for (var i = 0; i < result.length; i++) {
                        var service = result[i]["service"]["description"];
                        var unityPrice = result[i]["unityPrice"];
                        var totalPrice = unityPrice;

                        var row = "<tr>";
                        row += "<td>"+service+"</td>";
                        row += "<td>"+unityPrice.toFixed(4).replace(".", ",")+"</td>";
                        row += "<td>"+totalPrice.toFixed(2).replace(".", ",")+"</td>";
                        row += "</tr>";

                        $('#bugetQuotationServiceTable > tbody').append(row);
                    }
                   $('#priceBudget').modal('show');
                }
            },
            error: function () {
                alert("Ocorreu um erro no processamento dos dados.");
            }
        });
    }
};

$('#priceBudget').on('hide.bs.modal', function (e) {
      $('#bugetQuotationMaterialTable > tbody > tr').remove();
      $('#bugetQuotationServiceTable > tbody > tr').remove();
});