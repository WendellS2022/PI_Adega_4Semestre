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
        <a class="navbar-brand" href="/home">
            <img src="LOGO1.png" alt="Logo" height="70">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
</nav>

<div class="container mt-4">
    <h1 class="mb-4">Gerenciar Pedidos</h1>
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Data do Pedido</th>
                    <th scope="col">Quantidade de itens</th>
                    <th scope="col">Total</th>
                    <th scope="col">Status</th>
                    <th scope="col">Id Cliente</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pedidos}" var="pedido">
                    <tr>
                        <td>${pedido.pedidoId}</td>
                        <td>${pedido.dataPedido}</td>
                        <td>${pedido.quantidadeDeItens}</td>
                        <td>R$ ${pedido.subTotal}</td>
                        <td>
                            <select class="form-control" name="status">
                                <option value="aguardando pagamento" ${pedido.statusPagamento == 'aguardando pagamento' ? 'selected' : ''}>Aguardando Pagamento</option>
                                <option value="pagamento rejeitado" ${pedido.statusPagamento == 'pagamento rejeitado' ? 'selected' : ''}>Pagamento Rejeitado</option>
                                <option value="pagamento com sucesso" ${pedido.statusPagamento == 'pagamento com sucesso' ? 'selected' : ''}>Pagamento com Sucesso</option>
                                <option value="aguardando retirada" ${pedido.statusPagamento == 'aguardando retirada' ? 'selected' : ''}>Aguardando Retirada</option>
                                <option value="em transito" ${pedido.statusPagamento == 'em transito' ? 'selected' : ''}>Em Trânsito</option>
                                <option value="entregue" ${pedido.statusPagamento == 'entregue' ? 'selected' : ''}>Entregue</option>
                            </select>
                            <td>${pedido.idCliente}</td>

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
