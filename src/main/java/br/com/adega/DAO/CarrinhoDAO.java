package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.Carrinho;
import br.com.adega.Model.Produto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO {
    public static void inserirProdutosCarrinho(List<Produto> produtos, int idCliente) {
        Connection connection = null;
        PreparedStatement carrinhoStatement = null;
        PreparedStatement produtoStatement = null;

        try {
            connection = ConnectionPoolConfig.getConnection();

            int idCarrinho = obterCarrinhoIdPorIdCliente(idCliente);

            if (idCarrinho <= 0) {
                idCarrinho += 1;

                String sqlInsertProduto = "INSERT INTO CARRINHO (IDCARRINHO, PRODUTOID, IDCLIENTE, QUANTIDADE, NOMEPRODUTO, DESCRICAO, VALOR) VALUES (?, ?, ?, ?, ?, ?, ?)";
                produtoStatement = connection.prepareStatement(sqlInsertProduto);
                for (Produto produto : produtos) {
                    produtoStatement.setInt(1, idCarrinho);
                    produtoStatement.setInt(2, produto.getCodProduto());
                    produtoStatement.setInt(3, idCliente);
                    produtoStatement.setInt(4, 1);
                    produtoStatement.setString(5, produto.getNomeProduto());
                    produtoStatement.setString(6, produto.getDscDetalhadaProduto());
                    produtoStatement.setBigDecimal(7, produto.getVlrVendaProduto());

                    produtoStatement.addBatch();
                }

                produtoStatement.executeBatch();
                return;
            }

            if (idCarrinho > 0) {
                produtos = verificarProdutosExistenteNoCarrinho(produtos, idCliente);

                String sqlInsertProduto = "INSERT INTO CARRINHO (IDCARRINHO, PRODUTOID, IDCLIENTE, QUANTIDADE, NOMEPRODUTO, DESCRICAO, VALOR) VALUES (?, ?, ?, ?, ?, ?, ?)";
                produtoStatement = connection.prepareStatement(sqlInsertProduto);

                for (Produto produto : produtos) {
                    produtoStatement.setInt(1, idCarrinho);
                    produtoStatement.setInt(2, produto.getCodProduto());
                    produtoStatement.setInt(3, idCliente);
                    produtoStatement.setInt(4, 1); // Quantidade fixa em 1, ajuste conforme necessário
                    produtoStatement.setString(5, produto.getNomeProduto());
                    produtoStatement.setString(6, produto.getDscDetalhadaProduto());
                    produtoStatement.setBigDecimal(7, produto.getVlrVendaProduto());

                    produtoStatement.addBatch();
                }

                // Executa o batch de inserção dos produtos
                produtoStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha o statement e a conexão
            if (produtoStatement != null) {
                try {
                    produtoStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (carrinhoStatement != null) {
                try {
                    carrinhoStatement.close();
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

    public static List<Integer> obterProdutosCarrinhoPorIdCliente(int idCliente) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Integer> idsProdutosCarrinho = new ArrayList<>();

        try {
            connection = ConnectionPoolConfig.getConnection();

            String sql = "SELECT PRODUTOID FROM CARRINHO WHERE IDCLIENTE = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCliente);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idProdutoCarrinho = resultSet.getInt(1);
                idsProdutosCarrinho.add(idProdutoCarrinho);
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
        return idsProdutosCarrinho;
    }

    private static int obterCarrinhoIdPorIdCliente(int idCliente) {
        int idCarrinho = 0;

        String SQL = "SELECT IDCARRINHO FROM CARRINHO WHERE IDCLIENTE = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, idCliente);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    idCarrinho = resultSet.getInt("IDCARRINHO");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return idCarrinho;
    }

    public static List<Produto> verificarProdutosExistenteNoCarrinho(List<Produto> produtos, int idCarrinho) {
        List<Carrinho> produtosCarrinho = obterProdutosCarrinhoPorIdCarrinho(idCarrinho);

        List<Produto> produtosNaoPresentesNoCarrinho = new ArrayList<>();

        for (Produto produto : produtos) {
            boolean presenteNoCarrinho = false;
            for (Carrinho carrinho : produtosCarrinho) {
                if (carrinho.getProduto().getCodProduto() == produto.getCodProduto()) {
                    presenteNoCarrinho = true;
                    break;
                }
            }
            if (!presenteNoCarrinho) {
                produtosNaoPresentesNoCarrinho.add(produto);
            }
        }

        return produtosNaoPresentesNoCarrinho;
    }



    public static List<Carrinho> obterProdutosCarrinhoPorIdCarrinho(int idCarrinho) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Carrinho> produtosCarrinho = new ArrayList<>();

        try {
            connection = ConnectionPoolConfig.getConnection();

            String sql = "SELECT * FROM CARRINHO WHERE IDCARRINHO = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCarrinho);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Carrinho produtoCarrinho = new br.com.adega.Model.Carrinho();
                Produto produto = new Produto();

                produto.setCodProduto(resultSet.getInt("codProduto"));
                produtoCarrinho.setIdCliente(resultSet.getInt("idCliente"));
                produtoCarrinho.setQuantidadeComprada(resultSet.getInt("quantidade"));
                produto.setNomeProduto(resultSet.getString("nomeProduto"));
                produto.setDscDetalhadaProduto(resultSet.getString("descricao"));
                produto.setVlrVendaProduto(BigDecimal.valueOf(resultSet.getInt("valor")));

                produtoCarrinho.setProduto(produto);
                produtosCarrinho.add(produtoCarrinho);
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
        return produtosCarrinho;
    }
}
