package br.com.fiap.to;

import java.util.Date;

public class ResponsavelTO {

    private int idResponsavel;
    private String nomeCliente;
    private Date dataNascimento;
    private String cpfCnpj;
    private String email;
    private String senha;
    private Integer quantidadeArmazenadaTotal;


    public ResponsavelTO() {
    }

    public ResponsavelTO(int idResponsavel, String nomeCliente, Date dataNascimento,
                         String cpfCnpj, String email, String senha, Integer quantidadeArmazenadaTotal) {
        this.idResponsavel = idResponsavel;
        this.nomeCliente = nomeCliente;
        this.dataNascimento = dataNascimento;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.senha = senha;
        this.quantidadeArmazenadaTotal = quantidadeArmazenadaTotal;
    }

    public int getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getQuantidadeArmazenadaTotal() {
        return quantidadeArmazenadaTotal;
    }

    public void setQuantidadeArmazenadaTotal(Integer quantidadeArmazenadaTotal) {
        this.quantidadeArmazenadaTotal = quantidadeArmazenadaTotal;
    }

    @Override
    public String toString() {
        return "ResponsavelTO{" +
                "idResponsavel=" + idResponsavel +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", quantidadeArmazenadaTotal=" + quantidadeArmazenadaTotal +
                '}';
    }
}

