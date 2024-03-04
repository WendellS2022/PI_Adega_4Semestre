package br.com.adega.Model;

public class User {
    private int UserId;
    private String Nome;
    private String Email;
    private String Senha;
    private String CPF;
    private boolean Situacao;
    private int Grupo;

    public User() {
    }

    public User(int userId, String nome, String email, String senha, String CPF, boolean situacao, int grupo) {
        UserId = userId;
        Nome = nome;
        Email = email;
        Senha = senha;
        this.CPF = CPF;
        Situacao = situacao;
        Grupo = grupo;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public boolean isSituacao() {
        return Situacao;
    }

    public void setSituacao(boolean situacao) {
        Situacao = situacao;
    }

    public int getGrupo() {
        return Grupo;
    }

    public void setGrupo(int grupo) {
        Grupo = grupo;
    }
}
