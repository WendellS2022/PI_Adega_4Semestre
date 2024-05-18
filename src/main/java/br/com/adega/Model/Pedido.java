package br.com.adega.Model;

import java.math.BigDecimal;
import java.util.Date;

public class Pedido {
    private int idCliente;
    private int idEndereco;
    private String subTotal;
    private int quantidadeDeItens;
    private String frete;
    private Date dataPedido;
    private String tipoPagamento;
    private String statusPagamento;


    public Pedido() {
    }

    public Pedido(int idCliente, int idEndereco, String subTotal,int quantidadeDeItens, String frete, Date dataPedido, String tipoPagamento, String statusPagamento) {
        this.idCliente = idCliente;
        this.idEndereco = idEndereco;
        this.subTotal = subTotal;
        this.quantidadeDeItens = quantidadeDeItens;
        this.frete = frete;
        this.dataPedido = dataPedido;
        this.tipoPagamento = tipoPagamento;
        this.statusPagamento = statusPagamento;
    }



    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getSubTotal() {return subTotal;}

    public void setSubTotal(String subTotal) {this.subTotal = subTotal;}

    public int getQuantidadeDeItens() {return quantidadeDeItens;}

    public void setQuantidadeDeItens(int quantidadeDeItens) {this.quantidadeDeItens = quantidadeDeItens;}

    public String getFrete() {return frete;}

    public void setFrete(String frete) {this.frete = frete;}

    public void setDataPedido(Date dataPedido) {this.dataPedido = dataPedido;}

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
