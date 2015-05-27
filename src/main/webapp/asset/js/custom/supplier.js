$(document).ready(function() {
  $('input').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? false : true;
  });
  fillTable();
});

function fillTable(){
 var oTable =  $('#supplierTable').dataTable({
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
    "sAjaxSource": getContextPath()+'fornecedor/paginar',
  });
};

function viewSupplier(supplier) {
    $.ajax({
      type: "GET",
      url: getContextPath()+'fornecedor/pesquisar/'+supplier+'/json',
      dataType: "json",
      beforeSend: function(){
      },
      success: function (result) {
        clearModal();
        /** Address  **/
        var city = result["address"]["city"];
        var neighborhood = result["address"]["neighborhood"];
        var number = result["address"]["number"];
        var state = result["address"]["state"]["name"]+" - "+result["address"]["state"]["acronym"];
        var street = result["address"]["street"];
        var zipCode = result["address"]["zipCode"];

        /** Category  **/
        var category = result["category"]["description"];

        /** Ativo **/

        var active = result["active"] == true ? 'Sim' : 'Não' ;

        /** Contact  **/
        var cellphone = result["contact"]["cellphone"];
        var contactName = result["contact"]["contactName"];
        var email = result["contact"]["email"];
        var phone = result["contact"]["phone"];
        var secondaryCellPhone = result["contact"]["secondaryCellPhone"];
        var secondaryPhone = result["contact"]["secondaryPhone"];
        var website = result["contact"]["website"];

        /** Person **/
        var typePerson = result["person"]["typePerson"];
        var name = result["person"]["name"];

        if (typePerson == 'Pessoa Jurídica') {
          var companyName = result["person"]["companyName"];
          var cnpj = result["person"]["cnpj"];
          var stateInscription = result["person"]["stateInscription"];
          var municipalInscription = result["person"]["municipalInscription"];

          $('#companyName').text(companyName);
          $('#cnpj').text(cnpj);
          $('#stateInscription').text(stateInscription);
          $('#municipalInscription').text(municipalInscription);

          $('#divNaturalPerson').hide();
          $('#divJuristicPerson').show();
        }else{
          var cpf = result["person"]["cpf"];
          var emittingOrgan = result["person"]["emittingOrgan"];
          var rg = result["person"]["rg"];

          $('#cpf').text(cpf);
          $('#emittingOrgan').text(emittingOrgan);
          $('#rg').text(rg);

          $('#divJuristicPerson').hide();
          $('#divNaturalPerson').show();
        };

        /** final Person **/  

        /** add value um span  **/

        $('#active').text(active);

        $('#city').text(city);
        $('#neighborhood').text(neighborhood);
        $('#number').text(number);
        $('#state').text(state);
        $('#street').text(street);
        $('#zipCode').text(zipCode);
        $('#category').text(category);
        $('#cellphone').text(cellphone);
        $('#contactName').text(contactName);
        $('#email').text(email);
        $('#phone').text(phone);
        $('#secondaryPhone').text(secondaryPhone);
        $('#secondaryCellPhone').text(secondaryCellPhone);
        $('#website').text(website);
        $('#typePerson').text(typePerson);
        $('#name').text(name);

        $('#viewSupplier').modal('show');
      },
      error: function () {
          alert("Ocorreu um erro no processamento dos dados.");
      }
    })
};

function clearModal() {
    $('#active').text("");

    $('#companyName').text("");
    $('#cnpj').text("");
    $('#stateInscription').text("");
    $('#municipalInscription').text("");
    
    $('#cpf').text("");
    $('#emittingOrgan').text("");
    $('#rg').text("");

    $('#city').text("");
    $('#neighborhood').text("");
    $('#number').text("");
    $('#state').text("");
    $('#street').text("");
    $('#zipCode').text("");
    $('#category').text("");
    $('#cellphone').text("");
    $('#contactName').text("");
    $('#email').text("");
    $('#phone').text("");
    $('#secondaryPhone').text("");
    $('#secondaryCellPhone').text("");
    $('#website').text("");
    $('#typePerson').text("");
    $('#name').text("");
};

function confirmDetele(id) {
    $('#code').val(id);
    $('#modalConfirm').modal('show');
};

$('#btn-confirm').click(function(){
    var id = $('#code').val();
        $.ajax({
            type: "GET",
            url: getContextPath()+'fornecedor/deletar/'+id,
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
