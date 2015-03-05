$(document).ready(function() {
    $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
    });
    fillTable();
});

function fillTable(){
    var oTable =  $('#userTable').dataTable({
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
        "sAjaxSource": getContextPath()+'usuario/paginar',
    });
};

function viewUser(user){
    $.ajax({
        type: "GET",
        url: getContextPath()+'usuario/pesquisar/'+user+'/json',
        dataType: "json",
        beforeSend: function(){
        },
        success: function (result) {
            clearModal();
            var name = result["name"];
            var username = result["username"];
            var email = result["email"];
            var active = result["active"] == true ? "Sim" : "Não";
            var role = result["role"]["description"];

            $('#name').text(name);
            $('#username').text(username);
            $('#email').text(email);
            $('#active').text(active);
            $('#role').text(role);

            for (var i = 0; i < result["costCenters"].length; i++) {
                var company = result["costCenters"][i]["description"];
                var costCenterDescription = result["costCenters"][i]["company"]["companyName"];
                var row = "<tr>";
                row +="<td>"+company+"</td>";
                row +="<td>"+costCenterDescription+"</td>";
                row+="</tr>";
                $('#costCenterUserTable').append(row);
            }
            $('#viewUser').modal('show');
        },
        error: function () {
            alert("Ocorreu um erro no processamento dos dados.");
        }
    })
};

function clearModal(){
    $('#name').text("");
    $('#username').text("");
    $('#email').text("");
    $('#active').text("");
    $('#role').text("");
    $('#costCenterUserTable > tbody >tr').remove();
}

function confirmDetele(id) {
    $('#code').val(id);
    $('#modalConfirm').modal('show');
};

$('#btn-confirm').click(function(){
    var id = $('#code').val();
        $.ajax({
            type: "GET",
            url: getContextPath()+'usuario/deletar/'+id,
            dataType: "json",
            beforeSend: function(){
            },
            success: function (result) {
              $('#modalConfirm').modal('hide');
              $('#modalSuccess').modal('show');
              setTimeout(function () {
                       $("#modalSuccess").modal('hide');
                        location.reload();
                   }, 1050)

            },
            error: function () {
              $('#modalConfirm').modal('hide');
              $('#modalError').modal('show');
              setTimeout(function () {
                    $("#modalError").modal('hide');
                     location.reload();
              }, 1050)
            }
        });
});

$('#btn-cancel').click(function(){
    $('#modalConfirm').modal('hide');
});