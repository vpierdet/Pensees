<%--
  Created by IntelliJ IDEA.
  User: valentin
  Date: 19/06/2018
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"> </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa" />
    <link rel="stylesheet" href="../css/Log.css" />
    <meta charset="UTF-8">
    <title>Connexion</title>
</head>
<body>
<img class="bandeau" src="../pictures/header.svg"/>
<div class="contour">
    <h1>Connexion</h1>
    <form action="/MessageServlet" method="POST" id="cof">
        Identifiant: <input id="id" type="text" name="username" required/><br><br>
        Mot de passe: <input id="ps" type="password" name="password" required/><br>
        <p id="error" style="display: none; color: #d4190e;"></p>
        <h4><input id="sub" class="boutonValider" type="submit" /></h4>
    </form></div>


<img class="logo" src="../pictures/logo_nom.svg"/>
</body>
<script>
    $('#sub').click(function(e) {
        e.preventDefault();
        var user = $('#id').val();
        var mdp = mdp = $('#ps').val();
        $.ajax({
            url: '/checklog',
            type: 'POST',
            data: {
                'username': user,
                'password': mdp,
            },
        })
            .done(function (data) {
                if (data == 0) {
                    $('#cof').submit();
                    return true;
                }

                else if (data == 2){
                    document.getElementById("error").innerText = "Le nom d'utilisateur n'existe pas.";
                    $('#error').show();
                    return false;
                }
                else {
                    document.getElementById("error").innerText = "Mauvais mot de passe";
                    $('#error').show();
                    return false;
                }
                //	$("h1").text('Le résultat est :' + data);
            })
            .fail(function (data) {
                alert('ça marche pas');
            });
    });
</script>

</html>
