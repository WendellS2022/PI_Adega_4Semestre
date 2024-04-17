package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/excluirImagem")
public class ExcluirImagem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String caminhoImagem = request.getParameter("caminhoImagemPrincipal");

        if (caminhoImagem != null) {
            String diretorioImagens = getServletContext().getRealPath("/imagens");
            File arquivoImagem = new File(diretorioImagens, caminhoImagem);

            int codProduto = ProdutoDAO.ObterProdutoIdPorNomeImagem(caminhoImagem);

            if (arquivoImagem.exists()) {
                if (arquivoImagem.delete()) {
                    if (ProdutoDAO.ExcluirImagem(caminhoImagem)) {

                        response.sendRedirect(request.getContextPath() + "/gerenciarImagens?codProduto=" + codProduto);

                    }
                }
            }
        }
    }
}