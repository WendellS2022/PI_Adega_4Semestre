package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Imagem;
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

        Produto produto = ProdutoDAO.ObterProdutoPorId(codProdutoParam);

        if (produto != null) {
            // Buscar imagens correspondentes ao produto
            List<Imagem> imagens = ProdutoDAO.obterImagensPorProdutoId(produto.getCodProduto());

            request.setAttribute("produto", produto);
            request.setAttribute("imagens", imagens);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/VisualizarProduto.jsp");
        dispatcher.forward(request, response);
    }
}
