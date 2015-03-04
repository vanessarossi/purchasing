$(document).ready(function() {
  $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
  });
});


$('#company').change(function (){
	$('#costCenter option').remove();
	var company = $('#company').val();
	$.ajax({
    	type: "GET",
    	url: getContextPath()+'centroCusto/pesquisar/empresa/'+company+'/json',
    	dataType: "json",
    	beforeSend: function(){
    	},
    	success: function (result) {
        	if (result != null) {
	       		for (var i = 0; i < result.length; i++) {
	       			var description = result[i]["description"];
    				var value = result[i]["id"];
	   				option = "<option value="+value+">"+description+"</option>";
	   				$('#costCenter').append(option);
	  			};
			};
    	},
    	error: function () {
      		alert("Ocorreu um erro no processamento dos dados.");
    	}
  	});
});

function addCostCenter() {
	var company = $('#company option:selected').text();
	var costCenterDescription = $('#costCenter option:selected').text();
	var costCenterId = $('#costCenter').val();
	var counter = $('#counter').val();

	if(company == 'Selecione' || costCenterDescription == 'Selecione' || company =='' || costCenterDescription==''){
		alert('Você deve selecionar uma empresa e um centro de custo.');
	}else{
		var row = "<tr id=costCenterUser"+counter+">";
		row +="<input type='hidden' name='user.costCenters["+counter+"].id' value="+costCenterId+">";
		row +="<td>"+company+"</td>";
		row +="<td>"+costCenterDescription+"</td>";
		row +="<td>";
		row +='<a class="btn btn-default btn-xs" onclick="deleteCostCenter('+counter+')" ><span class="fa fa-trash-o"></span></a>';
		row +="</td>";
		row+="</tr>";
		$('#userCostCenterTable').append(row);
		$('#counter').val(parseInt(counter) + 1);
	}
};

function deleteCostCenter(index){
	$('#costCenterUser'+index).remove();
};
