package br.com.fiap.dao;

import br.com.fiap.to.BeneficiarioTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeneficiarioDAO {
    private Connection connection;

    public BeneficiarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(BeneficiarioTO beneficiario) throws SQLException {
        String sql = "INSERT INTO T_PIECS_BENEFICIARIOS (nm_beneficiario, email, senha, id_micro_regiao, id_responsavel) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, beneficiario.getNome());
            stmt.setString(2, beneficiario.getEmail());
            stmt.setString(3, beneficiario.getSenha());
            stmt.setInt(4, beneficiario.getIdMicroRegiao());
            stmt.setInt(5, beneficiario.getIdResponsavel());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    beneficiario.setIdBeneficiario(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void atualizar(BeneficiarioTO beneficiario) throws SQLException {
        String sql = "UPDATE T_PIECS_BENEFICIARIOS SET nm_beneficiario = ?, email = ?, senha = ?, id_micro_regiao = ?, id_responsavel = ? WHERE id_beneficiario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, beneficiario.getNome());
            stmt.setString(2, beneficiario.getEmail());
            stmt.setString(3, beneficiario.getSenha());
            stmt.setInt(4, beneficiario.getIdMicroRegiao());
            stmt.setInt(5, beneficiario.getIdResponsavel());
            stmt.setInt(6, beneficiario.getIdBeneficiario());
            stmt.executeUpdate();
        }
    }

    public void deletar(int idBeneficiario) throws SQLException {
        String sql = "DELETE FROM T_PIECS_BENEFICIARIOS WHERE id_beneficiario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idBeneficiario);
            stmt.executeUpdate();
        }
    }

    public BeneficiarioTO buscarPorId(int idBeneficiario) throws SQLException {
        String sql = "SELECT * FROM T_PIECS_BENEFICIARIOS WHERE id_beneficiario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idBeneficiario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BeneficiarioTO beneficiario = new BeneficiarioTO();
                    beneficiario.setIdBeneficiario(rs.getInt("id_beneficiario"));
                    beneficiario.setNome(rs.getString("nm_beneficiario"));
                    beneficiario.setEmail(rs.getString("email"));
                    beneficiario.setSenha(rs.getString("senha"));
                    beneficiario.setIdMicroRegiao(rs.getInt("id_micro_regiao"));
                    beneficiario.setIdResponsavel(rs.getInt("id_responsavel"));
                    return beneficiario;
                }
            }
        }
        return null;
    }

    public List<BeneficiarioTO> listarTodos() throws SQLException {
        List<BeneficiarioTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_PIECS_BENEFICIARIOS";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                BeneficiarioTO beneficiario = new BeneficiarioTO();
                beneficiario.setIdBeneficiario(rs.getInt("id_beneficiario"));
                beneficiario.setNome(rs.getString("nm_beneficiario"));
                beneficiario.setEmail(rs.getString("email"));
                beneficiario.setSenha(rs.getString("senha"));
                beneficiario.setIdMicroRegiao(rs.getInt("id_micro_regiao"));
                beneficiario.setIdResponsavel(rs.getInt("id_responsavel"));
                lista.add(beneficiario);
            }
        }
        return lista;
    }
}

