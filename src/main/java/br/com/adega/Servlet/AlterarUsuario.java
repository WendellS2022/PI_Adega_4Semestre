package br.com.adega.Servlet;

import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/alterarUsuario")
public class AlterarUsuario extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verifica se o ID do usuário está presente nos parâmetros da requisição
        String userIdParam = request.getParameter("userId");
        if (userIdParam != null && !userIdParam.isEmpty()) {
            // Ação de alterar usuário
            int userId = Integer.parseInt(userIdParam);
            User user = UsuarioDAO.ObterUsuarioPorId(userId);

            // Define os atributos do usuário como atributos da requisição para serem acessíveis no JSP
            request.setAttribute("user", user);
            request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp").forward(request, response);
        } else {
            // Ação de cadastrar novo usuário
            request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp").forward(request, response);
        }
    }
}


