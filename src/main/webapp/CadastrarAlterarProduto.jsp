<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro/Edição de Produto</title>
</head>
<body>

    <h1>Cadastro/Edição de Produto</h1>

    <form action="/cadastrarProduto" method="post">
        <input type="hidden" name="COD_PRODUTO" value="${produto.COD_PRODUTO}" />
        <label for="DSC_NOME">Nome do Produto:</label>
        <input type="text" id="DSC_NOME" name="DSC_NOME" value="${produto.DSC_NOME}" required />
        <br>
        <label for="DSC_DETALHADA">Descrição Detalhada:</label>
        <input type="text" id="DSC_DETALHADA" name="DSC_DETALHADA" value="${produto.DSC_DETALHADA}" />
        <br>
        <label for="COD_AVALIACAO">Avaliação:</label>
        <input type="text" id="COD_AVALIACAO" name="COD_AVALIACAO" value="${produto.COD_AVALIACAO}" />
        <br>
        <label for="COD_SITUACAO">Situação:</label>
        <input type="text" id="COD_SITUACAO" name="COD_SITUACAO" value="${produto.COD_SITUACAO}" />
        <br>
        <label for="QTD_ESTOQUE">Quantidade em Estoque:</label>
        <input type="number" id="QTD_ESTOQUE" name="QTD_ESTOQUE" value="${produto.QTD_ESTOQUE}" required />
        <br>
        <label for="VLR_VENDA">Valor:</label>
        <input type="text" id="VLR_VENDA" name="VLR_VENDA" value="${produto.VLR_VENDA}" required />
        <br>

        <button type="submit">Salvar</button>
    </form>

    <a href="/listarProdutos">Voltar para a Lista de Produtos</a>

    <%-- Adicione aqui a lógica para exibir mensagens de sucesso ou erro --%>

</body>
</html>
