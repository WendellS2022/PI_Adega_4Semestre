package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static List<Produto> obterTodosOsProdutos(){
        List<Produto> produtos = new ArrayList<>();

        String SQL = "SELECT * FROM PRODUCT ORDER BY COD_PRODUTO DESC";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Produto produto = new Produto();

                produto.setCodProduto(resultSet.getInt("COD_PRODUTO"));
                produto.setNomeProduto(resultSet.getString("DSC_NOME"));
                produto.setDscDetalhadaProduto(resultSet.getString("DSC_DETALHADA"));
                produto.setAvaliacaoProduto(resultSet.getInt("COD_AVALIACAO"));
                produto.setQtdEstoque(resultSet.getInt("QTD_ESTOQUE"));
                produto.setVlrVendaProduto(resultSet.getDouble("VLR_VENDA"));
                produto.setSituacaoProduto(resultSet.getBoolean("COD_SITUACAO"));

                produtos.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public static Produto obterProdutoPorId(int codProduto) {
        Produto produto = new Produto();

        String SQL = "SELECT * FROM PRODUCT WHERE COD_PRODUTO = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, codProduto);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    produto.setCodProduto(resultSet.getInt("COD_PRODUTO"));
                    produto.setNomeProduto(resultSet.getString("DSC_NOME"));
                    produto.setDscDetalhadaProduto(resultSet.getString("DSC_DETALHADA"));
                    produto.setAvaliacaoProduto(resultSet.getInt("COD_AVALIACAO"));
                    produto.setQtdEstoque(resultSet.getInt("QTD_ESTOQUE"));
                    produto.setVlrVendaProduto(resultSet.getDouble("VLR_VENDA"));
                    produto.setSituacaoProduto(resultSet.getBoolean("COD_SITUACAO"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produto;
    }

    public static boolean adicionarProduto(Produto produto) {
        String SQL = "INSERT INTO PRODUCT (DSC_NOME, DSC_DETALHADA, COD_AVALIACAO, QTD_ESTOQUE, VLR_VENDA, COD_SITUACAO) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, produto.getNomeProduto());
            preparedStatement.setString(2, produto.getDscDetalhadaProduto());
            preparedStatement.setDouble(3, produto.getAvaliacaoProduto());
            preparedStatement.setInt(4, produto.getQtdEstoque());
            preparedStatement.setDouble(5, produto.getVlrVendaProduto());
            preparedStatement.setBoolean(6, produto.isSituacaoProduto());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarProduto(Produto produto) {
        boolean sucesso = false;

        String SQL = "UPDATE PRODUCT SET DSC_NOME = ?, DSC_DETALHADA = ?, COD_AVALIACAO = ?, QTD_ESTOQUE = ?, VLR_VENDA = ?, COD_SITUACAO = ? WHERE COD_PRODUTO = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);


            preparedStatement.setString(1, produto.getNomeProduto());
            preparedStatement.setString(2, produto.getDscDetalhadaProduto());
            preparedStatement.setDouble(3, produto.getAvaliacaoProduto());
            preparedStatement.setInt(4, produto.getQtdEstoque());
            preparedStatement.setDouble(5, produto.getVlrVendaProduto());
            preparedStatement.setBoolean(6, produto.isSituacaoProduto());
            preparedStatement.setInt(7, produto.getCodProduto());

            int linhasAfetadas = preparedStatement.executeUpdate();

            // Verifica se o update foi bem-sucedido
            if (linhasAfetadas > 0) {
                sucesso = true;
            }

            // Fecha a conexão
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    public static boolean obterSituacaoProduto(int codProduto) {
        String SQL = "SELECT COD_SITUACAO FROM PRODUCT WHERE COD_PRODUTO = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, codProduto);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("COD_SITUACAO");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retorne false por padrão se não conseguir obter a situação do produto
        return false;
    }

    public static boolean alternarSituacaoDoProduto(String codProduto) {

        Produto produto = obterProdutoPorId(Integer.parseInt(codProduto));

        String SQL = "UPDATE PRODUCT SET COD_SITUACAO = ? WHERE COD_PRODUTO = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            // Inverte o status
            boolean novoStatus = !produto.isSituacaoProduto();

            preparedStatement.setBoolean(1, novoStatus);
            preparedStatement.setInt(2, produto.getCodProduto());

            int linhasAfetadas = preparedStatement.executeUpdate();

            // Verifique se a atualização foi bem-sucedida
            if (linhasAfetadas > 0) {
                // Atualize o objeto de usuário com o novo status
                produto.setSituacaoProduto(novoStatus);
                return produto.isSituacaoProduto();
            } else {
                // Se nenhuma linha foi afetada, retorne null ou lance uma exceção, conforme necessário
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static List<Produto> pesquisarProdutosPorNome(String nomeProduto) {
        List<Produto> produtos = new ArrayList<>();

        String SQL = "SELECT * FROM PRODUCT WHERE LOWER(DSC_NOME) LIKE LOWER(?) ORDER BY COD_PRODUTO DESC";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, "%" + nomeProduto + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Produto produto = new Produto();

                    produto.setCodProduto(resultSet.getInt("COD_PRODUTO"));
                    produto.setNomeProduto(resultSet.getString("DSC_NOME"));
                    produto.setDscDetalhadaProduto(resultSet.getString("DSC_DETALHADA"));
                    produto.setAvaliacaoProduto(resultSet.getInt("COD_AVALIACAO"));
                    produto.setQtdEstoque(resultSet.getInt("QTD_ESTOQUE"));
                    produto.setVlrVendaProduto(resultSet.getDouble("VLR_VENDA"));
                    produto.setSituacaoProduto(resultSet.getBoolean("COD_SITUACAO"));//verificar

                    produtos.add(produto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public static List<Produto> obterPaginaDeProdutos(int page, int pageSize) {
        List<Produto> produtos = new ArrayList<>();

        String SQL = "SELECT * FROM PRODUCT ORDER BY COD_PRODUTO DESC LIMIT ? OFFSET ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, (page - 1) * pageSize);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Produto produto = new Produto();

                    produto.setCodProduto(resultSet.getInt("COD_PRODUTO"));
                    produto.setNomeProduto(resultSet.getString("DSC_NOME"));
                    produto.setDscDetalhadaProduto(resultSet.getString("DSC_DETALHADA"));
                    produto.setAvaliacaoProduto(resultSet.getInt("COD_AVALIACAO"));
                    produto.setQtdEstoque(resultSet.getInt("QTD_ESTOQUE"));
                    produto.setVlrVendaProduto(resultSet.getDouble("VLR_VENDA"));
                    produto.setSituacaoProduto(resultSet.getBoolean("COD_SITUACAO"));

                    produtos.add(produto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produtos;
    }

}