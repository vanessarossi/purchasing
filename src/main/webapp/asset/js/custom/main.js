var getContextPath = function() {
    var pathArray = window.location.pathname.split("/");
    if (pathArray[1] === "purchasing") {
        return "/purchasing/";
    } else {
        return "/";
    }
};


var getContextPathDataTable = function() {
    return  getContextPath()+"asset/js/util/DTi18n_ptbr.json";
}

$('.navbar').find('li').on('click', function() {
    $('.navbar').find('li').removeClass('active');
    $(this).addClass('active');
});