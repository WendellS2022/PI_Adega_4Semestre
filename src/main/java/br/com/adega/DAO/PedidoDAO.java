package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.*;

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


    public static List<ItemPedido> obterInformacoesDoPedidoPorIdCliente(int pedidoId) {
        List<ItemPedido> itensPedido = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPoolConfig.getConnection();

            // Query SQL para obter o último pedidoId
            String sql = "SELECT  p.Subtotal, p.quantidadeDeItens, p.TipoPagamento, p.StatusPagamento, p.Frete, ip.ProdutoId, ip.Quantidade, ip.Valor, e.Cep, e.Logradouro, e.Numero, e.complemento, e.Bairro, e.Cidade, e.Uf, pd.ProdutoId, pd.Nome, pd.Descricao FROM Pedidos p \n" +
                    "JOIN ItemPedido ip ON p.PedidoId = ip.PedidoId \n" +
                    "JOIN Endereco e on  p.IdEndereco  = e.IdEndereco \n" +
                    "JOIN Produtos pd on ip.ProdutoId = pd.ProdutoId\n" +
                    "WHERE p.PedidoId = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, pedidoId);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ItemPedido itemPedido = new ItemPedido();
                Endereco endereco = new Endereco();

                itemPedido.setPedidoId(pedidoId);
                itemPedido.setSubTotal(resultSet.getBigDecimal("SubTotal"));
                itemPedido.setQuantidadeComprada(resultSet.getInt("QuantidadeDeItens"));
                itemPedido.setTipoPagamento(resultSet.getString("TipoPagamento"));
                itemPedido.setStatusPagamento(resultSet.getString("StatusPagamento"));
                itemPedido.setFrete(resultSet.getString("Frete"));

                endereco.setCep(resultSet.getString("Cep"));
                endereco.setLogradouro("Logradouro");
                endereco.setComplemento("Complemento");
                endereco.setBairro("Barirro");
                endereco.setCidade("Cidade");
                endereco.setUf("Uf");

                itemPedido.setEndereco(endereco);


                itemPedido.setProdutoId(String.valueOf(resultSet.getInt("ProdutoId")));
                itemPedido.setNomeProduto(resultSet.getString("Nome"));
                itemPedido.setPreco(resultSet.getBigDecimal("Valor"));
                itemPedido.setDescricaoProduto(resultSet.getString("Descricao"));
                itemPedido.setQuantidadeComprada(resultSet.getInt("Quantidade"));

                itensPedido.add(itemPedido);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

        return itensPedido;
    }

    public static List<Pedido> obterTodosOsPedidosPorIdCliente(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Estabelece a conexão com o banco de dados
            connection = ConnectionPoolConfig.getConnection();

            // Prepara a query SQL para selecionar todos os pedidos de um cliente específico
            String sql = "SELECT * FROM Pedidos WHERE IdCliente = ? ORDER BY PedidoId DESC";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCliente);

            // Executa a query e obtém o resultado
            resultSet = statement.executeQuery();

            // Itera sobre o resultado e adiciona cada pedido à lista de pedidos
            while (resultSet.next()) {

                Pedido pedido = new Pedido();

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

    public static List<Pedido> obterTodosOsPedidos() {
        List<Pedido> pedidos = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Estabelece a conexão com o banco de dados
            connection = ConnectionPoolConfig.getConnection();

            // Prepara a query SQL para selecionar todos os pedidos de um cliente específico
            String sql = "SELECT * FROM Pedidos";
            statement = connection.prepareStatement(sql);


            // Executa a query e obtém o resultado
            resultSet = statement.executeQuery();

            // Itera sobre o resultado e adiciona cada pedido à lista de pedidos
            while (resultSet.next()) {

                Pedido pedido = new Pedido();
                // Obtém os dados do pedido do resultado da query
                int pedidoId = resultSet.getInt("PedidoId");
                int idCliente = resultSet.getInt("IdCliente");
                LocalDate dataPedido = LocalDate.parse(resultSet.getString("DataPedido"));
                int quantidadeDeItens = resultSet.getInt("QuantidadeDeItens");
                BigDecimal subtotal = resultSet.getBigDecimal("SubTotal");
                String statusPagamento = resultSet.getString("StatusPagamento");

                pedido.setPedidoId(pedidoId);
                pedido.setIdCliente(idCliente);
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

    public static boolean atualizarStatusPedido(int pedidoId, String status) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPoolConfig.getConnection();
            String sql = "UPDATE Pedidos SET StatusPagamento = ? WHERE PedidoId = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, pedidoId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
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




