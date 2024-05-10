package br.com.adega.Servlet;

import br.com.adega.DAO.CarrinhoDAO;
import br.com.adega.DAO.ClienteDAO;
import br.com.adega.Model.Carrinho;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/atualizarQuantidade")
public class AtualizarQuantidade extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Carrinho produtoCarrinho = new Carrinho();
        List<Carrinho> produtosCarrinho = (List<Carrinho>) session.getAttribute("carrinho");
        String produtoId = request.getParameter("produtoId");
        String acao = request.getParameter("action"); // ou request.getParameter("aumentar")
        String clienteLogado = (String) session.getAttribute("clienteLogado");

        try {
            if (clienteLogado != null && clienteLogado != "") {
                if (acao != null) {
                    if (acao.equals("decrease")) {
                        produtoCarrinho = CarrinhoDAO.obterProdutoDoCarrinhoPorProdutoId(Integer.parseInt(produtoId));
                        int quantidadeAtualizada = produtoCarrinho.getQuantidadeComprada() + (-1);
                        CarrinhoDAO.atualizarQuantidadeNoCarrinho(Integer.parseInt(produtoId), quantidadeAtualizada);


                    } else if (acao.equals("increase")) {
                        produtoCarrinho = CarrinhoDAO.obterProdutoDoCarrinhoPorProdutoId(Integer.parseInt(produtoId));
                        int quantidadeAtualizada = produtoCarrinho.getQuantidadeComprada() + 1;
                        CarrinhoDAO.atualizarQuantidadeNoCarrinho(Integer.parseInt(produtoId), quantidadeAtualizada);
                    }

                    int clienteId = ClienteDAO.buscarIdClienteEmail(clienteLogado);
                    produtosCarrinho = CarrinhoDAO.obterProdutosCarrinhoPorIdCliente(clienteId);

                    session.setAttribute("clienteLogado", clienteLogado);
                    session.setAttribute("carrinho", produtosCarrinho);
                    response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");

                }

            } else {

                if (acao.equals("decrease")) {
                    for (Carrinho prodCarrinho : produtosCarrinho) {
                        if (prodCarrinho.getProduto().getCodProduto() == Integer.parseInt(produtoId)) {
                            int quantidadeProduto = prodCarrinho.getQuantidadeComprada();
                            quantidadeProduto -= 1;
                            prodCarrinho.setQuantidadeComprada(quantidadeProduto);
                            break;
                        }
                    }
                    session.setAttribute("clienteLogado", clienteLogado);
                    session.setAttribute("carrinho", produtosCarrinho);
                    response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");

                } else if (acao.equals("increase")) {

                    for (Carrinho prodCarrinho : produtosCarrinho) {
                        if (prodCarrinho.getProduto().getCodProduto() == Integer.parseInt(produtoId)) {
                            int quantidadeProduto = prodCarrinho.getQuantidadeComprada();
                            quantidadeProduto += 1;
                            prodCarrinho.setQuantidadeComprada(quantidadeProduto);
                            break;
                        }
                    }
                    session.setAttribute("clienteLogado", clienteLogado);
                    session.setAttribute("carrinho", produtosCarrinho);
                    response.sendRedirect(request.getContextPath() + "/Carrinho.jsp");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Tratar a exceção adequadamente
        }
    }
}
