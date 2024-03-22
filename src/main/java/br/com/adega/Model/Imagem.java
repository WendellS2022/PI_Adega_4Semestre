package br.com.adega.Model;

public class Imagem {
    private int id;
    private int produtoId;
    private String diretorio;
    private String nome;
    private boolean qualificacao;
    private String extensao;

    public Imagem() {
    }

    public Imagem(int id, int produtoId, String diretorio, String nome, boolean qualificacao, String extensao) {
        this.id = id;
        this.produtoId = produtoId;
        this.diretorio = diretorio;
        this.nome = nome;
        this.qualificacao = qualificacao;
        this.extensao = extensao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(boolean qualificacao) {
        this.qualificacao = qualificacao;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }



}
