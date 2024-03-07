package br.com.adega.Servlet;

import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.User;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@WebServlet("/cadastrar")
public class CadastrarUsuario extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User usuario = new User();


        usuario.setEmail(request.getParameter("email"));
        usuario.setNome(request.getParameter("nome"));
        String cpf = request.getParameter("cpf");
        usuario.setCPF(limparCPF(cpf));
        usuario.setGrupo(Integer.parseInt(request.getParameter("grupo")));
        String senha = request.getParameter("senha");
        String senhaConfirmacao = request.getParameter("senha-2");

        if (!senha.equals(senhaConfirmacao)) {
            request.setAttribute("mensagem", "Senhas não correspondem");
        } else {
            String senhaCriptografada = encoder.encode(senha);
//            boolean isUser = UsuarioDAO.verificarUsuario(usuario.getEmail());
            String alteracao = (String) request.getParameter("userId");
            if (alteracao.isEmpty()) {
//            if (!isUser) {
                usuario.setSituacao(true);
                usuario.setSenha(senhaCriptografada);

                boolean sucesso = UsuarioDAO.CadastrarUsuario(usuario);
                if (sucesso) {
                    request.setAttribute("mensagem", "Usuário cadastrado com sucesso!");
                } else {
                    request.setAttribute("mensagem", "Usuário já cadastrado!");
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp");
                dispatcher.forward(request, response);
            } else {
                usuario.setSituacao(true);
                usuario.setSenha(senhaCriptografada);

                boolean updateUser = UsuarioDAO.AlterarUsuario(usuario);
                if (updateUser) {
                    request.setAttribute("mensagem", "Usuário alterado com sucesso!");
                } else {
                    request.setAttribute("mensagem", "Falha ao alterar usuário!");
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp");
                dispatcher.forward(request, response);

            }

        }
    }

    public String limparCPF(String cpf) {
        // Remove todos os caracteres que não são números
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf;
    }
}
