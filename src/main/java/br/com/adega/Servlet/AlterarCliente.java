package br.com.adega.Servlet;

import br.com.adega.DAO.ClienteDAO;
import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Cliente;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/alterarCliente")
public class AlterarCliente extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém o email do cliente logado da sessão
        String emailCliente = (String) request.getSession().getAttribute("clienteLogado");

        // Verifica se o cliente está logado
        if (emailCliente != null) {
            // Busca os dados do cliente pelo email
            Cliente cliente = ClienteDAO.VerificarCredenciais(emailCliente);

            // Define o objeto cliente como um atributo de requisição
            request.setAttribute("clienteLogado", emailCliente);
            request.setAttribute("cliente", cliente);

            // Encaminha a requisição para o JSP de alteração de dados

            request.getRequestDispatcher("/AlterarDadosCliente.jsp").forward(request, response);

        } else {
            // Se o cliente não estiver logado, redireciona para a página de login
            response.sendRedirect("/login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Cliente cliente = new Cliente();

        String clienteLogado = request.getParameter("clienteLogado");

        if(clienteLogado!= null){

         cliente.setNome(request.getParameter("nome"));
         cliente.setDataNascimento(request.getParameter("dataNascimento"));
         cliente.setGenero(request.getParameter("genero"));
         cliente.setSenha(request.getParameter("senha"));
         cliente.setEmail(clienteLogado);

         if (!cliente.getSenha().isEmpty() ){

             String senhaCriptografada = encoder.encode(request.getParameter("senha"));
             cliente.setSenha(senhaCriptografada);
         }

            boolean sucesso = ClienteDAO.AtualizarCliente(cliente);

           if(sucesso){

               request.setAttribute("cliente", cliente);

               // Encaminha a requisição para o JSP de alteração de dados

               response.sendRedirect(request.getContextPath() + "/TelaProdutos?clienteLogado=" + clienteLogado);



           } else {
            // Se o cliente não estiver logado, redireciona para a página de login
            response.sendRedirect("/login.jsp");
        }
    }


    }
}

