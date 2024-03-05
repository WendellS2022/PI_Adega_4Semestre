package br.com.adega.Servlet;

import br.com.adega.Autenticacao.AutenticacaoService;
import br.com.adega.Model.User;
import br.com.adega.Autenticacao.AutenticacaoService;
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
        User autenticacao;
        AutenticacaoService autenticacaoService = new AutenticacaoService();

        String email = request.getParameter("email");
        String senha = request.getParameter("password");

        autenticacao = autenticacaoService.autenticarUsuario(email, senha);

        if (autenticacao != null) {
            request.setAttribute("grupo", autenticacao.getGrupo());

            RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
            dispatcher.forward(request, response);
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
