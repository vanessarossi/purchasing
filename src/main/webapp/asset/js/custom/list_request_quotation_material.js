$(document).ready(function() {
  fillTable();
});


function fillTable() {
    var quotation = $('#number').val().trim();
    if (quotation != null && quotation != '') {
      $.ajax({
            url: getContextPath() + 'cotacao/listagem/total/material/'+quotation+'/json',
            type: 'GET',
            dataType: 'json',
            beforeSend: function(){
            },
            success: function (result) {
                $('#quotationMaterialTable > tbody >tr').remove();
                   if (result != false) {
	                    for (var i = 0; i < result.length; i++) {
	         				var productId = result[i]["product"]["id"];
	                        var description = result[i]["product"]["description"];
	                        var model = ((result[i]["product"]["model"] == null) ? '' : result[i]["product"]["model"]);
	                        var mark = ((result[i]["product"]["mark"] == null) ? '' : result[i]["product"]["mark"]);
	                        var unit = result[i]["product"]["unit"]["description"];
	                      	var quantity = result[i]["quantity"];
	                            
	                        var product = description+" "+model+" "+mark;

	                        var row = '<tr>';
	                            row += "<td>"+product+"</td>";
	                            row += "<td>"+quantity+"</td>";
	                            row += "<td>"+unit+"</td>";
	                            row += "<td>";
	                            row += '<a onclick="expandProducts('+productId+','+quotation+')"><span class="fa fa-expand btn btn-default btn-xs"></span></a>';
	                            row += "</td>";
	                            row += "<td>";
	                            row += '<a onclick="removedProducts('+productId+','+quotation+')"><span class="fa fa-trash-o btn btn-default btn-xs"></span></a>';
	                            row += "</td>";
	                            row += "</tr>";

	                            $('#quotationMaterialTable > tbody').append(row);
                        }
                	};
            },
            error: function (result) {
                alert('Ocorreu um erro ao tentar realizar esta ação');
            }
        });
    }
};


function expandProducts(product,quotation){
	alert("Expandir");
};

function removedProducts(product,quotation){
	alert("Remover");
};
