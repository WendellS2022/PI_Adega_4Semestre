package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.Cliente;
import br.com.adega.Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;

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
                cliente.setDataNascimento((DateFormat) resultSet.getMetaData());
                cliente.setGenero(resultSet.getString("Genero"));
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cliente; // Retorne o objeto Usuario
    }

}
