package br.com.adega.Servlet;

import br.com.adega.DAO.CarrinhoDAO;
import br.com.adega.DAO.ClienteDAO;
import br.com.adega.DAO.EnderecoDAO;
import br.com.adega.DAO.PedidoDAO;
import br.com.adega.Model.Carrinho;
import br.com.adega.Model.Endereco;
import br.com.adega.Model.Pedido;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/finalizarCompra")
public class FinalizarCompra  extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        String clienteLogado = request.getParameter("clienteLogado");
        HttpSession session = request.getSession(true);
        List<Carrinho> produtosCarrinho = new ArrayList<>();
        Date dataAtual = new Date();


        int idCliente = ClienteDAO.buscarIdClienteEmail(clienteLogado);
        produtosCarrinho = CarrinhoDAO.obterProdutosCarrinhoPorIdCliente(idCliente);
        Endereco enderecoPadrao = EnderecoDAO.obterEnderecoPadraoPorIdCliente(String.valueOf(idCliente));



        Pedido pedido =new Pedido();
        pedido.setIdCliente(idCliente);
        pedido.setIdEndereco(enderecoPadrao.getIdEndereco());
        pedido.setSubTotal(request.getParameter("subtotal"));
        pedido.setQuantidadeDeItens(Integer.parseInt(request.getParameter("totalDeItens")));
        pedido.setFrete(request.getParameter("frete"));
        pedido.setDataPedido(dataAtual);
        pedido.setTipoPagamento(request.getParameter("pagamento"));
        pedido.setStatusPagamento("Pendente");

        //int  pedidoId = PedidoDAO.inserirPedido(pedido);

//        List<Integer> idsProdutosCarrinho = new ArrayList<>();
//        produtosCarrinho.forEach(produtoCarrinho -> idsProdutosCarrinho.add(produtoCarrinho.getProduto().getCodProduto()));

//        boolean sucesso = PedidoDAO.inserirPedido(pedido, idsProdutosCarrinho);

//        if(sucesso)
//           CarrinhoDAO.excluirCarrinhoPorIdCliente(idCliente);

    }
}

