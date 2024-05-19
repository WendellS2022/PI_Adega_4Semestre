package br.com.adega.DAO;


import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.Carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemPedidoDAO {

    public static boolean inserirItemPedido(List<Carrinho> produtosCarrinho, int pedidoId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPoolConfig.getConnection();
            String sqlInsertItemPedido = "INSERT INTO ItemPedido (PedidoId, ProdutoId, Quantidade, Valor) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sqlInsertItemPedido);

            for (Carrinho produtoCarrinho : produtosCarrinho) {
                statement.setInt(1, pedidoId);
                statement.setInt(2, produtoCarrinho.getProduto().getCodProduto());
                statement.setInt(3, produtoCarrinho.getQuantidadeComprada());
                statement.setBigDecimal(4, produtoCarrinho.getProduto().getVlrVendaProduto());
                statement.addBatch();
            }

            statement.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

