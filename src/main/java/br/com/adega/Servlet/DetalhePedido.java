package br.com.adega.Servlet;

import br.com.adega.DAO.PedidoDAO;
import br.com.adega.Model.ItemPedido;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/detalhePedido")
public class DetalhePedido extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));

        List<ItemPedido> itensPedido = PedidoDAO.obterInformacoesDoPedidoPorIdCliente(pedidoId);
      //  int quantidadeComprada = itensPedido.

        request.setAttribute("itensPedido", itensPedido);



        RequestDispatcher dispatcher = request.getRequestDispatcher("/DetalhePedido.jsp");
        dispatcher.forward(request, response);
    }
}
