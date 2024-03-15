package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Produto;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/cadastrarProduto")
public class CadastrarProduto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém o ID do produto da requisição
        String codProdutoParam = request.getParameter("COD_PRODUTO");

        if (codProdutoParam != null && !codProdutoParam.isEmpty()) {
            // Se o ID do produto está presente, é uma edição
            int codProduto = Integer.parseInt(codProdutoParam);
            Produto produto = ProdutoDAO.ObterProdutoPorId(codProduto);
            request.setAttribute("produto", produto);
        }

        // Encaminha para a página de cadastro/edição de produtos
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Produto produto = new Produto();
        String codProduto = request.getParameter("id");

        if (!codProduto.isEmpty()) {
            produto.setCodProduto(Integer.parseInt(codProduto));
            produto.setNomeProduto(request.getParameter("nomeProduto"));
            produto.setDscDetalhadaProduto(request.getParameter("dscDetalhadaProduto"));
            produto.setAvaliacaoProduto(Double.parseDouble(request.getParameter("avaliacaoProduto")));
            produto.setVlrVendaProduto(Double.parseDouble(request.getParameter("vlrVendaProduto")));
            produto.setQtdEstoque(Integer.parseInt(request.getParameter("qtdEstoque")));

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
                String fileName = filePart.getName();
                String directory = "/PI_Adega_4Semestre/src/main/webapp/imgProdutos";
                String filePath = directory + File.separator + fileName;
                filePart.write(filePath);


                // Atualizar o banco de dados com o caminho da imagem
                Imagem imagem = new Imagem();
                imagem.setProdutoId(produto.getCodProduto());
                imagem.setDiretorio(directory);
                imagem.setNome(fileName);
                imagem.setExtensao(fileName.substring(fileName.lastIndexOf(".") + 1));
                ProdutoDAO.AdicionarImagem(imagem);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarProduto.jsp");
            dispatcher.forward(request, response);
        }
    }
}
