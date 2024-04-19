package br.com.adega.Servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/sair")
public class Sair extends HttpServlet {
    private static final Map<String, HttpSession> emailToSessionMap = new HashMap<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String clienteLogado = request.getParameter("email");
        HttpSession session = request.getSession(true);
        session.setAttribute("clienteLogado", clienteLogado);
        emailToSessionMap.put(clienteLogado, session);

        if (clienteLogado != null && emailToSessionMap.containsKey(clienteLogado)) {
            session = emailToSessionMap.get(clienteLogado);
            session.invalidate(); // Invalida a sessão existente
            emailToSessionMap.remove(clienteLogado);

            // Redireciona para a página de login com o parâmetro cliente=true
            response.sendRedirect(request.getContextPath() + "/TelaProdutos");
        }
    }
}

