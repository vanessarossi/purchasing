function buildDelete(id, entityPath) {
    return '<a href="/' + entityPath + '/delete/" data-ref="' + id + '" class="dlink  alert "><i class="fa fa-trash"></i></a>'
}

function buildEdit(id, entityPath) {
    return '<a href="/' + entityPath + '/editar/' + id + '" class=" info "><i class="fa fa-edit"></i></a>'
}

function buildShow(id) {
    return ' <a href="#" class="show" data-ref="' + id + '"><i class="fa fa-eye"></i></a>'
}

function convertDate(date) {
    if (date == null) return "";
    var parsedJavaLocalDate = new Date(date);
    return moment(parsedJavaLocalDate).format('DD/MM/YYYY');
}

function convertTime(time) {
    return moment({hour: time[0], minute: time[1]}).format('HH:mm');
}

function getCareOrPrivate(care) {
    if (care.privateCare && care.specialPrice) {
        return "Particular com preço especial"
    } else if (care.privateCare) {
        return "Particular";
    } else {
        return care.healthCare.name;
    }
}
