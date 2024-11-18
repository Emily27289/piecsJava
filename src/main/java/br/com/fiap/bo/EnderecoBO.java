package br.com.fiap.bo;

import br.com.fiap.dao.EnderecoDAO;
import br.com.fiap.to.EnderecoTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EnderecoBO {

    private EnderecoDAO enderecoDAO;

    public EnderecoBO(Connection connection) {
        this.enderecoDAO = new EnderecoDAO(connection);
    }

    public boolean adicionarEndereco(EnderecoTO endereco) throws SQLException {
        if (endereco == null) {
            throw new IllegalArgumentException("O endereço não pode ser nulo.");
        }

        if (endereco.getCep() == null || endereco.getCep().isEmpty()) {
            throw new IllegalArgumentException("O CEP é obrigatório.");
        }

        if (endereco.getRua() == null || endereco.getRua().isEmpty()) {
            throw new IllegalArgumentException("A rua é obrigatória.");
        }

        if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) {
            throw new IllegalArgumentException("O bairro é obrigatório.");
        }

        if (endereco.getCidade() == null || endereco.getCidade().isEmpty()) {
            throw new IllegalArgumentException("A cidade é obrigatória.");
        }

        if (endereco.getEstado() == null || endereco.getEstado().isEmpty()) {
            throw new IllegalArgumentException("O estado é obrigatório.");
        }

        if (endereco.getNumero() == null || endereco.getNumero().isEmpty()) {
            throw new IllegalArgumentException("O número é obrigatório.");
        }

        return enderecoDAO.inserirEndereco(endereco);
    }

    public boolean atualizarEndereco(EnderecoTO endereco) throws SQLException {
        if (endereco == null || endereco.getIdEndereco() <= 0) {
            throw new IllegalArgumentException("ID de endereço inválido para atualização.");
        }

        if (endereco.getCep() == null || endereco.getCep().isEmpty()) {
            throw new IllegalArgumentException("O CEP é obrigatório.");
        }

        if (endereco.getRua() == null || endereco.getRua().isEmpty()) {
            throw new IllegalArgumentException("A rua é obrigatória.");
        }

        if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) {
            throw new IllegalArgumentException("O bairro é obrigatório.");
        }

        if (endereco.getCidade() == null || endereco.getCidade().isEmpty()) {
            throw new IllegalArgumentException("A cidade é obrigatória.");
        }

        if (endereco.getEstado() == null || endereco.getEstado().isEmpty()) {
            throw new IllegalArgumentException("O estado é obrigatório.");
        }

        if (endereco.getNumero() == null || endereco.getNumero().isEmpty()) {
            throw new IllegalArgumentException("O número é obrigatório.");
        }

        return enderecoDAO.atualizarEndereco(endereco);
    }

    public EnderecoTO buscarEnderecoPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de endereço inválido.");
        }

        return enderecoDAO.buscarPorId(id);
    }

    public List<EnderecoTO> findAll() throws SQLException {
        return enderecoDAO.findAll(); // Chamando o método findAll da DAO
    }

    public boolean removerEndereco(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de endereço inválido.");
        }

        EnderecoTO endereco = enderecoDAO.buscarPorId(id);
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não encontrado para exclusão.");
        }

        return enderecoDAO.deletarEndereco(id);
    }
}
