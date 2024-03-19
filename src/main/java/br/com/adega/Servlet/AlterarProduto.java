package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Produto;
import br.com.adega.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet("/alterarProduto")
public class AlterarProduto extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String codProdutoParam = request.getParameter("id");

        String isSession = (String) session.getAttribute("usuarioLogado");

        int grupo = UsuarioDAO.ObterGrupo(isSession);

            request.setAttribute("grupo", grupo);

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
        HttpSession session = request.getSession();
        int pagina = 1;

        String isSession = (String) session.getAttribute("usuarioLogado");
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


        int grupo = UsuarioDAO.ObterGrupo(isSession);

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
