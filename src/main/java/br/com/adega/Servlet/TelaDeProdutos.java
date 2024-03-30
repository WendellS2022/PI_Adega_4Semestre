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
            List<Imagem> imagensQualificadas = new ArrayList<>();

            for (Imagem imagem : imagens) {
                if (imagem.isQualificacao()) {
                    imagensQualificadas.add(imagem);
                }
            }

            imagensPorProduto.put(produto.getCodProduto(), imagensQualificadas);
        }

        request.setAttribute("imagensPorProduto", imagensPorProduto);
        request.setAttribute("produtos", produtos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaDeProdutos.jsp");
        dispatcher.forward(request, response);
    }
}
