package br.com.adega.Servlet;


import br.com.adega.DAO.PedidoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/atualizarStatusPedido")
public class AtualizarStatusPedido extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pedidoIdStr = request.getParameter("pedidoId");
        String status = request.getParameter("status");

        if (pedidoIdStr != null && status != null) {
            int pedidoId = Integer.parseInt(pedidoIdStr);

            // Atualiza o status do pedido no banco de dados
            boolean sucesso = PedidoDAO.atualizarStatusPedido(pedidoId, status);

            if (sucesso) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

