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

//@WebServlet("/listarProdutos")
//public class ListarProdutos extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        List<Produto> produtos = ProdutoDAO.obterPaginaDeProdutos(1, 10); // Obtém a primeira página de produtos (máximo 10 por página)
//
//        request.setAttribute("produtos", produtos);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarProdutos.jsp");
//        dispatcher.forward(request, response);
//    }
//}
@WebServlet("/listarProdutos")
public class ListarProdutos extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pagina = 1; // Página padrão
        HttpSession session = request.getSession();
        String pagInicial = request.getParameter("page");
        String isSession = (String) session.getAttribute("usuarioLogado");


        int grupo = UsuarioDAO.ObterGrupo(isSession);

        request.setAttribute("grupo", grupo);

        if (pagInicial != null)
            pagina = Integer.parseInt(pagInicial);

        List<Produto> produtos;
        String search = request.getParameter("search");

        if (search != null && !search.isEmpty()) {
            produtos = ProdutoDAO.PesquisarProdutosPorNome(search);
        } else {
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

        request.setAttribute("produtos", produtos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarProdutos.jsp");
        dispatcher.forward(request, response);
    }
}