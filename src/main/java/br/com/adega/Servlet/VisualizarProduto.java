package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/visualizarProduto")
public class VisualizarProduto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        int codProdutoParam = Integer.parseInt(request.getParameter("id"));

        Produto produtos = ProdutoDAO.ObterProdutoPorId(codProdutoParam);

        request.setAttribute("produto", produtos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/VisualizarProduto.jsp");
        dispatcher.forward(request, response);
    }
}