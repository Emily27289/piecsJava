package br.com.fiap.bo;

import br.com.fiap.dao.ResponsavelDAO;
import br.com.fiap.to.ResponsavelTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ResponsavelBO {

    private ResponsavelDAO responsavelDAO;

    public ResponsavelBO(Connection connection) {
        this.responsavelDAO = new ResponsavelDAO(connection);
    }

    public boolean adicionarResponsavel(ResponsavelTO responsavel) throws SQLException {
        if (responsavel == null) {
            throw new IllegalArgumentException("O responsável não pode ser nulo.");
        }

        if (responsavel.getCpfCnpj() == null || responsavel.getCpfCnpj().isEmpty()) {
            throw new IllegalArgumentException("O CPF/CNPJ é obrigatório.");
        }

        if (responsavel.getEmail() == null || responsavel.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O e-mail é obrigatório.");
        }

        if (responsavel.getSenha() == null || responsavel.getSenha().length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }

        ResponsavelTO existente = responsavelDAO.buscarPorCpfCnpj(responsavel.getCpfCnpj());
        if (existente != null) {
            throw new IllegalArgumentException("Já existe um responsável com este CPF/CNPJ.");
        }

        return responsavelDAO.inserirResponsavel(responsavel);
    }

    public boolean atualizarResponsavel(ResponsavelTO responsavel) throws SQLException {
        if (responsavel == null || responsavel.getIdResponsavel() <= 0) {
            throw new IllegalArgumentException("ID de responsável inválido para atualização.");
        }

        if (responsavel.getEmail() == null || responsavel.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O e-mail é obrigatório.");
        }

        if (responsavel.getSenha() == null || responsavel.getSenha().length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }

        ResponsavelTO existente = responsavelDAO.buscarPorId(responsavel.getIdResponsavel());
        if (existente == null) {
            throw new IllegalArgumentException("Responsável não encontrado para atualização.");
        }

        return responsavelDAO.atualizarResponsavel(responsavel);
    }

    public ResponsavelTO buscarResponsavelPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de responsável inválido.");
        }

        return responsavelDAO.buscarPorId(id);
    }

    public List<ResponsavelTO> buscarTodosResponsaveis() throws SQLException {
        return responsavelDAO.buscarTodos();
    }

    public boolean removerResponsavel(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de responsável inválido.");
        }

        ResponsavelTO responsavel = responsavelDAO.buscarPorId(id);
        if (responsavel == null) {
            throw new IllegalArgumentException("Responsável não encontrado para exclusão.");
        }

        return responsavelDAO.deletarResponsavel(id);
    }
}

