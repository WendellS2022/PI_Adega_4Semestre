package br.com.adega.Servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadImagem")
@MultipartConfig
public class UploadImagemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Diretório onde as imagens serão salvas (caminho relativo)
        String diretorio = "imagens";

        // Diretório absoluto da aplicação
        String diretorioAbsoluto = getServletContext().getRealPath("/" + diretorio);

        System.out.println("Diretório de imagens: " + diretorioAbsoluto); // Adicione este log para verificar se o caminho do diretório está correto

        File diretorioImagens = new File(diretorioAbsoluto);
        if (!diretorioImagens.exists()) {
            System.out.println("O diretório de imagens não existe. Criando..."); // Adicione este log para verificar se o diretório está sendo criado corretamente
            if (!diretorioImagens.mkdirs()) {
                System.err.println("Falha ao criar o diretório de imagens."); // Adicione este log para indicar uma falha na criação do diretório
                response.getWriter().write("Falha ao criar o diretório de imagens."); // Adicione uma resposta para o cliente indicando o problema
                return;
            }
        }

        for (Part part : request.getParts()) {
            String nomeArquivo = extractFileName(part);
            String caminhoCompleto = diretorioAbsoluto + File.separator + nomeArquivo;
            part.write(caminhoCompleto);
            System.out.println("Arquivo salvo em: " + caminhoCompleto); // Adicione este log para verificar se o arquivo está sendo salvo corretamente
        }

        response.getWriter().write("Imagens enviadas com sucesso!");
    }

    // Método para extrair o nome do arquivo de uma Part
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
