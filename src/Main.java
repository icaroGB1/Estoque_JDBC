import Entidades.Filial;
import Entidades.Produto;
import Entidades.movimentacaoEstoque;
import repository.FilialRepository;
import repository.ProdutoRepository;
import repository.movimentacaoEstoqueRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws Exception {
        movimentacaoEstoqueRepository repository = new movimentacaoEstoqueRepository();
        movimentacaoEstoque mov = new movimentacaoEstoque(2,2,50, 10, "entre", "nota");
        repository.entrada(mov);
    }
}