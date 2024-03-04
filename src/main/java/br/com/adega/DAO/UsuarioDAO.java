package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public static User verificarCredenciais(String email) {
        User usuario = new User();

        String SQL = "SELECT * FROM USERS WHERE EMAIL = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, email);


            ResultSet resultSet = preparedStatement.executeQuery();

            // Se houver pelo menos uma linha no ResultSet, as credenciais são válidas
            if (resultSet.next()) {
                // Crie um objeto Usuario com os dados do ResultSet

                usuario.setUserId(resultSet.getInt("UsersId"));
                usuario.setNome(resultSet.getString("Nome"));
                usuario.setEmail(resultSet.getString("Email"));
                usuario.setSenha(resultSet.getString("Senha"));
                usuario.setCPF(resultSet.getString("CPF"));
                usuario.setSituacao(resultSet.getBoolean("situacao"));
                usuario.setGrupo(resultSet.getInt("Grupo"));
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario; // Retorne o objeto Usuario
    }

    public static boolean cadastrarUsuario(User usuario) {
        String SQL = "INSERT INTO USERS (email, nome, cpf, grupo, situacao, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getNome());
            preparedStatement.setString(3, usuario.getCPF());
            preparedStatement.setInt(4, usuario.getGrupo());
            preparedStatement.setBoolean(5, true); // Definindo situacao como verdadeiro (true)
            preparedStatement.setString(6, usuario.getSenha());

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
}