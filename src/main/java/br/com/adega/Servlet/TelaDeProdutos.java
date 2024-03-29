package br.com.adega.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adega.Model.Produto;
import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Imagem;

@WebServlet("/TelaProdutos")
public class TelaDeProdutos extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtendo a lista de todos os produtos
        List<Produto> produtos = ProdutoDAO.ObterTodosOsProdutos();

        // Criando um mapa para associar cada produto à sua lista de imagens
        Map<Integer, List<Imagem>> imagensPorProduto = new HashMap<>();

        // Para cada produto, obtendo a lista de imagens associadas
        for (Produto produto : produtos) {
            List<Imagem> imagens = ProdutoDAO.obterImagensPorProdutoId(produto.getCodProduto());
            imagensPorProduto.put(produto.getCodProduto(), imagens);
        }

        // Definindo a lista de produtos e o mapa de imagens como atributos para a requisição
        request.setAttribute("produtos", produtos);
        request.setAttribute("imagensPorProduto", imagensPorProduto);

        // Encaminhando para a página JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaDeProdutos.jsp");
        dispatcher.forward(request, response);
    }
}
