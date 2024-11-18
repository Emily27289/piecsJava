package br.com.fiap.dao;

import br.com.fiap.to.ResponsavelTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelDAO {

    private Connection connection;

    public ResponsavelDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean inserirResponsavel(ResponsavelTO responsavel) throws SQLException {
        String sql = "INSERT INTO T_PIECS_RESPONSAVEL (nm_cliente, dt_nascimento, cpf_cnpj, email, senha, qt_armazenada_total) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, responsavel.getNomeCliente());
            stmt.setDate(2, new java.sql.Date(responsavel.getDataNascimento().getTime()));
            stmt.setString(3, responsavel.getCpfCnpj());
            stmt.setString(4, responsavel.getEmail());
            stmt.setString(5, responsavel.getSenha());
            stmt.setObject(6, responsavel.getQuantidadeArmazenadaTotal(), Types.INTEGER);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    responsavel.setIdResponsavel(generatedKeys.getInt(1));
                }
                return true;
            }
        }
        return false;
    }

    public ResponsavelTO buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM T_PIECS_RESPONSAVEL WHERE id_responsavel = ?";
        ResponsavelTO responsavel = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                responsavel = mapResultSetToResponsavel(rs);
            }
        }
        return responsavel;
    }

    public List<ResponsavelTO> findAll() throws SQLException {
        String sql = "SELECT * FROM T_PIECS_RESPONSAVEL";
        List<ResponsavelTO> responsaveis = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                responsaveis.add(mapResultSetToResponsavel(rs));
            }
        }
        return responsaveis;
    }

    public boolean atualizarResponsavel(ResponsavelTO responsavel) throws SQLException {
        String sql = "UPDATE T_PIECS_RESPONSAVEL SET nm_cliente = ?, dt_nascimento = ?, cpf_cnpj = ?, " +
                "email = ?, senha = ?, qt_armazenada_total = ? WHERE id_responsavel = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, responsavel.getNomeCliente());
            stmt.setDate(2, new java.sql.Date(responsavel.getDataNascimento().getTime()));
            stmt.setString(3, responsavel.getCpfCnpj());
            stmt.setString(4, responsavel.getEmail());
            stmt.setString(5, responsavel.getSenha());
            stmt.setObject(6, responsavel.getQuantidadeArmazenadaTotal(), Types.INTEGER);
            stmt.setInt(7, responsavel.getIdResponsavel());

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deletarResponsavel(int id) throws SQLException {
        String sql = "DELETE FROM T_PIECS_RESPONSAVEL WHERE id_responsavel = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public ResponsavelTO buscarPorCpfCnpj(String cpfCnpj) throws SQLException {
        String sql = "SELECT * FROM T_PIECS_RESPONSAVEL WHERE cpf_cnpj = ?";
        ResponsavelTO responsavel = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpfCnpj);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                responsavel = mapResultSetToResponsavel(rs);
            }
        }
        return responsavel;
    }

    private ResponsavelTO mapResultSetToResponsavel(ResultSet rs) throws SQLException {
        ResponsavelTO responsavel = new ResponsavelTO();
        responsavel.setIdResponsavel(rs.getInt("id_responsavel"));
        responsavel.setNomeCliente(rs.getString("nm_cliente"));
        responsavel.setDataNascimento(rs.getDate("dt_nascimento"));
        responsavel.setCpfCnpj(rs.getString("cpf_cnpj"));
        responsavel.setEmail(rs.getString("email"));
        responsavel.setSenha(rs.getString("senha"));
        responsavel.setQuantidadeArmazenadaTotal((Integer) rs.getObject("qt_armazenada_total"));
        return responsavel;
    }
}
