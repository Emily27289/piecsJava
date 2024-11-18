package br.com.fiap.bo;

import br.com.fiap.dao.BeneficiarioDAO;
import br.com.fiap.to.BeneficiarioTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BeneficiarioBO {
    private BeneficiarioDAO beneficiarioDAO;

    public BeneficiarioBO(Connection connection) {
        this.beneficiarioDAO = new BeneficiarioDAO(connection);
    }

    public void adicionarBeneficiario(BeneficiarioTO beneficiario) throws SQLException {
        if (beneficiario.getNome() != null && !beneficiario.getNome().isEmpty() &&
                beneficiario.getEmail() != null && !beneficiario.getEmail().isEmpty() &&
                beneficiario.getSenha() != null && !beneficiario.getSenha().isEmpty()) {
            beneficiarioDAO.inserir(beneficiario);
        } else {
            throw new IllegalArgumentException("Nome, email e senha são obrigatórios.");
        }
    }

    public void atualizarBeneficiario(BeneficiarioTO beneficiario) throws SQLException {
        beneficiarioDAO.atualizar(beneficiario);
    }

    public void deletarBeneficiario(int idBeneficiario) throws SQLException {
        beneficiarioDAO.deletar(idBeneficiario);
    }

    public BeneficiarioTO buscarBeneficiarioPorId(int idBeneficiario) throws SQLException {
        return beneficiarioDAO.buscarPorId(idBeneficiario);
    }

    public List<BeneficiarioTO> findAll() throws SQLException {
        return beneficiarioDAO.findAll(); // Chamando o método findAll da DAO
    }
}
