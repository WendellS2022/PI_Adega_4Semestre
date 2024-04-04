<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tela de Venda</title>
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
        <a class="navbar-brand" href="#">
            <img src="LOGO1.png" alt="Logo" height="70">
            Adega E-Dev's
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link mr-2" href="#">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Cadastre-se</a>
                </li>
                <li class="nav-item">
                 <a class="nav-link" href="/Carrinho.jsp"><i class="fas fa-shopping-cart"></i> Carrinho</a>
                 </li>
            </ul>
        </div>
    </div>
</nav>

  <div class="container">
      <h1 class="my-4">Produtos Disponíveis</h1>
      <div class="row">
          <!-- Iterar sobre a lista de produtos -->
          <c:forEach var="produto" items="${produtos}">
              <div class="col-lg-3 col-md-6 mb-4">
                  <div class="card h-100">
                      <div class="card-body d-flex flex-column">
                          <div>
                              <c:forEach var="imagem" items="${imagensPorProduto[produto.codProduto]}">
                                  <img src="${imagem.diretorio}/${imagem.nome}" alt="${produto.nomeProduto}" class="img-fluid" style="max-height: 210px;">
                              </c:forEach>
                          </div>
                          <div class="mt-auto">
                              <p class="card-title">${produto.nomeProduto}</p>
                              <p class="card-text">Preço: R$ ${produto.vlrVendaProduto}</p>
                              <a href="/visualizarProduto?codProduto=${produto.codProduto}" class="btn btn-primary btn-block">Detalhes</a>
                          </div>
                      </div>
                  </div>
              </div>
          </c:forEach>
      </div>
  </div>


  <!-- Bootstrap JS e dependências opcionais -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
