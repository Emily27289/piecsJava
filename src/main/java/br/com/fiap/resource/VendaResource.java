package br.com.fiap.resource;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.VendaDAO;
import br.com.fiap.to.VendaTO;

import java.sql.Connection;
import java.util.List;

public class VendaResource {
    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            VendaDAO vendaDAO = new VendaDAO(connection);
            List<VendaTO> vendas = vendaDAO.findAll();
            vendas.forEach(venda -> {
                System.out.println("ID: " + venda.getIdVenda());
                System.out.println("Preço: " + venda.getPreco());
                System.out.println("Quantidade de Energia: " + venda.getQtEnergia());
                System.out.println("ID do Comprador: " + venda.getIdComprador());
                System.out.println("------");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

