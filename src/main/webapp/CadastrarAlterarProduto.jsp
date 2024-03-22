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

            <form action="/cadastrarProduto?id=${produto.codProduto}" method="POST" enctype="multipart/form-data">
                <div id="informacao-produto">
                    <input type="hidden" name="codProduto" value="${produto != null ? produto.codProduto : ''}">
                    <input type="hidden" name="situacao" value="${produto != null ? produto.situacaoProduto : ''}">

                    <label for="nomeProduto" class="titulo-campo">Nome do Produto:</label>
                    <input type="text" name="nomeProduto" id="nome-Produto" placeholder="Nome do produto" required maxlength="200" value="${produto != null ? produto.nomeProduto : ''}" ${grupo == 1 ? '' : 'readonly'}>

                    <label for="dscDetalhadaProduto" class="titulo-campo">Descrição Detalhada do Produto:</label>
                    <input type="text" name="dscDetalhadaProduto" id="dsc-Produto" placeholder="Descrição detalhada do produto" required maxlength="2000" value="${produto != null ? produto.dscDetalhadaProduto : ''}" ${grupo == 1 ? '' : 'readonly'}>

                    <label for="avaliacaoProduto" class="titulo-campo">Avaliação do Produto:</label>
                    <input type="number" name="avaliacaoProduto" id="avaliacao-Produto" required min="0" max="5" step="0.5" value="${produto != null ? produto.avaliacaoProduto : ''}" ${grupo == 1 ? '' : 'readonly'}>

                    <label for="vlrVendaProduto" class="titulo-campo">Preço do Produto:</label>
                    <input type="number" name="vlrVendaProduto" id="vlr-VendaProduto" required value="${produto != null ? produto.vlrVendaProduto : ''}" ${grupo == 1 ? '' : 'readonly'}>

                    <label for="qtdEstoque" class="titulo-campo">Quantidade em Estoque:</label>
                    <input type="number" name="qtdEstoque" id="qtd-Estoque" required min="0" max="99999" step="1" value="${produto != null ? produto.qtdEstoque : ''}">
                </div>

                <!-- Seção de imagens do produto -->
                <div id="imagens-produto">
                    <label for="selImagem" class="titulo-campo">Seleção de Imagem do Produto:</label>
                    <input type="file" name="selImagem" id="selecao-imagem" required multiple>
                    <input type="hidden" name="caminhoImagemPrincipal" id="caminho-imagem-principal"> <!-- Adicionando o campo oculto -->
                    <header id="cabecalho-imagem">
                        <h5>Imagem(ns) do Produto</h5>
                    </header>

                    <!-- Iterar sobre as imagens do produto -->
                    <c:forEach var="imagem" items="${imagensProduto}">
                        <div class="informacao-imagem">
                            <!-- Adicione um botão para excluir a imagem, se necessário -->
                            <!-- Adicione uma lógica para definir a imagem principal, se necessário -->
                            <img src="${imagem.diretorio}/${imagem.nome}.${imagem.extensao}" alt="Imagem do Produto" style="max-width: 100px; max-height: 100px;">
                        </div>
                    </c:forEach>
                </div>


                <!-- <p id="total-imagens">Total de imagens anexadas: 0</p> -->

                <table id="tabela-imagem">
                    <tbody id="lista-imagens">
                        <!-- Esta parte será preenchida dinamicamente com JavaScript -->
                    </tbody>
                </table>

                <button type="submit" id="btn-salvar">Salvar</button>
                <button type="button" id="btn-cancelar" onclick="window.location.href='/listarProdutos'">Cancelar</button>
            </form>

        </div>
    </section>
</article>
</body>

<script src="cadastrarProduto.js"></script>

</html>
