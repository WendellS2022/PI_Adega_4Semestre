package br.com.adega.Servlet;

import br.com.adega.DAO.UsuarioDAO;
import br.com.adega.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/listar")
public class ListarUsuario extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomePesquisa = request.getParameter("nome-pesquisa");
        List<User> usuarios;

        if (nomePesquisa != null && !nomePesquisa.isEmpty()) {
            usuarios = UsuarioDAO.ObterUsuarioPorNome(nomePesquisa);

        } else {
            usuarios = UsuarioDAO.ObterUsuarios();
        }

        request.setAttribute("usuarios", usuarios);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarUsuario.jsp");
        dispatcher.forward(request, response);
    }

}



