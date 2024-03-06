<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.adega.Model.User" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org" lang="pt">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="CadastrarAlterarUsuario.css">
    <title>Cadastrar ou Alterar Usuário</title>
</head>

<body>
    <form action="/cadastrar" method="POST">
        <div id="info-campos">
            <label for="email" class="titulo-campo">E-mail:</label>
            <input type="email" name="email" id="email-usuario" placeholder="E-mail do usuário" required
                value="${user != null ? user.email : ''}" ${user != null ? 'readonly' : ''}> <!-- Verifica se há um objeto User presente e preenche o campo de email -->
            <label for="email" class="titulo-campo">Nome:</label>
            <input type="text" name="nome" id="nome-usuario" placeholder="Nome do usuário" required
                value="${user != null ? user.nome : ''}"> <!-- Verifica se há um objeto User presente e preenche o campo de nome -->
            <label for="email" class="titulo-campo">CPF:</label>
            <input type="text" name="cpf" id="cpf-usuario" placeholder="CPF do usuário" required
                value="${user != null ? user.CPF : ''}"> <!-- Verifica se há um objeto User presente e preenche o campo de CPF -->
            <label for="email" class="titulo-campo">Grupo:</label>
            <select name="grupo" id="grupo-usuario" required>
                <option value="1" ${user != null && user.grupo == 1 ? 'selected' : ''}>Administrador</option> <!-- Verifica se há um objeto User presente e seleciona o grupo Administrador se corresponder -->
                <option value="2" ${user != null && user.grupo == 2 ? 'selected' : ''}>Estoquista</option> <!-- Verifica se há um objeto User presente e seleciona o grupo Estoquista se corresponder -->
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
</html>
