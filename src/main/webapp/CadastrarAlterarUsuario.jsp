<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.adega.Model.Produto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <link href="CadastrarAlterarUsuario.css" rel="stylesheet">
    <title>Cadastrar ou Alterar Produto</title>
</head>

<header id="cabecalho-site">
    <h1 id="identificacao-site">e-Commerce e-Devs - Área de BackOffice</h1>
</header>

<body>
    <article id="area-usuario">
        <section id="caixa-usuario">
            <header id="cabecalho-usuario">
                <h2>Cadastrar ou Alterar Usuário</h2>
            </header>

            <form action="/cadastrar" method="POST">
                <div id="info-campos">
                    <input type="hidden" name="usuarioId" value="${usuario.usuarioId}">
                    <input type="hidden" name="sessao" value="${sessao}">

                    <label for="email" class="titulo-campo">E-mail:</label>
                    <input type="email" name="email" id="email-usuario" placeholder="E-mail do usuário" required
                        value="${usuario != null ? usuario.email : ''}" ${usuario != null ? 'readonly' : ''}> <!-- Verifica se há um objeto User presente e preenche o campo de email -->
                    <label for="email" class="titulo-campo">Nome:</label>
                    <input type="text" name="nome" id="nome-usuario" placeholder="Nome do usuário" required
                        value="${usuario != null ? usuario.nome : ''}"> <!-- Verifica se há um objeto User presente e preenche o campo de nome -->
                    <label for="email" class="titulo-campo">CPF:</label>
                    <input type="text" name="cpf" id="cpf-usuario" placeholder="CPF do usuário" required
                        value="${usuario != null ? usuario.CPF : ''}"> <!-- Verifica se há um objeto User presente e preenche o campo de CPF -->
                    <label for="email" class="titulo-campo">Grupo:</label>
                    <!-- Verifica se o campo "sessao" tem valor -->
                    <select name="grupo" id="grupo-usuario" ${not empty sessao ? 'disabled' : ''}>
                        <option value="1" ${usuario != null && usuario.grupo == 1 ? 'selected' : ''}>Administrador</option>
                        <option value="2" ${usuario != null && usuario.grupo == 2 ? 'selected' : ''}>Estoquista</option>
                    </select>

                    <label for="senha" class="titulo-campo">Senha:</label>
                    <input type="password" name="senha" id="senha-usuario" class="senha-usuario" required>
                    <label for="senha-2" class="titulo-campo">Confirme a senha:</label>
                    <input type="password" name="senha-2" id="senha-usuario-confirmacao" class="senha-usuario" required>
                    <div id="mensagem-erro-senha" style="color: red;"></div>
                    <%
                    String mensagem = (String) request.getAttribute("mensagem");
                    if (mensagem != null) {
                    %>
                    <p><%= mensagem %></p>
                    <%
                    }
                    %>
                </div>
                <button type="submit" id="btn-salvar">Salvar</button>
            </form>
            <div id="botoes">
                <form action="/listar" method="GET">
                    <button type="submit" id="btn-cancelar">Cancelar</button>
                </form>
            </div>

            <%
            String usuarioLogado = (String) session.getAttribute("usuarioLogado");
            %>
        </section>
    </article>
</body>
<script src="ValidaCPF.js" type="text/javascript"></script>
<script src="ValidaSenha.js" type="text/javascript"></script>
<script>
    var selectedIndex = document.getElementById('grupo-usuario').selectedIndex;

    // Impede que o valor seja alterado se o campo estiver desabilitado
    document.getElementById('grupo-usuario').addEventListener('change', function() {
        if (this.disabled) {
            this.selectedIndex = selectedIndex;
        } else {
            selectedIndex = this.selectedIndex;
        }
    });
</script>
</html>
