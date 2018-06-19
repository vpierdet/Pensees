<%--
  Created by IntelliJ IDEA.
  User: Marine
  Date: 14/06/2018
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mvc.model.message"%>


<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa" />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/file_message.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"> </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../JS/AGDAG.js" type="text/javascript"></script>
    <title>Mes Messages</title>
</head>
<img class="bandeau" src="../pictures/header_menu.svg"/>


<ul id="menu_user">
    <li><a href="/MessageServlet">Fil Message</a></li>
    <li id="activ_window"><a href="/mas">Messages me concernant</a></li>
    <li><a href="/vue/publier.jsp">Publier</a></li>
    <li><a href="#">Votre Avis</a></li>
    <li><a href="/vue/signal.jsp">Signalement</a></li>
    <li><a href="/deco">Déconnexion</a></li>
</ul>




<body>
<br>
<div class="contour_bleu">
    <form action="/mas" method="POST" id="triage">
        <br>
        Trier par  :
        <% String triS;
            if (request.getAttribute("tri") != null )
             triS = (String) request.getAttribute("tri");
        else triS = "date";%>

        <select size="1" name="tri" onchange="this.form.submit()" id="triSel" >
            <option value = "date">Tous
            <option value = "repondu">Repondu
            <option value = "nonrep">Non Repondu
            <option value = "pertinence">Pertinence
        </select>
        <script type="text/javascript">Selector("<%=triS%>");</script>
    </form>
    <div class="contour_jaune">
        <%
            ArrayList<message>  listeMessage = (ArrayList<message>) request.getAttribute("listeMessage");
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


                                "                <button id=\""+valueCouleurAG+"\" class=\"button_ok\" type=\"submit\" name=\""+mes.getIdMessage()+"\" value=\""+valueAG+"\">D'accord ("+mes.getAgree()+")</button>\n" +
                                "                <button id=\""+valueCouleurDAG+"\" class=\"button_pasok\" type=\"submit\" name=\""+mes.getIdMessage()+"\" value=\""+valueDAG+"\">Pas d'accord ("+mes.getDisagree()+")</button>\n" +


                                "                <p class=\"utilisateur\">" + mes.getUsername() +"   "+ mes.getDate() +"</p>\n ");
                if (mes.getIdReponse() == -1)
                out.println(
                                "            <br></form>\n" + "<br><button onclick=\"hidendisplay("+i+")\" id=\"answer"+i+"\" class=\"button_repondre\" >Répondre</button>\n" +
                                "            <form  method=\"POST\" action=\"/post\"> \n" +
                                "                <input hidden name=\"idMes\" value=\""+mes.getIdMessage()+"\"/>"+

                                "                <textarea hidden id=\"reponse"+i+"\"  class=\"cadre_reponse\" name=\"user_answer\">Entrez votre réponse ici.\n"+
                                "</textarea>"+
                                "                <br>\n" +
                                "                <button hidden id=\"publish"+i+"\" class=\"button_repondre\" type=\"submit\" name=\"post\" value=\"reponse\">Publier</button>\n" +
                                "\n" +
                                "            </form>");
                else out.println(
                        // if reponse existe :
                        "            <div class=\"cadre_reponse_util\">\n" +
                                "                <p>"+ mes.getReponse() + "</p>\n" +
                                "                <p class=\"reponse_admin\">" + mes.getUsernameAnswer() +"</p>\n" + //ICI INPUT NOM + DATE ADMIN
                                "            </div>\n"
                );

                out.println(
                                "        </div>\n" +
                                "\n" +
                                "\n" +
                                "        <br>"
                );
            }


        %>
        <form action="/mas" method="POST" >
            <input type="hidden" name="page" value="<%=request.getAttribute("page")%>">
            <input type="hidden" name="tri" value="<%=request.getAttribute("tri")%>">
            <div id="button_pages">


                <%int debut = (Integer) request.getAttribute("debut"); %>
                <%if (debut != 0){
                    out.println("<button onclick=\"submit\" class=\"pages fleche precedent\" name=\"bouton_page\" value=\""+(debut-10)+"\">←</button>");
                }%>
                <button name="bouton_page" class="pages between">|</button>
                <%if (debut+10 < (Integer) request.getAttribute("nbrMessage")){
                    out.println("<button onclick=\"submit\" class=\"pages fleche suivant\" name=\"bouton_page\" value=\""+(debut+10)+"\">→</button>");
                }%>
                <%--
                <button onclick="submit" name="boutton_page" value="pm"> Messages précédents </button><button onclick="submit" name="boutton_page" value="pp">Messages suivants</button>
                --%>
            </div>
        </form>
    </div>
</div>


</div>

</body>


<img class="logo" src="../pictures/logo_sans_nom.svg"/>
<script src="../JS/AGDAG.js" type="text/javascript"></script>

</html>