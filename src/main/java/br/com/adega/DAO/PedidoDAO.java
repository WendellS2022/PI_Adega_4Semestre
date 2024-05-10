package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.Pedido;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    public static int inserirPedido(Pedido pedido) {
        Connection connection = null;
        PreparedStatement pedidoStatement = null;
        ResultSet generatedKeys = null;
        LocalDate dataPedido = LocalDate.now();


        int pedidoId = -1;

        try {
            connection = ConnectionPoolConfig.getConnection();

            // Prepara a query SQL para inserir um pedido
            String sqlInsertPedido = "INSERT INTO Pedidos (IdCliente, IdEndereco, SubTotal, QuantidadeDeItens, TipoPagamento, StatusPagamento,Frete, DataPedido) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pedidoStatement = connection.prepareStatement(sqlInsertPedido, Statement.RETURN_GENERATED_KEYS);


            pedidoStatement.setInt(1, pedido.getIdCliente());
            pedidoStatement.setInt(2, pedido.getIdEndereco());
            pedidoStatement.setString(3, pedido.getSubTotal());
            pedidoStatement.setInt(4, pedido.getQuantidadeDeItens());
            pedidoStatement.setString(5, pedido.getTipoPagamento());
            pedidoStatement.setString(6, pedido.getStatusPagamento());
            pedidoStatement.setString(7, pedido.getFrete());
            pedidoStatement.setDate(8, Date.valueOf(dataPedido));

            // Executa o insert do pedido
            int affectedRows = pedidoStatement.executeUpdate();

            if (affectedRows > 0) {
                // Obtém o ID gerado do pedido
                generatedKeys = pedidoStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    pedidoId = generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Trata a exceção imprimindo o stack trace
        } finally {
            // Fecha o ResultSet, statement e a conexão
            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

        return pedidoId;
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

    public static List<Pedido> obterTodosOsPedidosPorIdCliente(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido = new Pedido();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Estabelece a conexão com o banco de dados
            connection = ConnectionPoolConfig.getConnection();

            // Prepara a query SQL para selecionar todos os pedidos de um cliente específico
            String sql = "SELECT * FROM Pedidos WHERE IdCliente = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCliente);

            // Executa a query e obtém o resultado
            resultSet = statement.executeQuery();

            // Itera sobre o resultado e adiciona cada pedido à lista de pedidos
            while (resultSet.next()) {
                // Obtém os dados do pedido do resultado da query
                int pedidoId = resultSet.getInt("PedidoId");
                LocalDate dataPedido = LocalDate.parse(resultSet.getString("DataPedido"));
                int quantidadeDeItens = resultSet.getInt("QuantidadeDeItens");
                BigDecimal subtotal = resultSet.getBigDecimal("SubTotal");
                String statusPagamento = resultSet.getString("StatusPagamento");

         pedido.setPedidoId(pedidoId);
         pedido.setDataPedido(dataPedido);
         pedido.setQuantidadeDeItens(quantidadeDeItens);
         pedido.setSubTotal(String.valueOf(subtotal));
         pedido.setStatusPagamento(statusPagamento);

         pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trata a exceção imprimindo o stack trace
        } finally {
            // Fecha o statement, o resultSet e a conexão
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

        // Retorna a lista de pedidos
        return pedidos;
    }

}


