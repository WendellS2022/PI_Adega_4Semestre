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


            String codProduto = request.getParameter("codProduto");

            Produto produto = ProdutoDAO.ObterProdutoPorId(Integer.parseInt(codProduto));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Carrinho.jsp");
            dispatcher.forward(request, response);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String codProduto = request.getParameter("codProduto");
        }
    }


