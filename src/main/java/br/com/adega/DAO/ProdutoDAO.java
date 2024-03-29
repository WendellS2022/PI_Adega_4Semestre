package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.Imagem;
import br.com.adega.Model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static List<Produto> ObterTodosOsProdutos(){
        List<Produto> produtos = new ArrayList<>();

        String SQL = "SELECT * FROM PRODUCTS ORDER BY PRODUTOID DESC";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Produto produto = new Produto();

                produto.setCodProduto(resultSet.getInt("ProdutoID"));
                produto.setNomeProduto(resultSet.getString("Nome"));
                produto.setDscDetalhadaProduto(resultSet.getString("Descricao"));
                produto.setAvaliacaoProduto(resultSet.getInt("Avaliacao"));
                produto.setQtdEstoque(resultSet.getInt("Quantidade"));
                produto.setVlrVendaProduto(resultSet.getDouble("Valor"));
                produto.setSituacaoProduto(resultSet.getBoolean("Situacao"));

                produtos.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public static Produto ObterProdutoPorId(int codProduto) {
        Produto produto = new Produto();

        String SQL = "SELECT * FROM PRODUCTS WHERE PRODUTOID = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, codProduto);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    produto.setCodProduto(resultSet.getInt("ProdutoID"));
                    produto.setNomeProduto(resultSet.getString("Nome"));
                    produto.setDscDetalhadaProduto(resultSet.getString("Descricao"));
                    produto.setAvaliacaoProduto(resultSet.getInt("Avaliacao"));
                    produto.setQtdEstoque(resultSet.getInt("Quantidade"));
                    produto.setVlrVendaProduto(resultSet.getDouble("Valor"));
                    produto.setSituacaoProduto(resultSet.getBoolean("Situacao"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produto;
    }

    public static boolean AdicionarProduto(Produto produto) {
        String SQL = "INSERT INTO PRODUCTS (Nome, Descricao, Avaliacao, Quantidade, Valor, Situacao) VALUES (?, ?, ?, ?, ?, ?)";

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
    public static int AdicionarProdutoRetornandoCodigo(Produto produto) {
        String SQL = "INSERT INTO PRODUCTS (Nome, Descricao, Avaliacao, Quantidade, Valor, Situacao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, produto.getNomeProduto());
            preparedStatement.setString(2, produto.getDscDetalhadaProduto());
            preparedStatement.setDouble(3, produto.getAvaliacaoProduto());
            preparedStatement.setInt(4, produto.getQtdEstoque());
            preparedStatement.setDouble(5, produto.getVlrVendaProduto());
            preparedStatement.setBoolean(6, produto.isSituacaoProduto());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                return 0; // Se não houve inserção, retorna 0
            }

            // Obter o código do produto inserido
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Retorna o código do produto inserido
                } else {
                    return 0; // Se não foi possível obter o código, retorna 0
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Em caso de exceção, retorna 0
        }
    }

    public static boolean AtualizarProduto(Produto produto) {
        boolean sucesso = false;

        String SQL = "UPDATE PRODUCTS SET Nome = ?, Descricao = ?, Avaliacao = ?, Quantidade = ?, Valor = ?, Situacao = ? WHERE ProdutoID = ?";

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

    public static boolean ObterSituacaoProduto(int codProduto) {
        String SQL = "SELECT Situacao FROM PRODUCTS WHERE ProdutoID = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, codProduto);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("Situacao");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retorne false por padrão se não conseguir obter a situação do produto
        return false;
    }

    public static boolean AtualizarStatus(String codProduto) {
        Produto produto = ObterProdutoPorId(Integer.parseInt(codProduto));

        // Inverte o status do produto
        boolean novoStatus = !produto.isSituacaoProduto();

        String SQL = "UPDATE PRODUCTS SET Situacao = ? WHERE ProdutoID = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setBoolean(1, novoStatus);
            preparedStatement.setInt(2, produto.getCodProduto());

            int linhasAfetadas = preparedStatement.executeUpdate();

            // Verifique se a atualização foi bem-sucedida
            if (linhasAfetadas > 0) {
                // Atualize o objeto de produto com o novo status
                produto.setSituacaoProduto(novoStatus);
                return true;
            } else {
                // Se nenhuma linha foi afetada, retorne false
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public static List<Produto> PesquisarProdutosPorNome(String nomeProduto) {
        List<Produto> produtos = new ArrayList<>();

        String SQL = "SELECT * FROM PRODUCTS WHERE LOWER(Nome) LIKE LOWER(?) ORDER BY ProdutoID DESC";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, "%" + nomeProduto + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Produto produto = new Produto();

                    produto.setCodProduto(resultSet.getInt("ProdutoID"));
                    produto.setNomeProduto(resultSet.getString("Nome"));
                    produto.setDscDetalhadaProduto(resultSet.getString("Descricao"));
                    produto.setAvaliacaoProduto(resultSet.getInt("Avaliacao"));
                    produto.setQtdEstoque(resultSet.getInt("Quantidade"));
                    produto.setVlrVendaProduto(resultSet.getDouble("Valor"));
                    produto.setSituacaoProduto(resultSet.getBoolean("Situacao"));//verificar

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

        String SQL = "SELECT * FROM PRODUCTS ORDER BY produtoID DESC LIMIT ? OFFSET ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, (page - 1) * pageSize);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Produto produto = new Produto();

                    produto.setCodProduto(resultSet.getInt("ProdutoID"));
                    produto.setNomeProduto(resultSet.getString("Nome"));
                    produto.setDscDetalhadaProduto(resultSet.getString("Descricao"));
                    produto.setAvaliacaoProduto(resultSet.getInt("Avaliacao"));
                    produto.setQtdEstoque(resultSet.getInt("Quantidade"));
                    produto.setVlrVendaProduto(resultSet.getDouble("Valor"));
                    produto.setSituacaoProduto(resultSet.getBoolean("Situacao"));

                    produtos.add(produto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produtos;
    }
    public static boolean AdicionarImagem(Imagem imagem) {
        String SQL = "INSERT INTO Imagens (ProdutoId, Diretorio, Nome, Qualificacao, Extensao) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, imagem.getProdutoId());
            preparedStatement.setString(2, imagem.getDiretorio());
            preparedStatement.setString(3, imagem.getNome());
            preparedStatement.setBoolean(4, imagem.isQualificacao());
            preparedStatement.setString(5, imagem.getExtensao());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean DefinirImagemPrincipal(int produtoId, String nomeImagemPrincipal) {
        String SQL = "UPDATE Imagens SET Qualificacao = ? WHERE ProdutoId = ? AND Nome = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            // Definir a qualificação da imagem para true para torná-la a imagem principal
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, produtoId);
            preparedStatement.setString(3, nomeImagemPrincipal);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean ExcluirImagem(int imagemId) {
        String SQL = "DELETE FROM Imagens WHERE ImagemID = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, imagemId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Imagem> obterImagensPorProdutoId(int produtoId) {
        List<Imagem> imagens = new ArrayList<>();

        String SQL = "SELECT * FROM Imagens WHERE ProdutoId = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, produtoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Imagem imagem = new Imagem();

                    imagem.setId(resultSet.getInt("ImagemID"));
                    imagem.setProdutoId(resultSet.getInt("ProdutoId"));
                    imagem.setDiretorio(resultSet.getString("Diretorio"));
                    imagem.setNome(resultSet.getString("Nome"));
                    imagem.setQualificacao(resultSet.getBoolean("Qualificacao"));
                    imagem.setExtensao(resultSet.getString("Extensao"));

                    imagens.add(imagem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imagens;
    }
}