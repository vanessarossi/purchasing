$(document).ready(function () {

    var mask9 = function (val) {
            return val.replace(/\D/g, '').length === 9 ? '00000-0000' : '0000-00009';
        },
        options = {
            onKeyPress: function (val, e, field, options) {
                field.mask(mask9.apply({}, arguments), options);
            }
        };


    //$(".rg").mask("00.000.000-0");
    $(".cpf").mask("000.000.000-00");
    $(".crefito").mask("00-00000 AA");
    $(".date").mask("00/00/0000", {placeholder: "__/__/____"});
    $(".time").mask("00:00");
    $(".zip").mask("00000-000");
    $(".phone").mask(mask9, options);
    $(".ddd").mask("00");
    $('.money').on('keyup', function (e) {

    })
});
