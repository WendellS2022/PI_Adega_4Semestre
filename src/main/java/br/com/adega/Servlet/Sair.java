package br.com.adega.Servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sair")
public class Sair extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(false); // Não cria uma nova sessão se não houver uma existente
            if (session != null) {
                session.invalidate(); // Invalida a sessão existente
            }
            response.sendRedirect("/TelaDeProdutos.jsp"); // Redireciona para a página de login
        }
    }

