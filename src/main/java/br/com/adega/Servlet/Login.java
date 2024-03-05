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

        if (autenticacao != null) {
            // Verifica se o email já está associado a uma sessão ativa
            if (emailToSessionMap.containsKey(email)) {
                // Se sim, impede o novo login e redireciona para a página de erro
                response.sendRedirect("login.jsp?erro=2");
                return; // Encerra o método
            }

            HttpSession session = request.getSession(true); // Cria uma nova sessão ou retorna a sessão existente
            session.setAttribute("usuarioLogado", email);
            emailToSessionMap.put(email, session); // Registra a nova sessão para o email
            request.setAttribute("grupo", autenticacao.getGrupo());

            // Redireciona para a página inicial
            RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
            dispatcher.forward(request, response);
        } else {
            // Se as credenciais estiverem incorretas, redireciona de volta para a página de login
            response.sendRedirect("login.jsp?erro=1");
        }
    }
}
