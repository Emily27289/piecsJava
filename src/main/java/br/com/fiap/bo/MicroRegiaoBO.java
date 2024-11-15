package br.com.fiap.bo;

import br.com.fiap.dao.MicroRegiaoDAO;
import br.com.fiap.to.MicroRegiaoTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MicroRegiaoBO {
    private MicroRegiaoDAO microRegiaoDAO;

    public MicroRegiaoBO(Connection connection) {
        this.microRegiaoDAO = new MicroRegiaoDAO(connection);
    }

    public void adicionarMicroRegiao(MicroRegiaoTO microRegiao) throws SQLException {
        if (microRegiao.getQtEquipamento() > 0 && microRegiao.getQtBateria() > 0) {
            microRegiaoDAO.inserir(microRegiao);
        } else {
            throw new IllegalArgumentException("Quantidade de equipamento e bateria devem ser maiores que zero.");
        }
    }

    public void atualizarMicroRegiao(MicroRegiaoTO microRegiao) throws SQLException {
        microRegiaoDAO.atualizar(microRegiao);
    }

    public void deletarMicroRegiao(int idMicroRegiao) throws SQLException {
        microRegiaoDAO.deletar(idMicroRegiao);
    }

    public MicroRegiaoTO buscarMicroRegiaoPorId(int idMicroRegiao) throws SQLException {
        return microRegiaoDAO.buscarPorId(idMicroRegiao);
    }

    public List<MicroRegiaoTO> listarTodasMicroRegioes() throws SQLException {
        return microRegiaoDAO.listarTodos();
    }
}

