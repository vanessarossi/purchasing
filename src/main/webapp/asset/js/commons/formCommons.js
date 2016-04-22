$(document).ready(function () {

    var mask9 = function (val) {
            return val.replace(/\D/g, '').length === 9 ? '(00) 00000-0000' : '(00) 0000-00009';
        },
        options = {
            onKeyPress: function (val, e, field, options) {
                field.mask(mask9.apply({}, arguments), options);
            }
        };


    $(".rg").mask("00.000.000-0");
    $(".cpf").mask("000.000.000-00");
    $(".date").mask("00/00/0000", {placeholder: "__/__/____"});
    $(".competence").mask("00/0000", {placeholder: "__/____"});
    $('.money').mask("#.##0,00", {reverse: true});
    $(".zip").mask("00000-000");
    $(".phone").mask(mask9, options);
    $(".cnpj").mask("99.999.999/9999-99");
});




