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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,    // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50)   // 50MB
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
            int produtoId;
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

                produtoId = produto.getCodProduto(); // Usar o ID do produto existente
            } else {
                produto.setNomeProduto(request.getParameter("nomeProduto"));
                produto.setDscDetalhadaProduto(request.getParameter("dscDetalhadaProduto"));
                produto.setAvaliacaoProduto(Double.parseDouble(request.getParameter("avaliacaoProduto")));
                produto.setVlrVendaProduto(Double.parseDouble(request.getParameter("vlrVendaProduto")));
                produto.setQtdEstoque(Integer.parseInt(request.getParameter("qtdEstoque")));
                produto.setSituacaoProduto(true);

                produtoId = ProdutoDAO.AdicionarProdutoRetornandoCodigo(produto);

                if (produtoId != 0) {
                    request.setAttribute("mensagem", "Produto adicionado com sucesso!");
                } else {
                    request.setAttribute("mensagem", "Falha ao adicionar produto!");
                }
            }

            // Obter o caminho da imagem principal do formulário
            String caminhoImagemPrincipal = request.getParameter("caminhoImagemPrincipal");

            // Atualizar o banco de dados para definir a imagem principal
            if (caminhoImagemPrincipal != null && !caminhoImagemPrincipal.isEmpty()) {
                ProdutoDAO.DefinirImagemPrincipal(produtoId, caminhoImagemPrincipal);
            }

            // Processar o upload de imagens
            List<Part> fileParts = request.getParts().stream().filter(part -> "selImagem".equals(part.getName())).collect(Collectors.toList());
            List<String> imagePaths = new ArrayList<>();
            String diretorio = "imagens"; // Diretório onde as imagens serão salvas (caminho relativo)
            String diretorioAbsoluto = getServletContext().getRealPath("/" + diretorio); // Diretório absoluto da aplicação

            // Verificar se o diretório de imagens existe e criar se não existir
            File diretorioImagens = new File(diretorioAbsoluto);
            if (!diretorioImagens.exists()) {
                diretorioImagens.mkdirs();
            }

            boolean primeiraImagem = true;
            for (Part filePart : fileParts) {
                String fileName = extractFileName(filePart);
                if (fileName != null && !fileName.isEmpty()) {
                    // Salvar a imagem no diretório
                    String novoNome = "imagem_" + System.currentTimeMillis() + "_" + fileName;
                    String filePath = diretorioAbsoluto + File.separator + novoNome;
                    filePart.write(filePath);
                    imagePaths.add(filePath);

                    // Salvar detalhes da imagem no banco de dados
                    Imagem imagem = new Imagem();
                    imagem.setProdutoId(produtoId); // Usando o ID do produto
                    imagem.setDiretorio(diretorio);
                    imagem.setNome(novoNome);
                    imagem.setExtensao(fileName.substring(fileName.lastIndexOf(".") + 1));

                    // Definir a qualificação da imagem principal
                    if (primeiraImagem) {
                        imagem.setQualificacao(true);
                        primeiraImagem = false;
                    } else {
                        imagem.setQualificacao(false);
                    }

                    ProdutoDAO.AdicionarImagem(imagem);
                }
            }

            // Redirecionar para a página de listagem de produtos
            response.sendRedirect(request.getContextPath() + "/listarProdutos");
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