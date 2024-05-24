package br.com.adega.Servlet;

import javax.servlet.RequestDispatcher;
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

import br.com.adega.DAO.CarrinhoDAO;
import br.com.adega.DAO.ClienteDAO;
import br.com.adega.Model.Produto;
import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Imagem;



@WebServlet("/TelaProdutos")
public class TelaDeProdutos extends HttpServlet {
    private static final Map<String, HttpSession> emailToSessionMap = new HashMap<>();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clienteLogado = request.getParameter("clienteLogado");
        List<Produto> produtos = new ArrayList<>();
        Map<Integer, List<Imagem>> imagensPorProduto = new HashMap<>();

        HttpSession session = request.getSession(true);
        session.setAttribute("clienteLogado", clienteLogado);
        request.setAttribute("clienteLogado", clienteLogado);
        emailToSessionMap.put(clienteLogado, session);

        String nomeProduto = request.getParameter("procurar");

        if (nomeProduto != null && !nomeProduto.isEmpty()) {
            produtos = ProdutoDAO.PesquisarProdutosPorNome(nomeProduto);

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
            request.setAttribute("clienteLogado", clienteLogado);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaDeProdutos.jsp");
            dispatcher.forward(request, response);

            return;
        }
        produtos = ProdutoDAO.ObterTodosOsProdutos();


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
        request.setAttribute("clienteLogado", clienteLogado);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaDeProdutos.jsp");
        dispatcher.forward(request, response);
    }
}