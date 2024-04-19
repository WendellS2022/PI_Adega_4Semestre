<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="ListarUsuario.css">
    <title>Login</title>
</head>

<!-- Aqui fica o cabeçalho! -->
<header id="cabecalho-site">
    <h1 id="identificacao-site">e-Commerce e-Devs</h1>
</header>

<body>
<input type="hidden" name="statusAtualizado" value="${statusAtualizado}">
    <article id="area-lista-usuario">
        <section id="caixa-lista-usuario">
            <div id="cabecalho-lista-usuario">
                <h2>Lista de Usuários</h2>
           </div>
            <div id="info-selecoes">
              <form action="/listar" method="GET">
                  <input type="text" name="nome-pesquisa" placeholder="Nome do Usuário" id="nome-pesquisa">
                  <button type="submit" id="btn-procurar">Procurar</button>
              </form>
                <form action="/cadastrar" method="Get">
                    <button type="submit" id="btn-cadastrar">Novo Usuário</button>
                </form>
            </div>

            <table id="tabela-usuario">
                <thead id="cabecalho-tabela-usuario">
                    <tr>
                        <th class="nome-usuario">Nome</th>
                        <th class="email-usuario">E-mail</th>
                        <th class="grupo-usuario">Grupo</th>
                        <th class="situacao-usuario">Status</th>
                        <th class="acao-usuario" colspan="2">Ação</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="itens" items="${usuarios}">
                    <tr>
                        <td value="${itens.usuarioId}">${itens.nome}</td>
                        <td>${itens.email}</td>
                        <td>${itens.grupo == 1 ? 'Administrador' : itens.grupo == 2 ? 'Estoquista' : ''}</td>
                        <td>${itens.situacao ? 'Ativo' : 'Inativo'}</td>
                        <td class="alterar-dados-usuario">
                            <a href="/alterarUsuario?usuarioId=${itens.usuarioId}">Alterar</a>
                        </td>
                        <td class="alterar-situacao-usuario">
                            <c:choose>
                                <c:when test="${usuarioLogado == itens.email}">
                                     <a href="#" onclick="return false;">${itens.situacao ? 'Inativar' : 'Ativar'}</a>
                                 </c:when>
                                <c:when test="${usuarioLogado && itens.grupo == 1}">
                                    <!-- Remova esta linha -->
                                </c:when>
                                <c:otherwise>
                                    <a href="#" onclick="confirmarAlteracao(${itens.usuarioId})">${itens.situacao ? 'Inativar' : 'Ativar'}</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                          </td>
                      </tr>
                </tbody>
            </table>
        </section>
    </article>
    <script>
      function confirmarAlteracao(usuarioId) {

          var confirmacao = confirm(`Deseja realmente alterar o usuário?`);
          if (confirmacao) {
              var form = document.createElement('form');
              form.method = 'POST'; // Usando POST para enviar o método PUT como um parâmetro
              form.action = `/alterarUsuario`;

              var usuarioIdInput = document.createElement('input');
              usuarioIdInput.type = 'hidden';
              usuarioIdInput.name = 'usuarioId';
              usuarioIdInput.value = usuarioId;


              var methodInput = document.createElement('input');
              methodInput.type = 'hidden';
              methodInput.name = '_method';
              methodInput.value = 'PUT'; // Enviando o método PUT como um parâmetro na solicitação POST

              form.appendChild(usuarioIdInput);
              form.appendChild(methodInput);
              document.body.appendChild(form);
              form.submit();

              setTimeout(function() {
                                      window.location.href = "/listar";
              }, 1000); // Redireciona após 1 segundos (1000 milissegundos)
          }
      }


    </script>
</body>
</html>





