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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listarProdutos")
public class ListarProdutos extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pagina = 1; // Página padrão
        String pagInicial = request.getParameter("page");

        if (pagInicial != null)
            pagina = Integer.parseInt(pagInicial);

        List<Produto> produtos;
        String search = request.getParameter("search");

        if (search != null && !search.isEmpty()) {
            produtos = ProdutoDAO.PesquisarProdutosPorNome(search);

            request.setAttribute("produtos", produtos);
            request.getRequestDispatcher("/ListarProdutos.jsp").forward(request, response);

        } else {
            List<Produto> todosOsProdutos = ProdutoDAO.ObterTodosOsProdutos();

            List<List<Produto>> listaDeListasDeProdutos = new ArrayList<>();

            List<Produto> subListaDeProdutos = new ArrayList<>();

            for (Produto produto : todosOsProdutos) {
                subListaDeProdutos.add(produto);
                if (subListaDeProdutos.size() == 10) {
                    listaDeListasDeProdutos.add(subListaDeProdutos);
                    subListaDeProdutos = new ArrayList<>();
                }
            }

            if (!subListaDeProdutos.isEmpty()) {
                listaDeListasDeProdutos.add(subListaDeProdutos);
            }

            String action = request.getParameter("action");

            if (action != null) {
                switch (action) {
                    case "firstPage":
                        pagina = 1;
                        break;
                    case "prevPage":
                        pagina = pagina - 1;
                        break;
                    case "nextPage":
                        pagina = pagina + 1;
                        break;
                    case "lastPage":
                        pagina = listaDeListasDeProdutos.size();
                        break;
                    default:
                        pagina = 1;
                        break;
                }
            }

            if (pagina > 0 && pagina <= listaDeListasDeProdutos.size()) {
                produtos = listaDeListasDeProdutos.get(pagina - 1);
            } else {
                produtos = listaDeListasDeProdutos.get(0);
                pagina = 1;
            }
        }

        request.setAttribute("produtos", produtos);
        request.setAttribute("page", pagina);
        request.getRequestDispatcher("/ListarProdutos.jsp").forward(request, response);
    }
}
