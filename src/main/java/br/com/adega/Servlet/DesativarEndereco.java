package br.com.adega.Servlet;

import br.com.adega.DAO.EnderecoDAO;
import br.com.adega.Model.Endereco;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/desativarEndereco")
public class DesativarEndereco extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailCliente = request.getParameter("clienteLogado");
        int idEndereco = Integer.parseInt(request.getParameter("idEndereco"));

        boolean sucesso = EnderecoDAO.DesativarEndereco(idEndereco);

        if (sucesso) {
            List<Endereco> enderecos = EnderecoDAO.obterEnderecosPorEmailCliente(emailCliente);
            if (enderecos != null && enderecos.size() == 1) {
                Endereco endereco = enderecos.get(0);
                endereco.setPadrao(true);
                boolean padrao = false;
                EnderecoDAO.atualizarEndereco(endereco, padrao);
            }
        }
        response.sendRedirect(request.getContextPath() + "/Enderecos?email=" + emailCliente);
    }
}
