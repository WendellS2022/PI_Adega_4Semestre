package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public static boolean verificarCredenciais(String email, String senha) {
        String SQL = "SELECT COUNT(*) FROM Users WHERE Email = ? AND Senha = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        return false;
    }

}

