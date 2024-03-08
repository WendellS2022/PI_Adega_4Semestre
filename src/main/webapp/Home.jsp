<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Home.css">
    <title>Home</title>
</head>
<header id="cabecalho-site">
    <h1 id="identificacao-site">${usuarioLogado}</h1>
</header>
<body>

<div class="options-container">
    <%
        int grupo = ((Integer) request.getAttribute("grupo")).intValue();
        if (grupo == 1) {
    %>
    <form action="/listar" method="get">
        <button type="submit" id="lista">Listar usuários</button>
    </form>
    <form action="/" method="get">
        <button type="submit" id="produto">Listar produtos</button>
    </form>
    <% } else if (grupo == 2) { %>
    <form action="/" method="get">
        <button type="submit" id="estoque">Listar pedidos</button>
    </form>
    <% } %>
</div>

</body>
</html>
