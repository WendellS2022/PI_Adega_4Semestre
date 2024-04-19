<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.adega.Model.Imagem" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <link href="CadastrarAlterarProduto.css" rel="stylesheet">
    <title>Gerênciar imagem(ns) do produto</title>
</head>
<body>
    <header id="cabecalho-site">
        <h1 id="identificacao-site">e-Commerce e-Devs - Área de BackOffice</h1>
    </header>
    <article id="area-produto">
        <input type="hidden" id="codProduto" name="codProduto" value="${codProduto}">
        <section id="caixa-produto">
            <header id="cabecalho-produto">
                <h2>Gerênciar imagem(ns) do produto</h2>
            </header>
            <div id="mensagem-erro" style="color: red;">
                <% String mensagem = (String) request.getAttribute("mensagem"); %>
                <% if (mensagem != null) { %>
                    <p><%= mensagem %></p>
                <% } %>
            </div>

            <!-- Seção de imagens do produto -->
            <div id="imagens-produto">
                <label for="selImagem" class="titulo-campo">Seleção de Imagem(ns) do Produto:</label>
                <input type="file" name="selImagem" id="selecao-imagem" multiple>
                <!-- Adicionando o campo oculto -->
                <header id="cabecalho-imagem">
                    <h5>Imagem(ns) do Produto</h5>
                </header>
                <div id="lista-imagens">
                    <!-- Aqui são exibidas as imagens selecionadas -->
                    <c:forEach var="imagem" items="${imagensProdutoBase}">
                        <div class="informacao-imagem">
                            <form action="/excluirImagem" method="POST">
                                <button type="submit" class="btn-excluir">X</button>
                                <img src="<c:url value='/imagens/${imagem.nome}'/>" alt="Imagem do Produto">
                                <input type="hidden" name="caminhoImagemPrincipal" value="${imagem.nome}" id="caminho-imagem-principal">
                            <input type="radio" name="imagemPrincipal" class="imagem-principal" data-caminho="${imagem.diretorio}/${imagem.nome}" ${imagem.qualificacao ? 'checked' : ''}>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <button type="button" id="btn-cancelar" onclick="window.location.href='/listarProdutos'">Voltar</button>
        </section>


    </article>
    <!-- Adicione o elemento de carregamento -->
    <div id="loading" style="display: none;"/>


</body>
<script src="GerenciarImagens.js"></script>
</html>
