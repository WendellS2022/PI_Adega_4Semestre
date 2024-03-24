<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="ListarProdutos.css" rel="stylesheet">
    <title>Lista de Produto</title>

    <!-- Adiciona jQuery para AJAX -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
    <header id="cabecalho-site">
        <h1 id="identificacao-site">e-Commerce e-Devs - Área de BackOffice</h1>
    </header>

    <article id="area-lista-produto">
        <section id="caixa-lista-produto">
            <header id="cabecalho-lista-produto">
                <h2>Lista de Produto</h2>
            </header>
            <div class="info-produtos">
                <div id="info-selecoes">
                    <form action="/listarProdutos" method="GET">
                        <input type="text" placeholder="Nome do Produto" id="nome-pesquisa" name="search">
                        <button id="btn-procurar" type="submit">Procurar</button>
                    </form>
                    <c:choose>
                        <c:when test="${grupo == 1}">
                            <form action="/cadastrarProduto" method="GET">
                                <button id="btn-cadastrar">Novo Produto</button>
                            </form>
                        </c:when>
                    </c:choose>
                </div>
                <table id="tabela-produto">
                    <thead id="cabecalho-tabela-produto">
                        <tr>
                            <th class="codigo-produto">Código</th>
                            <th class="nome-produto">Nome do Produto</th>
                            <th class="estoque-produto">Qtd. Estoque</th>
                            <th class="preco-produto">Preço de Venda</th>
                            <th class="situacao-produto">Situação</th>
                            <th class="acao-produto" colspan="3">Ação</th>
                        </tr>
                    </thead>
                    <tbody id="detalhe-tabela-produto">
                        <c:forEach var="produto" items="${produtos}">
                            <tr>
                                <td class="codigo-produto">${produto.codProduto}</td>
                                <td class="nome-produto">${produto.nomeProduto}</td>
                                <td class="estoque-produto">${produto.qtdEstoque}</td>
                                <td class="preco-produto">${produto.vlrVendaProduto}</td>
                                <td class="situacao-produto">${produto.situacaoProduto ? 'Ativo' : 'Inativo'}</td>
                                <td class="alterar-dados-produto">
                                    <a href="/alterarProduto?id=${produto.codProduto}">Alterar</a>
                                </td>
                                <c:choose>
                                    <c:when test="${grupo == 1}">
                                        <td class="alterar-situacao-produto">
                                            <a href="#" onclick="confirmarAlteracao('${produto.codProduto}', '${produto.situacaoProduto ? 'Inativar' : 'Reativar'}', '${page}')">
                                                ${produto.situacaoProduto ? 'Inativar' : 'Reativar'}
                                            </a>
                                        </td>
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${grupo == 1}">
                                        <td class="visualizar-produto">
                                            <form id="visualizarForm" action="/visualizarProduto" method="GET">
                                                <input type="hidden" name="id" value="${produto.codProduto}">
                                                <a href="#" onclick="document.getElementById('visualizarForm').submit(); return false;">Visualizar</a>
                                            </form>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <section id="controle-paginacao">
                    <div class="paginacao">
                        <div class="primeira-pagina">
                            <form action="listarProdutos" method="get">
                                <input type="hidden" name="page" value="${page}">
                                <button type="submit" name="action" value="firstPage">&lt;&lt;</button>
                            </form>
                        </div>
                        <div class="pagina-anterior">
                            <form action="listarProdutos" method="get">
                                <input type="hidden" name="page" value="${page}">
                                <button type="submit" name="action" value="prevPage">&lt;</button>
                            </form>
                        </div>
                        <div class="numero-pagina">
                            <div type="hidden" value="${page}" >${page}</div>
                        </div>
                        <div class="proxima-pagina">
                            <form action="listarProdutos" method="GET">
                                <input type="hidden" name="page" value="${page}">
                                <button type="submit" name="action" value="nextPage">&gt;</button>
                            </form>
                        </div>
                        <div class="ultima-pagina">
                            <form action="listarProdutos" method="get">
                                <input type="hidden" name="page" value="${page}">
                                <button type="submit" name="action" value="lastPage">&gt;&gt;</button>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </section>
    </article>

    <script>
        function confirmarAlteracao(codProduto, acao, page) {
            var confirmacao = confirm(`Deseja realmente ${acao} o produto?`);
            if (confirmacao) {
                var form = document.createElement('form');
                form.method = 'POST';
                form.action = '/alterarProduto';

                var codProdutoInput = document.createElement('input');
                codProdutoInput.type = 'hidden';
                codProdutoInput.name = 'codProduto';
                codProdutoInput.value = codProduto;

                var acaoInput = document.createElement('input');
                acaoInput.type = 'hidden';
                acaoInput.name = 'acao';
                acaoInput.value = acao;

                var pageInput = document.createElement('input'); // Adiciona um input hidden para a variável page
                pageInput.type = 'hidden';
                pageInput.name = 'page';
                pageInput.value = page;

                form.appendChild(codProdutoInput);
            form.appendChild(acaoInput);
            form.appendChild(pageInput); // Adiciona o input hidden para a variável page ao formulário
            document.body.appendChild(form);
            form.submit();

            setTimeout(function() {
                window.location.href = "/listarProdutos"; // Redireciona após 1 segundo (1000 milissegundos)
            }, 1000);
        }
    }
</script>
</body>
</html>
