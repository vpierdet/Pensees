<%@ page import="java.util.ArrayList" %>
<%@ page import="mvc.model.message"%>


<%--
  Created by IntelliJ IDEA.
  User: valentin
  Date: 24/05/2018
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa" />
<html lang="en">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"> </script>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="file_message.css" />
    <title>Fil Actualite</title>
</head>
<img class="bandeau" src="pictures/header_menu.svg"/>


<ul id="menu_user">
    <li id="activ_window"><a href="#">Fil Message</a></li>
    <li><a href="/publier_user.html">Publier</a></li>
    <li><a href="#">Votre Avis</a></li>
    <li><a href="/signal.jsp">Signalement</a></li>
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
                message mes = listeMessage.get(i);
                String catego = mes.getCategories();
                String valueAG = mes.getEtat() == 1 ? "a-1" :"a+1";
                String valueDAG = mes.getEtat() == -1 ? "d-1":"d+1";
                out.println(
                        "<div class=\"type_enseignement\">\n" +
                                "                <h1 class=\"etiquette\">"+catego+"</h1>\n" +
                                "            </div>\n" +
                                "            <div class=\"cadre_message\">\n" +
                                mes.getText()+
                                "\n" +
                                "<form> \n" +
                                "                <br>\n" +
                                // if deja vote : enlever type:"submit" sur les 2 boutons ok/pas ok
                                "                <input type=\"hidden\" class=\"idmes\" value=\"" +mes.getIdMessage() +"\"/>"+
                                "                <button class=\"button_ok\" type=\"submit\" name=\""+mes.getIdMessage()+"\" value=\""+valueAG+"\">D'accord ("+mes.getAgree()+")</button>\n" +
                                "                <button class=\"button_pasok\" type=\"submit\" name=\""+mes.getIdMessage()+"\" value=\""+valueDAG+"\">Pas d'accord ("+mes.getDisagree()+")</button>\n" +
                               //fin du if

                                "                <p class=\"utilisateur\">" + mes.getUsername() + mes.getTimestamp() +"</p>\n" + //ICI INPUT NOM + DATE UTILISATEUR
                                "            </form>\n");
                if (mes.getIdReponse() != -1)out.println(
                                // if reponse existe :
                                "            <div class=\"cadre_reponse_util\">\n" +
                                "                <p>"+ mes.getReponse() + "</p>\n" + //ICI INPUT REPONSE
                                "                <p class=\"reponse_admin\">" + mes.getUsernameAnswer() + "DATE" +"</p>\n" + //ICI INPUT NOM + DATE ADMIN
                                "            </div>\n"
                );
                                //fin du if
                out.println(
                                "\n" +
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
<script src="JS/AGDAG.js" type="text/javascript"></script>
<img class="logo" src="pictures/logo_sans_nom.svg"/>
</html>