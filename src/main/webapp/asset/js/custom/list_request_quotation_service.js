$(document).ready(function() {
  fillTable();
});

function fillTable() {
    var quotation = $('#number').val().trim();
    if (quotation != null && quotation != '') {
      $.ajax({

            url: getContextPath() + 'cotacao/listagem/total/servico/'+quotation+'/json',
            type: 'GET',
            dataType: 'json',
            beforeSend: function(){
            },
            success: function (result) {
                $('#quotationServiceTable > tbody >tr').remove();
                   if (result != false) {
	                    for (var i = 0; i < result.length; i++) {
	         				
	                    	var id = result[i]["id"]; 
	                    	var solicitation = result[i]["solicitation"]["id"];
	                    	var typeService = result[i]["typeService"]["description"];
	                    	var costCenter = result[i]["costCenter"]["description"];
	                    	var description = result[i]["description"];


	                        var row = '<tr>';
	                            row += "<td>"+solicitation+"</td>";
	                            row += "<td>"+typeService+"</td>";
	                            row += "<td>"+costCenter+"</td>";
	                            row += "<td>"+description+"</td>";
	                            row += "<td>";
	                            row += '<a onclick="confirmDetele('+id+')"><span class="fa fa-trash-o btn btn-default btn-xs"></span></a>';
	                            row += "</td>";
	                            row += "</tr>";

	                            $('#quotationServiceTable > tbody').append(row);
                        }
                	};
            },
            error: function (result) {
                alert('Ocorreu um erro ao tentar realizar esta ação');
            }
        });
    }
};


var codeQuotationRequest=0;


function confirmDetele(id){
	codeQuotationRequest = id;
    $('#modalConfirm').modal('show');
};

$('#btn-confirm').click(function(){
    if (codeQuotationRequest != 0) {
        $.ajax({
            type: "GET",                
            url: getContextPath()+'cotacao/deletar/pedido/'+codeQuotationRequest+'/json',
            dataType: "json",
            beforeSend: function(){
            },
            success: function (result) {
              $('#modalConfirm').modal('hide');
              $('#modalSuccess').modal('show');
              setTimeout(function () {
                       $("#modalSuccess").modal('hide');
                       location.href=getContextPath()+'cotacao/pesquisar/'+codeQuotationRequest; 
                   }, 1050)

            },
            error: function () {
              $('#modalConfirm').modal('hide');
              $('#modalError').modal('show');
              setTimeout(function () {
                    $("#modalError").modal('hide');
                     location.href=getContextPath()+'cotacao/pesquisar/'+codeQuotationRequest; 
              }, 1050)
            }
        });
    };
});

$('#btn-cancel').click(function(){
    $('#modalConfirm').modal('hide');
});