package br.com.adega.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adega.Model.Produto;
import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Imagem;

@WebServlet("/TelaProdutos")
public class TelaDeProdutos extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> produtos = ProdutoDAO.ObterTodosOsProdutos();
        Map<Integer, List<Imagem>> imagensPorProduto = new HashMap<>();

        for (Produto produto : produtos) {
            List<Imagem> imagens = ProdutoDAO.obterImagensPorProdutoId(produto.getCodProduto());

            // Verifica se existem imagens para o produto
            if (!imagens.isEmpty()) {
                // Define a primeira imagem como verdadeira
                imagens.get(0).setQualificacao(true);
                // Remove as qualificações das outras imagens
                for (int i = 1; i < imagens.size(); i++) {
                    imagens.get(i).setQualificacao(false);
                }
            }

            imagensPorProduto.put(produto.getCodProduto(), imagens);
        }


        request.setAttribute("imagensPorProduto", imagensPorProduto);
        request.setAttribute("produtos", produtos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaDeProdutos.jsp");
        dispatcher.forward(request, response);
    }
}
