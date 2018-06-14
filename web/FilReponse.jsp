<%--
  Created by IntelliJ IDEA.
  User: Marine
  Date: 14/06/2018
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mvc.model.message"%>


<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa" />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="file_message.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Mes Messages</title>
</head>
<img class="bandeau" src="pictures/header_menu.svg"/>


<ul id="menu_user">
    <li><a href="#">Fil Message</a></li>
    <li id="activ_window"><a href="#">Messages me concernant</a></li>
    <li><a href="#">Publier</a></li>
    <li><a href="#">Votre Avis</a></li>
    <li><a href="#">Signalement</a></li>
    <li><a href="/deco">Déconnexion</a></li>
</ul>


<body>
<br>
<div class="contour_bleu">
    <form action="/" method="POST" id="triage">
        <br>
        Trier par  :
        <select size="1" name="username">
            <option>Enseignement
            <option>Organisation
            <option>Aménagement et Matériel
        </select>
    </form>
    <div class="contour_jaune">
        <%
            ArrayList<message>  listeMessage = (ArrayList<message>) request.getAttribute("listeMessage");
            for (int i = 0; i < listeMessage.size(); i++ ){
                //out.println(listeMessage.get(i).getText());
                message mes = listeMessage.get(i);
                String catego = mes.getCategories();
                // UNIQUEMENT MESSAGES AUQUEL IL PEUT REPONDRE
                out.println(
                        "<div class=\"type_enseignement\">\n" +
                                "                <h1 class=\"etiquette\">"+catego+"</h1>\n" +
                                "            </div>\n" +
                                "            <div class=\"cadre_message\">\n" +
                                mes.getText()+
                                "\n <br>" +
                                "<button class=\"button_ok\" name=\"ok\" style=\"background: #d0ffd1\">D'accord (30)</button>\n" +
                                "                <button class=\"button_pasok\" name=\"pasok\" style=\"background: #ffc1c1\" >Pas d'accord (12)</button><br><br>\n" +
                                "                <button onclick=\"hidendisplay(1)\" id=\"answer1\" class=\"button_repondre\" >Répondre</button>\n" +
                                "                <p class=\"utilisateur\">"+"NOM"+"DATE"+"</p>\n" + //ICI INPUT NOM/DATE USER
                                "                <br>\n" +
                                "            <form  method=\"post\"> \n" +
                                "                <textarea hidden id=\"reponse1\"  class=\"cadre_reponse\">Entrez votre réponse ici.\n" + //ENTREE FORM TEXTE
                                "                </textarea>\n" +
                                "                <br>\n" +
                                "                <button hidden id=\"publish1\" class=\"button_repondre\" type=\"submit\" >Publier</button>\n" +
                                "\n" +
                                "            </form>" +
                                "        </div>\n" +
                                "\n" +
                                "\n" +
                                "        <br>"
                );
            }

        %>

    </div>
</div>


</div>

</body>

<img class="logo" src="pictures/logo_sans_nom.svg"/>
<script>
    function hidendisplay(x) {
        var answer = document.getElementById('answer'+x.toString());
        var textarea = document.getElementById('reponse'+x.toString());
        var publish = document.getElementById('publish'+x.toString());
        answer.style.display = "none";
        textarea.style.display = "block";
        publish.style.display = "block";


    }
</script>
</html>