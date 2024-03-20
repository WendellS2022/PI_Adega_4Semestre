package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Imagem;
import br.com.adega.Model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/cadastrarProduto")
@MultipartConfig
public class CadastrarProduto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String isSession = (String) session.getAttribute("usuarioLogado");

        int grupo = UsuarioDAO.ObterGrupo(isSession);

        request.setAttribute("grupo", grupo);

        // Encaminha para a página de cadastro/edição de produtos
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Produto produto = new Produto();
        String codProduto = request.getParameter("id");

        try {
            if (codProduto != null && !codProduto.isBlank()) {
                produto.setCodProduto(Integer.parseInt(codProduto));
                produto.setNomeProduto(request.getParameter("nomeProduto"));
                produto.setDscDetalhadaProduto(request.getParameter("dscDetalhadaProduto"));
                produto.setAvaliacaoProduto(Double.parseDouble(request.getParameter("avaliacaoProduto")));
                produto.setVlrVendaProduto(Double.parseDouble(request.getParameter("vlrVendaProduto")));
                produto.setQtdEstoque(Integer.parseInt(request.getParameter("qtdEstoque")));
                produto.setSituacaoProduto(Boolean.parseBoolean(request.getParameter("situacao")));

                boolean updateProduto = ProdutoDAO.AtualizarProduto(produto);

                if (updateProduto) {
                    request.setAttribute("mensagem", "Produto alterado com sucesso!");
                } else {
                    request.setAttribute("mensagem", "Falha ao alterar produto!");
                }
            } else {
                produto.setNomeProduto(request.getParameter("nomeProduto"));
                produto.setDscDetalhadaProduto(request.getParameter("dscDetalhadaProduto"));
                produto.setAvaliacaoProduto(Double.parseDouble(request.getParameter("avaliacaoProduto")));
                produto.setVlrVendaProduto(Double.parseDouble(request.getParameter("vlrVendaProduto")));
                produto.setQtdEstoque(Integer.parseInt(request.getParameter("qtdEstoque")));
                produto.setSituacaoProduto(true);

                boolean createProduto = ProdutoDAO.AdicionarProduto(produto);

                if (createProduto) {
                    request.setAttribute("mensagem", "Produto adicionado com sucesso!");
                } else {
                    request.setAttribute("mensagem", "Falha ao adicionar produto!");
                }
            }

            // Processar o upload de imagens
            List<Part> fileParts = request.getParts().stream().filter(part -> "selImagem".equals(part.getName())).collect(Collectors.toList());
            List<String> imagePaths = new ArrayList<>();
            String diretorio = getServletContext().getRealPath("/imagens"); // Diretório onde as imagens serão salvas
            File diretorioImagens = new File(diretorio);

            // Verificar se o diretório de imagens existe e criar se não existir
            if (!diretorioImagens.exists()) {
                diretorioImagens.mkdirs();
            }

            for (Part filePart : fileParts) {
                String fileName = extractFileName(filePart);
                if (fileName != null && !fileName.isEmpty()) {
                    // Salvar a imagem no diretório
                    String filePath = diretorio + File.separator + fileName;
                    filePart.write(filePath);
                    imagePaths.add(filePath);

                    // Salvar detalhes da imagem no banco de dados
                    Imagem imagem = new Imagem();
                    imagem.setProdutoId(produto.getCodProduto());
                    imagem.setDiretorio(diretorio);
                    imagem.setNome(fileName);
                    imagem.setExtensao(fileName.substring(fileName.lastIndexOf(".") + 1));
                    ProdutoDAO.AdicionarImagem(imagem);
                }
            }

            // Adicionar os caminhos das imagens à requisição para exibição posterior (opcional)
            request.setAttribute("imagePaths", imagePaths);

            // Redirecionar para a página de cadastro/edição de produtos
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para extrair o nome do arquivo de uma Part
    private static String extractFileName(Part part) {
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
