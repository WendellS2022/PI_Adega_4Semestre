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
import java.math.BigDecimal;
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
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");
        int grupo = UsuarioDAO.ObterGrupo(usuarioLogado);
        request.setAttribute("grupo", grupo);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codProduto = request.getParameter("id");
        String caminhoImagem = request.getParameter("caminhoImagem");
        String caminhoImagemPrincipal = request.getParameter("caminhoImagemPrincipal");

        if (caminhoImagemPrincipal != null || !caminhoImagemPrincipal.isEmpty()) {
            setarImagemPrincipal(caminhoImagemPrincipal);
        }

        if ((caminhoImagem == null || !caminhoImagem.isEmpty()) && (!codProduto.isEmpty() || codProduto == null)) {
            boolean imagemAdicionada = processarImagens(request, Integer.parseInt(codProduto), response);
            if (imagemAdicionada) {
                carregarProduto(request, response, codProduto);
                return;
            }
        }

        Produto produto = new Produto();
        try {
            int produtoId;
            if (codProduto != null && !codProduto.isBlank()) {
                preencherProduto(request, produto);

                boolean produtoAtualizado = ProdutoDAO.AtualizarProduto(produto);
                if (caminhoImagem != null && !produtoAtualizado) {
                    boolean imagemExcluida = excluirImagem(caminhoImagem);
                    if (imagemExcluida) {
                        carregarProduto(request, response, codProduto);
                        return;
                    }
                }

                if (produtoAtualizado) {
                    request.setAttribute("mensagem", "Produto alterado com sucesso!");
                } else {
                    request.setAttribute("mensagem", "Falha ao alterar produto!");
                }
            } else {
                preencherProduto(request, produto);
                produto.setSituacaoProduto(true);
                produtoId = ProdutoDAO.AdicionarProdutoRetornandoCodigo(produto);

                if (produtoId != 0) {
                    request.setAttribute("mensagem", "Produto adicionado com sucesso!");
                } else {
                    request.setAttribute("mensagem", "Falha ao adicionar produto!");
                }

                // Área de processamento de imagens
                processarImagens(request, produtoId, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Ocorreu um erro ao processar a requisição.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }

    private void preencherProduto(HttpServletRequest request, Produto produto) {
        produto.setNomeProduto(request.getParameter("nomeProduto"));
        produto.setDscDetalhadaProduto(request.getParameter("dscDetalhadaProduto"));
        produto.setAvaliacaoProduto(Double.parseDouble(request.getParameter("avaliacaoProduto")));
        produto.setVlrVendaProduto(new BigDecimal(request.getParameter("vlrVendaProduto")));
        produto.setQtdEstoque(Integer.parseInt(request.getParameter("qtdEstoque")));
        produto.setSituacaoProduto(Boolean.parseBoolean(request.getParameter("situacao")));
    }

    private boolean processarImagens(HttpServletRequest request, int produtoId, HttpServletResponse response) throws IOException, ServletException {
        List<Part> fileParts = request.getParts().stream()
                .filter(part -> "selImagem".equals(part.getName()))
                .collect(Collectors.toList());

        String diretorio = "imagens";
        String diretorioAbsoluto = getServletContext().getRealPath("/" + diretorio);
        File diretorioImagens = new File(diretorioAbsoluto);
        if (!diretorioImagens.exists()) {
            diretorioImagens.mkdirs();
        }

        List<Imagem> nomesImagensExistentes = ProdutoDAO.obterImagensPorProdutoId(produtoId);
        boolean imagemAdicionada = false;

        for (Part filePart : fileParts) {
            String fileName = extractFileName(filePart);
            if (!fileName.isEmpty() && !nomesImagensExistentes.contains(fileName)) {
                String novoNome = "imagem_" + System.currentTimeMillis() + "_" + fileName;
                String filePath = diretorioAbsoluto + File.separator + novoNome;
                filePart.write(filePath);

                // Salvar informações da imagem no banco de dados
                Imagem imagem = new Imagem();
                imagem.setProdutoId(produtoId);
                imagem.setDiretorio(diretorio);
                imagem.setNome(novoNome);
                imagem.setExtensao(fileName.substring(fileName.lastIndexOf(".") + 1));
                imagem.setQualificacao(false);
                ProdutoDAO.AdicionarImagem(imagem);

                imagemAdicionada = true;
            }
        }

        return imagemAdicionada;
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

    private boolean excluirImagem(String caminhoImagem) throws IOException {
        String nomeArquivo = caminhoImagem.substring(caminhoImagem.lastIndexOf("/") + 1);
        String diretorioImagens = getServletContext().getRealPath("/imagens");
        File arquivoImagem = new File(diretorioImagens);

        if (arquivoImagem.exists() && arquivoImagem.isDirectory()) {
            for (File arquivo : arquivoImagem.listFiles()) {
                if (arquivo.getName().equals(nomeArquivo)) {
                    if (arquivo.delete()) {
                        if (ProdutoDAO.ExcluirImagem(nomeArquivo)) {
                            return true; // Imagem excluída com sucesso
                        } else {
                            // Se houver falha ao excluir a imagem do banco de dados, não há necessidade de continuar
                            return false;
                        }
                    } else {
                        // Se houver falha ao excluir a imagem do sistema de arquivos
                        return false;
                    }
                }
            }
        } else {
            // Diretório de imagens não encontrado
            return false;
        }
        // Imagem não encontrada
        return false;
    }

    private void carregarProduto(HttpServletRequest request, HttpServletResponse response, String codProdutoParam) throws ServletException, IOException {
        Produto produto = ProdutoDAO.ObterProdutoPorId(Integer.parseInt(codProdutoParam));

        if (produto != null) {
            // Obtém as imagens associadas ao produto
            List<Imagem> imagensProduto = ProdutoDAO.obterImagensPorProdutoId(Integer.parseInt(codProdutoParam));

            // Passa as imagens e o produto para o JSP
            request.setAttribute("produto", produto);
            request.setAttribute("imagensProduto", imagensProduto);

            // Encaminha para a página de cadastro/edição de produtos
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
            dispatcher.forward(request, response);
        } else {
            // Se o produto não foi encontrado, define uma mensagem de erro e encaminha para a página principal
            request.setAttribute("mensagem", "Produto não encontrado!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listarProdutos");
            dispatcher.forward(request, response);
        }
    }

    private void setarImagemPrincipal(String nomeImagemPrincipal) {
        File file = new File(nomeImagemPrincipal);
        String nomeImagem = file.getName();
        if (!nomeImagem.isEmpty()) {

            Imagem imagem = new Imagem();

            String diretorio = "imagens";
            ;

            // Salvar o arquivo no servidor


            int codProduto = ProdutoDAO.ObterProdutoIdPorNomeImagem(nomeImagem);

            imagem.setProdutoId(codProduto);
            imagem.setDiretorio(diretorio);
            imagem.setNome(nomeImagem);
            imagem.setQualificacao(true);
            String extensao;

            int index = nomeImagemPrincipal.lastIndexOf(".");
            if (index == -1 || index == nomeImagemPrincipal.length() - 1) {
                // Se não houver ponto na string ou se o ponto for o último caractere, não há extensão
                imagem.setExtensao(""); // ou poderia lançar uma exceção, dependendo do comportamento desejado
            } else {
                extensao = nomeImagemPrincipal.substring(index + 1);
                imagem.setExtensao(extensao);
            }

            // Salvar informações da imagem no banco de dados
            boolean sucesso = ProdutoDAO.atualizarQualificacaoImagem(imagem);
            if (sucesso) {

            }
        }
    }
}