package Entidades;

public class Estoque {
    private  int id;
    private  Produto produto;
    private  Filial filial;
    private  int  quantidade;

    public Estoque(int id, Produto produto, int quantidade, Filial filial) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.filial = filial;
    }

    public int getId() {
        return id;
    }


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
