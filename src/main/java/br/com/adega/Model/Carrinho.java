package br.com.adega.Model;

public class Carrinho {
   private Produto produto;
   private int idCliente;
   private int quantidadeComprada;


    public Carrinho(Produto produto, int idCliente, int quantidadeComprada) {
        this.produto = produto;
        this.idCliente = idCliente;
        this.quantidadeComprada = quantidadeComprada;
    }

    public Carrinho() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getIdCliente() {return idCliente;}

    public void setIdCliente(int idCliente) {this.idCliente = idCliente;}

    public int getQuantidadeComprada() {return quantidadeComprada;}

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }
}
