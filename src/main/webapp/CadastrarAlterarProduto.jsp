<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.adega.Model.Produto" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

            <form action="/CadastrarProduto" method="POST">
                <div id="informacao-produto">
                    <input type="hidden" name="codProduto" value="${produto.codProduto}">

                    <label for="nomeProduto" class="titulo-campo">Nome do Produto:</label>
                    <input type="text" name="nomeProduto" id="nome-produto" placeholder="Nome do produto" required
                        value="${produto != null ? produto.nomeProduto : ''}">

                    <label for="dscDetalhadaProduto" class="titulo-campo">Descrição Detalhada do Produto:</label>
                    <input type="text" name="dscDetalhadaProduto" id="descricao-produto" placeholder="Descrição detalhada do produto" required
                        value="${produto != null ? produto.dscDetalhadaProduto : ''}">

                    <label for="avaliacaoProduto" class="titulo-campo">Avaliação do Produto:</label>
                    <input type="number" name="avaliacaoProduto" id="avaliacao-produto" required
                        value="${produto != null ? produto.avaliacaoProduto : ''}">

                    <label for="vlrVendaProduto" class="titulo-campo">Preço do Produto:</label>
                    <input type="text" name="vlrVendaProduto" id="preco-produto" required
                        value="${produto != null ? produto.vlrVendaProduto : ''}">

                    <label for="qtdEstoque" class="titulo-campo">Quantidade em Estoque:</label>
                    <input type="number" name="qtdEstoque" id="estoque-produto" required
                        value="${produto != null ? produto.qtdEstoque : ''}">
                </div>
            </form>

            <div id="imagens-produto">
                <label for="selImagem" class="titulo-campo">Seleção de Imagem do Produto:</label>
                <input type="file" name="selImagem" id="selecao-imagem" required>
                <header id="cabecalho-imagem">
                    <h5>Imagem(ns) do Produto</h5>
                </header>
            </div>

            <table id="tabela-imagem">
                <tbody>
                    <c:forEach var="imagem" items="${imagens}">
                        <tr>
                            <td>
                                <div class="informação-imagem">
                                    <form action="/excluir-imagem" method="DELETE">
                                        <button type="submit" id="btn-excluir">X</button>
                                    </form>
                                    ${imagem.figura}
                                    ${imagem.dscNome}
                                    <input type="checkbox" name="codQualificacao" id="qualificacao-produto" placeholder="Nome do produto" required
                                     value="${imagem.codQualificacao}">
                                </div>
                            </td>
                            <!-- Adicione mais colunas se necessário -->
                        </tr>
                        <!-- Adicione mais linhas se necessário -->
                    </c:forEach>
                </tbody>
            </table>

            <div id="botoes">
                <form action="/cadastrar-produto" method="POST">
                    <button type="submit" id="btn-salvar">Salvar</button>
                </form>
                <form action="/listar-produto" method="GET">
                    <button type="submit" id="btn-cancelar">Cancelar</button>
                </form>
            </div>
        </section>
    </article>
</body>

<script>
    var selectedIndex = document.getElementById('grupo-usuario').selectedIndex;

    // Impede que o valor seja alterado se o campo estiver desabilitado
    document.getElementById('grupo-usuario').addEventListener('change', function() {
        if (this.disabled) {
            this.selectedIndex = selectedIndex;
        } else {
            selectedIndex = this.selectedIndex;
        }
    }
    );
</script>
</html>
