package br.com.adega.Servlet;

import br.com.adega.DAO.ClienteDAO;
import br.com.adega.DAO.PedidoDAO;
import br.com.adega.Model.Carrinho;
import br.com.adega.Model.ItemPedido;
import br.com.adega.Model.Pedido;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listarPedidos")
public class ListarPedidos extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String clienteLogado = (String) session.getAttribute("clienteLogado");
        String subTotal = null;
        String frete = null;
        String subTotalDispacher = null;

        List<ItemPedido> itensPedido = new ArrayList<>();
        List<Pedido> meusPedidos = new ArrayList<>();

        int idCliente = ClienteDAO.buscarIdClienteEmail(clienteLogado);
        meusPedidos = PedidoDAO.obterTodosOsPedidosPorIdCliente(idCliente);
        for (Pedido pedido : meusPedidos) {
            subTotal = String.valueOf(pedido.getSubTotal());
            Double subTotalDouble = Double.parseDouble(subTotal);
            frete = pedido.getFrete();
            String[] parts = frete.split(" - ");
            if (parts.length > 1) {
                String fretePart = parts[1].replace("R$ ", "").replace(",", ".");
                Double freteDouble = Double.valueOf(fretePart);
                Double subTotalMaisFrete = freteDouble + subTotalDouble;
                subTotalDispacher = String.valueOf(subTotalMaisFrete).replace(".", ",");

                // Atualiza o subtotal do pedido com o valor calculado
                pedido.setSubTotal(subTotalDispacher);
            }
        }

        request.setAttribute("pedidos", meusPedidos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarPedidos.jsp");
        dispatcher.forward(request, response);
    }
}