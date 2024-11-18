package br.com.fiap.bo;

import br.com.fiap.dao.VendaDAO;
import br.com.fiap.to.VendaTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VendaBO {
    private VendaDAO vendaDAO;

    public VendaBO(Connection connection) {
        this.vendaDAO = new VendaDAO(connection);
    }

    public void adicionarVenda(VendaTO venda) throws SQLException {
        if (venda.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
        if (venda.getQtEnergia() <= 0) {
            throw new IllegalArgumentException("A quantidade de energia deve ser maior que zero.");
        }
        if (venda.getIdComprador() <= 0) {
            throw new IllegalArgumentException("O ID do comprador deve ser válido.");
        }

        vendaDAO.inserir(venda);
    }

    public void atualizarVenda(VendaTO venda) throws SQLException {
        if (venda.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
        if (venda.getQtEnergia() <= 0) {
            throw new IllegalArgumentException("A quantidade de energia deve ser maior que zero.");
        }
        if (venda.getIdComprador() <= 0) {
            throw new IllegalArgumentException("O ID do comprador deve ser válido.");
        }

        vendaDAO.atualizar(venda);
    }

    public void deletarVenda(int idVenda) throws SQLException {
        vendaDAO.deletar(idVenda);
    }

    public VendaTO buscarVendaPorId(int idVenda) throws SQLException {
        return vendaDAO.buscarPorId(idVenda);
    }

    public List<VendaTO> findAll() throws SQLException {
        return vendaDAO.findAll(); // Chamando o método findAll da DAO
    }
}
