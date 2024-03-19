package br.com.adega.Servlet;

import br.com.adega.Autenticacao.AutenticacaoService;
import br.com.adega.Model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {

    // Mapa para rastrear emails logados
    private static final Map<String, HttpSession> emailToSessionMap = new HashMap<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Encaminha para a página de login
        RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaLogin.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User autenticacao;
        AutenticacaoService autenticacaoService = new AutenticacaoService();

        String email = request.getParameter("email");
        String senha = request.getParameter("password");


        autenticacao = autenticacaoService.autenticarUsuario(email, senha);
        try {
            if (autenticacao.getUserId() > 0) {
                if (emailToSessionMap.containsKey(email)) {
                    response.sendRedirect("TelaLogin.jsp?mensagem=Você já está logado. Por favor, faça logout antes de tentar novamente.");
                    return;
                }

                if (!autenticacao.isSituacao()) {
                    response.sendRedirect("TelaLogin.jsp?mensagem=Usuário inativo!");
                    return;
                }

                HttpSession session = request.getSession(true);
                session.setAttribute("usuarioLogado", email);
                emailToSessionMap.put(email, session);
                request.setAttribute("grupo", autenticacao.getGrupo());

                RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("TelaLogin.jsp?mensagem=Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("TelaLogin.jsp?mensagem=Credenciais inválidas. Por favor, verifique seu e-mail e senha e tente novamente.");
        }
    }
}

