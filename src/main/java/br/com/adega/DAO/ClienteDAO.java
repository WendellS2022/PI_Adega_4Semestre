package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.Cliente;
import br.com.adega.Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static boolean CadastrarCliente(Cliente cliente) {
        if (VerificarEmailExistente(cliente.getEmail())) {
            return false;
        }

        String SQL = "INSERT INTO CLIENTES (email, nome, cpf, genero, dataNascimento, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, cliente.getEmail());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getCpf());
            preparedStatement.setString(4, cliente.getGenero());
            preparedStatement.setString(5, cliente.getDataNascimento());
            preparedStatement.setString(6, cliente.getSenha());


            int rowsAffected = preparedStatement.executeUpdate();
            connection.close();

            // Verifica se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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



}
