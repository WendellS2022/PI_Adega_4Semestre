<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.adega.Model.Produto" %>
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


                <form action="/cadastrarProduto?id=${produto.codProduto}" method="POST">
                    <div id="informacao-produto">
                        <input type="hidden" name="codProduto" value="${produto != null ? produto.codProduto : ''}">
                          <input type="hidden" name="situacao" value="${produto != null ? produto.situacaoProduto : ''}">

                        <label for="nomeProduto" class="titulo-campo">Nome do Produto:</label>
                        <input type="text" name="nomeProduto" id="nome-Produto" placeholder="Nome do produto" required
                            value="${produto != null ? produto.nomeProduto : ''}">

                          <label for="dscDetalhadaProduto" class="titulo-campo">Descrição Detalhada do Produto:</label>
                          <input type="text" name="dscDetalhadaProduto" id="dsc-Produto" placeholder="Descrição detalhada do produto" required
                                 value="${produto != null ? produto.dscDetalhadaProduto : ''}" ${grupo == 1 ? '' : 'readonly'}>

                        <label for="dscDetalhadaProduto" class="titulo-campo">Descrição Detalhada do Produto:</label>
                        <input type="text" name="dscDetalhadaProduto" id="dsc-Produto" placeholder="Descrição detalhada do produto" required maxlength="2000"
                        value="${produto != null ? produto.dscDetalhadaProduto : ''}">

                          <label for="avaliacaoProduto" class="titulo-campo">Avaliação do Produto:</label>
                          <input type="number" name="avaliacaoProduto" id="avaliacao-Produto" required
                                 value="${produto != null ? produto.avaliacaoProduto : ''}" ${grupo == 1 ? '' : 'readonly'}>
                        <label for="avaliacaoProduto" class="titulo-campo">Avaliação do Produto:</label>
                        <input type="number" name="avaliacaoProduto" id="avaliacao-Produto" required min="0" max="5" step="0.5"
                            value="${produto != null ? produto.avaliacaoProduto : ''}">

                        <label for="vlrVendaProduto" class="titulo-campo">Preço do Produto:</label>
                        <input type="text" name="vlrVendaProduto" id="vlr-VendaProduto" required
                            value="${produto != null ? produto.vlrVendaProduto : ''}">

                          <label for="qtdEstoque" class="titulo-campo">Quantidade em Estoque:</label>
                         <input type="number" name="qtdEstoque" id="qtd-Estoque" required
                                value="${produto != null ? produto.qtdEstoque : ''}">
                        <label for="qtdEstoque" class="titulo-campo">Quantidade em Estoque:</label>
                        <input type="number" name="qtdEstoque" id="qtd-Estoque" required min="0" max="99999" step="1"
                            value="${produto != null ? produto.qtdEstoque : ''}">


                    </div>

                    <input value="Enviar">



       <div id="imagens-produto">
           <label for="selImagem" class="titulo-campo">Seleção de Imagem do Produto:</label>
           <input type="file" name="selImagem" id="selecao-imagem" required multiple>
           <header id="cabecalho-imagem">
               <h5>Imagem(ns) do Produto</h5>
           </header>
       </div>

       <p id="total-imagens">Total de imagens anexadas: 0</p>

       <table id="tabela-imagem">
           <tbody id="lista-imagens">
               <!-- Esta parte será preenchida dinamicamente com JavaScript -->
           </tbody>
       </table>
                    <button type="submit" id="btn-salvar">Salvar</button>
                </form>
                <form action="/listarProdutos" method="GET">
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

    document.getElementById('selecao-imagem').addEventListener('change', function(event) {
           var imagens = event.target.files;
           var listaImagens = document.getElementById('lista-imagens');

           // Adiciona as novas imagens à lista existente
           for (var i = 0; i < imagens.length; i++) {
               var imagem = imagens[i];
               var tr = document.createElement('tr');
               var td = document.createElement('td');
               var div = document.createElement('div');
               div.className = 'informacao-imagem';
               div.innerHTML = `
                   <form action="/excluir-imagem" method="DELETE">
                       <button type="submit" class="btn-excluir">X</button>
                   </form>
                   <img src="${URL.createObjectURL(imagem)}" alt="Imagem do Produto">
                   <input type="checkbox" name="codQualificacao" class="qualificacao-produto" placeholder="Nome do produto" required>
               `;
               td.appendChild(div);
               tr.appendChild(td);
               listaImagens.appendChild(tr);
           }

           // Atualiza o total de imagens anexadas
           var totalAtual = parseInt(document.getElementById('total-imagens').textContent.split(' ')[4]); // Extrai o número atual de imagens anexadas
           var novoTotal = totalAtual + imagens.length;
           document.getElementById('total-imagens').textContent = 'Total de imagens anexadas: ' + novoTotal;
       });

                   String mensagem = (String) request.getAttribute("mensagem");
                   if (mensagem != null) {

                      <p><%= mensagem %></p>

                   }

</script>
</html>
