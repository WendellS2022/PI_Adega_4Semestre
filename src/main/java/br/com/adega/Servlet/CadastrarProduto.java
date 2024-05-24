package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Produto;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;


@WebServlet("/cadastrarProduto")
public class CadastrarProduto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");
        int grupo = UsuarioDAO.ObterGrupo(usuarioLogado);
        request.setAttribute("grupo", grupo);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codProduto = request.getParameter("codProduto");

        Produto produto = new Produto();
        try {
            int produtoId;
            if (codProduto != null && !codProduto.isBlank()) {
                produto.setCodProduto(Integer.parseInt(codProduto));
                preencherProduto(request, produto);

                boolean produtoAtualizado = ProdutoDAO.AtualizarProduto(produto);

                if (produtoAtualizado) {
                    request.setAttribute("mensagem", "Produto alterado com sucesso!");
                    request.setAttribute("produto", produto);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
                    dispatcher.forward(request, response);

                    return;

                } else {
                    request.setAttribute("mensagem", "Falha ao alterar produto!");
                }
            } else {
                preencherProduto(request, produto);
                produto.setSituacaoProduto(true);
                produtoId = ProdutoDAO.AdicionarProdutoRetornandoCodigo(produto);

                if (produtoId != 0) {
                    produto.setCodProduto(produtoId);
                    request.setAttribute("mensagem", "Produto adicionado com sucesso!");
                    request.setAttribute("produto", produto);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
                    dispatcher.forward(request, response);

                    return;

                } else {
                    request.setAttribute("mensagem", "Falha ao adicionar produto!");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Ocorreu um erro ao processar a requisição.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }

    private void preencherProduto(HttpServletRequest request, Produto produto) {
        produto.setNomeProduto(request.getParameter("nomeProduto"));
        produto.setDscDetalhadaProduto(request.getParameter("dscDetalhadaProduto"));
        produto.setAvaliacaoProduto(Double.parseDouble(request.getParameter("avaliacaoProduto")));
        produto.setVlrVendaProduto(new BigDecimal(request.getParameter("vlrVendaProduto")));
        produto.setQtdEstoque(Integer.parseInt(request.getParameter("qtdEstoque")));
        produto.setSituacaoProduto(Boolean.parseBoolean(request.getParameter("situacao")));
    }
}
