package br.com.adega.Model;

public class Carrinho {
   private int produtoId;
   private int quantidadeComprada;

    public Carrinho(int produtoId) {
        this.produtoId = produtoId;
    }

    public Carrinho() {
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }
}
