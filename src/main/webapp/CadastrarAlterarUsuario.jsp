<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org" lang="pt">
<head>
    <meta charset="UTF-8">
    <link rel
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CadastrarAlterarUsuario.css">
    <script src="ValidaCPF.js" type="text/javascript"></script>

    <title>Cadastrar ou Alterar Usuário</title>
</head>

<!-- Aqui fica o cabeçalho! -->

<body>
    <form action="/cadastrar" method="POST">
        <div id="info-campos">
            <label for="email" class="titulo-campo">E-mail:</label>
            <input type="email" name="email" id="email-usuario" placeholder="E-mail do usuário" required>
            <label for="email" class="titulo-campo">Nome:</label>
            <input type="text" name="nome" id="nome-usuario" placeholder="Nome do usuário" required>
            <label for="email" class="titulo-campo">CPF:</label>
            <input type="text" name="cpf" id="cpf-usuario" placeholder="CPF do usuário" required>
            <label for="email" class="titulo-campo">Grupo:</label>
            <select name="grupo" id="grupo-usuario" required>
                <option value="1">Administrador</option>
                <option value="2">Estoquista</option>
            </select>
            <label for="email" class="titulo-campo">Senha:</label>
            <input type="password" name="senha" class="senha-usuario" required>
            <label for="email" class="titulo-campo">Confirme a senha:</label>
            <input type="password" name="senha-2" class="senha-usuario" required>
        </div>
        <div id="botoes">
            <button type="submit" id="btn-confirmar">Confirmar</button>
            <button type="reset" id="btn-cancelar">Cancelar</button>
        </div>
    </form>
</body>
<script src="ValidaCPF.js" type="text/javascript"></script>
</html>