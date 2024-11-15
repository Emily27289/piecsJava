package br.com.fiap.dao;

import br.com.fiap.to.VendaTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    private Connection connection;

    public VendaDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(VendaTO venda) throws SQLException {
        String sql = "INSERT INTO T_PIECS_VENDA (preco, qt_energia, id_comprador) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, venda.getPreco());
            stmt.setInt(2, venda.getQtEnergia());
            stmt.setInt(3, venda.getIdComprador());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    venda.setIdVenda(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void atualizar(VendaTO venda) throws SQLException {
        String sql = "UPDATE T_PIECS_VENDA SET preco = ?, qt_energia = ?, id_comprador = ? WHERE id_venda = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, venda.getPreco());
            stmt.setInt(2, venda.getQtEnergia());
            stmt.setInt(3, venda.getIdComprador());
            stmt.setInt(4, venda.getIdVenda());
            stmt.executeUpdate();
        }
    }

    public void deletar(int idVenda) throws SQLException {
        String sql = "DELETE FROM T_PIECS_VENDA WHERE id_venda = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVenda);
            stmt.executeUpdate();
        }
    }

    public VendaTO buscarPorId(int idVenda) throws SQLException {
        String sql = "SELECT * FROM T_PIECS_VENDA WHERE id_venda = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVenda);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    VendaTO venda = new VendaTO();
                    venda.setIdVenda(rs.getInt("id_venda"));
                    venda.setPreco(rs.getDouble("preco"));
                    venda.setQtEnergia(rs.getInt("qt_energia"));
                    venda.setIdComprador(rs.getInt("id_comprador"));
                    return venda;
                }
            }
        }
        return null;
    }

    public List<VendaTO> listarTodos() throws SQLException {
        List<VendaTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_PIECS_VENDA";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                VendaTO venda = new VendaTO();
                venda.setIdVenda(rs.getInt("id_venda"));
                venda.setPreco(rs.getDouble("preco"));
                venda.setQtEnergia(rs.getInt("qt_energia"));
                venda.setIdComprador(rs.getInt("id_comprador"));
                lista.add(venda);
            }
        }
        return lista;
    }
}

