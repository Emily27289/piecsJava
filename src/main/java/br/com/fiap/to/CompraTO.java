package br.com.fiap.to;

public class CompraTO {
    private int idCompra;
    private double preco;
    private int qtEnergia;
    private int idResponsavel;
    private String statusCompra; // "disponivel" ou "vendido"
    private int idBateria;

    // Getters e Setters
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtEnergia() {
        return qtEnergia;
    }

    public void setQtEnergia(int qtEnergia) {
        this.qtEnergia = qtEnergia;
    }

    public int getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getStatusCompra() {
        return statusCompra;
    }

    public void setStatusCompra(String statusCompra) {
        this.statusCompra = statusCompra;
    }

    public int getIdBateria() {
        return idBateria;
    }

    public void setIdBateria(int idBateria) {
        this.idBateria = idBateria;
    }
}

