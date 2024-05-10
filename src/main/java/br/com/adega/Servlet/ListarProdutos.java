package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listarProdutos")
public class ListarProdutos extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pagina = 1;
        HttpSession session = request.getSession();
        String pagInicial = request.getParameter("pagina");
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");


        int grupo = UsuarioDAO.ObterGrupo(usuarioLogado);

        request.setAttribute("grupo", grupo);

        if (pagInicial != null)
            pagina = Integer.parseInt(pagInicial);

        List<Produto> produtos;
        String nomeProduto = request.getParameter("procurar");

        if (nomeProduto != null && !nomeProduto.isEmpty()) {
            produtos = ProdutoDAO.PesquisarProdutosPorNome(nomeProduto);
        } else {
            List<Produto> todosOsProdutos = ProdutoDAO.ObterTodosOsProdutos();

            if(todosOsProdutos.size() == 0){
                request.setAttribute("produtos", todosOsProdutos);
                request.setAttribute("pagina", pagina);
                request.getRequestDispatcher("/ListarProdutos.jsp").forward(request, response);
            return;
            }

            List<List<Produto>> listaDeListasDeProdutos = dividirProdutosEmListas(todosOsProdutos);

            String acao = request.getParameter("acao");
            pagina = calcularPagina(listaDeListasDeProdutos, pagina, acao);

            if (pagina > 0 && pagina <= listaDeListasDeProdutos.size()) {
                produtos = listaDeListasDeProdutos.get(pagina - 1);
            } else {
                produtos = listaDeListasDeProdutos.get(0);
                pagina = 1;
            }
        }

        boolean backOffice = true;

        request.setAttribute("isBackOffice", backOffice);
        request.setAttribute("produtos", produtos);
        request.setAttribute("pagina", pagina);
        request.getRequestDispatcher("/ListarProdutos.jsp").forward(request, response);
    }


    private List<List<Produto>> dividirProdutosEmListas(List<Produto> todosOsProdutos) {
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

        return listaDeListasDeProdutos;
    }

    private int calcularPagina(List<List<Produto>> listaDeListasDeProdutos, int pagina, String acao) {
        if (acao != null) {
            switch (acao) {
                case "primeiraPag":
                    return 1;
                case "anteriorPag":
                    return pagina - 1;
                case "proximaPag":
                    return pagina + 1;
                case "ultimaPag":
                    return listaDeListasDeProdutos.size();
                default:
                    return 1;
            }
        }

        return pagina;
    }

}