<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cadastro de Endereço</title>
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
        <div class="collapse navbar-collapse" id="navbarNav">

            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="my-4 text-center">Cadastre seu Endereço</h1>
    <div class="row">
        <div class="col-md-6 offset-md-3">
           <form id="enderecoForm" method="post">
                <div class="needs-validation" novalidate>
                   <input type="hidden" name="clienteLogado" value="${clienteLogado}">
                   <input type="hidden" name="adicionar" value="${adicionar}">

                    <label for="cep" class="titulo-campo">CEP:</label>
                    <div class="input-group">

                                            <c:choose>
                        <c:when test="${empty cep}">
                        <input type="text" name="cep" id="cep" class="form-control" placeholder="CEP" maxlength="8">
                        </c:when>
                        <c:otherwise>

                                <input type="text" name="cep" id="cep" class="form-control" placeholder="CEP" maxlength="8"
                                       value="${not empty cep ? cep : ''}">

                        </c:otherwise>
                    </c:choose>
                    <div class="input-group-append">
                                                <button class="btn btn-primary" type="button" id="btn-buscar-cep">Buscar</button>
                                            </div>
 </div>

                    <div class="form-group">
                        <label for="logradouro" class="titulo-campo">Logradouro:</label>
                        <input type="text" name="logradouro" id="logradouro" class="form-control" placeholder="Rua, Avenida, etc." required maxlength="200">
                    </div>

                    <div class="form-group">
                        <label for="numero" class="titulo-campo">Número:</label>
                        <input type="text" name="numero" id="numero" class="form-control" placeholder="Número" required maxlength="20">
                    </div>

                    <div class="form-group">
                        <label for="complemento" class="titulo-campo">Complemento:</label>
                        <input type="text" name="complemento" id="complemento" class="form-control" placeholder="Complemento (opcional)" maxlength="200">
                    </div>

                    <div class="form-group">
                        <label for="bairro" class="titulo-campo">Bairro:</label>
                        <input type="text" name="bairro" id="bairro" class="form-control" placeholder="Bairro" required maxlength="100">
                    </div>

                    <div class="form-group">
                        <label for="cidade" class="titulo-campo">Cidade:</label>
                        <input type="text" name="cidade" id="cidade" class="form-control" placeholder="Cidade" required maxlength="100">
                    </div>

                    <div class="form-group">
                        <label for="estado" class="titulo-campo">Estado:</label>
                        <input type="text" name="estado" id="estado" class="form-control" placeholder="Estado" required maxlength="2">
                    </div>

                    <div class="text-center">
                        <button type="submit" id="btn-salvar" name="adicionar" class="btn btn-primary">Cadastrar</button>
                    </div>
                </div>
           </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS e dependências opcionais -->
<script src="CadastrarEndereco.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script src="APIViaCEP.js"></script>

</body>

</html>
