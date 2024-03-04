package br.com.adega.Servlet;

import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaLogin.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User usuario = new User();

        String email = request.getParameter("email");
        String senha = request.getParameter("password");

        usuario = UsuarioDAO.verificarCredenciais(email, senha);

        if (usuario != null) {
            if(usuario.getGrupo() == 1){

            }
            response.sendRedirect("Card.jsp");
        } else {
            // Adiciona mensagem de credenciais inválidas à requisição
            request.setAttribute("mensagem", "Credenciais inválidas");
            // Limpa os campos para uma nova inserção
            request.setAttribute("email", "");
            request.setAttribute("password", "");
            // Redireciona de volta para a página de login
            RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaLogin.jsp");
            dispatcher.forward(request, response);
        }
    }
}
