<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" th:href="@{ListarUsuario.css}">

    <title>Login</title>
</head>
</head>

<!-- Aqui fica o cabeçalho! -->
<header id="cabecalho-site">
    <h1 id="identificacao-site">e-Commerce - Vamos Bebemorar!!!</h1>
</header>

<body>
    <article id="area-lista-usuario">
        <section id="caixa-lista-usuario">
            <header id="cabecalho-lista-usuario">
                <h2>Lista de Usuário</h2>
            </header>
            <form id="info-usuarios">
                <div id="info-selecoes">
                    <input type="text" placeholder="Nome do Usuário" id="nome-pesquisa">
                    <button id="btn-procurar">Procurar</button>
                    <button id="btn-cadastrar">Novo Usuário</button>
                </div>
                <table id="tabela-usuario">
                    <thead id="cabecalho-tabela-usuario">
                    <tr>
                        <th class="nome-usuario">Nome</th>
                        <th class="email-usuario">E-mail</th>
                        <th class="grupo-usuario">Grupo</th>
                        <th class="situacao-usuario">Situacao</th>
                        <th class="acao-usuario" colspan="2">Açao</th>
                    </tr>
                    </thead>
                    <tbody id=detalhe-tabela-usuario>
                    <tr>
                        <td class="nome-usuario" th:field="*{nome-usuario}"></td>
                        <td class="email-usuario" th:field="*{email-usuario}"></td>
                        <td class="grupo-usuario" th:field="*{grupo-usuario}"></td>
                        <td class="situacao-usuario" th:field="*{situacao-usuario}"></td>
                        <td class="alterar-dados-usuario">
                            <a href="xxxxxxxxxxxxxxxxxxxxx">Alterar</a>
                        </td>
                        <td class="alterar-situacao-usuario">
                            <a href="yyyyyyyyyyyyyyyyyyyyy">Ativar</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </section>
    </article>
</body>
</html>