$(document).ready(function() {
   actionTypePerson();

  $("#zipCode").mask("99999-999");
  $("#phone").mask("(99) 9999-9999");
  $("#secondaryPhone").mask("(99) 9999-9999");
  $("#cellPhone").mask("(99) 99999-999?9");
  $("#secondaryCellPhone").mask("(99) 99999-999?9");
  $("#cnpj").mask("99.999.999/9999-99");
  $("#cpf").mask("999.999.999-99");
  $("#rg").mask("99.999.999-9");
  $('#cpfEquals').hide();
  $('#cnpjEquals').hide();
});

$('#typePerson').change(function(){
    actionTypePerson();
});

function actionTypePerson(){
  var typePerson = $('#typePerson').val();
    if (typePerson == 'JuristicPerson'){
      $('#divNaturalPerson').hide();
      $('#divJuristicPerson').show();
    }else{
      $('#divNaturalPerson').show();
      $('#divJuristicPerson').hide();
   }
};

function checkByCpf() {
};

function checkByCnpj() {
};

$('#cpf').blur(function(){
  var cpf = $('#cpf').val();
  if ((cpf != null) || (cpf!= '')) { 
     $.ajax({
          url: getContextPath() + 'fornecedor/pesquisar/cpf',
          type: 'POST',
          dataType: 'json',
          data: {cpf: cpf},
        beforeSend: function(){
        },
        success: function (result) {
          if (result != false) {
              $('#cpf').val('');
              $('#cpfEquals').show();
          }else{
              $('#cpfEquals').hide();
          }
        },
        error: function (result) {
          alert('Ocorreu um erro ao tentar realizar esta ação');
        }
    })   
  }
});


$('#cnpj').blur(function(){
  var cnpj = $('#cnpj').val();
  if ((cnpj != null) || (cnpj!= '')){
    $.ajax({
            url: getContextPath() + 'fornecedor/pesquisar/cnpj',
            type: 'POST',
            dataType: 'json',
            data: {cnpj: cnpj},
          beforeSend: function(){
          },
          success: function (result) {
            if (result != false) {
              $('#cnpj').val('');
              $('#cnpjEquals').show();
            }else{
              $('#cnpjEquals').hide();
            }
          },
          error: function (result) {
            alert('Ocorreu um erro ao tentar realizar esta ação');
          }
      })    
  }
});