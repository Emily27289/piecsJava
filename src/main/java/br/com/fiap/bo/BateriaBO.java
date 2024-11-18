package br.com.fiap.bo;

import br.com.fiap.dao.BateriaDAO;
import br.com.fiap.to.BateriaTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BateriaBO {
    private BateriaDAO bateriaDAO;

    public BateriaBO(Connection connection) {
        this.bateriaDAO = new BateriaDAO(connection);
    }

    public void adicionarBateria(BateriaTO bateria) throws SQLException {
        if (bateria.getCapacidade() > 0) {
            bateriaDAO.inserir(bateria);
        } else {
            throw new IllegalArgumentException("A capacidade da bateria deve ser maior que zero.");
        }
    }

    public void atualizarBateria(BateriaTO bateria) throws SQLException {
        if (bateria.getCapacidade() > 0) {
            bateriaDAO.atualizar(bateria);
        } else {
            throw new IllegalArgumentException("A capacidade da bateria deve ser maior que zero.");
        }
    }

    public void deletarBateria(int idBateria) throws SQLException {
        bateriaDAO.deletar(idBateria);
    }

    public BateriaTO buscarBateriaPorId(int idBateria) throws SQLException {
        return bateriaDAO.buscarPorId(idBateria);
    }

    public List<BateriaTO> findAll() throws SQLException {
        return bateriaDAO.findAll(); // Chamando o método findAll da DAO
    }
}
