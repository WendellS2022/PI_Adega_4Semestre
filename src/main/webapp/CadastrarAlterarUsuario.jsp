<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CadastrarAlterarUsuario.css">

    <title>Cadastrar ou Alterar Usuário</title>
</head>

<!-- Aqui fica o cabeçalho! -->
<body>
<header id="cabecalho-site">
    <h1 id="identificacao-site">e-Commerce - Vamos Bebemorar!!!</h1>
</header>

<article id="area-usuario">
    <section id="caixa-usuario">
        <header id="cabecalho-usuario">
            <h2>Cadastrar ou Alterar Usuário</h2>
        </header>
       <form action="/cadastrar" method="POST">
           <div id="info-titulos">
               <label for="email" class="titulo-campo">E-mail:</label>
               <label for="nome" class="titulo-campo">Nome:</label>
               <label for="cpf" class="titulo-campo">CPF:</label>
               <label for="grupo" class="titulo-campo">Grupo:</label>
               <label for="senha" class="titulo-campo">Senha:</label>
               <label for="senha-2" class="titulo-campo">Confirmar Senha:</label>
           </div>
           <div id="info-campos">
               <input type="email" name="email" id="email-usuario" placeholder="E-mail do usuário" required>
               <input type="text" name="nome" id="nome-usuario" placeholder="Nome do usuário" required>
               <input type="text" name="cpf" id="cpf-usuario" placeholder="CPF do usuário" required>
               <select name="grupo" id="grupo-usuario" required>
                   <option value="1">Administrador</option>
                   <option value="2">Estoquista</option>
               </select>
               <input type="password" name="senha" class="senha-usuario" required>
               <input type="password" name="senha-2" class="senha-usuario" required>
           </div>
            <div id="botoes">

                  <button type="submit" id="btn-confirmar">Confirmar</button>
                </form>
                <button type="reset" id="btn-cancelar">Cancelar</button>
            </div>
    </section>
</article>
</body>
</html>
