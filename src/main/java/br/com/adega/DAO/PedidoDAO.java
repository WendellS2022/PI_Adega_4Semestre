package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PedidoDAO {
    public static boolean inserirPedido(Pedido pedido, List<Integer> idsProdutosCarrinho) {
        Connection connection = null;
        PreparedStatement pedidoStatement = null;
        int ultimoPedidoId = obterUltimoPedidoId();
        ultimoPedidoId += 1;

        try {
            connection = ConnectionPoolConfig.getConnection();

            // Prepara a query SQL para inserir um pedido
            String sqlInsertPedido = "INSERT INTO Pedidos (PedidoId, IdCliente, ProdutoId, IdEndereco, TipoPagamento, StatusPagamento) VALUES (?, ?, ?, ?, ?, ?)";
            pedidoStatement = connection.prepareStatement(sqlInsertPedido);

            // Obtém os valores do pedido
            int pedidoId = pedido.getProdutoId();
            int idCliente = pedido.getIdCliente();
            int idEndereco = pedido.getIdEndereco();
            String tipoPagamento = pedido.getTipoPagamento();
            String statusPagamento = pedido.getStatusPagamento();

            // Itera sobre os IDs dos produtos do carrinho
            for (int idProduto : idsProdutosCarrinho) {
                // Define os valores do pedido
                pedidoStatement.setInt(1, ultimoPedidoId);
                pedidoStatement.setInt(2, idCliente);
                pedidoStatement.setInt(3, idProduto);
                pedidoStatement.setInt(4, idEndereco);
                pedidoStatement.setString(5, tipoPagamento);
                pedidoStatement.setString(6, statusPagamento);

                // Executa o insert do pedido
                pedidoStatement.executeUpdate();
            }

            return true; // Sucesso ao inserir os pedidos
        } catch (SQLException e) {
            e.printStackTrace(); // Trata a exceção imprimindo o stack trace
            return false; // Falha ao inserir os pedidos
        } finally {
            // Fecha o statement e a conexão
            if (pedidoStatement != null) {
                try {
                    pedidoStatement.close();
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

    public static int obterUltimoPedidoId() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPoolConfig.getConnection();

            // Query SQL para obter o último pedidoId
            String sql = "SELECT MAX(PedidoId) AS UltimoPedidoId FROM Pedidos";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retorna o valor do último pedidoId
                return resultSet.getInt("UltimoPedidoId");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Trata a exceção imprimindo o stack trace
        } finally {
            // Fecha o statement, resultSet e a conexão
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

        // Retorna -1 se não houver nenhum pedido na tabela
        return 0;
    }
}


