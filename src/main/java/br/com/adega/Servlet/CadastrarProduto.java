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

@WebServlet("/cadastrarProduto")
public class CadastrarProduto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém o ID do produto da requisição
        String codProdutoParam = request.getParameter("COD_PRODUTO");

        if (codProdutoParam != null && !codProdutoParam.isEmpty()) {
            // Se o ID do produto está presente, é uma edição
            int codProduto = Integer.parseInt(codProdutoParam);
            Produto produto = ProdutoDAO.obterProdutoPorId(codProduto);
            request.setAttribute("produto", produto);
        }

        // Encaminha para a página de cadastro/edição de produtos
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os dados do formulário
        String codProdutoParam = request.getParameter("COD_PRODUTO");
        String nomeProduto = request.getParameter("DSC_NOME");
        String dscDetalhadaProduto = request.getParameter("DSC_DETALHADA");
        String avaliacaoProdutoParam = request.getParameter("COD_AVALIACAO");
        String qtdEstoqueParam = request.getParameter("QTD_ESTOQUE");
        String vlrVendaProdutoParam = request.getParameter("VLR_VENDA");

        // Converte os parâmetros para os tipos adequados
        int codProduto = 0;  // Inicializa com valor padrão
        if (codProdutoParam != null && !codProdutoParam.isEmpty()) {
            codProduto = Integer.parseInt(codProdutoParam);
        }
        int qtdEstoque = (qtdEstoqueParam != null && !qtdEstoqueParam.isEmpty()) ? Integer.parseInt(qtdEstoqueParam) : 0;
        double vlrVendaProduto = (vlrVendaProdutoParam != null && !vlrVendaProdutoParam.isEmpty()) ? Double.parseDouble(vlrVendaProdutoParam) : 0.0;
        int avaliacaoProduto = (avaliacaoProdutoParam != null && !avaliacaoProdutoParam.isEmpty()) ? Integer.parseInt(avaliacaoProdutoParam) : 0;

        // Cria um objeto Product com os dados do formulário
        Produto produto = new Produto(codProduto, nomeProduto, dscDetalhadaProduto, avaliacaoProduto, qtdEstoque, vlrVendaProduto, true);

        // Verifica se é uma operação de cadastro ou edição
        if (codProduto == 0) {
            // Cadastro de novo produto
            boolean success = ProdutoDAO.adicionarProduto(produto);

            if (success) {
                request.setAttribute("mensagem", "Produto cadastrado com sucesso!");
            } else {
                request.setAttribute("mensagem", "Falha ao cadastrar o produto. Verifique os dados e tente novamente.");
            }
        } else {

            // Edição de produto existente
            boolean success = ProdutoDAO.atualizarProduto(produto);

            if (success) {
                request.setAttribute("mensagem", "Produto atualizado com sucesso!");
            } else {
                request.setAttribute("mensagem", "Falha ao atualizar o produto. Verifique os dados e tente novamente.");
            }
        }

        // Encaminha de volta para a página de cadastro/edição
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }
}

