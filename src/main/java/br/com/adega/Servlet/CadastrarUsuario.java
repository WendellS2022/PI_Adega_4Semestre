package br.com.adega.Servlet;

import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.User;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@WebServlet("/cadastrar")
public class CadastrarUsuario extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String isSession = (String) session.getAttribute("usuarioLogado");

        if (isSession != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp");
            dispatcher.forward(request, response);
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaLogin.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User usuario = new User();

        String isSession = (String) session.getAttribute("usuarioLogado");
        boolean senhasCorrespondem = false;

        String userIdParam = request.getParameter("userId");
        do {
            if (!userIdParam.isBlank()) {
                usuario.setUserId(Integer.parseInt(userIdParam));
            }

            usuario.setEmail(request.getParameter("email"));
            usuario.setNome(request.getParameter("nome"));
            String cpf = request.getParameter("cpf");
            usuario.setCPF(limparCPF(cpf));

            try {
                int grupo = Integer.parseInt(request.getParameter("grupo"));
                if (grupo != 0)
                    usuario.setGrupo(grupo);
            } catch (NumberFormatException e) {
                int grupoBanco = UsuarioDAO.ObterGrupo(usuario.getEmail());
                usuario.setGrupo(grupoBanco);
            }

            String senha = request.getParameter("senha");
            String senhaConfirmacao = request.getParameter("senha-2");

            if (!senha.equals(senhaConfirmacao)) {
                request.setAttribute("mensagem", "Senhas não correspondem");


                usuario.setSenha("");
                request.setAttribute("user", usuario);
                request.setAttribute("isSession", isSession);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp");
                dispatcher.forward(request, response);

                return;
            } else {
                senhasCorrespondem = true;
                String senhaCriptografada = encoder.encode(senha);
                usuario.setSenha(senhaCriptografada);
            }
        } while (!senhasCorrespondem);

        if (isSession != null) {
            if (userIdParam.isBlank()) {
                usuario.setSituacao(true);
                boolean sucesso = UsuarioDAO.CadastrarUsuario(usuario);
                if (sucesso) {
                    request.setAttribute("mensagem", "Usuário cadastrado com sucesso!");
                } else {
                    request.setAttribute("mensagem", "Usuário já cadastrado!");
                }
            } else {
                usuario.setSituacao(true);
                boolean updateUser = UsuarioDAO.AlterarUsuario(usuario);
                if (updateUser) {
                    request.setAttribute("mensagem", "Usuário alterado com sucesso!");
                } else {
                    request.setAttribute("mensagem", "Falha ao alterar usuário!");
                }
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp");
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaLogin.jsp");
            dispatcher.forward(request, response);
        }
    }

    public String limparCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf;
    }
}