<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Alterar Dados</title>
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
            <ul class="navbar-nav ml-auto">
                <li class="nav-item" id="enderecoTab" style="display: none;">
                    <a class="nav-link font-weight-bold" href="#">Cadastrar Endereço</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="my-4 text-center" >Alterar Dados</h1>
    <div class="row">
        <div class="col-md-6 offset-md-3">

            <form id="alterarDadosForm" action="/AlterarCliente" method="POST" class="needs-validation" novalidate>
                <input type="hidden" name="clienteLogado" value="${clienteLogado}">
                <div class="form-group">
                    <label for="nome" class="titulo-campo">Nome:</label>
                    <input type="text" name="nome" id="nome" class="form-control" placeholder="Nome completo" required maxlength="200" value="${cliente.nome}">
                </div>

                <div class="form-group">
                    <label for="dataNascimento" class="titulo-campo">Data de Nascimento:</label>
                    <input type="date" name="dataNascimento" id="dataNascimento" class="form-control" required value="${cliente.dataNascimento}">
                </div>



                <div class="form-group">
                 <label for="genero" class="titulo-campo">Gênero:</label>
                 <input type="txt" name="genero" id="genero" class="form-control" placeholder="Gênero" required maxlength="200" value="${cliente.genero}">
                 </div>

                <div class="form-group">
                    <label for="senha" class="titulo-campo">Senha:</label>
                    <input type="password" name="senha" id="senha" class="form-control" placeholder="Senha" required maxlength="200">
                </div>

                <div class="text-center">
                    <button type="submit" id="btn-salvar" class="btn btn-primary">Salvar Alterações</button>
                    <button type="button" id="btn-cancelar" onclick="window.location.href='/TelaProdutos'" class="btn btn-secondary">Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS e dependências opcionais -->
<script src="Cpf.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
