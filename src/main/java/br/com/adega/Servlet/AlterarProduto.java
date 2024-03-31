package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Imagem;
import br.com.adega.Model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/alterarProduto")
public class AlterarProduto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém informações do usuário logado
        HttpSession session = request.getSession();
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");
        int grupo = UsuarioDAO.ObterGrupo(usuarioLogado);
        request.setAttribute("grupo", grupo);

        // Obtém o ID do produto a ser alterado
        String codProdutoParam = request.getParameter("id");
        Produto produto = ProdutoDAO.ObterProdutoPorId(Integer.parseInt(codProdutoParam));

        // Obtém as imagens associadas ao produto
        List<Imagem> imagensProduto = ProdutoDAO.obterImagensPorProdutoId(Integer.parseInt(codProdutoParam));

        // Passa as imagens e o produto para o JSP
     request.setAttribute("produto", produto);

        request.setAttribute("imagensProduto", imagensProduto);

        // Encaminha para a página de cadastro/edição de produtos
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> produtos = new ArrayList<>();
        HttpSession session = request.getSession();
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");

        // Obtém os parâmetros enviados pelo formulário
        String codProduto = request.getParameter("codProduto");
        String acao = request.getParameter("acao");
        int pagina = Integer.parseInt(request.getParameter("pagina")); // Obtém o número da página atual

        // Atualiza o status do produto com base na ação
        boolean isSuccess = ProdutoDAO.AtualizarStatus(codProduto);

        // Obtém os parâmetros de página da requisição
        String pageParam = request.getParameter("pagina");
        if (pageParam != null && !pageParam.isEmpty()) {
            pagina = Integer.parseInt(pageParam);
            request.setAttribute("page", pageParam);
        } else {
            request.setAttribute("page", pagina);
        }

        int grupo = UsuarioDAO.ObterGrupo(usuarioLogado);
        List<Produto> todosOsProdutos = ProdutoDAO.ObterTodosOsProdutos();

        List<List<Produto>> listaDeListasDeProdutos = dividirProdutosEmListas(todosOsProdutos);


        pagina = calcularPagina(listaDeListasDeProdutos, pagina, acao);

        if (pagina > 0 && pagina <= listaDeListasDeProdutos.size()) {

            produtos = listaDeListasDeProdutos.get(pagina - 1);
        } else {
            produtos = listaDeListasDeProdutos.get(0);
            pagina = 1;
        }

        request.setAttribute("grupo", grupo);
        request.setAttribute("produtos", produtos);
        request.setAttribute("pagina", pagina);
        request.getRequestDispatcher("/ListarProdutos.jsp").forward(request, response);
    }

    // Método para dividir a lista de produtos em listas de tamanho fixo
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

    // Método para calcular a página atual com base na ação do usuário
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
}
