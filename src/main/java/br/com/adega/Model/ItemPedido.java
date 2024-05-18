package br.com.adega.Model;

import java.math.BigDecimal;

public class ItemPedido {
    private int pedidoId;
    protected String produtoId;
    private String nomeProduto;
    private BigDecimal preco;
    private int quantidadeComprada;
    private String frete;
    private Endereco endereco;
    private BigDecimal subTotal;
    private String statusPagamento;
    private String tipoPagamento;
    private String descricaoProduto;

    public ItemPedido() {
    }

    public ItemPedido(int pedidoId, String produtoId, String nomeProduto, BigDecimal preco, int quantidadeComprada, String frete, Endereco endereco, BigDecimal subTotal, String statusPagamento, String tipoPagamento, String descricaoProduto) {
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.quantidadeComprada = quantidadeComprada;
        this.frete = frete;
        this.endereco = endereco;
        this.subTotal = subTotal;
        this.statusPagamento = statusPagamento;
        this.tipoPagamento = tipoPagamento;
        this.descricaoProduto = descricaoProduto;
    }

    public int getPedidoId() {return pedidoId;}

    public void setPedidoId(int pedidoId) {this.pedidoId = pedidoId;}

    public String getProdutoId() {return produtoId;}

    public void setProdutoId(String produtoId) {this.produtoId = produtoId;}

    public String getNomeProduto() {return nomeProduto;}

    public void setNomeProduto(String nomeProduto) {this.nomeProduto = nomeProduto;}

    public BigDecimal getPreco() {return preco;}

    public void setPreco(BigDecimal preco) {this.preco = preco;}

    public int getQuantidadeComprada() {return quantidadeComprada;}

    public void setQuantidadeComprada(int quantidadeComprada) {this.quantidadeComprada = quantidadeComprada;}

    public String getFrete() {return frete;}


    public void setFrete(String frete) {this.frete = frete;}

    public Endereco getEndereco() {return endereco;}

    public void setEndereco(Endereco endereco) {this.endereco = endereco;}

    public BigDecimal getSubTotal() {return subTotal;}

    public void setSubTotal(BigDecimal subTotal) {this.subTotal = subTotal;}

    public String getStatusPagamento() {return statusPagamento;}

    public void setStatusPagamento(String statusPagamento) {this.statusPagamento = statusPagamento;}

    public String getTipoPagamento() {return tipoPagamento;}

    public void setTipoPagamento(String tipoPagamento) {this.tipoPagamento = tipoPagamento;}

    public String getDescricaoProduto() {return descricaoProduto;}

    public void setDescricaoProduto(String descricaoProduto) {this.descricaoProduto = descricaoProduto;}
}
