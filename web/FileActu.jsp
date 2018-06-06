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
<html>
<head>
    <link rel="stylesheet" href="file_message.css" />
    <title>File d'actualité</title>
</head>
<body>
<div>
    <img class="bandeau" src="pictures/header_menu.svg"/>

    <ul id="menu_user">
        <li id ="activ_window"><a href="#">Fil Message</a></li>
        <li ><a href="#">Publier</a></li>
        <li><a href="#">Votre Avis</a></li>
        <li><a href="#">Signalement</a></li>
        <li><a href="/deco">Déconnexion</a></li>
    </ul>
    <br>
</div>


<div class="contour_jaune">
    <div class="contour_bleu">
        <form action="/" method="POST">
            Trier par  :
            <select size="1" name="username">
                <option>Enseignement
                <option>Organisation
                <option>Aménagement et Matériel
            </select>
        </form>
    </div>


<%
    ArrayList<message>  listeMessage = (ArrayList<message>) request.getAttribute("listeMessage");
    for (int i = 0; i < listeMessage.size(); i++ ){
        //out.println(listeMessage.get(i).getText());
        message mes = listeMessage.get(i);
        String catego = mes.getCategories();
        out.println("<div class=\"cadre_message\"> " +
                "<div class=\"type_enseignement\">\n" +
                         catego +"\n" +
                        "        </div>"+
                ""+ mes.getText()+"<form action=\"/deco\" method=\"post\">\n" +
                "            <button id=\"button_ok\" type=\"submit\" name=\"ok\">D'accord</button>\n" +
                "            <button id=\"button_pasok\" type=\"submit\" name=\"pasok\" >Pas d'accord</button>\n" +
                "        </form> </div>");
    }

%>
</div>



</body>
<img class="logo" src="pictures/logo_sans_nom.svg"/>
</html>
