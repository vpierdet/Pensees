<%--
  Created by IntelliJ IDEA.
  User: valentin
  Date: 19/06/2018
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/publier.css" />
    <title>Publier</title>
</head>
<img class="bandeau" src="../pictures/header_menu.svg"/>


<ul id="menu_user">
    <li><a href="/MessageServlet">Fil Message</a></li>
    <li id="activ_window"><a href="#">Publier</a></li>
    <li><a href="#">Votre Avis</a></li>
    <li><a href="/vue/signal.jsp">Signalement</a></li>
    <li><a href="/deco">Déconnexion</a></li>
</ul>


<body>
<br>
<div class="contour_bleu">
    <form action="/post" id="idee" method="POST" onsubmit="checkEmpty()">
        <div class="contour_jaune">
            <h1 id="etiquette">Publier un message</h1>
            <input type="hidden" name="post" value="message">
            <textarea id="msg" name="user_message">Ecrivez votre message ici...</textarea>
            <br><br>

        </div>
        <br>
        <div id="partiebleue">
            Entrez une catégorie* :
            <select size="1" name="category" class="form-control" onchange="change(this)">
                <option value = "Planning">Planning
                <option value = "Comportement et Incivilite">Comportement et Inicivilité
                <option value = "Problemes Materiel">Problèmes Matériel
                <option value = "Organisation">Organisation
                <option value = "Enseignement">Enseignement
            </select>
            <br><br>
            <div hidden id="type_enseignement">
                Entrez le cours* :
                <select size="1" name="category2" class="form-control">
                    <option value = "Genie Logiciel">Genie Logiciel
                    <option value = "TechnologiesWeb">Technologies Web
                    <option value = "Cybersecurite">Cybersecurite
                    <option value = "Algorithmique Programmation">Algorithmique et Programmation
                    <option value = "Enseignement Culturel">Enseignement Culturel
                    <option value = "Base de Donnees">Base de Donnees
                </select>
            </div>
            <br>
            <input type="checkbox" name="anonymat">
            Cochez la case si vous voulez poster le message en format anonyme.
            <br><br>
            <i>* Champs obligatoires</i>
        </div>
        <input class="boutonPublier" type="submit" value="Publier"/>
        <br>
    </form>
</div>



</body>

<img class="logo" src="../pictures/logo_sans_nom.svg"/>
</html>


<script>
    function change(obj) {
        var selectBox = obj;
        var selected = selectBox.options[selectBox.selectedIndex].value;
        var textarea = document.getElementById("type_enseignement");

        if(selected === 'Enseignement'){
            textarea.style.display = "block";
        }
        else{
            textarea.style.display = "none";
        }
    }
</script>
