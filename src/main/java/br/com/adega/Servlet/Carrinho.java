package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/carrinho")
    public class Carrinho extends HttpServlet {
    private static final Map<String, HttpSession> emailToSessionMap = new HashMap<>();
    private List<br.com.adega.Model.Carrinho>[] Session;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clienteLogado = request.getParameter("clienteLogado");
        HttpSession session = request.getSession(true);
        session.setAttribute("clienteLogado", clienteLogado);
        request.setAttribute("clienteLogado", clienteLogado);
        emailToSessionMap.put(clienteLogado, session);
        List<br.com.adega.Model.Carrinho> produtosCarrinho = null;


        if (clienteLogado.isEmpty() || clienteLogado.isBlank()) {


            produtosCarrinho = Session[Integer.parseInt("ListaProdutos")];


        } else {
            produtosCarrinho = ProdutoDAO.obterProdutosCarrinhoPorEmail(clienteLogado);

            request.setAttribute("produtos", produtosCarrinho);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Carrinho.jsp");
            dispatcher.forward(request, response);
        }


       request.setAttribute("produtos", produtosCarrinho);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Carrinho.jsp");
        dispatcher.forward(request, response);
    }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(true);
            String clienteLogado = request.getParameter("clienteLogado");
            session.setAttribute("clienteLogado", clienteLogado);

            List<br.com.adega.Model.Carrinho> produtos = null;
            Produto produto = null;
            int produtoId = Integer.parseInt(request.getParameter("codProduto"));

            if(clienteLogado == null){
                produtos = Session[Integer.parseInt("ListaProdutos")];
            }else{
                produto = ProdutoDAO.ObterProdutoPorId(produtoId);
            }

            String codProduto = request.getParameter("codProduto");
        }
    }


