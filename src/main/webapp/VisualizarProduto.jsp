<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.adega.Model.Produto" %>
<%@ page import="br.com.adega.Model.Imagem" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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

<!-- Aqui fica o cabeçalho com bootstrap-->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary vh-100">
    <div class="container">
      <!-- Logo -->
      <a class="navbar-brand h-100%" href="/TelaProdutos?clienteLogado=${clienteLogado}">
        <img src="LOGO1.png" alt="Logo" height="300">
      </a>


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

             <form action="/carrinho" method="POST">
               <div class="informacao-produto">
                   <input type="hidden" name="codProduto" value="${produto.codProduto}">
                   <input type="hidden" name="clienteLogado" value="${clienteLogado}">

                   <label for="nomeProduto" class="titulo-campo">Nome do Produto</label>
                   <textarea name="nomeProduto" id="nome-Produto" readonly>${produto != null ? produto.getNomeProduto() : ''}</textarea>

                   <label for="dscDetalhadaProduto" class="titulo-campo">Descrição Detalhada do Produto</label>
                   <textarea name="dscDetalhadaProduto" id="descricao-Produto" readonly>${produto != null ? produto.getDscDetalhadaProduto() : ''}</textarea>

                   <label for="avaliacaoProduto" class="titulo-campo">Avaliação do Produto</label>
                   <input type="number" name="avaliacaoProduto" id="avaliacao-Produto" readonly value="${produto != null ? produto.avaliacaoProduto : ''}">

                   <label for="vlrVendaProduto" class="titulo-campo">Preço do Produto</label>
                   <input type="number" name="vlrVendaProduto" id="vlr-VendaProduto" readonly value="${produto != null ? produto.vlrVendaProduto : ''}">

                   <!-- Botão Comprar dentro do loop for -->

                       <button class="btn btn-primary" style="margin-top: 25px;" onclick="comprarProduto(${produto.codProduto})">Comprar</button>
                   </form>
               </div>

                </div>
            </section>
        </section>
    </article>
<!-- Script JavaScript para enviar os dados do produto para a página do carrinho -->



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>


</body>
</html>
