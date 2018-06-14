/**
 *
 */

/*$('#sub').click(function( event ) {
        event.preventDefault();

        var $loginInfo = {
        		login : $('#login').val() ? $('#login').val() : 'null',
        		password: $('#pass').val() ? $('#pass').val() : 'null'
        };

        $.post('login', $loginInfo, function (data, status){
        	alert(data);
        	if (data == 1 && status == 'success') {
        		alert('Login information correct !');
        	} else {
        		alert('Wrong login or password');
        	}
        });
});*/


/* $.get("essai.jsp", function(data) {
          $("#demo").text(data);
       });*/



$('.button_ok').on('click',function (e) {
    e.preventDefault();
    var form = $(this);
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
        $(this).attr("value", "a-1");
    }
    else {
        $(this).attr("value", "a+1");
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
    }
    else {
        $(this).attr("value", "d+1");
    }
});