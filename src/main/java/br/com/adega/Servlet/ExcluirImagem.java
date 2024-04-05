package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Imagem;
import br.com.adega.Model.Produto;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/excluirImagem")
public class ExcluirImagem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obter o nome da imagem a ser excluída dos parâmetros da solicitação

        String caminhoImagem = request.getParameter("nomeImagem");
        Boolean isTemp = Boolean.parseBoolean(request.getParameter("temp"));

        if (isTemp && caminhoImagem != null) {
            List<Imagem> images = (List<Imagem>) request.getAttribute("imagensTempProduto");
            String diretorioImagens = getServletContext().getRealPath("/imagens-temp");
            File arquivoImagem = new File(diretorioImagens, caminhoImagem);
            if (arquivoImagem.exists()) {
                if(arquivoImagem.delete()) {
                    Imagem imagemTemp = images.stream().filter(image -> image.getNome().equals(caminhoImagem)).toList().get(0);
                    if (imagemTemp != null) {
                        images.remove(imagemTemp);
                        request.setAttribute("imagensTempProduto", images);
                    }
                }
            }

            return;
        }

        if (caminhoImagem != null) {
            String diretorioImagens = getServletContext().getRealPath("/imagens");
            File arquivoImagem = new File(diretorioImagens, caminhoImagem);

            int codProduto = ProdutoDAO.ObterProdutoIdPorNomeImagem(caminhoImagem);

            if (arquivoImagem.exists()) {
                if (arquivoImagem.delete()) {
                    if (ProdutoDAO.ExcluirImagem(caminhoImagem)) {
                        List<Imagem> imagensProduto = ProdutoDAO.obterImagensPorProdutoId(codProduto); // Implemente essa lógica conforme necessário

                        if (!imagensProduto.isEmpty()) {
                            request.setAttribute("imagensProduto", imagensProduto);
                        }
                        request.setAttribute("codProduto", codProduto);
                        request.getRequestDispatcher("/GerenciarImagem.jsp");


                    }
                }
            }
        }
    }
}