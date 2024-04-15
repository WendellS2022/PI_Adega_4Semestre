package br.com.adega.DAO;

import br.com.adega.Model.Endereco;
import br.com.adega.Config.ConnectionPoolConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnderecoDAO {
    // Método para salvar o endereço no banco de dados
    public void salvarEndereco(Endereco endereco) {
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
            statement.setString(3, endereco.getNumero());
            statement.setString(4, endereco.getComplemento());
            statement.setString(5, endereco.getBairro());
            statement.setString(6, endereco.getCidade());
            statement.setString(7, endereco.getUf());
            statement.setBoolean(8,endereco.isStatus());
            statement.setBoolean(9,endereco.isPadrao());
            statement.setBoolean(10,endereco.isEnderecoFaturamento());
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
}
