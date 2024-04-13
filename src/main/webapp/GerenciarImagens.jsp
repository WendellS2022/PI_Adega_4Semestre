<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.adega.Model.Produto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <link href="CadastrarAlterarProduto.css" rel="stylesheet">
    <title>Cadastrar ou Alterar Produto</title>
</head>
<header id="cabecalho-site">
    <h1 id="identificacao-site">e-Commerce e-Devs - Área de BackOffice</h1>
</header>
<body>
    <article id="area-produto">
        <section id="caixa-produto">
            <header id="cabecalho-produto">
                <h2>Cadastrar ou Alterar Produto</h2>
            </header>
            <div id="mensagem-erro" style="color: red;"></div>
            <%
                String mensagem = (String) request.getAttribute("mensagem");
                if (mensagem != null) {
            %>
            <p><%= mensagem %></p>
            <% } %>


                <!-- Seção de imagens do produto -->
                <div id="imagens-produto">

                    <label for="selImagem" class="titulo-campo">Seleção de Imagem do Produto:</label>
                    <input type="file" name="selImagem" id="selecao-imagem" multiple>
                    <input type="hidden" name="caminhoImagemPrincipal" id="caminho-imagem-principal"> <!-- Adicionando o campo oculto -->
                    <header id="cabecalho-imagem">
                        <h5>Imagem(ns) do Produto</h5>
                    </header>
                    <div id="imagens-produto">
                        <!-- Aqui são exibidas as imagens selecionadas -->
                    </div>

        </section>
         <button type="submit" id="btn-salvar">Salvar</button>
                            <button type="button" id="btn-cancelar" onclick="window.location.href='/listarProdutos'">Cancelar</button>

    </article>
</body>
<script src="cadastrarProduto.js"></script>

</html>