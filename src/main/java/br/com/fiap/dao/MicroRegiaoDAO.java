package br.com.fiap.dao;

import br.com.fiap.to.MicroRegiaoTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MicroRegiaoDAO {
    private Connection connection;

    public MicroRegiaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(MicroRegiaoTO microRegiao) throws SQLException {
        String sql = "INSERT INTO T_PIECS_MICRO_REGIAO (qt_equipamento, qt_bateria, id_responsavel, id_endereco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, microRegiao.getQtEquipamento());
            stmt.setInt(2, microRegiao.getQtBateria());
            stmt.setInt(3, microRegiao.getIdResponsavel());
            stmt.setInt(4, microRegiao.getIdEndereco());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    microRegiao.setIdMicroRegiao(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void atualizar(MicroRegiaoTO microRegiao) throws SQLException {
        String sql = "UPDATE T_PIECS_MICRO_REGIAO SET qt_equipamento = ?, qt_bateria = ?, id_responsavel = ?, id_endereco = ? WHERE id_micro_regiao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, microRegiao.getQtEquipamento());
            stmt.setInt(2, microRegiao.getQtBateria());
            stmt.setInt(3, microRegiao.getIdResponsavel());
            stmt.setInt(4, microRegiao.getIdEndereco());
            stmt.setInt(5, microRegiao.getIdMicroRegiao());
            stmt.executeUpdate();
        }
    }

    public void deletar(int idMicroRegiao) throws SQLException {
        String sql = "DELETE FROM T_PIECS_MICRO_REGIAO WHERE id_micro_regiao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idMicroRegiao);
            stmt.executeUpdate();
        }
    }

    public MicroRegiaoTO buscarPorId(int idMicroRegiao) throws SQLException {
        String sql = "SELECT * FROM T_PIECS_MICRO_REGIAO WHERE id_micro_regiao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idMicroRegiao);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    MicroRegiaoTO microRegiao = new MicroRegiaoTO();
                    microRegiao.setIdMicroRegiao(rs.getInt("id_micro_regiao"));
                    microRegiao.setQtEquipamento(rs.getInt("qt_equipamento"));
                    microRegiao.setQtBateria(rs.getInt("qt_bateria"));
                    microRegiao.setIdResponsavel(rs.getInt("id_responsavel"));
                    microRegiao.setIdEndereco(rs.getInt("id_endereco"));
                    return microRegiao;
                }
            }
        }
        return null;
    }

    public List<MicroRegiaoTO> listarTodos() throws SQLException {
        List<MicroRegiaoTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_PIECS_MICRO_REGIAO";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                MicroRegiaoTO microRegiao = new MicroRegiaoTO();
                microRegiao.setIdMicroRegiao(rs.getInt("id_micro_regiao"));
                microRegiao.setQtEquipamento(rs.getInt("qt_equipamento"));
                microRegiao.setQtBateria(rs.getInt("qt_bateria"));
                microRegiao.setIdResponsavel(rs.getInt("id_responsavel"));
                microRegiao.setIdEndereco(rs.getInt("id_endereco"));
                lista.add(microRegiao);
            }
        }
        return lista;
    }
}

