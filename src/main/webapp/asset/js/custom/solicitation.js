$(document).ready(function() {
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });

   oTable =  $('#solicitationTable').dataTable({
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
        "bProcessing": false,
        "bServerSide": true,
        "sAjaxSource": getContextPath()+'solicitacao/paginar',
    });
});

$('#status').change(function () {
  status = $('#status').val();
   if(status != ''){
     filtrar();
    }else{
		if(oTable != null)oTable.fnDestroy();
		$('#solicitationTable').dataTable({
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
		    "bProcessing": false,
		    "bServerSide": true,
		    "sAjaxSource": getContextPath()+'solicitacao/paginar',
		});
	}
});

function filtrar () {
      if(oTable != null)oTable.fnDestroy();
     status = $('#status').val();
	 
	$('#solicitationTable').dataTable({
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
	    "bProcessing": false,
	    "bServerSide": true,
	    "sAjaxSource": getContextPath()+'solicitacao/paginar/filtro/'+status,
	});
}

function reproveSolicitation(solicitation) {
    $('#solicitationId').val(solicitation);
    $('#reproveSolicitation').modal('show');
};

function send() {
    var justification =  $('#justification').val();
    if (justification != null && justification != '') {
        $( "#formReproveSolicitation" ).submit();
    }
};

function viewMore(id) {
	   $.ajax({
        url: getContextPath()+'solicitacao/visualizacao/rapida/'+id+'/json',
        type: "GET",
        dataType: 'json',
        beforeSend: function(){
        },
        success: function (result) {
        	var code  = result["id"];
        	var costCenter = result['costCenter']['description'];
        	var user = result['user']['name'];
        	var type = result['type'];

        	$('#solicitationCode').val(code);
        	$('#solicitationCostCenter').val(costCenter);
        	$('#solicitationUser').val(user);

        	if(type === 'Service'){
        		$('#productType').hide();
        		$('#productTable').hide();
        		$('#serviceTable').show();
        		$('#serviceType').show();

        		var type = result['solicitationRequests'][0]['service']['typeService']['description'];
				var serviceDescription = result['solicitationRequests'][0]['service']['description'];

        		 var row = '<tr>';
		                row += "<td>"+type+"</td>";
		                row += "<td>"+serviceDescription+"</td>";
		               	row += "</tr>";

	             $('#serviceTable > tbody').append(row);

        	}
			if(type === 'Material'){
        		$('#productType').show();
        		$('#productTable').show();
        		$('#serviceTable').hide();
        		$('#serviceType').hide();

        		for (var i = 0; i < result['solicitationRequests'].length; i++) {
	                  	var code = result['solicitationRequests'][i]['product']['id'];
	                  	var description = result['solicitationRequests'][i]['product']['description'];
	                  	var model = ((result['solicitationRequests'][i]['product']['model'] == null) ? '' : result['solicitationRequests'][i]['product']['model']);
	                  	var mark = ((result['solicitationRequests'][i]['product']['mark'] == null) ? '' : result['solicitationRequests'][i]['product']['mark']);
	                  	
	                  	var quantity = result['solicitationRequests'][i]['quantity'];

		                var row = '<tr>';
		                row += "<td>"+code+"</td>";
		                row += "<td>"+description+"</td>";
		                row += "<td>"+model+"</td>";
		                row += "<td>"+mark+"</td>";
		                row += "<td>"+quantity+"</td>";
		                row += "</tr>";

	                  	$('#productTable > tbody').append(row);
              		}
   

        	}
			if(type === 'MaterialService'){
				$('#productType').show();
				$('#productTable').show();
				$('#serviceTable').show();
				$('#serviceType').show();

				for (var i = 0; i < result['solicitationRequests'].length; i++) {

					if(result['solicitationRequests'][i]['service'] != null ) {
						var type = result['solicitationRequests'][i]['service']['typeService']['description'];
						var serviceDescription = result['solicitationRequests'][i]['service']['description'];

						var row = '<tr>';
						row += "<td>" + type + "</td>";
						row += "<td>" + serviceDescription + "</td>";
						row += "</tr>";

						$('#serviceTable > tbody').append(row);
					}
					if(result['solicitationRequests'][i]['product'] != null ){
						var code = result['solicitationRequests'][i]['product']['id'];
						var description = result['solicitationRequests'][i]['product']['description'];
						var model = ((result['solicitationRequests'][i]['product']['model'] == null) ? '' : result['solicitationRequests'][i]['product']['model']);
						var mark = ((result['solicitationRequests'][i]['product']['mark'] == null) ? '' : result['solicitationRequests'][i]['product']['mark']);

						var quantity = result['solicitationRequests'][i]['quantity'];

						var row = '<tr>';
						row += "<td>"+code+"</td>";
						row += "<td>"+description+"</td>";
						row += "<td>"+model+"</td>";
						row += "<td>"+mark+"</td>";
						row += "<td>"+quantity+"</td>";
						row += "</tr>";

						$('#productTable > tbody').append(row);
					}
				}
			}
            $('#viewQuick').modal('show');
        },
        error: function () {
            alert("Ocorreu um erro no processamento dos dados.");
        }
    });

};

$('#viewQuick').on('hide.bs.modal', function (e) {
      $('#productTable > tbody > tr').remove();
      $('#serviceTable > tbody > tr').remove();
});
