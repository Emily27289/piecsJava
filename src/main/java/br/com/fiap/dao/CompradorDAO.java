package br.com.fiap.dao;

import br.com.fiap.to.CompradorTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompradorDAO {
    private Connection connection;

    public CompradorDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(CompradorTO comprador) throws SQLException {
        String sql = "INSERT INTO T_PIECS_COMPRADOR (nm_comprador, cpf_cnpj, email, senha, id_endereco) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, comprador.getNomeComprador());
            stmt.setString(2, comprador.getCpfCnpj());
            stmt.setString(3, comprador.getEmail());
            stmt.setString(4, comprador.getSenha());
            stmt.setInt(5, comprador.getIdEndereco());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    comprador.setIdComprador(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void atualizar(CompradorTO comprador) throws SQLException {
        String sql = "UPDATE T_PIECS_COMPRADOR SET nm_comprador = ?, cpf_cnpj = ?, email = ?, senha = ?, id_endereco = ? WHERE id_comprador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, comprador.getNomeComprador());
            stmt.setString(2, comprador.getCpfCnpj());
            stmt.setString(3, comprador.getEmail());
            stmt.setString(4, comprador.getSenha());
            stmt.setInt(5, comprador.getIdEndereco());
            stmt.setInt(6, comprador.getIdComprador());
            stmt.executeUpdate();
        }
    }

    public void deletar(int idComprador) throws SQLException {
        String sql = "DELETE FROM T_PIECS_COMPRADOR WHERE id_comprador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idComprador);
            stmt.executeUpdate();
        }
    }

    public CompradorTO buscarPorId(int idComprador) throws SQLException {
        String sql = "SELECT * FROM T_PIECS_COMPRADOR WHERE id_comprador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idComprador);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CompradorTO comprador = new CompradorTO();
                    comprador.setIdComprador(rs.getInt("id_comprador"));
                    comprador.setNomeComprador(rs.getString("nm_comprador"));
                    comprador.setCpfCnpj(rs.getString("cpf_cnpj"));
                    comprador.setEmail(rs.getString("email"));
                    comprador.setSenha(rs.getString("senha"));
                    comprador.setIdEndereco(rs.getInt("id_endereco"));
                    return comprador;
                }
            }
        }
        return null;
    }

    public List<CompradorTO> listarTodos() throws SQLException {
        List<CompradorTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_PIECS_COMPRADOR";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CompradorTO comprador = new CompradorTO();
                comprador.setIdComprador(rs.getInt("id_comprador"));
                comprador.setNomeComprador(rs.getString("nm_comprador"));
                comprador.setCpfCnpj(rs.getString("cpf_cnpj"));
                comprador.setEmail(rs.getString("email"));
                comprador.setSenha(rs.getString("senha"));
                comprador.setIdEndereco(rs.getInt("id_endereco"));
                lista.add(comprador);
            }
        }
        return lista;
    }
}

