package br.com.fiap.dao;

import br.com.fiap.to.EnderecoTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    private Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um novo endereço
    public boolean inserirEndereco(EnderecoTO endereco) throws SQLException {
        String sql = "INSERT INTO T_PIECS_ENDERECO (cep, rua, bairro, cidade, estado, numero) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getRua());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getEstado());
            stmt.setString(6, endereco.getNumero());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    endereco.setIdEndereco(generatedKeys.getInt(1));
                }
                return true;
            }
        }
        return false;
    }

    // Método para buscar um endereço pelo ID
    public EnderecoTO buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM T_PIECS_ENDERECO WHERE id_endereco = ?";
        EnderecoTO endereco = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                endereco = mapResultSetToEndereco(rs);
            }
        }
        return endereco;
    }

    // Método para buscar todos os endereços
    public List<EnderecoTO> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM T_PIECS_ENDERECO";
        List<EnderecoTO> enderecos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                enderecos.add(mapResultSetToEndereco(rs));
            }
        }
        return enderecos;
    }

    // Método para atualizar um endereço
    public boolean atualizarEndereco(EnderecoTO endereco) throws SQLException {
        String sql = "UPDATE T_PIECS_ENDERECO SET cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, numero = ? WHERE id_endereco = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getRua());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getEstado());
            stmt.setString(6, endereco.getNumero());
            stmt.setInt(7, endereco.getIdEndereco());

            return stmt.executeUpdate() > 0;
        }
    }

    // Método para deletar um endereço pelo ID
    public boolean deletarEndereco(int id) throws SQLException {
        String sql = "DELETE FROM T_PIECS_ENDERECO WHERE id_endereco = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Método utilitário para mapear um ResultSet para um objeto EnderecoTO
    private EnderecoTO mapResultSetToEndereco(ResultSet rs) throws SQLException {
        EnderecoTO endereco = new EnderecoTO();
        endereco.setIdEndereco(rs.getInt("id_endereco"));
        endereco.setCep(rs.getString("cep"));
        endereco.setRua(rs.getString("rua"));
        endereco.setBairro(rs.getString("bairro"));
        endereco.setCidade(rs.getString("cidade"));
        endereco.setEstado(rs.getString("estado"));
        endereco.setNumero(rs.getString("numero"));
        return endereco;
    }
}

