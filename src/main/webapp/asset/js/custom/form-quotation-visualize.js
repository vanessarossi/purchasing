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
                    
                    $('#priceBudget').modal('show'); 
                }
                if(result != false && type === 'Service'){
                   
                   $('#priceBudget').modal('show');
                }
            },
            error: function () {
                alert("Ocorreu um erro no processamento dos dados.");
            }
        });
    }
};