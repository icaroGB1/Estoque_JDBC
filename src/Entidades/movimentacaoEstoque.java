package Entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class movimentacaoEstoque {
    private  int id;
    private int produto;
    private int filial;
    private  int quantidade;
    private  String tipo;
    private LocalDate data_movimentacao;
    private  String referencia;

//    public movimentacaoEstoque(int id, Produto produto, int quantidade, Filial filial, String tipo, LocalDate data_movimentacao, String referencia) {
//        this.id = id;
//        this.produto = produto;
//        this.quantidade = quantidade;
//        this.filial = filial;
//        this.tipo = tipo;
//        this.data_movimentacao = data_movimentacao;
//        this.referencia = referencia;
//    }
    public movimentacaoEstoque(int produtoid, int quantidade, int filial, String tipo, String referencia) {
        this.produto = produtoid;
        this.quantidade = quantidade;
        this.filial = filial;
        this.tipo = tipo;
        this.referencia = referencia;
    }


    public int getId() {
        return id;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto.getId();
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial.getId();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData_movimentacao() {
        return data_movimentacao;
    }

    public void setData_movimentacao(LocalDate data_movimentacao) {
        this.data_movimentacao = data_movimentacao;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
