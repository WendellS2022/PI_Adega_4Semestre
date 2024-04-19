<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>

  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Gerenciamento de Endereços</title>
  <!-- Bootstrap CSS -->
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <!-- Logo -->
        <a class="navbar-brand mr-auto" href="#">
            <img src="LOGO1.png" alt="Logo" height="70">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="my-4 text-center">Gerenciamento de Endereços</h1>
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Endereço de Faturamento</h5>
                    <p class="card-text">
                        <!-- Lista de endereços de faturamento -->
                        <c:if test="${not empty enderecoFaturamento}">
                            <div class="form-check">
                                <label class="form-check-label" for="enderecoFaturamento${enderecoFaturamento.idEndereco}">
                                    ${enderecoFaturamento.logradouro}, ${enderecoFaturamento.numero}, ${enderecoFaturamento.complemento}, ${enderecoFaturamento.bairro}, ${enderecoFaturamento.cidade}, ${enderecoFaturamento.uf}, ${enderecoFaturamento.cep}
                                </label>
                            </div>
                        </c:if>
                    </p>
                </div>
            </div>
            <br>
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Endereço(s) de Entrega</h5>
                    <p class="card-text">
                        <!-- Lista de endereços de entrega -->
                 <c:forEach items="${enderecos}" var="endereco">
                     <c:if test="${endereco.status == true}">
                         <div class="endereco" data-cliente-logado="${clienteLogado}">
                             <label class="form-check-label" for="endereco${endereco.idEndereco}">
                                 ${endereco.logradouro}, ${endereco.numero}, ${endereco.complemento}, ${endereco.bairro}, ${endereco.cidade}, ${endereco.uf}, ${endereco.cep}

                                 <div class="form-check">

                                     <input class="form-check-input" type="radio" name="enderecoPadrao" value="${endereco.idEndereco}" id="enderecoPadrao${endereco.idEndereco}" ${endereco.padrao ? 'checked' : ''} required>

                                     <label class="form-check-label font-weight-bold" for="enderecoPadrao${endereco.idEndereco}">Endereço padrão</label>
                                                                      <a href="/desativarEndereco?idEndereco=${endereco.idEndereco}&clienteLogado=${clienteLogado}">Desativar</a>
                                 </div>
                             </label>
                         </div>
                     </c:if>
                 </c:forEach>


                    </p>
                </div>

                <button class="btn btn-primary" onclick="window.location.href='/CadastrarEndereco?clienteLogado=${clienteLogado}'">Adicionar</button>

            </div>
        </div>
    </div>
</div>



<!-- Bootstrap JS e dependências opcionais -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="IndicarEndereco.js"></script>

</body>
</html>
