$(document).ready(function() {
   actionTypePerson();

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

$('#cpf').blur(function(){
  var cpf = $('#cpf').val();
  if ((cpf != null) || (cpf!= '')) { 
     $.ajax({
          url: getContextPath() + 'fornecedor/pesquisar/cpf/json',
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
            url: getContextPath() + 'fornecedor/pesquisar/cnpj/json',
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