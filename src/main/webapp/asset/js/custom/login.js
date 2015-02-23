$(document).ready(function() {
  $('.forgot-password-form').hide();
  $('.forgot-password-done').hide();
});

function submitLogin() {
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	if ((username.trim() === '' || password.trim() === '') || (username.trim() === null || password.trim() === null)) {
		$('.messageNullLogin').text('Não pode conter informações em branco');
	}else{
		document.getElementById('login').submit();
	}
}

function submitNewPassword () {
	var email = document.getElementById('email').value;
	var form = $('#newPassword');
	if (email.trim() === '' || email.trim() === null) {
		$('.messageNullNewPassword').text('Não pode conter informações em branco');
	}else{
		$.ajax({
		    type: "POST",
		    url: form.attr('action'),
		    data: form.serialize(),
		    dataType: "json",
		    beforeSend: function(){

		    },
		    success: function (result) {
		      	if(result['boolean'] === true){
		      		$('.forgot-password-form').hide();
  					$('.forgot-password-done').show();
		      	}
		    },
		    error: function (result) {
		      alert('Ocorreu um erro ao tentar realizar esta ação');
		    }
	    });

	}
}

function returnLogin() {
  $('.forgot-password-link').show();
  $('.login-form').show();
  $('.forgot-password-done').hide();
  $('.forgot-password-form').hide();
}

$('#password').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? submitLogin() : true;
 });

$('#email').keypress(function (e) {
        var code = null;
        code = (e.keyCode ? e.keyCode : e.which);
        return (code == 13) ? submitNewPassword() : true;
 });

$('.forgot-password-link').click(function(){
  $('.forgot-password-link').hide();
  $('.login-form').hide();
  $('.forgot-password-form').show();
});