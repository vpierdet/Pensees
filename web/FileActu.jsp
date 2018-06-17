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
    <script src="JS/AGDAG.js" type="text/javascript"></script>
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
    <form action="/MessageServlet" method="POST" id="triage">
        <br>
        Trier par  :
        <%  String triS = (String) request.getAttribute("tri");
            triS = triS.replace(" ","_");%>

        <select size="1" name="tri" onchange="this.form.submit()" id="triSel" >
            <option value = "Date">Date
            <option value = "Pertinence">Pertinence
            <option value = "Cat_Planning">Planning
            <option value = "Cat_Comportement_et_Incivilite">Comportement et Incivilité
            <option value = "Cat_Problemes_Materiel">Problèmes Matériel
            <option value = "Cat_Organisation">Organisation
            <option value = "Cat_Enseignement">Enseignement
        </select>
        <script type="text/javascript">Selector("<%=triS%>");</script>
    </form>

    <div class="contour_jaune">
        <%
            ArrayList<message>  listeMessage = (ArrayList<message>) request.getAttribute("listeMessage");
            if (listeMessage.isEmpty()) out.println("\tAucun Message");
            for (int i = 0; i < listeMessage.size(); i++ ){
                message mes = listeMessage.get(i);
                String catego = mes.getCategories();
                String valueAG = mes.getEtat() == 1 ? "a-1" :"a+1";
                String valueDAG = mes.getEtat() == -1 ? "d-1":"d+1";
                String valueCouleurAG = mes.getEtat() == 1 ? "bos" : "bons";
                String valueCouleurDAG = mes.getEtat()== -1 ? "bpos":"bpons";
                out.println(
                        "<div class=\"type_enseignement\" id=\""+catego +"\">\n" +
                                "                <h1 class=\"etiquette "+catego+"\">"+catego+"</h1>\n" +
                                "            </div>\n" +
                                "            <div class=\"cadre_message\">\n" +
                                mes.getText()+
                                "\n" +
                                "<form> \n" +
                                "                <br>\n" +

                                "                <input type=\"hidden\" class=\"idmes\" value=\"" +mes.getIdMessage() +"\"/>"+
                                // if deja vote : enlever type:"submit" sur les 2 boutons ok/pas ok
                                //"                <button style=\"background: #d0ffd1\" class=\"button_ok\" type=\"submit\" name=\""+mes.getIdMessage()+"\" value=\""+valueAG+"\">D'accord ("+mes.getAgree()+")</button>\n" +
                                //"                <button style=\"background: #ffc1c1\" class=\"button_pasok\" type=\"submit\" name=\""+mes.getIdMessage()+"\" value=\""+valueDAG+"\">Pas d'accord ("+mes.getDisagree()+")</button>\n" +

                                //fin du if

                                "                <button id=\""+valueCouleurAG+"\" class=\"button_ok\" type=\"submit\" name=\""+mes.getIdMessage()+"\" value=\""+valueAG+"\">D'accord ("+mes.getAgree()+")</button>\n" +
                                "                <button id=\""+valueCouleurDAG+"\" class=\"button_pasok\" type=\"submit\" name=\""+mes.getIdMessage()+"\" value=\""+valueDAG+"\">Pas d'accord ("+mes.getDisagree()+")</button>\n" +


                                "                <p class=\"utilisateur\">" + mes.getUsername() +"   "+ mes.getDate() +"</p>\n" +
                                "            </form>\n");
                if (mes.getIdReponse() != -1)out.println(
                                // if reponse existe :
                                "            <div class=\"cadre_reponse_util\">\n" +
                                "                <p>"+ mes.getReponse() + "</p>\n" +
                                "                <p class=\"reponse_admin\">" + mes.getUsernameAnswer() +"</p>\n" + //ICI INPUT NOM + DATE ADMIN
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
<form action="/MessageServlet" method="POST" >
    <input type="hidden" name="page" value="<%=request.getAttribute("page")%>">
    <input type="hidden" name="tri" value="<%=request.getAttribute("tri")%>">
    <%int debut = (Integer) request.getAttribute("debut"); %>
    <%if (debut != 0){
        out.println("<button onclick=\"submit\" name=\"bouton_page\" value=\""+(debut-10)+"\"> Messages précédents </button>");
    }%>
    <%if (debut+10 < (Integer) request.getAttribute("nbrMessage")){
        out.println("<button onclick=\"submit\" name=\"bouton_page\" value=\""+(debut+10)+"\"> Messages suivants </button>");
    }%>
<%--
<button onclick="submit" name="boutton_page" value="pm"> Messages précédents </button><button onclick="submit" name="boutton_page" value="pp">Messages suivants</button>
--%>
</form>

<img class="logo" src="pictures/logo_sans_nom.svg"/>
</html>