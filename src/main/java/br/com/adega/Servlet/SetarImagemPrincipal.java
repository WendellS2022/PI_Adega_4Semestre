package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Imagem;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/setarImagemPrincipal")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,    // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class SetarImagemPrincipal extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeImagemPrincipal = request.getParameter("caminhoImagemPrincipal");

        File file = new File(nomeImagemPrincipal);
        String nomeImagem = file.getName();
        if (!nomeImagem.isEmpty()) {

            Imagem imagem = new Imagem();

            String diretorio = "imagens";

            int codProduto = ProdutoDAO.ObterProdutoIdPorNomeImagem(nomeImagem);

            imagem.setProdutoId(codProduto);
            imagem.setDiretorio(diretorio);
            imagem.setNome(nomeImagem);
            imagem.setQualificacao(true);
            String extensao;

            int index = nomeImagemPrincipal.lastIndexOf(".");
            if (index == -1 || index == nomeImagemPrincipal.length() - 1) {
                imagem.setExtensao("");
            } else {
                extensao = nomeImagemPrincipal.substring(index + 1);
                imagem.setExtensao(extensao);
            }

            boolean sucesso = ProdutoDAO.atualizarQualificacaoImagem(imagem);
            if (sucesso) {
                response.sendRedirect(request.getContextPath() + "/gerenciarImagens?codProduto=" + codProduto);

            }
        }
    }
}
