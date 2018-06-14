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
    <meta charset="UTF-8">
    <link rel="stylesheet" href="file_message.css" />
    <title>Fil Actualite</title>
</head>
<img class="bandeau" src="pictures/header_menu.svg"/>


<ul id="menu_user">
    <li id="activ_window"><a href="#">Fil Message</a></li>
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
                out.println(
                        "<div class=\"type_enseignement\">\n" +
                                "                <h1 class=\"etiquette\">"+catego+"</h1>\n" +
                                "            </div>\n" +
                                "            <div class=\"cadre_message\">\n" +
                                mes.getText()+
                                "\n" +
                                "<form  method=\"post\"> \n" +
                                "                <br>\n" +
                                // if deja vote :
                                //"                <button class=\"button_ok\" name=\"ok\" style="background: #d0ffd1">D'accord (30)</button>\n" +
                                //"                <button class=\"button_pasok\" name=\"pasok\" style="background: #ffc1c1" >Pas d'accord (12)</button>\n" +
                               //sinon :
                                "                <button class=\"button_ok\" type=\"submit\" name=\"ok\">D'accord (30)</button>\n" +
                                "                <button class=\"button_pasok\" type=\"submit\" name=\"pasok\" >Pas d'accord (12)</button>\n" +
                                //fin du if


                                "                <p class=\"utilisateur\">" + "NOM" + "DATE" +"</p>\n" + //ICI INPUT NOM + DATE UTILISATEUR
                                "            </form>\n" +
                                // if reponse existe :
                                "            <div class=\"cadre_reponse_util\">\n" +
                                "                <p>"+ "MESSAGE REPONSE" + "</p>\n" + //ICI INPUT REPONSE
                                "                <p class=\"reponse_admin\">" + "NOM" + "DATE" +"</p>\n" + //ICI INPUT NOM + DATE ADMIN
                                "            </div>\n" +
                                //fin du if
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

<img class="logo" src="pictures/logo_sans_nom.svg"/>
</html>