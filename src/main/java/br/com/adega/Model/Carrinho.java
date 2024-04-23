package br.com.adega.Model;

public class Carrinho extends Produto{
   private int quatidadeCompra;
   private  int idCliente;

    public int getQuatidadeCompra() {
        return quatidadeCompra;
    }

    public void setQuatidadeCompra(int quatidadeCompra) {
        this.quatidadeCompra = quatidadeCompra;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
