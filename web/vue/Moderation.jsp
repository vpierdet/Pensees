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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"> </script>
    <script src="../JS/moder.js" type="text/javascript"></script>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/ModoPanel.css" />
    <title>ModeratorPanel</title>
</head>

<img class="bandeau" src="../pictures/header_menu.svg"/>

<ul id="menu_user">
    <li><a href="/MessageServlet">Fil Message</a></li>
    <%int ut =(Integer)session.getAttribute("userType"); %>
    <%if (ut == 1) out.println("<li><a href=\"/mas\">Messages me concernant</a></li>");%>
    <%if (ut == 2) out.println("<li  id=\"activ_window\"><a href=\"/vue/moderation.jsp\">Modération</a></li>");%>
    <li><a href="/vue/publier.jsp" >Publier</a></li>
    <li><a href="#">Votre Avis</a></li>
    <li><a href="/vue/signal.jsp">Signalement</a></li>
    <li><a href="/deco">Déconnexion</a></li>
</ul>

<body>

<div class="contour_bleu">
    <h3>    Page de modération  </h3>

    <div class ="contour_jaune">

        <h4>Bannir un utilisateur</h4>
        <form action="/Modo" method="POST">

            <input type="hidden" name="formName" value = "ban" id="formName1"/>
            <h5>    Bannir un utilisateur :       <input type="text" name="usernameBan" id="usernameBan"/>  <p id="error1" style="display: none; color: #d4190e;"></p>
                <p id="succes1" style="display: none; color: #000000;"></p></h5>
            <input class="boutonValider" type="submit" value = "Confirmer" id="subban"/><br>

            </br></br>

        </form>

        <h4>Lier un nouvel administrateur à une catégorie</h4>
        <form action="/Modo" method="POST">

            <input type="hidden" name="formName" value = "catego" id="formName2"/>
            <h5>    Administrateur :       <input type="text" name="adminName" id="adminName"/></h5>
            <h5> Categorie :  <select name="category" id="catego">
                <option value="Planning">Planning</option>
                <option value="Comportement et Incivilite">Comportement et Incivilite</option>
                <option value="Problemes Materiel">Problemes Materiel</option>
                <option value="Organisation">Organisation</option>
                <option value="Enseignement">Enseignement</option>
            </select> <p id="error2" style="display: none; color: #d4190e;"></p>
                <p id="succes2" style="display: none; color: rgba(0,0,0,0.95);"></p></h5>                <input class="boutonValider" type="submit" value = "Confirmer" id="sublier"/><br>

            </br></br>
        </form>
    </div>
</div>
<script src="../JS/moder.js" type="text/javascript"></script>
</body>



</html>
