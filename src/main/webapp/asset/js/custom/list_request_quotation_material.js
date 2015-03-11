$(document).ready(function() {
  $('#quotationMaterialDetailTable').hide();
  fillTable();
});

var productId=0;
var quotationId=$('#number').val().trim();

function fillTable() {
    if (quotationId != null && quotationId != '') {
      $.ajax({
            url: getContextPath() + 'cotacao/listagem/total/material/'+quotationId+'/json',
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
	                            row += '<a onclick="expandProducts('+productId+','+quotationId+')"><span class="fa fa-expand btn btn-default btn-xs"></span></a>';
	                            row += "</td>";
	                            row += "<td>";
	                            row += '<a onclick="confirmDetele('+productId+','+quotationId+')"><span class="fa fa-trash-o btn btn-default btn-xs"></span></a>';
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
	   productId = product;
     quotationId = quotation; 

     $('#quotationMaterialTable').hide();
     $('#quotationMaterialDetailTable').show();

      if (productId != 0 && quotationId != 0) {
        $.ajax({
              url: getContextPath() + 'cotacao/listagem/detalhada/material/'+quotationId+'/'+productId+'/json',
              type: 'GET',
              dataType: 'json',
              beforeSend: function(){
              },
              success: function (result) {
                  $('#quotationMaterialDetailTable > tbody >tr').remove();
                     if (result != false) {
                        for (var i = 0; i < result.length; i++) {
                             
                            var codeQuotationRequest = result[i]["id"];

                            var solicitation = result[i]["solicitationRequest"]["solicitation"]["id"];

                            var description = result[i]["solicitationRequest"]["product"]["description"];
                            var model = ((result[i]["solicitationRequest"]["product"]["model"] == null) ? '' : result[i]["solicitationRequest"]["product"]["model"]);
                            var mark = ((result[i]["solicitationRequest"]["product"]["mark"] == null) ? '' : result[i]["solicitationRequest"]["product"]["mark"]);
                            var unit = result[i]["solicitationRequest"]["product"]["unit"]["description"];
                            var quantity = result[i]["solicitationRequest"]["quantity"];
                                
                            var product = description+" "+model+" "+mark;

                            var row = '<tr>';
                                row += "<td>"+solicitation+"</td>";
                                row += "<td>"+product+"</td>";
                                row += "<td>"+quantity+"</td>";
                                row += "<td>"+unit+"</td>";
                                row += "<td>";
                                row += '<a onclick="removeQuotationRequestUnit('+codeQuotationRequest+')"><span class="fa fa-trash-o btn btn-default btn-xs"></span></a>';
                                row += "</td>";
                                row += "</tr>";

                                $('#quotationMaterialDetailTable > tbody').append(row); 
                          }
                    };
              },
              error: function (result) {
                  alert('Ocorreu um erro ao tentar realizar esta ação');
              }
          });
        }
};

function confirmDetele(product,quotation) {
    productId = product;
    quotationId = quotation; 
    $('#modalConfirm').modal('show');
};

function removeQuotationRequestUnit(codeQuotationRequest){
    if (codeQuotationRequest != 0) {
        $.ajax({
            type: "GET",                
            url: getContextPath()+'cotacao/deletar/pedido/'+codeQuotationRequest+'/json',
            dataType: "json",
            beforeSend: function(){
            },
            success: function (result) {
              $('#modalSuccess').modal('show');
              setTimeout(function () {
                       $("#modalSuccess").modal('hide');
                        location.href=getContextPath()+'cotacao/pesquisar/'+quotationId; 
                   }, 1050)

            },
            error: function () {
              $('#modalError').modal('show');
              setTimeout(function () {
                    $("#modalError").modal('hide');
                     location.href=getContextPath()+'cotacao/pesquisar/'+quotationId; 
              }, 1050)
            }
        });
    };
};

$('#btn-confirm').click(function(){
	if (productId != 0 && quotationId != 0) {
		$.ajax({
            type: "GET",				
            url: getContextPath()+'cotacao/deletar/pedido/material/total/'+quotationId+'/'+productId+'/json',
            dataType: "json",
            beforeSend: function(){
            },
            success: function (result) {
              $('#modalConfirm').modal('hide');
              $('#modalSuccess').modal('show');
              setTimeout(function () {
                       $("#modalSuccess").modal('hide');
                        location.href=getContextPath()+'cotacao/pesquisar/'+quotationId; 
                   }, 1050)

            },
            error: function () {
              $('#modalConfirm').modal('hide');
              $('#modalError').modal('show');
              setTimeout(function () {
                    $("#modalError").modal('hide');
                     location.href=getContextPath()+'cotacao/pesquisar/'+quotationId; 
              }, 1050)
            }
        });
	};
});

$('#btn-cancel').click(function(){
    $('#modalConfirm').modal('hide');
});

