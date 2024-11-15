package br.com.fiap.dao;

import br.com.fiap.to.BateriaTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BateriaDAO {
    private Connection connection;

    public BateriaDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(BateriaTO bateria) throws SQLException {
        String sql = "INSERT INTO T_PIECS_BATERIA (qt_armazenada_bateria, capacidade, id_micro_regiao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, bateria.getQtArmazenadaBateria());
            stmt.setInt(2, bateria.getCapacidade());
            stmt.setInt(3, bateria.getIdMicroRegiao());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    bateria.setIdBateria(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void atualizar(BateriaTO bateria) throws SQLException {
        String sql = "UPDATE T_PIECS_BATERIA SET qt_armazenada_bateria = ?, capacidade = ?, id_micro_regiao = ? WHERE id_bateria = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bateria.getQtArmazenadaBateria());
            stmt.setInt(2, bateria.getCapacidade());
            stmt.setInt(3, bateria.getIdMicroRegiao());
            stmt.setInt(4, bateria.getIdBateria());
            stmt.executeUpdate();
        }
    }

    public void deletar(int idBateria) throws SQLException {
        String sql = "DELETE FROM T_PIECS_BATERIA WHERE id_bateria = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idBateria);
            stmt.executeUpdate();
        }
    }

    public BateriaTO buscarPorId(int idBateria) throws SQLException {
        String sql = "SELECT * FROM T_PIECS_BATERIA WHERE id_bateria = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idBateria);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BateriaTO bateria = new BateriaTO();
                    bateria.setIdBateria(rs.getInt("id_bateria"));
                    bateria.setQtArmazenadaBateria(rs.getInt("qt_armazenada_bateria"));
                    bateria.setCapacidade(rs.getInt("capacidade"));
                    bateria.setIdMicroRegiao(rs.getInt("id_micro_regiao"));
                    return bateria;
                }
            }
        }
        return null;
    }

    public List<BateriaTO> listarTodos() throws SQLException {
        List<BateriaTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_PIECS_BATERIA";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                BateriaTO bateria = new BateriaTO();
                bateria.setIdBateria(rs.getInt("id_bateria"));
                bateria.setQtArmazenadaBateria(rs.getInt("qt_armazenada_bateria"));
                bateria.setCapacidade(rs.getInt("capacidade"));
                bateria.setIdMicroRegiao(rs.getInt("id_micro_regiao"));
                lista.add(bateria);
            }
        }
        return lista;
    }
}

