package Entidades;

import java.time.LocalDate;

public class transferencia_estoque {
    private  int id;
    private  Produto produto;
    private Filial filialOrigem;
    private Filial filialDestino;
    private  int quantidade;
    private LocalDate data_transferencia;
    private  String status;

    public transferencia_estoque(int id, Produto produto, Filial filialOrigem, Filial filialDestino, int quantidade, LocalDate data_transferencia, String status) {
        this.id = id;
        this.produto = produto;
        this.filialOrigem = filialOrigem;
        this.filialDestino = filialDestino;
        this.quantidade = quantidade;
        this.data_transferencia = data_transferencia;
        this.status = status;

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

    public Filial getFilialDestino() {
        return filialDestino;
    }

    public void setFilialDestino(Filial filialDestino) {
        this.filialDestino = filialDestino;
    }

    public Filial getFilialOrigem() {
        return filialOrigem;
    }

    public void setFilialOrigem(Filial filialOrigem) {
        this.filialOrigem = filialOrigem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData_transferencia() {
        return data_transferencia;
    }

    public void setData_transferencia(LocalDate data_transferencia) {
        this.data_transferencia = data_transferencia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
