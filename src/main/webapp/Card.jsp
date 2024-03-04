<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Card.css">
    <title>Document</title>
</head>
<body>
</head>
<body>
<div class="options-container">
    <%
        int grupo = ((Integer) request.getAttribute("grupo")).intValue();
        if (grupo == 1) {
    %>
    <button onclick="window.location.href='/listar_usuarios'" id="lista">Listar Usuários</button>
    <button onclick="window.location.href='/cadastrar_usuario'" id="produto">Listar Produtos</button>
    <% } else if (grupo == 2) { %>
    <button onclick="window.location.href='/estoque'" id="estoque">Estoque</button>
    <% } %>
</div>

</body>
</html>
</body>
</html>