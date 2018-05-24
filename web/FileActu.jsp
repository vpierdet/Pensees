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
    <title>File d'actualité</title>
</head>
<body>

<%
    ArrayList<message>  listeMessage = (ArrayList<message>) request.getAttribute("listeMessage");
    for (int i = 0; i < listeMessage.size(); i++ ){
        out.println(listeMessage.get(i).getText()+"</br>");
    }

%>








</body>
</html>
