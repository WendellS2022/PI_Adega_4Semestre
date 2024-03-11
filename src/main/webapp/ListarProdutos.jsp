<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="ListarProduto.css" rel="stylesheet">
    <title>Lista de Produto</title>

    <!-- Adiciona jQuery para AJAX -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

    <script>
            $(document).ready(function() {
                function confirmarAlteracao(codProduto, situacaoProduto) {
                    var confirmacao = confirm("Deseja realmente " + (situacaoProduto === "inativar" ? "inativar" : "reativar") + " o produto?");
                    if (confirmacao) {
                        alterarStatusProduto(codProduto, situacaoProduto);
                    }
                }

                function alterarStatusProduto(codProduto, situacaoProduto) {
                    $.ajax({
                        type: "POST",
                        url: "AlterarProduto",
                        data: {
                            "COD_PRODUTO": codProduto,
                            "COD_SITUACAO": situacaoProduto
                        },
                        success: function(response) {
                            alert("Status alterado com sucesso!");
                            // Atualizar a tabela ou realizar outras ações necessárias após a alteração
                            // Pode ser necessário recarregar a página ou atualizar apenas a linha afetada
                            // Isso depende da estrutura da sua aplicação
                        },
                        error: function(error) {
                            alert("Erro ao alterar o status do produto. Verifique o console para mais informações.");
                            console.error(error);
                        }
                    });
                }

                $(".alterar-situacao-produto a").click(function(e) {
                    e.preventDefault();
                    var codProduto = $(this).data("codproduto");
                    var situacaoProduto = $(this).data("situacaoProduto");
                    confirmarAlteracao(codProduto, situacaoProduto);
                });
            });
        </script>
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
            <form id="info-produtos" action="/listarProdutos" method="GET">
                <div id="info-selecoes">
                    <input type="text" placeholder="Nome do Produto" id="nome-pesquisa" name="search">
                    <button id="btn-procurar" type="submit">Procurar</button>
                    <a href="CadastrarEditarProduto.jsp" id="btn-cadastrar">Novo Produto</a>
                </div>
                <table id="tabela-produto">
                    <thead id="cabecalho-tabela-produto">
                        <tr>
                            <th class="codigo-produto">Código do Produto</th>
                            <th class="nome-produto">Nome do Produto</th>
                            <th class="estoque-produto">Quantidade em Estoque</th>
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
                                    <a href="alterarProduto?id=${produto.codProduto}">Alterar</a>
                                </td>
                                <td class="alterar-situacao-produto">
                                    <c:choose>
                                        <c:when test="${produto.situacaoProduto}">
                                            <a href="alterarProduto?id=${produto.codProduto}&acao=inativar">Inativar</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="alterarProduto?id=${produto.codProduto}&acao=reativar">Reativar</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="visualizar-produto">
                                    <a href="visualizarProduto?id=${produto.codProduto}">Visualizar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <section id="controle-paginacao">
                    <div class="paginacao">
                        <div class="primeira-pagina">&lt&lt</div>
                        <div class="proxima-pagina">&lt</div>
                        <div class="numero-pagina">
                            <div>${currentPage}</div>
                        </div>
                        <div class="pagina-anterior">&gt</div>
                        <div class="ultima-pagina">&gt&gt</div>
                    </div>
                </section>
            </form>
        </section>
    </article>
</body>
</html>
