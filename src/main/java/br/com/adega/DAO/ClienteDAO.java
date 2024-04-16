package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.Cliente;
import br.com.adega.Model.Usuario;

import java.sql.*;
import java.text.DateFormat;
import java.util.Date;

public class ClienteDAO {
    public static Cliente VerificarCredenciais(String email) {
        Cliente cliente = new Cliente();

        String SQL = "SELECT * FROM CLIENTES WHERE EMAIL = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Se houver pelo menos uma linha no ResultSet, as credenciais são válidas
            if (resultSet.next()) {
                // Crie um objeto Usuario com os dados do ResultSet

                cliente.setIdCliente(resultSet.getInt("IdCliente"));
                cliente.setNome(resultSet.getString("Nome"));
                cliente.setEmail(resultSet.getString("Email"));
                cliente.setSenha(resultSet.getString("Senha"));
                cliente.setCpf(resultSet.getString("CPF"));
                cliente.setDataNascimento(resultSet.getString("DataNascimento"));
                cliente.setGenero(resultSet.getString("Genero"));
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cliente; // Retorne o objeto Usuario
    }

    public static int CadastrarCliente(Cliente cliente) {
        if (VerificarEmailExistente(cliente.getEmail())) {
            // Retornar -1 indicando que o cliente já existe com o mesmo e-mail
            return -1;
        }

        String SQL = "INSERT INTO CLIENTES (email, nome, cpf, genero, dataNascimento, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            // Informa que queremos obter a chave gerada pelo INSERT
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cliente.getEmail());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getCpf());
            preparedStatement.setString(4, cliente.getGenero());
            preparedStatement.setString(5, cliente.getDataNascimento());
            preparedStatement.setString(6, cliente.getSenha());

            // Executa o INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            // Verifica se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                // Recupera a chave gerada
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idCliente = generatedKeys.getInt(1);
                    connection.close();
                    return idCliente; // Retorna o IdCliente
                } else {
                    connection.close();
                    return -1; // Se não foi possível recuperar o IdCliente, retorna -1
                }
            } else {
                connection.close();
                return -1; // Se não houve inserção, retorna -1
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Se ocorreu uma exceção, retorna -1
        }
    }


    private static boolean VerificarEmailExistente(String email) {
        String SQL = "SELECT COUNT(*) FROM CLIENTES WHERE EMAIL = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Retorna true se o email já existir no banco de dados
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public int buscarIdCliente(String emailCliente) {
        int idCliente = -1; // Valor padrão para indicar que o cliente não foi encontrado
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Obtém a conexão do pool de conexões
            connection = ConnectionPoolConfig.getConnection();

            // Prepara a query SQL para buscar o idCliente pelo email do cliente
            String sql = "SELECT idCliente FROM CLIENTES WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, emailCliente);

            // Executa a query
            resultSet = statement.executeQuery();

            // Verifica se o cliente foi encontrado e recupera o ID
            if (resultSet.next()) {
                idCliente = resultSet.getInt("idCliente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Aqui você pode lidar com exceções SQL de acordo com a necessidade do seu sistema
        } finally {
            // Fecha a conexão, o statement e o resultSet
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Aqui você pode lidar com exceções de fechamento de recursos
            }
        }

        return idCliente;
    }


}
