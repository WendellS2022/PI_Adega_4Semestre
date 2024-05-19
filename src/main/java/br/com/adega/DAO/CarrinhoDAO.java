package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.Carrinho;
import br.com.adega.Model.Produto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarrinhoDAO {
    public static void inserirProdutosCarrinho(List<Carrinho> produtosCarrinho, int idCliente, boolean login) {
        Connection connection = null;
        PreparedStatement produtoCarrinhoStatement = null;

        try {
            connection = ConnectionPoolConfig.getConnection();
            int idCarrinho = obterCarrinhoIdPorIdCliente(idCliente);

            if (idCarrinho <= 0) {
                idCarrinho += 1; // Incrementa o idCarrinho para criar um novo

                String sqlInsertProdutoCarrinho = "INSERT INTO CARRINHO (IDCARRINHO, PRODUTOID, IDCLIENTE, QUANTIDADE, NOMEPRODUTO, DESCRICAO, VALOR) VALUES (?, ?, ?, ?, ?, ?, ?)";
                produtoCarrinhoStatement = connection.prepareStatement(sqlInsertProdutoCarrinho);

                for (Carrinho produtoCarrinho : produtosCarrinho) {
                    produtoCarrinhoStatement.setInt(1, idCarrinho);
                    produtoCarrinhoStatement.setInt(2, produtoCarrinho.getProduto().getCodProduto());
                    produtoCarrinhoStatement.setInt(3, idCliente);
                    if (produtoCarrinho.getQuantidadeComprada() > 1) {
                        produtoCarrinhoStatement.setInt(4, produtoCarrinho.getQuantidadeComprada());
                    } else {
                        produtoCarrinhoStatement.setInt(4, 1);
                    }
                    produtoCarrinhoStatement.setString(5, produtoCarrinho.getProduto().getNomeProduto());
                    produtoCarrinhoStatement.setString(6, produtoCarrinho.getProduto().getDscDetalhadaProduto());
                    produtoCarrinhoStatement.setBigDecimal(7, produtoCarrinho.getProduto().getVlrVendaProduto());

                    produtoCarrinhoStatement.addBatch();
                }

                produtoCarrinhoStatement.executeBatch();
                return;
            }

            if (idCarrinho > 0) {
                // Verificar produtos existentes no carrinho
                produtosCarrinho = verificarProdutosExistenteNoCarrinho(produtosCarrinho, idCliente);
                if (produtosCarrinho == null || produtosCarrinho.isEmpty()) {
                    return;
                }

                String sqlInsertProdutoCarrinho = "INSERT INTO CARRINHO (IDCARRINHO, PRODUTOID, IDCLIENTE, QUANTIDADE, NOMEPRODUTO, DESCRICAO, VALOR) VALUES (?, ?, ?, ?, ?, ?, ?)";
                produtoCarrinhoStatement = connection.prepareStatement(sqlInsertProdutoCarrinho);

                // Se não estiver logado, inserir apenas o último produto
                if (!login) {
                    Carrinho produtoCarrinho = produtosCarrinho.get(produtosCarrinho.size() - 1);
                    produtoCarrinhoStatement.setInt(1, idCarrinho);
                    produtoCarrinhoStatement.setInt(2, produtoCarrinho.getProduto().getCodProduto());
                    produtoCarrinhoStatement.setInt(3, idCliente);
                    if (produtoCarrinho.getQuantidadeComprada() > 1) {
                        produtoCarrinhoStatement.setInt(4, produtoCarrinho.getQuantidadeComprada());
                    } else {
                        produtoCarrinhoStatement.setInt(4, 1);
                    }
                    produtoCarrinhoStatement.setString(5, produtoCarrinho.getProduto().getNomeProduto());
                    produtoCarrinhoStatement.setString(6, produtoCarrinho.getProduto().getDscDetalhadaProduto());
                    produtoCarrinhoStatement.setBigDecimal(7, produtoCarrinho.getProduto().getVlrVendaProduto());

                    produtoCarrinhoStatement.executeUpdate(); // Executa o insert do último produto

                    // Não há necessidade de continuar, pois já inserimos o produto
                    return;
                }
            }

            // Se chegarmos aqui, significa que não estamos logados ou há mais produtos para inserir
            for (Carrinho carrinho : produtosCarrinho) {
                produtoCarrinhoStatement.setInt(1, idCarrinho);
                produtoCarrinhoStatement.setInt(2, carrinho.getProduto().getCodProduto());
                produtoCarrinhoStatement.setInt(3, idCliente);
                if (carrinho.getQuantidadeComprada() > 1) {
                    produtoCarrinhoStatement.setInt(4, carrinho.getQuantidadeComprada());
                } else {
                    produtoCarrinhoStatement.setInt(4, 1);
                }
                produtoCarrinhoStatement.setString(5, carrinho.getProduto().getNomeProduto());
                produtoCarrinhoStatement.setString(6, carrinho.getProduto().getDscDetalhadaProduto());
                produtoCarrinhoStatement.setBigDecimal(7, carrinho.getProduto().getVlrVendaProduto());

                produtoCarrinhoStatement.addBatch();
            }

            produtoCarrinhoStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace(); // Trata a exceção imprimindo o stack trace
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
        List<Carrinho> produtosNaoEncontrados = new ArrayList<>();

        for (Iterator<Carrinho> iterator = produtosCarrinho.iterator(); iterator.hasNext();) {
            Carrinho produtoASerVerificado = iterator.next();

            boolean encontrado = false;

            for (Carrinho produtoDaBase : produtosCarrinhoDaBase) {
                // Se os IDs dos produtos forem iguais, atualize a quantidade
                if (produtoASerVerificado.getProduto().getCodProduto() == produtoDaBase.getProduto().getCodProduto()) {
                    int novaQuantidade = produtoDaBase.getQuantidadeComprada() + 1;
                    atualizarQuantidadeNoCarrinho(produtoASerVerificado.getProduto().getCodProduto(), novaQuantidade);
                    encontrado = true;
                    iterator.remove(); // Remove o produto da lista de entrada
                    break;
                }
            }

            // Se o produto não foi encontrado na lista da base, adicioná-lo à lista de produtos não encontrados
            if (!encontrado) {
                produtosNaoEncontrados.add(produtoASerVerificado);
            }
        }

        // Retorne a lista de produtos não encontrados
        return produtosNaoEncontrados;
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
                produto.setVlrVendaProduto(resultSet.getBigDecimal("valor"));

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

    public static boolean removerProdutoDoCarrinho(int produtoId) {
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

    public static void excluirCarrinhoPorIdCliente(int idCliente) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPoolConfig.getConnection();


            String sql = "DELETE FROM Carrinho WHERE IDCLIENTE = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCliente);

        } catch (SQLException e) {
            e.printStackTrace(); // Trata a exceção imprimindo o stack trace
        } finally {
            // Fecha o statement e a conexão
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

