package br.com.adega.Servlet;

import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/alterarUsuario")
public class AlterarUsuario extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            int usuarioIdParam = Integer.parseInt(request.getParameter("usuarioId"));


            Usuario usuario = UsuarioDAO.ObterUsuarioPorId(usuarioIdParam);

            if (usuarioLogado.equals(usuario.getEmail())) {
                request.setAttribute("sessao", usuarioLogado);

                request.setAttribute("usuario", usuario);

                request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp").forward(request, response);
            } else {
                request.setAttribute("usuario", usuario);

                request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp").forward(request, response);
            }
        }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/TelaLogin.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String usuarioLogado = (String) session.getAttribute("usuarioLogado");
        String usuarioId = request.getParameter("usuarioId");


        boolean statusAtualizado = UsuarioDAO.AtualizarStatus(usuarioId);

        List<Usuario> usuarios = UsuarioDAO.ObterUsuarios();

        request.setAttribute("statusAtualizado", statusAtualizado);
        request.setAttribute("usuarios", usuarios);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarUsuario.jsp");
        dispatcher.forward(request, response);
    }
}


