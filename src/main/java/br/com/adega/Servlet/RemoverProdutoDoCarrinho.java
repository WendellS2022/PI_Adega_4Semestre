package br.com.adega.Servlet;

import br.com.adega.DAO.CarrinhoDAO;
import br.com.adega.DAO.ClienteDAO;
import br.com.adega.Model.Carrinho;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/removerProdutoDoCarrinho")
public class RemoverProdutoDoCarrinho extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String produtoId = request.getParameter("produtoId");
        List<Carrinho> produtosCarrinho = (List<Carrinho>) session.getAttribute("carrinho");
        String clienteLogado = (String) session.getAttribute("clienteLogado");


        if (clienteLogado != null && clienteLogado != "") {
            boolean sucesso = CarrinhoDAO.removerProdutoDoCarrinho(Integer.parseInt(produtoId));
            if(sucesso){
                int idCliente = ClienteDAO.buscarIdClienteEmail(clienteLogado);
                produtosCarrinho = CarrinhoDAO.obterProdutosCarrinhoPorIdCliente(idCliente);

                }

        } else {
            for (Carrinho prodCarrinho : produtosCarrinho) {
                if (prodCarrinho.getProduto().getCodProduto() == Integer.parseInt(produtoId)) {
                    produtosCarrinho.remove(prodCarrinho);

                    break;
                }
            }
        }
        session.setAttribute("clienteLogado", clienteLogado);
        session.setAttribute("carrinho", produtosCarrinho);
        response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");
    }
}
