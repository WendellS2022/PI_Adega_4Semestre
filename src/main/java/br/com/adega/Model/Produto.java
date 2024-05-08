package br.com.adega.Model;

import java.math.BigDecimal;

public class Produto {

    private int codProduto;
    private String nomeProduto;
    private String dscDetalhadaProduto;
    private double avaliacaoProduto;
    private int qtdEstoque;
    private BigDecimal vlrVendaProduto;
    private boolean situacaoProduto;

    public Produto() {
    }

    public Produto(int codProduto, String nomeProduto, String dscDetalhadaProduto, double avaliacaoProduto, int qtdEstoque, BigDecimal vlrVendaProduto, boolean situacaoProduto) {
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.dscDetalhadaProduto = dscDetalhadaProduto;
        this.avaliacaoProduto = avaliacaoProduto;
        this.qtdEstoque = qtdEstoque;
        this.vlrVendaProduto = vlrVendaProduto;
        this.situacaoProduto = situacaoProduto;

    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDscDetalhadaProduto() {
        return dscDetalhadaProduto;
    }

    public void setDscDetalhadaProduto(String dscDetalhadaProduto) {
        this.dscDetalhadaProduto = dscDetalhadaProduto;
    }

    public double getAvaliacaoProduto() {
        return avaliacaoProduto;
    }

    public void setAvaliacaoProduto(double avaliacaoProduto) {
        this.avaliacaoProduto = avaliacaoProduto;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {this.qtdEstoque = qtdEstoque;}

    public BigDecimal getVlrVendaProduto() {
        return vlrVendaProduto;
    }

    public void setVlrVendaProduto(BigDecimal vlrVendaProduto) {
        this.vlrVendaProduto = vlrVendaProduto;
    }

    public boolean isSituacaoProduto() {
        return situacaoProduto;
    }

    public void setSituacaoProduto(boolean situacaoProduto) {
        this.situacaoProduto = situacaoProduto;
    }
}