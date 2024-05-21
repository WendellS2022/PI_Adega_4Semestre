<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Pedidos</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <!-- Logo -->
        <a class="navbar-brand" href="/TelaProdutos?clienteLogado=${clienteLogado}">
            <img src="LOGO1.png" alt="Logo" height="70">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

 <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <%-- Verifica se o cliente está logado --%>
                <c:if test="${empty clienteLogado}">
                    <li class="nav-item">
                        <form action="/login" method="get">
                            <a class="nav-link mr-2" href="/login?cliente=true">Login</a>
                        </form>

                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/CadastrarCliente">Cadastre-se</a>
                    </li>
                </c:if>

                <c:if test="${not empty clienteLogado}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-user"></i> ${clienteLogado}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="/AlterarCliente?email=${clienteLogado}">Dados pessoais</a>
                            <a class="dropdown-item" href="/Enderecos?email=${clienteLogado}">Endereços</a>
                            <a class="dropdown-item" href="/sair?email=${clienteLogado}">Sair</a>

                        </div>
                    </li>
                </c:if>
            </ul>
        </div>
</div>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <h1 class="mb-4">Lista de Pedidos</h1>
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Data do Pedido</th>
                    <th scope="col">Quantidade de itens</th>
                    <th scope="col">Total</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pedidos}" var="pedido">
                    <tr>
                        <td>${pedido.pedidoId}</td>
                        <td>${pedido.dataPedido}</td>
                        <td>${pedido.quantidadeDeItens}</td>
                        <td>R$ ${pedido.subTotal}</td>
                        <td>${pedido.statusPagamento}</td>
                        <td><a href="/detalhePedido?pedidoId=${pedido.pedidoId}" class="btn btn-primary">Detalhes</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Bootstrap JS e dependências opcionais -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>