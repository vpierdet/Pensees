$('#subban').click(function(e) {
    e.preventDefault();
    $('#error1').hide();
    $('#succes1').hide();
    $('#error2').hide();
    $('#succes2').hide();
    var user = $('#usernameBan').val();
    $.ajax({
        url: '/Modo',
        type: 'POST',
        data: {
            'usernameBan': user,
            'formName' : 'bann',
        }
    })
        .done(function (data) {
            if (data == 0) {
                document.getElementById("succes1").innerText = "Succès";
                $('#succes1').show();
            }

            else {
                document.getElementById("error1").innerText = "Utilisateur inconnu.";
                $('#error1').show();
            }
        })
        .fail(function (data) {
            alert('Erreur réseau');
        });
});

$('#sublier').click(function(e) {
    e.preventDefault();
    $('#error1').hide();
    $('#succes1').hide();
    $('#error2').hide();
    $('#succes2').hide();
    var user = $('#adminName').val();
    var catego = $('#catego').val();
    $.ajax({
        url: '/Modo',
        type: 'POST',
        data: {
            'formName' : "catego",
            'adminName': user,
            'catego': catego,
        },
    })
        .done(function (data) {
            if (data == 0) {
                document.getElementById("succes2").innerText = "Succès";
                $('#succes2').show();
            }

            else {
                document.getElementById("error2").innerText = "Utilisateur inconnu";
                $('#error2').show();
            }
        })
        .fail(function (data) {
            alert('Erreur réseau');
        });
});