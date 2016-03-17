$(document).ready(function() {

});

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

