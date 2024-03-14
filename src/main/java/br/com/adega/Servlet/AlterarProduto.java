package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@WebServlet("/alterarProduto")
public class AlterarProduto extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codProdutoParam = request.getParameter("id");



            Produto produtos = ProdutoDAO.ObterProdutoPorId(Integer.parseInt(codProdutoParam));

            request.setAttribute("produto", produtos);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
            dispatcher.forward(request, response);


    }


//        int codProduto = Integer.parseInt(codProdutoParam);
//        Produto produto = ProdutoDAO.obterProdutoPorId(codProduto);
//
//        if (produto != null) {
//            request.setAttribute("produto", produto);
//        } else {
//            request.setAttribute("mensagem", "Produto não encontrado");
//        }

//        String situacaoProduto = request.getParameter("COD_SITUACAO");
//
//        if ("inativar".equals(situacaoProduto) || "reativar".equals(situacaoProduto)) {
//            Produto produto = ProdutoDAO.obterProdutoPorId(Integer.parseInt(codProdutoParam));
//
//            if (produto != null) {
//                String confirmacao = request.getParameter("confirmacao");
//
//                if (confirmacao != null && confirmacao.equals("true")) {
//                    boolean novoStatus = !produto.isSituacaoProduto();
//                    produto.setSituacaoProduto(novoStatus);
//
//                    boolean sucesso = ProdutoDAO.atualizarProduto(produto);
//
//                    if (sucesso) {
//                        String mensagem = "Produto " + (novoStatus ? "reativado" : "inativado") + " com sucesso!";
//                        request.setAttribute("mensagem", mensagem);
//                    } else {
//                        String mensagem = "Falha ao " + (novoStatus ? "reativar" : "inativar") + " o produto. Verifique os dados e tente novamente.";
//                        request.setAttribute("mensagem", mensagem);
//                    }
//                } else {
//                    request.setAttribute("produto", produto);
//                    RequestDispatcher dispatcher = request.getRequestDispatcher("/ConfirmacaoAlteracao.jsp");
//                    dispatcher.forward(request, response);
//                    return;
//                }
//            } else {
//                request.setAttribute("mensagem", "Produto não encontrado");
//            }
//        }

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
//        dispatcher.forward(request, response);
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém os dados do formulário
        String codProdutoParam = request.getParameter("codProduto");

        if (codProdutoParam != null) {

            boolean isSucess = ProdutoDAO.AtualizarStatus(codProdutoParam);

            List<Produto> produtos = ProdutoDAO.ObterTodosOsProdutos();

            Collection<Part> parts = request.getParts();

            for (Part part : parts) {
                // Verifique se é um campo de arquivo
                if (part.getContentType() != null) {
                    // Lide com o upload do arquivo aqui
                    String fileName = extractFileName(part);
                    // Salve o arquivo no sistema de arquivos ou armazene-o em um banco de dados
                }
            }

            request.setAttribute("produtos", produtos);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarProdutos.jsp");
            dispatcher.forward(request, response);
        }


//        String codProdutoParam = request.getParameter("COD_PRODUTO");
//        String nomeProduto = request.getParameter("DSC_NOME");
//        String dscDetalhadaProduto = request.getParameter("DSC_DETALHADA");
//        String avaliacaoProdutoParam = request.getParameter("COD_AVALIACAO");
//        String qtdEstoqueParam = request.getParameter("QTD_ESTOQUE");
//        String vlrVendaProdutoParam = request.getParameter("VLR_VENDA");
//        String situacaoProdutoParam = request.getParameter("COD_SITUACAO");
//
//        // Converte os parâmetros para os tipos adequados
//        int codProduto = 0;  // Inicializa com valor padrão
//        if (codProdutoParam != null && !codProdutoParam.isEmpty()) {
//            codProduto = Integer.parseInt(codProdutoParam);
//        }
//        int qtdEstoque = (qtdEstoqueParam != null && !qtdEstoqueParam.isEmpty()) ? Integer.parseInt(qtdEstoqueParam) : 0;
//        double vlrVendaProduto = (vlrVendaProdutoParam != null && !vlrVendaProdutoParam.isEmpty()) ? Double.parseDouble(vlrVendaProdutoParam) : 0.0;
//        boolean situacaoProduto = Boolean.parseBoolean(situacaoProdutoParam);
//        double avaliacaoProduto = (avaliacaoProdutoParam != null && !avaliacaoProdutoParam.isEmpty()) ? Double.parseDouble(avaliacaoProdutoParam) : 0.0;
//
//
//        // Cria um objeto Produto com os dados do formulário
//        Produto produto = new Produto(codProduto, nomeProduto, dscDetalhadaProduto, avaliacaoProduto, qtdEstoque, vlrVendaProduto, situacaoProduto);
//
//        // Edição de produto existente
//        boolean success = ProdutoDAO.atualizarProduto(produto);
//
//        if (success) {
//            request.setAttribute("mensagem", "Produto atualizado com sucesso!");
//        } else {
//            request.setAttribute("mensagem", "Falha ao atualizar o produto. Verifique os dados e tente novamente.");
//        }
//
//        // Encaminha de volta para a página de cadastro/edição
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarEditarProduto.jsp");
//        dispatcher.forward(request, response);
//    }
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