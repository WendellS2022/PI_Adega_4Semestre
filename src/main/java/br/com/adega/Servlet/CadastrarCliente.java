package br.com.adega.Servlet;

import br.com.adega.DAO.ClienteDAO;
import br.com.adega.Model.Cliente;
import br.com.adega.Model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/CadastrarCliente")
public class CadastrarCliente extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarCliente.jsp");
        dispatcher.forward(request, response);
}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Cliente cliente = new Cliente();

        cliente.setNome(request.getParameter("nome"));
        String Cpf = request.getParameter("cpf");
        cliente.setCpf(limparCPF(Cpf));
        cliente.setEmail(request.getParameter("email"));
        cliente.setGenero(request.getParameter("genero"));

        String senhaCriptografada = encoder.encode(request.getParameter("senha"));
        cliente.setSenha(senhaCriptografada);

        cliente.setDataNascimento(request.getParameter("dataNascimento"));


        int idCliente = ClienteDAO.CadastrarCliente(cliente);
        if(idCliente > 0){

            request.setAttribute("idCliente", idCliente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarEndereco.jsp");
            dispatcher.forward(request, response);

        }


    }
    public String limparCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf;
    }

}
