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
        usuario.setCPF(request.getParameter("cpf"));
        usuario.setGrupo(Integer.parseInt(request.getParameter("grupo")));
        String senha = request.getParameter("senha");
        String senhaConfirmacao = request.getParameter("senha-2");

        if (!senha.equals(senhaConfirmacao)) {
            request.setAttribute("mensagem", "Senhas não correspondem");
        } else  {
            boolean isUser = UsuarioDAO.verificarUsuario(usuario.getEmail());
            if (!isUser) {
                String senhaCriptografada = encoder.encode(senha);
                usuario.setSituacao(true);
                usuario.setSenha(senhaCriptografada);

                boolean sucesso = UsuarioDAO.cadastrarUsuario(usuario);
            }
        }
    }
}

