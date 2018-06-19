<%--
  Created by IntelliJ IDEA.
  User: valentin
  Date: 18/06/2018
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Comfortaa" />
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/Log.css" />
    <title>Connexion</title>
</head>
<body>
<img class="bandeau" src="../pictures/header.svg"/>
<div class="contour">
    <h1>Connexion</h1>
    <form action="/checklog" method="POST">
        Identifiant: <input type="text" name="username" /><br><br>
        Mot de passe: <input type="text" name="password" /><br>
        <br><h4><input class="boutonValider" type="submit" /></h4>
    </form></div>


<img class="logo" src="../pictures/logo_nom.svg"/></body>
</html>

