$(document).ready(function() {
  $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
  });
});

$("#text").keydown(function (event) {
      var text = $('#text').val();
      $('#tableSearchProduct > tbody >tr').remove();
      if (text.length >= 4) {
          $.ajax({
              url: getContextPath() + 'produto/pesquisar/json',
              type: 'POST',
              dataType: 'json',
              data: {text: text},
			    beforeSend: function(){
			    },
			    success: function (result) {
			      for (var i = 0; i < result.length; i++) {
	                  	var code = result[i]["id"];
	                  	var description = result[i]["description"];
	                  	var model = ((result[i]["model"] == null) ? '' : result[i]["model"]);
	                  	var mark = ((result[i]["mark"] == null) ? '' : result[i]["mark"]);
	                  	var unit = result[i]["unit"]["description"];
	                  	var category = result[i]["category"]["description"];

	                  	var product = description.replace('"','')+" "+model.replace('"','')+" "+mark.replace('"','');

		                var row = '<tr onclick="chooseProduct('+code+','+"'"+product+"'"+','+"'"+unit+"'"+')">';
		                row += "<td>"+code+"</td>";
		                row += "<td>"+product+"</td>";
		                row += "<td>"+unit+"</td>";
		                row += "<td>"+category+"</td>";
		                row += "</tr>";

	                  	$('#tableSearchProduct > tbody').append(row);
              		}
			    },
			    error: function (result) {
			      alert('Ocorreu um erro ao tentar realizar esta ação');
			    }
      		})
      	}
  });

$('#searchProduct').on('hide.bs.modal', function (e) {
      $('#tableSearchProduct > tbody > tr').remove();
      $('#text').val('');
});