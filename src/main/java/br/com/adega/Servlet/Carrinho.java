package br.com.adega.Servlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    @WebServlet("/Carrinho")
    public class Carrinho extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Carrinho.jsp");
            dispatcher.forward(request, response);
        }
    }


