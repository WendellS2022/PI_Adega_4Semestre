<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="ListarUsuario.css">
    <title>Login</title>
</head>

<!-- Aqui fica o cabeçalho! -->
<header id="cabecalho-site">
    <h1 id="identificacao-site">e-Commerce e-Devs</h1>
</header>

<body>
    <article id="area-lista-usuario">
        <section id="caixa-lista-usuario">
            <header id="cabecalho-lista-usuario">
                <h2>Lista de Usuário</h2>
            </header>

            <div id="info-selecoes">
                <input type="text" placeholder="Nome do Usuário" id="nome-pesquisa">
                <button id="btn-procurar">Procurar</button>
                <form action="/cadastrar" method="Get">
                    <button type="submit" id="btn-cadastrar">Novo Usuário</button>
                </form>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Grupo</th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="itens" items="${usuario}" >
                        <tr>
                            <td>${itens.nome}</td>
                            <td>${itens.email}</td>
                            <td>${itens.grupo}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </article>
</body>
</html>
