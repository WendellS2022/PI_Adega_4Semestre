package br.com.adega.Servlet;

import br.com.adega.DAO.EnderecoDAO;
import br.com.adega.Model.Endereco;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/indicarEndereco")
public class IndicarEndereco extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailCliente = request.getParameter("clienteLogado");
        String idEndereco = (request.getParameter("idEndereco"));

        if(!idEndereco.isEmpty() || idEndereco != null){
            Endereco endereco = EnderecoDAO.obterEnderecoPorIdEndereco((Integer.parseInt(idEndereco)));
            if(endereco.isPadrao() == false){
                endereco.setPadrao(true);
                boolean padrao = true;
                EnderecoDAO.atualizarEndereco(endereco, padrao);

                response.sendRedirect(request.getContextPath() + "/Enderecos?email=" + emailCliente);

            }


        }
    }
}