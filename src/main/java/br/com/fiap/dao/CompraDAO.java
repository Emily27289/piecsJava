package br.com.fiap.dao;

import br.com.fiap.to.CompraTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {
    private Connection connection;

    public CompraDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(CompraTO compra) throws SQLException {
        String sql = "INSERT INTO T_PIECS_COMPRA (preco, qt_energia, id_responsavel, st_compra, id_bateria) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, compra.getPreco());
            stmt.setInt(2, compra.getQtEnergia());
            stmt.setInt(3, compra.getIdResponsavel());
            stmt.setString(4, compra.getStatusCompra());
            stmt.setInt(5, compra.getIdBateria());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    compra.setIdCompra(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void atualizar(CompraTO compra) throws SQLException {
        String sql = "UPDATE T_PIECS_COMPRA SET preco = ?, qt_energia = ?, id_responsavel = ?, st_compra = ?, id_bateria = ? WHERE id_compra = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, compra.getPreco());
            stmt.setInt(2, compra.getQtEnergia());
            stmt.setInt(3, compra.getIdResponsavel());
            stmt.setString(4, compra.getStatusCompra());
            stmt.setInt(5, compra.getIdBateria());
            stmt.setInt(6, compra.getIdCompra());
            stmt.executeUpdate();
        }
    }

    public void deletar(int idCompra) throws SQLException {
        String sql = "DELETE FROM T_PIECS_COMPRA WHERE id_compra = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCompra);
            stmt.executeUpdate();
        }
    }

    public CompraTO buscarPorId(int idCompra) throws SQLException {
        String sql = "SELECT * FROM T_PIECS_COMPRA WHERE id_compra = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCompra);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CompraTO compra = new CompraTO();
                    compra.setIdCompra(rs.getInt("id_compra"));
                    compra.setPreco(rs.getDouble("preco"));
                    compra.setQtEnergia(rs.getInt("qt_energia"));
                    compra.setIdResponsavel(rs.getInt("id_responsavel"));
                    compra.setStatusCompra(rs.getString("st_compra"));
                    compra.setIdBateria(rs.getInt("id_bateria"));
                    return compra;
                }
            }
        }
        return null;
    }

    public List<CompraTO> findAll() throws SQLException {
        List<CompraTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_PIECS_COMPRA";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CompraTO compra = new CompraTO();
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setPreco(rs.getDouble("preco"));
                compra.setQtEnergia(rs.getInt("qt_energia"));
                compra.setIdResponsavel(rs.getInt("id_responsavel"));
                compra.setStatusCompra(rs.getString("st_compra"));
                compra.setIdBateria(rs.getInt("id_bateria"));
                lista.add(compra);
            }
        }
        return lista;
    }
}
