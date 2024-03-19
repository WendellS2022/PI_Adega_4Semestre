<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.adega.Model.Produto" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <%
                String mensagem = (String) request.getAttribute("mensagem");
                if (mensagem != null) {
                    %>
                        <p><%= mensagem %></p>
                    <%
                }
            %>
        
            <section class="area-produto">
                <div class="area-imagem">
                    <ul class="controle-imagem">
                        <li class="imagem" data-active>
                            <img src="/src/main/resources/imagens/imagem01.avif"
                                alt=""
                            />
                        </li>
                        <li class="imagem">
                            <img src="/src/main/resources/imagens/imagem02.avif"
                                alt=""
                            />
                        </li>
                        <li class="imagem">
                            <img src="/src/main/resources/imagens/imagem03.avif"
                                alt=""
                            />
                        </li>
                    </ul>

                    <button class="previous-button" data-change-slide-button="previous">&lt;</button>
                    <button class="next-button" data-change-slide-button="next">&gt;</button>
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
</body>
</html>