package br.com.adega.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AlterarUsuario {
    @WebServlet("/alterarUsuario")
    public class AlterarUsuarioServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Obtenha o ID do usuário do parâmetro da solicitação
            int userId = Integer.parseInt(request.getParameter("userId"));

            // Aqui você pode fazer o que for necessário para alterar o usuário no banco de dados
            // Por exemplo, você pode chamar seu DAO para atualizar os dados do usuário

            // Redirecionar de volta para a página de listagem de usuários após a alteração
            response.sendRedirect(request.getContextPath() + "/listar");
        }
    }

}
