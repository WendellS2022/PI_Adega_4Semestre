package br.com.adega.Servlet;

import br.com.adega.DAO.EnderecoDAO;
import br.com.adega.Model.Endereco;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Enderecos")
public class ListarEndereco extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String clienteLogado = request.getParameter("email");



        EnderecoDAO enderecoDAO = new EnderecoDAO();


        List<Endereco> enderecos = enderecoDAO.obterEnderecosPorEmailCliente(clienteLogado);

        Endereco enderecoFaturamento =  EnderecoDAO.obterEnderecoFaturamentoPorEmailCliente(clienteLogado);

        request.setAttribute("enderecoFaturamento", enderecoFaturamento);
        request.setAttribute("enderecos", enderecos);
        request.setAttribute("clienteLogado", clienteLogado);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarEnderecos.jsp");
        dispatcher.forward(request, response);
    }
}