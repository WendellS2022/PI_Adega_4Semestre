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
    <input type="email" name="email" placeholder="E-MAIL" required><br> <!-- Alterado para name="email" -->
    <input type="password" name="password" placeholder="SENHA" required><br> <!-- Alterado para name="password" -->
    <button type="submit" class="btn">Entrar</button>
    <button type="reset" class="btn">Cancelar</button>

    <% String mensagem = request.getParameter("mensagem");
           if (mensagem != null) { %>
              <p><%= mensagem %></p>
        <% } %>
</form>

</body>
</html>
