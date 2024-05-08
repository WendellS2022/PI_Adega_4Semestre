package br.com.adega.Servlet;

import br.com.adega.DAO.CarrinhoDAO;
import br.com.adega.DAO.ClienteDAO;
import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Produto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/carrinho")
public class Carrinho extends HttpServlet {
    private static final Map<String, HttpSession> emailToSessionMap = new HashMap<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clienteLogado = request.getParameter("clienteLogado");
        HttpSession session = request.getSession(true);
        session.setAttribute("clienteLogado", clienteLogado);
        request.setAttribute("clienteLogado", clienteLogado);
        emailToSessionMap.put(clienteLogado, session);

        List<Produto> produtos = new ArrayList<>();


        if (clienteLogado.isEmpty() || clienteLogado.isBlank()) {


            produtos = (List<Produto>) session.getAttribute("carrinho");

            if (produtos == null) {
                produtos = new ArrayList<>();
                session.setAttribute("carrinho", produtos);

                response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");
            } else {
                session.setAttribute("carrinho", produtos);

                response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");
            }


        } else {

            List<Integer> idsProdutos;
            int idCliente = ClienteDAO.buscarIdClienteEmail(clienteLogado);
            idsProdutos = CarrinhoDAO.obterProdutosCarrinhoPorIdCliente(idCliente);

            for (Integer idProduto : idsProdutos) {
                Produto produto = ProdutoDAO.ObterProdutoPorId(idProduto);
                produtos.add(produto);
            }

            session.setAttribute("carrinho", produtos);

            response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String clienteLogado = (String) session.getAttribute("clienteLogado");
        List<Produto> produtos;

        if (clienteLogado == null || clienteLogado.isEmpty()) {

            produtos = (List<Produto>) session.getAttribute("carrinho");

            if (produtos == null) {
                produtos = new ArrayList<>();
                session.setAttribute("carrinho", produtos);

            }
            int produtoId = Integer.parseInt(request.getParameter("codProduto"));
            Produto produto = ProdutoDAO.ObterProdutoPorId(produtoId);
            produtos.add(produto);

            session.setAttribute("carrinho", produtos);

            response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");

        } else {
            produtos = (List<Produto>) session.getAttribute("carrinho");


            if (produtos == null) {
                produtos = new ArrayList<>();
                session.setAttribute("carrinho", produtos);

            }

            int produtoId = Integer.parseInt(request.getParameter("codProduto"));
            Produto produto = ProdutoDAO.ObterProdutoPorId(produtoId);

            // Adicionar o produto à lista de produtos
            produtos.add(produto);
            session.setAttribute("carrinho", produtos);

            int idCliente = ClienteDAO.buscarIdClienteEmail(clienteLogado);

            CarrinhoDAO.inserirProdutosCarrinho(produtos, idCliente);

            request.setAttribute("clienteLogado", clienteLogado);
            response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");

        }
    }
}




