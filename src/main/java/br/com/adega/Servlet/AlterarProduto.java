package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Imagem;
import br.com.adega.Model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/alterarProduto")
public class AlterarProduto extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");
        int grupo = UsuarioDAO.ObterGrupo(usuarioLogado);
        request.setAttribute("grupo", grupo);

        String codProdutoParam = request.getParameter("id");
        Produto produto = ProdutoDAO.ObterProdutoPorId(Integer.parseInt(codProdutoParam));

        // Obter as imagens associadas ao produto
        List<Imagem> imagensProduto = ProdutoDAO.obterImagensPorProdutoId(Integer.parseInt(codProdutoParam));

        // Passar as imagens e o produto para o JSP
        request.setAttribute("produto", produto);
        request.setAttribute("imagensProduto", imagensProduto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int pagina = 1;

        String usuarioLogado = (String) session.getAttribute("usuarioLogado");
        String codProdutoParam = request.getParameter("codProduto");


        List<Produto> produtos = null;

        if (codProdutoParam != null) {
            boolean isSuccess = ProdutoDAO.AtualizarStatus(codProdutoParam);

            // Obtém os parâmetros de página da requisição
            int page = 1; // Página padrão é a primeira
            String pageParam = request.getParameter("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                pagina = Integer.parseInt(pageParam);

                request.setAttribute("page", pageParam);
            } else {
                request.setAttribute("page", pagina);
            }

            List<Produto> todosOsProdutos = ProdutoDAO.ObterTodosOsProdutos();

            List<List<Produto>> listaDeListasDeProdutos = dividirProdutosEmListas(todosOsProdutos);


            String action = request.getParameter("action");
            pagina = calcularPagina(listaDeListasDeProdutos, pagina, action);

            if (pagina > 0 && pagina <= listaDeListasDeProdutos.size()) {
                produtos = listaDeListasDeProdutos.get(pagina - 1);
            } else {
                produtos = listaDeListasDeProdutos.get(0);
                pagina = 1;
            }
        }


        int grupo = UsuarioDAO.ObterGrupo(usuarioLogado);

        request.setAttribute("grupo", grupo);
        request.setAttribute("produtos", produtos);
        request.setAttribute("page", pagina);
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

    private int calcularPagina(List<List<Produto>> listaDeListasDeProdutos, int pagina, String action) {
        if (action != null) {
            switch (action) {
                case "firstPage":
                    return 1;
                case "prevPage":
                    return pagina - 1;
                case "nextPage":
                    return pagina + 1;
                case "lastPage":
                    return listaDeListasDeProdutos.size();
                default:
                    return 1;
            }
        }

        return pagina;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}
