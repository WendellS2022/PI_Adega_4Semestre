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
    <h1 class="mb-4">Carrinho de Compras</h1>
    <div class="row">
        <div class="col-md-8">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Produto</th>
                        <th scope="col">Preço</th>
                        <th scope="col">Quantidade</th>
                        <th scope="col">Total</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                  <c:choose>
                      <c:when test="${empty sessionScope.clienteLogado or sessionScope.clienteLogado eq ''}">
                          <c:forEach items="${sessionScope.carrinho}" var="produtoCarrinho" varStatus="loop">
                              <tr>
                                  <th scope="row">${produtoCarrinho.produto.codProduto}</th>
                                  <td>${produtoCarrinho.produto.nomeProduto}</td>
                                  <td>R$ ${produtoCarrinho.produto.vlrVendaProduto}</td>
                                  <td>
                                      <form action="/atualizarQuantidade" method="post">
                                          <input type="hidden" name="produtoId" value="${produtoCarrinho.produto.codProduto}">
                                       <button type="submit" name="action" value="decrease" class="btn btn-sm btn-outline-secondary" ${produtoCarrinho.quantidadeComprada eq 1 ? 'disabled' : ''}>
                                              <i class="fas fa-minus"></i>
                                          </button>
                                          <span>${produtoCarrinho.quantidadeComprada}</span> <!-- Mostra a quantidade atual -->
                                          <button type="submit" name="action" value="increase" class="btn btn-sm btn-outline-secondary">
                                              <i class="fas fa-plus"></i>
                                          </button>
                                      </form>
                                  </td>
                                  <td>R$ ${produtoCarrinho.produto.vlrVendaProduto * produtoCarrinho.quantidadeComprada}</td>
                                  <td>
                                   <a href="/removerProdutoDoCarrinho?produtoId=${produtoCarrinho.produto.codProduto}" class="btn btn-danger btn-sm remover-produto">Remover</a>
                              </td>
                              </tr>
                          </c:forEach>
                      </c:when>
                    <c:otherwise>
                        <c:forEach items="${sessionScope.carrinho}" var="produtoCarrinho" varStatus="loop">
                            <tr>
                                <th scope="row">${produtoCarrinho.produto.codProduto}</th>
                                <td>${produtoCarrinho.produto.nomeProduto}</td>
                                <td>R$ ${produtoCarrinho.produto.vlrVendaProduto}</td>
                                <td>
                                   <form action="/atualizarQuantidade" method="post">
                                       <input type="hidden" name="produtoId" value="${produtoCarrinho.produto.codProduto}">
                                       <button type="submit" name="action" value="decrease" class="btn btn-sm btn-outline-secondary" ${produtoCarrinho.quantidadeComprada eq 1 ? 'disabled' : ''}>
                                           <i class="fas fa-minus"></i>
                                       </button>
                                       <span>${produtoCarrinho.quantidadeComprada}</span> <!-- Mostra a quantidade atual -->
                                       <button type="submit" name="action" value="increase" class="btn btn-sm btn-outline-secondary">
                                           <i class="fas fa-plus"></i>
                                       </button>
                                   </form>
                                    </form>
                                </td>
                                <td>R$ ${produtoCarrinho.produto.vlrVendaProduto * produtoCarrinho.quantidadeComprada}</td>
                                <td>
                                   <a href="/removerProdutoDoCarrinho?produtoId=${produtoCarrinho.produto.codProduto}" class="btn btn-danger btn-sm remover-produto">Remover</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>

                  </c:choose>
                </tbody>
            </table>
        </div>
        <div class="col-md-4">

                  <div class="card">
                      <h5 class="card-title">Resumo do Pedido</h5>
                      <div class="card-body">
<c:set var="totalItensComprados" value="0" />
<c:forEach var="produtoCarrinho" items="${sessionScope.carrinho}">
    <c:set var="totalItensComprados" value="${totalItensComprados + produtoCarrinho.quantidadeComprada}" />
 </c:forEach>
 <p class="card-text">Total de itens comprados: ${totalItensComprados}</p>
            <c:set var="subtotal" value="0" />
                          <c:forEach items="${sessionScope.carrinho}" var="produtoCarrinho">
                              <c:set var="subtotal" value="${subtotal + (produtoCarrinho.produto.vlrVendaProduto * produtoCarrinho.quantidadeComprada)}" />
                          </c:forEach>
                          <p class="card-text">Subtotal: R$ ${subtotal}</p> <!-- Adicione esta linha para exibir o subtotal -->
                          <div class="form-group">
                             <form action="/pagamento" method="GET"> <!-- Formulário para enviar frete selecionado -->
                                        <div class="form-group">
                                            <label for="frete">Selecionar Frete:</label>
                                            <select class="form-control" id="frete" name="frete">
                                                <option value="Entrega Normal - R$ 15,00">Entrega Normal - R$ 15,00</option>
                                                <option value="Entrega Expressa - R$ 30,00">Entrega Expressa - R$ 30,00</option>
                                                <option value="Entrega Urgente - R$ 150,00">Entrega Urgente - R$ 150,00</option>
                                            </select>
                                        </div>
                                            <input type="hidden" name="subtotal" value="${subtotal}"> <!-- Campo oculto para enviar o subtotal -->
                                            <input type="hidden" name="totalItensComprados" value="${totalItensComprados}"> <!-- Campo oculto para enviar o subtotal -->
<c:choose>
    <c:when test="${empty sessionScope.carrinho}">
        <button type="submit" class="btn btn-primary btn-block" disabled>Pagamento</button>
    </c:when>
    <c:otherwise>
        <button type="submit" class="btn btn-primary btn-block">Pagamento</button>
    </c:otherwise>
</c:choose>
                                    </form>
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
