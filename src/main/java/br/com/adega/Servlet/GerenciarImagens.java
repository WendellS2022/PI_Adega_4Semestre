package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Imagem;
import br.com.adega.Model.Produto;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/gerenciarImagens")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,    // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class GerenciarImagens extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codProduto = request.getParameter("codProduto");
        Produto produto = ProdutoDAO.ObterProdutoPorId(Integer.parseInt(codProduto));
        List<Imagem> imagensProduto = ProdutoDAO.obterImagensPorProdutoId(Integer.parseInt(codProduto));

        if (imagensProduto.size() > 0) {

            request.setAttribute("produto", produto);
            request.setAttribute("codProduto", codProduto);
            request.setAttribute("imagensProdutoBase", imagensProduto);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/GerenciarImagens.jsp");
            dispatcher.forward(request, response);

        } else {
            request.setAttribute("produto", produto);
            request.setAttribute("codProduto", codProduto);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/GerenciarImagens.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codProduto = request.getParameter("codProduto");

        List<Part> fileParts = request.getParts().stream()
                .filter(part -> "selImagem".equals(part.getName()))
                .collect(Collectors.toList());

        String diretorio = "imagens";
        String diretorioAbsoluto = getServletContext().getRealPath("/" + diretorio);
        File diretorioImagens = new File(diretorioAbsoluto);
        if (!diretorioImagens.exists()) {
            diretorioImagens.mkdirs();
        }

        List<Imagem> nomesImagensExistentes = ProdutoDAO.obterImagensPorProdutoId(Integer.parseInt(codProduto));
        boolean imagemAdicionada = false;

        for (Part filePart : fileParts) {
            String fileName = extractFileName(filePart);
            if (!fileName.isEmpty() && !nomesImagensExistentes.contains(fileName)) {
                String novoNome = "imagem_" + System.currentTimeMillis() + "_" + fileName;
                String filePath = diretorioAbsoluto + File.separator + novoNome;
                filePart.write(filePath);

                // Salvar informações da imagem no banco de dados
                Imagem imagem = new Imagem();

                imagem.setProdutoId(Integer.parseInt(codProduto));
                imagem.setDiretorio(diretorio);
                imagem.setNome(novoNome);
                imagem.setExtensao(fileName.substring(fileName.lastIndexOf(".") + 1));
                imagem.setQualificacao(false);
                ProdutoDAO.AdicionarImagem(imagem);

                imagemAdicionada = true;
            }
        }

        if (!imagemAdicionada) {
            response.sendRedirect("/GerenciarImagens.jsp");
        } else {
            List<Imagem> imagensProduto = ProdutoDAO.obterImagensPorProdutoId(Integer.parseInt(codProduto));
            request.setAttribute("imagensProdutoBase", imagensProduto);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/GerenciarImagens.jsp");
            dispatcher.forward(request, response);
        }
    }

    public String extractFileName(Part part) {
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

