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
            Produto produto = ProdutoDAO.ObterProdutoPorId(codProduto);
            request.setAttribute("produto", produto);
        }

        // Encaminha para a página de cadastro/edição de produtos
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Produto produto = new Produto();
        String codProduto = request.getParameter("id");

        if (!codProduto.isEmpty()) {
            produto.setCodProduto(Integer.parseInt(codProduto));
            produto.setNomeProduto(request.getParameter("nomeProduto"));
            produto.setDscDetalhadaProduto(request.getParameter("dscDetalhadaProduto"));
            produto.setAvaliacaoProduto(Double.parseDouble(request.getParameter("avaliacaoProduto")));
            produto.setVlrVendaProduto(Double.parseDouble(request.getParameter("vlrVendaProduto")));
            produto.setQtdEstoque(Integer.parseInt(request.getParameter("qtdEstoque")));
            produto.setSituacaoProduto(Boolean.parseBoolean(request.getParameter("situacao")));

            boolean updateProduto = ProdutoDAO.AtualizarProduto(produto);

            if (updateProduto) {
                request.setAttribute("mensagem", "Produto alterado com sucesso!");
            } else {
                request.setAttribute("mensagem", "Falha ao alterar produto!");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
            dispatcher.forward(request, response);
        } else {
            produto.setNomeProduto(request.getParameter("nomeProduto"));
            produto.setDscDetalhadaProduto(request.getParameter("dscDetalhadaProduto"));
            produto.setAvaliacaoProduto(Double.parseDouble(request.getParameter("avaliacaoProduto")));
            produto.setVlrVendaProduto(Double.parseDouble(request.getParameter("vlrVendaProduto")));
            produto.setQtdEstoque(Integer.parseInt(request.getParameter("qtdEstoque")));
            produto.setSituacaoProduto(true);

            boolean createProduto = ProdutoDAO.AdicionarProduto(produto);

            if (createProduto) {
                request.setAttribute("mensagem", "Produto adcionado com sucesso!");
            } else {
                request.setAttribute("mensagem", "Falha ao adcionar produto!");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
            dispatcher.forward(request, response);
        }
//        Converte os parâmetros para os tipos adequados
//        int codProduto = 0;  // Inicializa com valor padrão
//        if (codProduto != null && !codProdutoParam.isEmpty()) {
//            codProduto = Integer.parseInt(codProdutoParam);
//        }
//        int qtdEstoque = (qtdEstoqueParam != null && !qtdEstoqueParam.isEmpty()) ? Integer.parseInt(qtdEstoqueParam) : 0;
//        double vlrVendaProduto = (vlrVendaProdutoParam != null && !vlrVendaProdutoParam.isEmpty()) ? Double.parseDouble(vlrVendaProdutoParam) : 0.0;
//        int avaliacaoProduto = (avaliacaoProdutoParam != null && !avaliacaoProdutoParam.isEmpty()) ? Integer.parseInt(avaliacaoProdutoParam) : 0;
//
//        // Cria um objeto Product com os dados do formulário
//        Produto produto = new Produto(codProduto, nomeProduto, dscDetalhadaProduto, avaliacaoProduto, qtdEstoque, vlrVendaProduto, true);
//
//        // Verifica se é uma operação de cadastro ou edição
//        if (codProduto == 0) {
//            // Cadastro de novo produto
//            boolean success = ProdutoDAO.adicionarProduto(produto);
//
//            if (success) {
//                request.setAttribute("mensagem", "Produto cadastrado com sucesso!");
//            } else {
//                request.setAttribute("mensagem", "Falha ao cadastrar o produto. Verifique os dados e tente novamente.");
//            }
//        } else {
//
//            // Edição de produto existente
//            boolean success = ProdutoDAO.atualizarProduto(produto);
//
//            if (success) {
//                request.setAttribute("mensagem", "Produto atualizado com sucesso!");
//            } else {
//                request.setAttribute("mensagem", "Falha ao atualizar o produto. Verifique os dados e tente novamente.");
//            }
//        }

        // Encaminha de volta para a página de cadastro/edição

    }
}


