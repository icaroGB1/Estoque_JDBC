package Entidades;

public class Estoque {
    private  int id;
    private int produto;
    private int filial;
    private  int  quantidade;

    public Estoque(int id, int produtoid, int quantidade, int filialid) {
        this.id = id;
        this.produto = produtoid;
        this.quantidade = quantidade;
        this.filial = filialid;
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
}
