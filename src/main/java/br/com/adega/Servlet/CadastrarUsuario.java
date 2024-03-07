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
        } else {
            boolean isUser = UsuarioDAO.verificarUsuario(usuario.getEmail());
            String senhaCriptografada = encoder.encode(senha);
            if (!isUser) {
                usuario.setSituacao(true);
                usuario.setSenha(senhaCriptografada);

                boolean sucesso = UsuarioDAO.CadastrarUsuario(usuario);
                request.setAttribute("mensagem", "Usuário cadastrado com sucesso!");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp");
                dispatcher.forward(request, response);
            } else {
                usuario.setSituacao(true);
                usuario.setSenha(senhaCriptografada);

                boolean updateUser = UsuarioDAO.AlterarUsuario(usuario);
                request.setAttribute("mensagem", "Usuário alterado com sucesso!");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}

