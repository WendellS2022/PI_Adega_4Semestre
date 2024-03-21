<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.adega.Model.Produto" %>
<%@ page import="br.com.adega.Model.Imagem" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-	rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <link href="/VisualizarProduto.css" rel="stylesheet">
    <script src="/VisualizarProduto.js" async></script>
    <title>Visualizar Produto</title>
</head>

<!-- Aqui fica o cabeçalho -->
<header id="cabecalho-site">
    <h1 id="identificacao-site">e-Commerce e-Devs - Área de BackOffice</h1>
</header>

<body>
    <article id="area-vizualizacao">
        <section id="caixa-vizualizacao">
            <header id="cabecalho-vizualizacao">
                <h2>Vizualização do Produto</h2>
            </header>

            <div id="mensagem-erro" style="color: red;"></div>
            <% String mensagem = (String) request.getAttribute("mensagem");
                if (mensagem != null) { %>
                    <p><%= mensagem %></p>
            <% } %>

            <section class="area-produto">
                <div class="area-imagem">
                   <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                      <div class="carousel-inner">
                                             <% // Exibir as imagens correspondentes ao produto
                                                    List<Imagem> imagens = (List<Imagem>) request.getAttribute("imagens");
                                                    if (imagens != null) {
                                                        for (Imagem imagem : imagens) { %>
                                                                      <div class="carousel-item active">
                                                                              <img src="<%= imagem.getDiretorio() + "/" + imagem.getNome() %>" alt="<%= imagem.getNome() %>" class="d-block w-100"/>
                                                                      </div>
                                                <%     }
                                                    } %>
                      </div>
                      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                      </button>
                      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                      </button>
                    </div>
                </div>

                <div class="informacao-produto">
                    <label for="codProduto" class="titulo-campo">Código do Produto</label>
                    <input type="number" name="codProduto" id="codigo-Produto" readonly
                         value="${produto != null ? produto.codProduto : ''}">

                    <label for="nomeProduto" class="titulo-campo">Nome do Produto</label>
                    <textarea name="nomeProduto" id="nome-Produto" readonly
                              value="${produto != null ? produto.nomeProduto : ''}">
                    </textarea>

                    <label for="dscDetalhadaProduto" class="titulo-campo">Descrição Detalhada do Produto</label>
                    <textarea  name="dscDetalhadaProduto" id="descricao-Produto" readonly
                               value="${produto != null ? produto.dscDetalhadaProduto : ''}">
                    </textarea>

                    <label for="avaliacaoProduto" class="titulo-campo">Avaliação do Produto</label>
                    <input type="number" name="avaliacaoProduto" id="avaliacao-Produto" readonly
                           value="${produto != null ? produto.avaliacaoProduto : ''}">

                    <label for="situacaoProduto" class="titulo-campo">Situação do Produto</label>
                    <input type="text" name="situacaoProduto" id="situacao-Produto" readonly
                           value="${produto != null ? (produto.situacaoProduto ? 'Ativo' : 'Inativo') : ''}">

                    <label for="vlrVendaProduto" class="titulo-campo">Preço do Produto</label>
                    <input type="number" name="vlrVendaProduto" id="vlr-VendaProduto" readonly
                           value="${produto != null ? produto.vlrVendaProduto : ''}">

                    <label for="qtdEstoque" class="titulo-campo">Quantidade em Estoque</label>
                    <input type="number" name="qtdEstoque" id="qtd-EstoqueProduto" readonly
                           value="${produto != null ? produto.qtdEstoque : ''}">
                </div>
            </section>
        </section>
    </article>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
