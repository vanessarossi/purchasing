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
	                            row += '<a onclick="removedService('+id+')"><span class="fa fa-trash-o btn btn-default btn-xs"></span></a>';
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

function removedService(id){
	alert("Remover");
};