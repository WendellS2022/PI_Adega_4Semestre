package br.com.adega.Servlet;

import br.com.adega.DAO.PedidoDAO;
import br.com.adega.Model.Endereco;
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
        String subTotal = null;
        String frete = null;
        Endereco endereco = new Endereco();
        String statusPagamento = null;
        String tipoPagamento = null;
        String subTotalDispacher = null;


        List<ItemPedido> itensPedido = PedidoDAO.obterInformacoesDoPedidoPorIdCliente(pedidoId);
        //  int quantidadeComprada = itensPedido.
        for (ItemPedido item : itensPedido) {
            subTotal = String.valueOf(item.getSubTotal());
            Double subTotalInt = Double.parseDouble(subTotal);
            frete = String.valueOf(item.getFrete());
            String[] parts = frete.split(" - ");
            if (parts.length > 1) {
                // Obtemos a segunda parte do array, que deve conter o valor do frete
                String fretePart = parts[1];

                // Removemos o "R$ " do valor do frete
                String freteDispacher = fretePart.replace("R$ ", "");

                // Substituímos a vírgula por um ponto
                freteDispacher = freteDispacher.replace(",", ".");

                Double freteDouble = Double.valueOf(freteDispacher);
                Double subTotaMaisFrete = Double.valueOf(freteDouble + subTotalInt);
                subTotalDispacher = String.valueOf(subTotaMaisFrete);
                subTotalDispacher = subTotalDispacher.replace(".", ",");

                endereco = item.getEndereco();
                statusPagamento = item.getStatusPagamento();
                tipoPagamento = item.getTipoPagamento();

                break;
            }
        }

            request.setAttribute("itensPedido", itensPedido);
            request.setAttribute("subTotal", subTotalDispacher);
            request.setAttribute("frete", frete);
            request.setAttribute("endereco", endereco);
            request.setAttribute("statusPagamento", statusPagamento);
            request.setAttribute("tipoPagamento", tipoPagamento);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/DetalhePedido.jsp");
            dispatcher.forward(request, response);
        }
    }

