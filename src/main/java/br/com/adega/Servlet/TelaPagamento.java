package br.com.adega.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pagamento")
public class TelaPagamento extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String clienteLogado = (String) session.getAttribute("clienteLogado");

        if (clienteLogado == null || clienteLogado.isEmpty()) {
            // Se o cliente não estiver logado, redirecione para a página de login
            response.sendRedirect(request.getContextPath() + "/login?cliente=true");
        } else {
            // Se o cliente estiver logado, redirecione para a página do carrinho
            response.sendRedirect(request.getContextPath() + "/TelaPagamento.jsp");
        }
    }
}
