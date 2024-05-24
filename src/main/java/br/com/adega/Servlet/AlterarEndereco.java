package br.com.adega.Servlet;

import br.com.adega.DAO.ClienteDAO;
import br.com.adega.DAO.EnderecoDAO;
import br.com.adega.DAO.ProdutoDAO;
import br.com.adega.Model.Endereco;
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
import java.util.List;

@WebServlet("/alterarEndereco")
public class AlterarEndereco extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailCliente = request.getParameter("clienteLogado");

        boolean adcionar = true;
        request.setAttribute("clienteLogado", emailCliente);
        request.setAttribute("adicionar", adcionar);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarEndereco.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String emailCliente = request.getParameter("clienteLogado");


        int idCliente = new ClienteDAO().buscarIdClienteEmail(emailCliente);

        List<Endereco> enderecos = EnderecoDAO.obterEnderecosPorEmailCliente(emailCliente);


        if (idCliente != -1) {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            // Obtém os parâmetros do formulário
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

            if (enderecos == null || enderecos.isEmpty()) {
                endereco.setPadrao(true);
                Endereco enderecoFaturamento = EnderecoDAO.obterEnderecoFaturamentoPorEmailCliente(emailCliente);
                if (enderecoFaturamento != null)
                    endereco.setEnderecoFaturamento(false);
            } else {
                endereco.setPadrao(false);
                endereco.setEnderecoFaturamento(false);
            }

            endereco.setIdCliente(idCliente); // Define o ID do cliente

            // Salva o endereço no banco de dados
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            enderecoDAO.salvarEndereco(endereco);


            request.setAttribute("isCliente", true);

            response.sendRedirect(request.getContextPath() + "/TelaProdutos?clienteLogado=" + emailCliente);
        } else {
            // Se o ID do cliente não foi encontrado, redirecione para uma página de erro ou trate de outra forma
            response.sendRedirect("/erro.jsp");
        }
    }
}

