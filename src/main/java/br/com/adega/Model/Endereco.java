

package br.com.adega.Model;

public class Endereco {

    private int idEndereco;
    private String cep;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private boolean status;
    private boolean padrao;
    private boolean enderecoFaturamento;
    private int idCliente;

    public Endereco() {
        this.idEndereco = idEndereco;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.status = status;
        this.padrao = padrao;
        this.enderecoFaturamento = enderecoFaturamento;
        this.idCliente = idCliente;
    }
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public boolean isStatus() {return status;}

    public void setStatus(boolean status) { this.status = status;}

    public boolean isPadrao() { return padrao;}

    public void setPadrao(boolean padrao) { this.padrao = padrao;}

    public boolean isEnderecoFaturamento() { return enderecoFaturamento;}

    public void setEnderecoFaturamento(boolean enderecoFaturamento) { this.enderecoFaturamento = enderecoFaturamento;}

    public int getIdCliente() {return idCliente;}

    public void setIdCliente(int idCliente) {this.idCliente = idCliente;}

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }
}
