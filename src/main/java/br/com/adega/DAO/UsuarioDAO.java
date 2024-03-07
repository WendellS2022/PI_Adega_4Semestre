package br.com.adega.DAO;

import br.com.adega.Config.ConnectionPoolConfig;
import br.com.adega.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public static boolean CadastrarUsuario(User usuario) {
        String SQL = "INSERT INTO USERS (email, nome, cpf, grupo, situacao, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getNome());
            preparedStatement.setString(3, usuario.getCPF());
            preparedStatement.setInt(4, usuario.getGrupo());
            preparedStatement.setBoolean(5, usuario.isSituacao()); // Definindo situacao como verdadeiro (true)
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

    public static List<User> ObterUsuarios() {
        List<User> usuarios = new ArrayList<>();
        String SQL = "SELECT * FROM USERS";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User usuario = new User();

                usuario.setUserId(resultSet.getInt("usersId"));
                usuario.setNome(resultSet.getString("Nome"));
                usuario.setEmail(resultSet.getString("Email"));
                usuario.setGrupo(resultSet.getInt("Grupo"));
                usuario.setSituacao(resultSet.getBoolean("situacao"));

                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public static boolean verificarUsuario(String email) {
        boolean usuarioExiste = false;

        String SQL = "SELECT COUNT(*) FROM USERS WHERE EMAIL = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Se houver pelo menos uma linha no ResultSet, significa que o usuário existe
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                usuarioExiste = count > 0;
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarioExiste;
    }

    public static User ObterUsuarioPorId(int userId) {
        User usuario = new User();
        String SQL = "SELECT * FROM USERS WHERE USERSID = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, userId); // Define o parâmetro do ID do usuário

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    usuario.setUserId(resultSet.getInt("usersId"));
                    usuario.setNome(resultSet.getString("Nome"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setCPF(resultSet.getString("CPF"));
                    usuario.setSenha(resultSet.getString("senha"));
                    usuario.setGrupo(resultSet.getInt("grupo"));
                    usuario.setSituacao(resultSet.getBoolean("situacao"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public static boolean AlterarUsuario(User usuario) {
        boolean sucesso = false;

        String SQL = "UPDATE USERS SET NOME = ?, SENHA = ?, CPF = ?, SITUACAO = ?, GRUPO = ? WHERE EMAIL = ?";

        try {
            Connection connection = ConnectionPoolConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            // Preenche os parâmetros da query com os valores do objeto User
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getCPF());
            preparedStatement.setBoolean(4, usuario.isSituacao());
            preparedStatement.setInt(5, usuario.getGrupo());
            preparedStatement.setString(6, usuario.getEmail());

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
}