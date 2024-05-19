<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrinho de Compras</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        /* Estilos personalizados */
        .readonly-input {
            background-color: #f8f9fa;
            pointer-events: none;
        }
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
                <li class="nav-item">
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
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <h1 class="mb-4">Detalhes do Pedido</h1>

    <div class="row">
        <div class="col-md-8">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Produto</th>
                        <th scope="col">Preço</th>
                        <th scope="col">Quantidade</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${itensPedido}" var="itemPedido" varStatus="loop">
                        <tr>
                            <th>${itemPedido.produtoId}</th>
                            <td>${itemPedido.nomeProduto}</td>
                            <td>R$ ${itemPedido.preco}</td>
                            <td>${itemPedido.quantidadeComprada}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-md-4">
            <div class="card">
                <h5 class="card-title">Resumo do Pedido</h5>
                <!-- Informações do resumo do pedido -->
                <div class="form-group">

                        <input type="hidden" name="clienteLogado" value="${clienteLogado}">
                        <div class="form-group">
                            <p class="card-text">Frete: R$ ${frete}</p>
                        </div>
                        <input type="hidden" name="frete" value="${frete}">
                        <div class="form-group">
                            <label for="endereco">Endereço:</label>
                            <p class="card-text">${endereco.logradouro}, ${endereco.numero}, ${endereco.bairro}, ${endereco.cidade}, ${endereco.uf}</p>
                        </div>
                        <div class="form-group">
                            <label for="subtotal">Subtotal: R$ ${subtotal}</label>
                        </div>
                        <input type="hidden" name="subtotal" value="${subtotal}">
                        <c:set var="totalItensComprados" value="0" />
                        <c:forEach var="itemPedido" items="${itensPedido}">
                            <c:set var="totalItensComprados" value="${totalItensComprados + itemPedido.quantidadeComprada}" />
                        </c:forEach>
                        <input type="hidden" name="totalDeItens" value="${totalItensComprados}">

                        <div class="form-group">
                            <label for="statusPagamento">Status do Pagamento:</label>
                            <p class="card-text">${statusPagamento}</p>
                        </div>
                        <input type="hidden" name="statusPagamento" value="${statusPagamento}">

                        <div class="form-group">
                            <label for="tipoPagamento">Tipo de Pagamento:</label>
                            <p class="card-text">${tipoPagamento}</p>
                        </div>
                        <input type="hidden" name="tipoPagamento" value="${tipoPagamento}">

                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS e dependências opcionais -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>