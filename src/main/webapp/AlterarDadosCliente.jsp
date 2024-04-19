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

            <form id="alterarDadosForm" action="/alterarCliente" method="POST" class="needs-validation" novalidate>
               <div id="nome-error-message" style="color: red;"></div>
                <input type="hidden" name="clienteLogado" value="${clienteLogado}">
                <div class="form-group">
                    <label for="nome" class="titulo-campo" placeholder = >Nome:</label>
                    <input type="text" name="nome" id="nome" class="form-control" "Nome completo" required maxlength="200" value="${cliente.nome}">
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

<script>
    $(document).ready(function() {
        // Função para verificar se o nome do cliente atende aos requisitos
        function validarNome(nome) {
            var palavras = nome.split(" "); // Divide o nome em palavras
            if (palavras.length >= 2) { // Verifica se há pelo menos duas palavras
                // Verifica se cada palavra tem pelo menos 3 letras
                for (var i = 0; i < palavras.length; i++) {
                    if (palavras[i].length < 3) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }

        // Função para atualizar o estado do botão de salvar e mostrar a mensagem de erro
        function atualizarBotaoSalvar() {
            var nome = $("#nome").val(); // Obtém o valor do campo de nome
            var botaoSalvar = $("#btn-salvar"); // Obtém o botão de salvar
            var mensagemErro = $("#nome-error-message"); // Obtém o elemento para a mensagem de erro

            // Verifica se o nome atende aos requisitos
            if (validarNome(nome)) {
                botaoSalvar.prop("disabled", false); // Habilita o botão de salvar
                mensagemErro.text(""); // Limpa a mensagem de erro
            } else {
                botaoSalvar.prop("disabled", true); // Desabilita o botão de salvar
                mensagemErro.text("O nome deve conter pelo menos duas palavras com no mínimo três letras cada."); // Define a mensagem de erro
            }
        }

        // Ao digitar no campo de nome, verificar se o botão de salvar deve ser habilitado ou desabilitado
        $("#nome").keyup(function() {
            atualizarBotaoSalvar();
        });

        // Ao enviar o formulário, verificar novamente se o nome atende aos requisitos
        $("#cadastroForm").submit(function(event) {
            var nome = $("#nome").val(); // Obtém o valor do campo de nome

            // Se o nome não atender aos requisitos, impedir o envio do formulário
            if (!validarNome(nome)) {
                event.preventDefault(); // Impede o envio do formulário
                // Exibe uma mensagem de erro na tela
                $("#nome-error-message").text("O nome deve conter pelo menos duas palavras com no mínimo três letras cada.");
            }
        });
    });
</script>
</html>
