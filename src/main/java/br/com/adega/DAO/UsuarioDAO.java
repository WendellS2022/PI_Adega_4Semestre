package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.User;
import br.com.adega.Model.User; // Importe a classe Usuario

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public static User verificarCredenciais(String email, String senha) {
        User usuario = new User();

        String SQL = "SELECT * FROM USERS WHERE EMAIL = ? AND SENHA = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Se houver pelo menos uma linha no ResultSet, as credenciais são válidas
            if (resultSet.next()) {
                // Crie um objeto Usuario com os dados do ResultSet

                usuario.setUserId(resultSet.getInt("UsersId"));
                usuario.setNome(resultSet.getString("Nome"));
                usuario.setEmail(resultSet.getString("Email"));
                usuario.setSenha(resultSet.getString("Senha"));
                usuario.setCPF(resultSet.getString("CPF"));
                usuario.setSituacao(resultSet.getBoolean("Situacao"));
                usuario.setGrupo(resultSet.getInt("Grupo"));
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario; // Retorne o objeto Usuario
    }
}