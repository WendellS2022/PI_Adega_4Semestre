package br.com.adega.Servlet;
import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Endereco;

import br.com.adega.DAO.EnderecoDAO;
import br.com.adega.Model.Imagem;
import br.com.adega.Model.Produto;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/CadastrarEndereco")
public class CadastrarEndereco extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarEndereco.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, List<Imagem>> imagensPorProduto = new HashMap<>();


        // Obtém os parâmetros do formulário
        String idCliente = (request.getParameter("idCliente"));
        String cep = request.getParameter("cep");
        String logradouro = request.getParameter("logradouro");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");

        // Cria um objeto Endereco com os dados do formulário
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(Integer.parseInt(numero));
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(estado);
        endereco.setStatus(true);
        endereco.setPadrao(true);
        endereco.setEnderecoFaturamento(true);
        endereco.setIdCliente(Integer.parseInt(idCliente));


        // Salva o endereço no banco de dados
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.salvarEndereco(endereco);

        // Define os atributos no request para que possam ser acessados na página JSP
        request.setAttribute("endereco", endereco);

        List<Produto> produtos = ProdutoDAO.ObterTodosOsProdutos();


        for (Produto produto : produtos) {
            List<Imagem> imagens = ProdutoDAO.obterImagensPorProdutoId(produto.getCodProduto());
            List<Imagem> imagensQualificadas = new ArrayList<>();

            for (Imagem imagem : imagens) {
                if (imagem.isQualificacao()) {
                    imagensQualificadas.add(imagem);
                }
            }

            imagensPorProduto.put(produto.getCodProduto(), imagensQualificadas);
        }

        request.setAttribute("imagensPorProduto", imagensPorProduto);
        request.setAttribute("produtos", produtos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaDeProdutos.jsp");
        dispatcher.forward(request, response);
    }

}
