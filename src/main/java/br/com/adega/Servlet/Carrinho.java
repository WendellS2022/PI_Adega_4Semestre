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

        List<Produto> produtos;


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

            List<br.com.adega.Model.Carrinho> produtosCarrinho;
            int idCliente = ClienteDAO.buscarIdClienteEmail(clienteLogado);
            produtosCarrinho = CarrinhoDAO.obterProdutosCarrinhoPorIdCliente(idCliente);

            List<Integer> idsProdutosCarrinho = new ArrayList<>();
            produtosCarrinho.forEach(produtoCarrinho -> idsProdutosCarrinho.add(produtoCarrinho.getProduto().getCodProduto()));


            session.setAttribute("carrinho", produtosCarrinho);

            response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String clienteLogado = (String) session.getAttribute("clienteLogado");
        List<br.com.adega.Model.Carrinho> produtosCarrinho;
        br.com.adega.Model.Carrinho produtoCarrinho = new br.com.adega.Model.Carrinho();

        if (clienteLogado == null || clienteLogado.isEmpty()) {

            produtosCarrinho = (List<br.com.adega.Model.Carrinho>) session.getAttribute("carrinho");

            if (produtosCarrinho == null) {
                produtosCarrinho = new ArrayList<>();
                session.setAttribute("carrinho", produtosCarrinho);

            }
            int produtoId = Integer.parseInt(request.getParameter("codProduto"));
            Produto produto = ProdutoDAO.ObterProdutoPorId(produtoId);


            if (produtosCarrinho.size() > 0) {
                for (br.com.adega.Model.Carrinho carrinho : produtosCarrinho) {
                    int produtoIdCarrinho = carrinho.getProduto().getCodProduto(); // Obtém o produto do carrinho

                    if (produtoIdCarrinho == produtoId) {
                        int quantidadeComprada = carrinho.getQuantidadeComprada();
                        quantidadeComprada += 1; // Incrementa a quantidade comprada
                        carrinho.setQuantidadeComprada(quantidadeComprada);

                        session.setAttribute("carrinho", produtosCarrinho);

                        response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");
                        return;
                    } else {
                        produtoCarrinho.setProduto(produto);
                        produtoCarrinho.setQuantidadeComprada(1);

                        produtosCarrinho.add(produtoCarrinho);

                        session.setAttribute("carrinho", produtosCarrinho);

                        response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");
                        return;
                    }
                }
            } else {
                produtoCarrinho.setProduto(produto);
                produtoCarrinho.setQuantidadeComprada(1);

                produtosCarrinho.add(produtoCarrinho);

                session.setAttribute("carrinho", produtosCarrinho);

                response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");
            }

        } else {
            produtosCarrinho = (List<br.com.adega.Model.Carrinho>) session.getAttribute("carrinho");


            if (produtosCarrinho == null) {
                produtosCarrinho = new ArrayList<>();
                session.setAttribute("carrinho", produtosCarrinho);

            }

            int produtoId = Integer.parseInt(request.getParameter("codProduto"));
            Produto produto = ProdutoDAO.ObterProdutoPorId(produtoId);

            // Adicionar o produto à lista de produtos
            produtoCarrinho.setProduto(produto);
            produtosCarrinho.add(produtoCarrinho);



            int idCliente = ClienteDAO.buscarIdClienteEmail(clienteLogado);

            CarrinhoDAO.inserirProdutosCarrinho(produtosCarrinho, idCliente);

            produtosCarrinho = CarrinhoDAO.obterProdutosCarrinhoPorIdCliente(idCliente);

            session.setAttribute("carrinho", produtosCarrinho);

            request.setAttribute("clienteLogado", clienteLogado);
            response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");

        }
    }
}





