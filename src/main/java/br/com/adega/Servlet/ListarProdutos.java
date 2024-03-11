package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/listarProdutos")
//public class ListarProdutos extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        List<Produto> produtos = ProdutoDAO.obterPaginaDeProdutos(1, 10); // Obtém a primeira página de produtos (máximo 10 por página)
//
//        request.setAttribute("produtos", produtos);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarProdutos.jsp");
//        dispatcher.forward(request, response);
//    }
//}
@WebServlet("/listarProdutos")
public class ListarProdutos extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parâmetro de pesquisa
        String search = request.getParameter("search");

        List<Produto> produtos;

        if (search != null && !search.isEmpty()) {
            // Se houver um parâmetro de pesquisa, chama o método de pesquisa por nome
            produtos = ProdutoDAO.pesquisarProdutosPorNome(search);
        } else {
            // Caso contrário, obtém a primeira página de produtos (máximo 10 por página)
            produtos = ProdutoDAO.obterPaginaDeProdutos(1, 10);
        }

        request.setAttribute("produtos", produtos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarProdutos.jsp");
        dispatcher.forward(request, response);
    }
}