<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="TelaLogin.css">
</head>
<body>

<form action="/login" method="POST"> <!-- Alterado para method="POST" -->
      <input type="hidden" name="clienteLogado" value="${isCliente}">
      <input type="email" name="email" placeholder="E-MAIL" required><br> <!-- Alterado para name="email" -->
      <input type="password" name="password" placeholder="SENHA" required><br> <!-- Alterado para name="password" -->
      <button type="submit" class="btn">Entrar</button>
    <button type="button" class="btn" id="btn-cancelar" value="${isCliente}" onclick="cancelarLogin()">Cancelar</button>
<c:choose>
    <c:when test="${isBackOffice == true}">
     <%          String mensagem = (String) request.getAttribute("mensagem");
                                        if (mensagem != null) {
                                        %>
                                            <p style="color:red;"><%= mensagem %></p>
                                        <%
                                        }
                                        %>
                        </form>
                    </div>
    </c:when>
    <c:otherwise>
<div class="nav-item">
                        <form action="/CadastrarCliente" method="get">
                              <a href="/CadastrarCliente">Cadastre-se</a>
                              <%          String mensagem = (String) request.getAttribute("mensagem");
                                        if (mensagem != null) {
                                        %>
                                            <p style="color:red;"><%= mensagem %></p>
                                        <%
                                        }
                                        %>
                        </form>
                    </div>
    </c:otherwise>
</c:choose>


</form>

<script src="CancelarLogin.js"></script>

</body>
</html>
