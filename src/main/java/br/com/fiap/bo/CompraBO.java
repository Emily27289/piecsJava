package br.com.fiap.bo;

import br.com.fiap.dao.CompraDAO;
import br.com.fiap.to.CompraTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompraBO {
    private CompraDAO compraDAO;

    public CompraBO(Connection connection) {
        this.compraDAO = new CompraDAO(connection);
    }

    public void adicionarCompra(CompraTO compra) throws SQLException {

        if (compra.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
        if (compra.getQtEnergia() <= 0) {
            throw new IllegalArgumentException("A quantidade de energia deve ser maior que zero.");
        }
        if (!compra.getStatusCompra().equals("disponivel") && !compra.getStatusCompra().equals("vendido")) {
            throw new IllegalArgumentException("O status da compra deve ser 'disponivel' ou 'vendido'.");
        }

        compraDAO.inserir(compra);
    }

    public void atualizarCompra(CompraTO compra) throws SQLException {

        if (compra.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
        if (compra.getQtEnergia() <= 0) {
            throw new IllegalArgumentException("A quantidade de energia deve ser maior que zero.");
        }
        if (!compra.getStatusCompra().equals("disponivel") && !compra.getStatusCompra().equals("vendido")) {
            throw new IllegalArgumentException("O status da compra deve ser 'disponivel' ou 'vendido'.");
        }

        compraDAO.atualizar(compra);
    }

    public void deletarCompra(int idCompra) throws SQLException {
        compraDAO.deletar(idCompra);
    }

    public CompraTO buscarCompraPorId(int idCompra) throws SQLException {
        return compraDAO.buscarPorId(idCompra);
    }

    public List<CompraTO> listarTodasCompras() throws SQLException {
        return compraDAO.listarTodos();
    }
}

