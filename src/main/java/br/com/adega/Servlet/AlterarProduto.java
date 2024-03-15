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

        Produto produto = ProdutoDAO.ObterProdutoPorId(Integer.parseInt(codProdutoParam));
        request.setAttribute("produto", produto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codProdutoParam = request.getParameter("codProduto");

        if (codProdutoParam != null) {
            boolean isSucess = ProdutoDAO.AtualizarStatus(codProdutoParam);

            List<Produto> produtos = ProdutoDAO.ObterTodosOsProdutos();

            Collection<Part> parts = request.getParts();

            for (Part part : parts) {
                if (part.getContentType() != null) {
                    String fileName = extractFileName(part);
                    // Lide com o upload do arquivo aqui
                    // Salve o arquivo no sistema de arquivos ou armazene-o em um banco de dados
                }
            }

            String[] imagensExcluir = request.getParameterValues("imagensExcluir");
            if (imagensExcluir != null) {
                for (String imagemId : imagensExcluir) {
                    // Lógica para excluir imagens do banco de dados e do servidor
                }
            }

            request.setAttribute("produtos", produtos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarProdutos.jsp");
            dispatcher.forward(request, response);
        }
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
