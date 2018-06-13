<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page import="mvc.model.message" %>
    <%@ page import="java.util.ArrayList" %>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="file_message.css" />
    <title>File d'actualité</title>
</head>
<body>
<img class="bandeau" src="pictures/header_menu.svg"/>


<ul id="menu_user">
    <li id ="activ_window"><a href="/MessageServlet">Fil Message</a></li>
    <li ><a href="/page_message.jsp">Publier</a></li>
    <li><a href="#">Votre Avis</a></li>
    <li><a href="/signal.jsp">Signalement</a></li>
    <li><a href="/deco">Déconnexion</a></li>
</ul>
<br>

<div class="contour_jaune">
    <div class="contour_bleu">
        <form action="/" method="POST">
            Trier par catégories :
            <select size="1" name="username">
                <option>Enseignement
                <option>Organisation
                <option>Aménagement et Matériel
            </select>
        </form>
    </div>

    <div class="cadre_message">
        <div class="type_enseignement">
            Test Option
        </div>
        <div class="type_organisation">
        </div>

        <div class="type_materiel">
        </div>

        <form action="/" method="post">
            <button id="button_ok" type="submit" name="ok">D'accord</button>
            <button id="button_pasok" type="submit" name="pasok">Pas d'accord</button>
        </form> </div>

</body>

<img class="logo" src="pictures/logo_sans_nom.svg"/>
</html>