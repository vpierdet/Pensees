<%--
  Created by IntelliJ IDEA.
  User: valentin
  Date: 19/06/2018
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa" />
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/ModoPanel.css" />
    <title>ModeratorPanel</title>
</head>

<img class="bandeau" src="../pictures/header_menu.svg"/>

<body>

<div class="contour_bleu">
    <h3> Page de modération  </h3>

    <div class ="contour_jaune">

        <h4>Bannir un utilisateur</h4>
        <form action="/MailServlet" method="POST">

            <input type="hidden" name="formName" value = "ban"/>
            <h5>    Bannir un utilisateur :       <input type="text" name="usernameBan"/></h5>
            <input class="boutonValider" type="submit" value = "Confirmer"/>
            </br></br>

        </form>

        <h4>Lier un nouvel administrateur à une catégorie</h4>
        <form action="/ModoServlet" method="POST">

            <input type="hidden" name="formName" value = "catego"/>
            <h5>    Administrateur :       <input type="text" name="adminName"/></h5>
            <h5> Categorie :  <select name="category">
                <option value="Planning">Planning</option>
                <option value="Comportement et Incivilite">Comportement et Incivilite</option>
                <option value="Problemes Materiel">Problemes Materiel</option>
                <option value="Organisation">Organisation</option>
                <option value="Enseignement">Enseignement</option>
            </select></h5>                <input class="boutonValider" type="submit" value = "Confirmer"/>
            </br></br>
        </form>
    </div>
    <br><br><br><br>
</div>

</body>



</html>
