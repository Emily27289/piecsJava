package br.com.fiap.to;

public class VendaTO {
    private int idVenda;
    private double preco;
    private int qtEnergia;
    private int idComprador;

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
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

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }
}

