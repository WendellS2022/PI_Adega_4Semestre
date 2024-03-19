package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Imagem;
import br.com.adega.Model.Produto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/cadastrarProduto")
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
            dispatcher.forward(request, response);
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

            // Processar o upload de imagens
            List<Part> fileParts = request.getParts().stream().filter(part -> "selImagem".equals(part.getName())).collect(Collectors.toList());
            for (Part filePart : fileParts) {
                String fileName = extractFileName(filePart.getHeader("content-disposition"));
                String directory = request.getServletContext().getRealPath("/imgProdutos"); // Caminho absoluto do diretório de imagens
                String filePath = directory + File.separator + fileName;
                filePart.write(filePath);

                // Atualizar o banco de dados com o caminho da nova imagem
                Imagem imagem = new Imagem();
                imagem.setProdutoId(produto.getCodProduto());
                imagem.setDiretorio(directory);
                imagem.setNome(fileName);
                imagem.setExtensao(fileName.substring(fileName.lastIndexOf(".") + 1));
                ProdutoDAO.AdicionarImagem(imagem);
            }

            // Redirecionar para a página de cadastro/edição de produtos
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Função para extrair o nome do arquivo do cabeçalho Content-Disposition
    private String extractFileName(String contentDispositionHeader) {
        // O cabeçalho Content-Disposition é enviado no formato:
        // Content-Disposition: form-data; name="file"; filename="nome-do-arquivo.extensao"
        // Vamos extrair o valor dentro das aspas após "filename="
        String fileName = null;
        String[] parts = contentDispositionHeader.split(";");
        for (String part : parts) {
            if (part.trim().startsWith("filename")) {
                fileName = part.substring(part.indexOf("=") + 1).trim().replace("\"", "");
                break;
            }
        }
        return fileName;
    }
}
