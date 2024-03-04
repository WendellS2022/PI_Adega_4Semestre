package br.com.adega.Servlet;

import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        User usuario;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String email = request.getParameter("email");
        String senha = request.getParameter("password");


        usuario = UsuarioDAO.verificarCredenciais(email);

        boolean senhaDesincrptografada = Boolean.parseBoolean(String.valueOf(encoder.matches(senha, usuario.getSenha())));

        if (usuario.getUserId() != 0 && senhaDesincrptografada == true){
            // Adiciona o atributo "grupo" à requisição para usar na página Home.jsp
            request.setAttribute("grupo", usuario.getGrupo());

            // Redireciona para a página Home.jsp
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