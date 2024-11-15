package br.com.fiap.bo;

import br.com.fiap.dao.CompradorDAO;
import br.com.fiap.to.CompradorTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompradorBO {
    private CompradorDAO compradorDAO;

    public CompradorBO(Connection connection) {
        this.compradorDAO = new CompradorDAO(connection);
    }

    public void adicionarComprador(CompradorTO comprador) throws SQLException {

        if (comprador.getNomeComprador() == null || comprador.getNomeComprador().isEmpty()) {
            throw new IllegalArgumentException("O nome do comprador é obrigatório.");
        }
        if (comprador.getCpfCnpj() == null || comprador.getCpfCnpj().isEmpty()) {
            throw new IllegalArgumentException("O CPF/CNPJ é obrigatório.");
        }
        if (comprador.getEmail() == null || comprador.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email é obrigatório.");
        }
        if (comprador.getSenha() == null || comprador.getSenha().isEmpty()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }

        compradorDAO.inserir(comprador);
    }

    public void atualizarComprador(CompradorTO comprador) throws SQLException {

        if (comprador.getNomeComprador() == null || comprador.getNomeComprador().isEmpty()) {
            throw new IllegalArgumentException("O nome do comprador é obrigatório.");
        }
        if (comprador.getCpfCnpj() == null || comprador.getCpfCnpj().isEmpty()) {
            throw new IllegalArgumentException("O CPF/CNPJ é obrigatório.");
        }
        if (comprador.getEmail() == null || comprador.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email é obrigatório.");
        }
        if (comprador.getSenha() == null || comprador.getSenha().isEmpty()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }

        compradorDAO.atualizar(comprador);
    }

    public void deletarComprador(int idComprador) throws SQLException {
        compradorDAO.deletar(idComprador);
    }

    public CompradorTO buscarCompradorPorId(int idComprador) throws SQLException {
        return compradorDAO.buscarPorId(idComprador);
    }

    public List<CompradorTO> listarTodosCompradores() throws SQLException {
        return compradorDAO.listarTodos();
    }
}

