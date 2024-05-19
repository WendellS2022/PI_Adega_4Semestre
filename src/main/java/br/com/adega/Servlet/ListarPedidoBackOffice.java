package br.com.adega.Servlet;

import br.com.adega.DAO.PedidoDAO;
import br.com.adega.Model.Pedido;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/listarPedidosBackOffice")
public class ListarPedidoBackOffice extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           List<Pedido> pedidos = new ArrayList<>();

            pedidos = PedidoDAO.obterTodosOsPedidos();

            request.setAttribute("pedidos", pedidos);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarPedidosBackOffice.jsp");
            dispatcher.forward(request, response);
        }
    }
