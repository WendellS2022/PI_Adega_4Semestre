package br.com.adega.DAO;

import br.com.adega.Model.Endereco;
import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.Produto;
import br.com.adega.Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    // Método para salvar o endereço no banco de dados
    public static void salvarEndereco(Endereco endereco) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Obtém a conexão do pool de conexões
            connection = ConnectionPoolConfig.getConnection();

            // Prepara a query SQL para inserção do endereço
            String sql = "INSERT INTO ENDERECO (cep, logradouro, numero, complemento, bairro, cidade, uf , status, padrao, enderecoFaturamento, idCliente) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ?)";
            statement = connection.prepareStatement(sql);

            // Define os parâmetros da query com os dados do endereço
            statement.setString(1, endereco.getCep());
            statement.setString(2, endereco.getLogradouro());
            statement.setInt(3, endereco.getNumero());
            statement.setString(4, endereco.getComplemento());
            statement.setString(5, endereco.getBairro());
            statement.setString(6, endereco.getCidade());
            statement.setString(7, endereco.getUf());
            statement.setBoolean(8, endereco.isStatus());
            statement.setBoolean(9, endereco.isPadrao());
            statement.setBoolean(10, endereco.isEnderecoFaturamento());
            statement.setInt(11, endereco.getIdCliente());


            // Executa a query para inserir o endereço no banco de dados
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão e o statement
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

    public static List<Endereco> obterEnderecosPorEmailCliente(String emailCliente) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Endereco> enderecos = new ArrayList<>();

        try {
            // Obtém a conexão do pool de conexões
            connection = ConnectionPoolConfig.getConnection();

            // Prepara a query SQL para buscar os endereços pelo e-mail do cliente
            String sql = "SELECT * FROM ENDERECO INNER JOIN CLIENTES ON ENDERECO.idCliente = CLIENTES.idCliente WHERE CLIENTES.email = ? AND STATUS = TRUE";

            statement = connection.prepareStatement(sql);
            statement.setString(1, emailCliente);

            // Executa a query
            resultSet = statement.executeQuery();

            // Percorre os resultados e adiciona os endereços à lista
            while (resultSet.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(resultSet.getInt("idEndereco"));
                endereco.setCep(resultSet.getString("cep"));
                endereco.setLogradouro(resultSet.getString("logradouro"));
                endereco.setNumero(resultSet.getInt("numero"));
                endereco.setComplemento(resultSet.getString("complemento"));
                endereco.setBairro(resultSet.getString("bairro"));
                endereco.setCidade(resultSet.getString("cidade"));
                endereco.setUf(resultSet.getString("uf"));
                endereco.setStatus(resultSet.getBoolean("status"));
                endereco.setPadrao(resultSet.getBoolean("padrao"));
                endereco.setEnderecoFaturamento(resultSet.getBoolean("enderecoFaturamento"));
                endereco.setIdCliente(resultSet.getInt("idCliente"));
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão, o statement e o resultSet
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

        return enderecos;
    }

    private static List<Endereco> obterEnderecosPorIdCliente(String idCliente) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Endereco> enderecos = new ArrayList<>();

        try {
            // Obtém a conexão do pool de conexões
            connection = ConnectionPoolConfig.getConnection();

            // Prepara a query SQL para buscar os endereços pelo e-mail do cliente
            String sql = "SELECT * FROM ENDERECO INNER JOIN CLIENTES ON ENDERECO.idCliente = CLIENTES.idCliente WHERE CLIENTES.idCliente = ? AND STATUS = TRUE";

            statement = connection.prepareStatement(sql);
            statement.setString(1, idCliente);

            // Executa a query
            resultSet = statement.executeQuery();

            // Percorre os resultados e adiciona os endereços à lista
            while (resultSet.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(resultSet.getInt("idEndereco"));
                endereco.setCep(resultSet.getString("cep"));
                endereco.setLogradouro(resultSet.getString("logradouro"));
                endereco.setNumero(resultSet.getInt("numero"));
                endereco.setComplemento(resultSet.getString("complemento"));
                endereco.setBairro(resultSet.getString("bairro"));
                endereco.setCidade(resultSet.getString("cidade"));
                endereco.setUf(resultSet.getString("uf"));
                endereco.setStatus(resultSet.getBoolean("status"));
                endereco.setPadrao(resultSet.getBoolean("padrao"));
                endereco.setEnderecoFaturamento(resultSet.getBoolean("enderecoFaturamento"));
                endereco.setIdCliente(resultSet.getInt("idCliente"));
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão, o statement e o resultSet
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

        return enderecos;
    }

    public static Endereco obterEnderecoFaturamentoPorEmailCliente(String clienteLogado) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Endereco endereco = null; // Inicializa como null, pois vamos retornar apenas um endereço

        try {
            // Obtém a conexão do pool de conexões
            connection = ConnectionPoolConfig.getConnection();

            // Prepara a query SQL para buscar o endereço pelo id do cliente
            String sql = "SELECT * FROM ENDERECO INNER JOIN CLIENTES ON ENDERECO.idCliente = CLIENTES.idCliente WHERE CLIENTES.email = ? AND ENDERECO.enderecoFaturamento = TRUE";

            statement = connection.prepareStatement(sql);
            statement.setString(1, clienteLogado);

            // Executa a query
            resultSet = statement.executeQuery();

            // Verifica se há um endereço retornado
            if (resultSet.next()) {
                endereco = new Endereco();
                endereco.setIdEndereco(resultSet.getInt("idEndereco"));
                endereco.setCep(resultSet.getString("cep"));
                endereco.setLogradouro(resultSet.getString("logradouro"));
                endereco.setNumero(resultSet.getInt("numero"));
                endereco.setComplemento(resultSet.getString("complemento"));
                endereco.setBairro(resultSet.getString("bairro"));
                endereco.setCidade(resultSet.getString("cidade"));
                endereco.setUf(resultSet.getString("uf"));
                endereco.setStatus(resultSet.getBoolean("status"));
                endereco.setPadrao(resultSet.getBoolean("padrao"));
                endereco.setEnderecoFaturamento(resultSet.getBoolean("enderecoFaturamento"));
                endereco.setIdCliente(resultSet.getInt("idCliente"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão, o statement e o resultSet
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

        return endereco;
    }


    public static boolean DesativarEndereco(int idEndereco) {
        String SQL = "UPDATE ENDERECO SET STATUS = ? WHERE IDENDERECO = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, idEndereco);

            int linhasAfetadas = preparedStatement.executeUpdate();

            // Verifique se a atualização foi bem-sucedida
            if (linhasAfetadas > 0) {
                // Atualize o objeto de produto com o novo status

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

    public static void atualizarEndereco(Endereco endereco, boolean padrao) {
        Connection connection = null;
        PreparedStatement statement = null;

        if (padrao) {
            List<Endereco> enderecos = new ArrayList<>();
            enderecos = obterEnderecosPorIdCliente(String.valueOf(endereco.getIdCliente()));
            atualizarTodosEnderecos(endereco);
        }
        try {
            // Obtém a conexão do pool de conexões
            connection = ConnectionPoolConfig.getConnection();

            // Prepara a query SQL para atualização do endereço
            String sql = "UPDATE ENDERECO " +
                    "SET cep = ?, logradouro = ?, numero = ?, complemento = ?, " +
                    "bairro = ?, cidade = ?, uf = ?, status = ?, " +
                    "padrao = ?, enderecoFaturamento = ? " +
                    "WHERE idEndereco = ?";
            statement = connection.prepareStatement(sql);

            // Define os parâmetros da query com os dados atualizados do endereço
            statement.setString(1, endereco.getCep());
            statement.setString(2, endereco.getLogradouro());
            statement.setInt(3, endereco.getNumero());
            statement.setString(4, endereco.getComplemento());
            statement.setString(5, endereco.getBairro());
            statement.setString(6, endereco.getCidade());
            statement.setString(7, endereco.getUf());
            statement.setBoolean(8, endereco.isStatus());
            statement.setBoolean(9, endereco.isPadrao());
            statement.setBoolean(10, endereco.isEnderecoFaturamento());
            statement.setInt(11, endereco.getIdEndereco());

            // Executa a query para atualizar o endereço no banco de dados
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão e o statement
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

    public static void atualizarTodosEnderecos(Endereco endereco) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Obtém a conexão do pool de conexões
            connection = ConnectionPoolConfig.getConnection();

            // Prepara a query SQL para atualização de todos os endereços
            String sql = "UPDATE ENDERECO SET padrao = ? WHERE IDCLIENTE = ?";
            statement = connection.prepareStatement(sql);

            // Define o parâmetro da query com o novo valor para o campo "padrao"
            statement.setBoolean(1, false);
            statement.setInt(2, endereco.getIdCliente());

            // Executa a query para atualizar todos os endereços no banco de dados
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão e o statement
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

    public static Endereco obterEnderecoPorIdEndereco(int idEndereco) {
        Endereco endereco = new Endereco();
        String SQL = "SELECT * FROM ENDERECO WHERE IDENDERECO = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, idEndereco); // Define o parâmetro do ID do usuário

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    endereco.setIdEndereco(resultSet.getInt("idEndereco"));
                    endereco.setCep(resultSet.getString("cep"));
                    endereco.setLogradouro(resultSet.getString("logradouro"));
                    endereco.setNumero(resultSet.getInt("numero"));
                    endereco.setComplemento(resultSet.getString("complemento"));
                    endereco.setBairro(resultSet.getString("bairro"));
                    endereco.setCidade(resultSet.getString("cidade"));
                    endereco.setUf(resultSet.getString("uf"));
                    endereco.setStatus(resultSet.getBoolean("status"));
                    endereco.setPadrao(resultSet.getBoolean("padrao"));
                    endereco.setEnderecoFaturamento(resultSet.getBoolean("enderecoFaturamento"));
                    endereco.setIdCliente(resultSet.getInt("idCliente"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return endereco;
    }



}










