package br.com.fiap.to;

public class BeneficiarioTO {
    private int idBeneficiario;
    private String nome;
    private String email;
    private String senha;
    private int idMicroRegiao;
    private int idResponsavel;

    // Getters e Setters
    public int getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(int idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public int getIdMicroRegiao() {
        return idMicroRegiao;
    }

    public void setIdMicroRegiao(int idMicroRegiao) {
        this.idMicroRegiao = idMicroRegiao;
    }

    public int getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }
}

