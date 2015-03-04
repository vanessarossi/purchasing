$(document).ready(function() {
});


$("#barCode").focus(function(){
    $(this).keypress(function(e){
        var interacao = $(this).val();
        if(e.keyCode == 13){
          return false;
        }
    });
});