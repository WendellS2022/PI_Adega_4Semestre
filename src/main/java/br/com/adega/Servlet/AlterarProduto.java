package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Imagem;
import br.com.adega.Model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/alterarProduto")
public class AlterarProduto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém informações do usuário logado
        HttpSession session = request.getSession();
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");
        int grupo = UsuarioDAO.ObterGrupo(usuarioLogado);
        request.setAttribute("grupo", grupo);

        // Obtém o ID do produto a ser alterado
        String codProdutoParam = request.getParameter("id");
        Produto produto = ProdutoDAO.ObterProdutoPorId(Integer.parseInt(codProdutoParam));

        // Obtém as imagens associadas ao produto
        List<Imagem> imagensProduto = ProdutoDAO.obterImagensPorProdutoId(Integer.parseInt(codProdutoParam));

        // Passa as imagens e o produto para o JSP
        request.setAttribute("produto", produto);
        request.setAttribute("imagensProduto", imagensProduto);

        // Encaminha para a página de cadastro/edição de produtos
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String usuarioLogado = (String) session.getAttribute("usuarioLogado");
            int pagina = 1;

            // Obtém o ID do produto e informações do usuário logado
            String codProdutoParam = request.getParameter("codProduto");

            List<Produto> produtos = null;

            if (codProdutoParam != null) {
                // Atualiza o status do produto
                boolean isSuccess = ProdutoDAO.AtualizarStatus(codProdutoParam);

                // Obtém os parâmetros de página da requisição
                String pageParam = request.getParameter("page");
                if (pageParam != null && !pageParam.isEmpty()) {
                    pagina = Integer.parseInt(pageParam);
                    request.setAttribute("page", pageParam);
                } else {
                    request.setAttribute("page", pagina);
                }

                List<Produto> todosOsProdutos = ProdutoDAO.ObterTodosOsProdutos();

                List<List<Produto>> listaDeListasDeProdutos = dividirProdutosEmListas(todosOsProdutos);

                String action = request.getParameter("action");
                pagina = calcularPagina(listaDeListasDeProdutos, pagina, action);

                if (pagina > 0 && pagina <= listaDeListasDeProdutos.size()) {
                    produtos = listaDeListasDeProdutos.get(pagina - 1);
                } else {
                    produtos = listaDeListasDeProdutos.get(0);
                    pagina = 1;
                }
            }

            // Área de exclusão de imagem
            String caminhoImagem = request.getParameter("caminhoImagem");
            if (caminhoImagem != null && !caminhoImagem.isEmpty()) {
                // Obtém apenas o nome do arquivo, removendo o caminho completo e o prefixo "imagens/"
                String nomeArquivo = caminhoImagem.substring(caminhoImagem.lastIndexOf("/") + 1);

                // Obtém o diretório absoluto de imagens da aplicação
                String diretorioImagens = getServletContext().getRealPath("/imagens");
                File arquivoImagem = new File(diretorioImagens);

                if (arquivoImagem.exists() && arquivoImagem.isDirectory()) {
                    // Itera sobre os arquivos dentro do diretório
                    for (File arquivo : arquivoImagem.listFiles()) {
                        // Verifica se o nome do arquivo corresponde ao nome que estamos buscando
                        if (arquivo.getName().equals(nomeArquivo)) {
                            // Exclui o arquivo encontrado
                            if (arquivo.delete()) {
                                response.setStatus(HttpServletResponse.SC_OK);
                                response.getWriter().write("Imagem excluída com sucesso!");
                                System.out.println("Imagem excluída: " + caminhoImagem);
                            } else {
                                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                response.getWriter().write("Falha ao excluir a imagem.");
                                System.err.println("Falha ao excluir a imagem: " + caminhoImagem);
                            }
                            break; // Saímos do loop após excluir o arquivo
                        }
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("Diretório de imagens não encontrado.");
                    return;
                }

                if (ProdutoDAO.ExcluirImagem(nomeArquivo)) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Imagem excluída com sucesso!");
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("Erro ao excluir imagem!");
                }
            } else {
                processarImagens(request, Integer.parseInt(codProdutoParam));
            }

            response.sendRedirect(request.getContextPath() + "/listarProdutos");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Ocorreu um erro ao processar a requisição.");
        }
    }

    // Método para dividir a lista de produtos em listas de tamanho fixo
    private List<List<Produto>> dividirProdutosEmListas(List<Produto> todosOsProdutos) {
        List<List<Produto>> listaDeListasDeProdutos = new ArrayList<>();
        List<Produto> subListaDeProdutos = new ArrayList<>();

        for (Produto produto : todosOsProdutos) {
            subListaDeProdutos.add(produto);
            if (subListaDeProdutos.size() == 10) {
                listaDeListasDeProdutos.add(subListaDeProdutos);
                subListaDeProdutos = new ArrayList<>();
            }
        }

        if (!subListaDeProdutos.isEmpty()) {
            listaDeListasDeProdutos.add(subListaDeProdutos);
        }

        return listaDeListasDeProdutos;
    }

    // Método para calcular a página atual com base na ação do usuário
    private int calcularPagina(List<List<Produto>> listaDeListasDeProdutos, int pagina, String action) {
        if (action != null) {
            switch (action) {
                case "firstPage":
                    return 1;
                case "prevPage":
                    return pagina - 1;
                case "nextPage":
                    return pagina + 1;
                case "lastPage":
                    return listaDeListasDeProdutos.size();
                default:
                    return 1;
            }
        }

        return pagina;
    }

    // Método para processar o upload de imagens
    private void processarImagens(HttpServletRequest request, int produtoId) throws IOException, ServletException {
        List<Part> fileParts = request.getParts().stream()
                .filter(part -> "selImagem".equals(part.getName()))
                .collect(Collectors.toList());

        String diretorio = "imagens";
        String diretorioAbsoluto = getServletContext().getRealPath("/" + diretorio);

        File diretorioImagens = new File(diretorioAbsoluto);
        if (!diretorioImagens.exists()) {
            diretorioImagens.mkdirs();
        }

        boolean primeiraImagem = true;
        for (Part filePart : fileParts) {
            String fileName = extractFileName(filePart);
            if (!fileName.isEmpty()) {
                String novoNome = "imagem_" + System.currentTimeMillis() + "_" + fileName;
                String filePath = diretorioAbsoluto + File.separator + novoNome;
                filePart.write(filePath);

                Imagem imagem = new Imagem();
                imagem.setProdutoId(produtoId);
                imagem.setDiretorio(diretorio);
                imagem.setNome(novoNome);
                imagem.setExtensao(fileName.substring(fileName.lastIndexOf(".") + 1));
                imagem.setQualificacao(primeiraImagem);

                ProdutoDAO.AdicionarImagem(imagem);
                primeiraImagem = false;
            }
        }
    }

    // Método para extrair o nome do arquivo de uma parte da solicitação (request)
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
