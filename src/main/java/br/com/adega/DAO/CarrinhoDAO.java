package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.Carrinho;
import br.com.adega.Model.Produto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO {
    public static void inserirProdutosCarrinho(List<Carrinho> produtosCarrinho, int idCliente) {
        Connection connection = null;
        PreparedStatement produtoCarrinhoStatement = null;

        try {
            connection = ConnectionPoolConfig.getConnection();
            int idCarrinho = obterCarrinhoIdPorIdCliente(idCliente);

            if (idCarrinho <= 0) {
                idCarrinho += 1;

                String sqlInsertProdutoCarrinho = "INSERT INTO CARRINHO (IDCARRINHO, PRODUTOID, IDCLIENTE, QUANTIDADE, NOMEPRODUTO, DESCRICAO, VALOR) VALUES (?, ?, ?, ?, ?, ?, ?)";
                produtoCarrinhoStatement = connection.prepareStatement(sqlInsertProdutoCarrinho);

                for (Carrinho produtoCarrinho : produtosCarrinho) {
                    produtoCarrinhoStatement.setInt(1, idCarrinho);
                    produtoCarrinhoStatement.setInt(2, produtoCarrinho.getProduto().getCodProduto());
                    produtoCarrinhoStatement.setInt(3, idCliente);
                    produtoCarrinhoStatement.setInt(4, 1);
                    produtoCarrinhoStatement.setString(5, produtoCarrinho.getProduto().getNomeProduto());
                    produtoCarrinhoStatement.setString(6, produtoCarrinho.getProduto().getDscDetalhadaProduto());
                    produtoCarrinhoStatement.setBigDecimal(7, produtoCarrinho.getProduto().getVlrVendaProduto());

                    produtoCarrinhoStatement.addBatch();
                }

                produtoCarrinhoStatement.executeBatch();
                return;
            }

            if (idCarrinho > 0) {
                produtosCarrinho = verificarProdutosExistenteNoCarrinho(produtosCarrinho, idCliente);
                if (produtosCarrinho == null) {
                    return;
                }

                String sqlInsertProdutoCarrinho = "INSERT INTO CARRINHO (IDCARRINHO, PRODUTOID, IDCLIENTE, QUANTIDADE, NOMEPRODUTO, DESCRICAO, VALOR) VALUES (?, ?, ?, ?, ?, ?, ?)";
                produtoCarrinhoStatement = connection.prepareStatement(sqlInsertProdutoCarrinho);

                for (Carrinho produtoCarrinho : produtosCarrinho) {
                    produtoCarrinhoStatement.setInt(1, idCarrinho);
                    produtoCarrinhoStatement.setInt(2, produtoCarrinho.getProduto().getCodProduto());
                    produtoCarrinhoStatement.setInt(3, idCliente);
                    produtoCarrinhoStatement.setInt(4, 1); // Quantidade fixa em 1, ajuste conforme necessário
                    produtoCarrinhoStatement.setString(5, produtoCarrinho.getProduto().getNomeProduto());
                    produtoCarrinhoStatement.setString(6, produtoCarrinho.getProduto().getDscDetalhadaProduto());
                    produtoCarrinhoStatement.setBigDecimal(7, produtoCarrinho.getProduto().getVlrVendaProduto());

                    produtoCarrinhoStatement.addBatch();
                }

                // Executa o batch de inserção dos produtos
                produtoCarrinhoStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha o statement e a conexão
            if (produtoCarrinhoStatement != null) {
                try {
                    produtoCarrinhoStatement.close();
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

    public static List<Carrinho> verificarProdutosExistenteNoCarrinho(List<Carrinho> produtosCarrinho, int idCliente) {
        List<Carrinho> produtosCarrinhoDaBase = obterProdutosCarrinhoPorIdCliente(idCliente);
        boolean encontrado = false;

        int lastIndex = produtosCarrinho.size() - 1;
        Carrinho carrinho = produtosCarrinho.get(lastIndex); // Pegando o último item de produtosCarrinho

        for (Carrinho produtoDaBase : produtosCarrinhoDaBase) {
            // Se os IDs dos produtos forem iguais, atualize a quantidade
            if (carrinho.getProduto().getCodProduto() == produtoDaBase.getProduto().getCodProduto()) {
                int novaQuantidade = carrinho.getQuantidadeComprada() + 1;
                atualizarQuantidadeNoCarrinho(carrinho.getProduto().getCodProduto(), novaQuantidade);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            return null;
        }

        // Se nenhum produto correspondente foi encontrado, retorna a lista original de produtos
        return produtosCarrinho;
    }

    // Método para atualizar a quantidade na tabela carrinho
    public static void atualizarQuantidadeNoCarrinho(int produtoId, int novaQuantidade) {
        String SQL = "UPDATE CARRINHO SET QUANTIDADE = ? WHERE PRODUTOID = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, novaQuantidade);
            preparedStatement.setInt(2, produtoId);

            // Executa a atualização
            int linhasAfetadas = preparedStatement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Quantidade atualizada com sucesso para o produto ID: " + produtoId);
            } else {
                System.out.println("Nenhuma linha afetada ao atualizar a quantidade para o produto ID: " + produtoId);
            }

            // Fecha a conexão e o PreparedStatement
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar a quantidade no carrinho: " + e.getMessage());
        }
    }


    public static List<Carrinho> obterProdutosCarrinhoPorIdCliente(int idCliente) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Carrinho> produtosCarrinho = new ArrayList<>();

        try {

            connection = ConnectionPoolConfig.getConnection();


            String sql = "SELECT * FROM CARRINHO WHERE IDCLIENTE = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCliente);


            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Carrinho produtoCarrinho = new br.com.adega.Model.Carrinho();
                Produto produto = new Produto();

                produto.setCodProduto(resultSet.getInt("ProdutoId"));
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

    public static Carrinho obterProdutoDoCarrinhoPorProdutoId(int produtoId) {
        Carrinho produtoCarrinho = null; // Inicialize com null
        Produto produto = new Produto();

        String SQL = "SELECT * FROM CARRINHO WHERE PRODUTOID = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, produtoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    produtoCarrinho = new Carrinho(); // Crie uma nova instância dentro do loop
                    produto.setCodProduto(resultSet.getInt("ProdutoId"));
                    produtoCarrinho.setIdCliente(resultSet.getInt("idCliente"));
                    produtoCarrinho.setQuantidadeComprada(resultSet.getInt("quantidade"));
                    produto.setNomeProduto(resultSet.getString("nomeProduto"));
                    produto.setDscDetalhadaProduto(resultSet.getString("descricao"));
                    produto.setVlrVendaProduto(BigDecimal.valueOf(resultSet.getInt("valor")));

                    produtoCarrinho.setProduto(produto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return produtoCarrinho;
    }

    public static boolean removerProdutoDoCarrinho(int produtoId){
        boolean sucesso = false;

        String SQL = "DELETE FROM CARRINHO WHERE PRODUTOID = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, produtoId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                sucesso = true;
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Fail in database connection!");
            System.out.println("Error: " + e.getMessage());
        }

        return sucesso;
    }

}

