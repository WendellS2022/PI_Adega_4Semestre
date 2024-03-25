package br.com.adega.Servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/carregarImagem")
public class CarregarImagem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verifica se o parâmetro "caminho" está presente na requisição
        String caminhoImagem = request.getParameter("caminho");

        // Se o parâmetro "caminho" estiver presente, trata-se de uma requisição para exclusão de imagem
        if (caminhoImagem != null && !caminhoImagem.isEmpty()) {
            // Diretório onde as imagens são armazenadas
            String diretorio = "imagens";
            String diretorioAbsoluto = getServletContext().getRealPath("/" + diretorio);
            String caminhoCompleto = diretorioAbsoluto + File.separator + caminhoImagem;

            // Verifica se o arquivo de imagem existe
            File arquivoImagem = new File(caminhoCompleto);
            if (!arquivoImagem.exists()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // Retorna um erro 404 (Not Found) se o arquivo não existir
                response.getWriter().write("Imagem não encontrada.");
                return;
            }

            // Tenta excluir o arquivo de imagem
            if (arquivoImagem.delete()) {
                response.setStatus(HttpServletResponse.SC_OK); // Retorna um código de sucesso 200 (OK) se a exclusão for bem-sucedida
                response.getWriter().write("Imagem excluída com sucesso!");
                System.out.println("Imagem excluída: " + caminhoCompleto);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Retorna um erro 500 (Internal Server Error) se a exclusão falhar
                response.getWriter().write("Falha ao excluir a imagem.");
                System.err.println("Falha ao excluir a imagem: " + caminhoCompleto);
            }
        } else {
            // Se não houver parâmetro "caminho", trata-se de uma requisição para salvar imagens

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


    // Método para lidar com solicitações DELETE e excluir a imagem correspondente do diretório de imagens
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtenha o caminho da imagem a ser excluída do parâmetro da solicitação
        String caminhoImagem = request.getParameter("caminho");

    }
}


