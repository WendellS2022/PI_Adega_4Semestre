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
        String clienteLogado = request.getParameter("clienteLogado");
        int codProduto= Integer.parseInt(request.getParameter("codProduto"));
        boolean backoffice = Boolean.parseBoolean((request.getParameter("isBackOffice")));

        Produto produto = ProdutoDAO.ObterProdutoPorId(codProduto);

        if (produto != null) {

            List<Imagem> imagens = ProdutoDAO.obterImagensPorProdutoId(produto.getCodProduto());

            request.setAttribute("clienteLogado", clienteLogado);
            request.setAttribute("produto", produto);
            request.setAttribute("imagens", imagens);
            request.setAttribute( "isBackOffice", backoffice);

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/VisualizarProduto.jsp");
        dispatcher.forward(request, response);
    }
}
