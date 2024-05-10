package br.com.adega.Model;

public class Pedido {
    private int idCliente;
    private int produtoId;
    private int idEndereco;
    private String tipoPagamento;
    private String statusPagamento;

    public Pedido() {
    }

    public Pedido(int idCliente, int produtoId, int idEndereco, String tipoPagamento, String statusPagamento) {
        this.idCliente = idCliente;
        this.produtoId = produtoId;
        this.idEndereco = idEndereco;
        this.tipoPagamento = tipoPagamento;
        this.statusPagamento = statusPagamento;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
