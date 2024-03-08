package br.com.adega.Servlet;

import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.User;

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
        String isSession = (String) session.getAttribute("usuarioLogado");
        int userIdParam = Integer.parseInt(request.getParameter("userId"));


        User user = UsuarioDAO.ObterUsuarioPorId(userIdParam);

        if (isSession.equals(user.getEmail())) {
            request.setAttribute("isSession", isSession);

            request.setAttribute("user", user);

            request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp").forward(request, response);
        } else {
            request.setAttribute("user", user);

            request.getRequestDispatcher("/CadastrarAlterarUsuario.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String isSession = (String) session.getAttribute("usuarioLogado");
        String userId = request.getParameter("userId");

        boolean isSucess = UsuarioDAO.AtualizarStatus(userId);

        List<User> usuarios = UsuarioDAO.ObterUsuarios();

        request.setAttribute("isSession", isSession);
        request.setAttribute("usuarios", usuarios);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarUsuario.jsp");
        dispatcher.forward(request, response);
    }
}


