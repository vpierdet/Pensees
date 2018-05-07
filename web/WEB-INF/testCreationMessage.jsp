<%--
  Created by IntelliJ IDEA.
  User: valentin
  Date: 05/05/2018
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>testCreationMessage</title>
</head>
<body>
<form action="/PostServlet" method="post">
    <input type="text" name="txt"/>
    <select name="categorie">
        <option value="Securité">Securité</option>
        <option value="Hygiène">Hygiène</option>
    </select>
    <input type="submit" value="envoyer"/>
</form>
</body>
</html>
