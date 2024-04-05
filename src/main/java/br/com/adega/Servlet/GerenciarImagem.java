package br.com.adega.Servlet;
import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Imagem;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@WebServlet("/gerenciarImagem")
public class GerenciarImagem extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int codProduto = Integer.parseInt(request.getParameter("codProduto"));

        List<Imagem> imagensProduto = ProdutoDAO.obterImagensPorProdutoId(codProduto); // Implemente essa lógica conforme necessário

           if (!imagensProduto.isEmpty()) {
           request.setAttribute("imagensProduto", imagensProduto);

           }

            request.setAttribute("codProduto", codProduto);
            request.getRequestDispatcher("GerenciarImagem.jsp").forward(request, response);
}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codProduto = Integer.parseInt(request.getParameter("codProduto"));

        List<Part> fileParts = request.getParts().stream()
                .filter(part -> "selImagem".equals(part.getName()))
                .collect(Collectors.toList());

        String diretorio = "imagens";
        String diretorioAbsoluto = request.getServletContext().getRealPath("/" + diretorio);
        File diretorioImagens = new File(diretorioAbsoluto);

        try {
            if (!diretorioImagens.exists()) {
                diretorioImagens.mkdirs();
            }
        } catch (SecurityException e) {
            // Lidar com exceções de permissão de arquivo aqui
            e.printStackTrace(); // Apenas para fins de exemplo, personalize conforme necessário
        } catch (Exception e) {
            // Lidar com outras exceções aqui
            e.printStackTrace(); // Apenas para fins de exemplo, personalize conforme necessário

    }


    List<Imagem> nomesImagensExistentes = ProdutoDAO.obterImagensPorProdutoId(codProduto);
        boolean imagemAdicionada = false;

        for (Part filePart : fileParts) {
            String fileName = extractFileName(filePart);
            if (!fileName.isEmpty() && !nomesImagensExistentes.contains(fileName)) {
                String novoNome = "imagem_" + System.currentTimeMillis() + "_" + fileName;
                String filePath = diretorioAbsoluto + File.separator + novoNome;
                filePart.write(filePath);

                // Salvar informações da imagem no banco de dados
                Imagem imagem = new Imagem();
                imagem.setProdutoId(codProduto);
                imagem.setDiretorio(diretorio);
                imagem.setNome(novoNome);
                imagem.setExtensao(fileName.substring(fileName.lastIndexOf(".") + 1));
                imagem.setQualificacao(false);
                ProdutoDAO.AdicionarImagem(imagem);

                imagemAdicionada = true;
            }
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
