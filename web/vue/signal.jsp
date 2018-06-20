<%--
  Created by IntelliJ IDEA.
  User: valentin
  Date: 06/06/2018
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa" />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/signal.css" />
    <title>Signaler</title>
</head>
<img class="bandeau" src="../pictures/header_menu.svg"/>

<ul id="menu_user">
    <li><a href="/MessageServlet">Fil Message</a></li>
    <%int ut =(Integer)session.getAttribute("userType"); %>
    <%if (ut == 1) out.println("<li><a href=\"/mas\">Messages me concernant</a></li>");%>
    <%if (ut == 2) out.println("<li><a href=\"/vue/moderation.jsp\">Modération</a></li>");%>
    <li><a href="/vue/publier.jsp">Publier</a></li>
    <li><a href="#">Votre Avis</a></li>
    <li id="activ_window"><a href="#">Signalement</a></li>
    <li><a href="/deco">Déconnexion</a></li>
</ul>


<body>

<form action="/MailServlet" method="POST">
    <input type="hidden" name="pageName" value = "signal"/>
    <div class="contour_bleu">
        <h3>    Signaler un utilisateur :       <input type="text" name="usernameBan"/></h3>

        <div class ="contour_jaune">

            <h4>Motif de bannissement</h4>

            <input type=radio id ="radio1" name="reason" value="Message Injurieux" />
            <label for="radio1">Message Injurieux</label>
            <br><br>
            <input type=radio id ="radio2" name="reason" value="Abus de l'outil" />
            <label for="radio2">Abus de l'outil</label>
            <br><br>
            <input type=radio id ="radio3" name="reason" value="Autre motif qui ne suit pas le règlement de l’outil" />
            <label for="radio3">Autre motif qui ne suit pas le règlement de l’outil</label>
            <br><br>

        </div>
        <h5>    Avez-vous un message à ajouter au bannissement ?</h5>

        <textarea id="msg" name="user_message">Entrez un message</textarea>
        <input class="boutonValider" type="submit" value = "Confirmer"/>
        <br><br><br><br>
    </div>
</form>

<img class="logo" src="../pictures/logo_sans_nom.svg"/>
</body>

</html>