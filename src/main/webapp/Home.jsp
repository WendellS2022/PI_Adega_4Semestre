<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Home.css">
    <title>Document</title>
</head>
<body>
<div class="options-container">
    <%
        int grupo = ((Integer) request.getAttribute("grupo")).intValue();
        if (grupo == 1) {
    %>
    <form action="/listar" method="get">
        <button type="submit" id="lista">Listar Usuários</button>
    </form>
    <form action="/cadastrar_usuario" method="get">
        <button type="submit" id="produto">Cadastrar Usuário</button>
    </form>
    <% } else if (grupo == 2) { %>
    <form action="/estoque" method="get">
        <button type="submit" id="estoque">Estoque</button>
    </form>
    <% } %>
</div>

</body>
</html>
