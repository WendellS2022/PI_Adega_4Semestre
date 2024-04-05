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

<form action="/cadastrarProduto?codProduto=${codProduto}" method="POST" enctype="multipart/form-data">
                          <!-- Seção de imagens do produto -->
                <div id="imagens-produto">
                    <label for="selImagem" class="titulo-campo">Seleção de Imagem do Produto:</label>
                    <input type="file" name="selImagem" id="selecao-imagem" multiple>
                    <input type="hidden" name="caminhoImagemPrincipal" id="caminho-imagem-principal"> <!-- Adicionando o campo oculto -->
                    <header id="cabecalho-imagem">
                        <h5>Imagem(ns) do Produto Salvas</h5>
                    </header>
<c:forEach var="imagem" items="${imagensProduto}">
    <div class="informacao-imagem">
        <!-- Formulário para excluir a imagem -->
        <form action="/excluirImagem" method="POST" enctype="multipart/form-data">

            <input type="hidden" name="caminhoImagem" value="${imagem.diretorio}/${imagem.nome}">
<button type="submit" onclick="excluirImagem('${imagem.nome}')">Excluir</button>

        </form>
        <!-- Imagem do Produto -->
        <img src="${imagem.diretorio}/${imagem.nome}" alt="Imagem do Produto" style="max-width: 100px; max-height: 100px;">
    </div>
</c:forEach>

<h5>Imagem(ns) Adicionadas</h5>
<img src="${imagemTempProdutoDir}" alt="Imagem do Produto" style="width: 100px; height: 100px;">
<c:forEach var="imagem" items="${imagensTempProduto}">
    <div class="informacao-imagem">
        <!-- Formulário para excluir a   imagem -->
        <form action="/excluirImagem" method="POST" enctype="multipart/form-data">

            <input type="hidden" name="caminhoImagemAdcionadas" value="${imagem.diretorio}/${imagem.nome}">
<button type="submit" onclick="excluirImagem('${imagem.nome}')">Excluir</button>

        </form>
        <!-- Imagem do Produto -->
        <img src="${imagem.diretorio}/${imagem.nome}" alt="Imagem do Produto" style="max-width: 100px; max-height: 100px;">
    </div>
</c:forEach>


                <!-- <p id="total-imagens">Total de imagens anexadas: 0</p> -->

         <c:forEach var="imagem" items="${img}">
             <img src="${imagem}" alt="Imagem do Produto">
         </c:forEach>

                <button type="submit" id="btn-salvar">Salvar</button>
                <button type="button" id="btn-cancelar" onclick="window.location.href='/listarProdutos'">Cancelar</button>
            </form>

        </div>
    </section>
</article>
</body>

<script src="cadastrarProduto.js"></script>

</html>