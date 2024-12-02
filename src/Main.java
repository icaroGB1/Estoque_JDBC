import Entidades.movimentacaoEstoque;
import Enums.Tipo;
import repository.EstoqueRepository;
import repository.MovimentacaoEstoqueRepository;


public class Main {
    public static void main(String[] args) throws Exception {
        MovimentacaoEstoqueRepository repository = new MovimentacaoEstoqueRepository();
        EstoqueRepository repository1 = new EstoqueRepository();
        repository.cancelarMovi(14);
    }
}