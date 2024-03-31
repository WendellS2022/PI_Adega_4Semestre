package br.com.adega.Servlet;

import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Imagem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/gerenciarImagem")
public class GerenciarImagens extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codProduto = Integer.parseInt(request.getParameter("codProduto"));
        List<Imagem> imagensProduto = ProdutoDAO.obterImagensPorProdutoId(codProduto); // Implemente essa lógica conforme necessário

        if (!imagensProduto.isEmpty()) {
            request.setAttribute("imagensProduto", imagensProduto);
        }
        request.setAttribute("codProduto", codProduto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/GerenciarImagem.jsp");
        dispatcher.forward(request, response);
    }
}

