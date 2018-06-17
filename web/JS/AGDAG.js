/**
 *
 */


$('.button_ok').on('click',function (e) {
    e.preventDefault();
    var action= $(this).val();
    var idmes = $(this).attr("name");
    $.ajax({
        url: '/ems',
        type: 'POST',
        data: {
            'idMessage': idmes,
            'action': action,
        },
    })
        .done(function (data) {
            //changerlenom
        })
        .fail(function (data) {
            alert('Echec');
        });
    if(action == "a+1"){
        $(this).text( PAccord($(this).text()));
        $(this).attr("value", "a-1");
        $(this).attr("id","bos");
    }
    else {
        $(this).text( MAccord($(this).text()));
        $(this).attr("value", "a+1");
        $(this).attr("id","bons");
    }
});

$('.button_pasok').on('click',function (e) {
    e.preventDefault();
    var action= $(this).val();
    var idmes = $(this).attr("name");
    $.ajax({
        url: '/ems',
        type: 'POST',
        data: {
            'idMessage': idmes,
            'action': action,
        },
    })
        .done(function (data) {


        })
        .fail(function (data) {
            alert('Echec');
        });
    //changer texte et changer valeur
    if(action == "d+1"){
        $(this).attr("value", "d-1");
        $(this).attr("id","bpos");
        $(this).text(PDesaccord($(this).text()));
    }
    else {
        $(this).attr("value", "d+1");
        $(this).attr("id","bpons");
        $(this).text(MDesaccord($(this).text()));
    }
});

function PAccord(str){
    var sf = "D'accord (";
    var num = str.substring(str.lastIndexOf("(")+1, str.lastIndexOf(")"));
    var newNum = eval(num) + 1;
    sf = sf + newNum.toString() + ")";
    return sf;
}

function MAccord(str){
    var sf = "D'accord (";
    var num = str.substring(str.lastIndexOf("(")+1, str.lastIndexOf(")"));
    var newNum = eval(num) - 1;
    sf = sf + newNum.toString() + ")";
    return sf;
}

function MDesaccord(str){
    var sf = "Pas d'accord (";
    var num = str.substring(str.lastIndexOf("(")+1,  str.lastIndexOf(")"));
    var newNum = eval(num) - 1;
    sf = sf + newNum.toString() + ")";
    return sf;
}

function PDesaccord(str){
    var sf = "Pas d'accord (";
    var num = str.substring(str.lastIndexOf("(")+1,  str.lastIndexOf(")"));
    var newNum = eval(num) + 1;
    sf = sf + newNum.toString() + ")";
    return sf;
}
