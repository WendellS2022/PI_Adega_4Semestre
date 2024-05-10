package br.com.adega.Servlet;

import br.com.adega.DAO.ClienteDAO;
import br.com.adega.DAO.EnderecoDAO;
import br.com.adega.Model.Carrinho;
import br.com.adega.Model.Cliente;
import br.com.adega.Model.Endereco;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/pagamento")
public class TelaPagamento extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        List<Carrinho> produtosCarrinho = new ArrayList<>();
        Endereco enderecoPadrao = new Endereco();

        String clienteLogado = (String) session.getAttribute("clienteLogado");

        if (clienteLogado == null || clienteLogado.isEmpty()) {
            // Se o cliente não estiver logado, redirecione para a página de login
            response.sendRedirect(request.getContextPath() + "/login?cliente=true");
        } else {
            produtosCarrinho = (List<Carrinho>) session.getAttribute("carrinho");


            String idCliente = String.valueOf(ClienteDAO.buscarIdClienteEmail(clienteLogado));
            enderecoPadrao = EnderecoDAO.obterEnderecoPadraoPorIdCliente(idCliente);
            String frete = request.getParameter("frete");
            String subtotal = request.getParameter("subtotal");

            session.setAttribute("carrinho", produtosCarrinho);
            request.setAttribute("clienteLogado", clienteLogado);
            request.setAttribute("frete", frete);
            request.setAttribute("subtotal", subtotal);
            request.setAttribute("endereco", enderecoPadrao);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaPagamento.jsp");
            dispatcher.forward(request, response);        }
    }
}