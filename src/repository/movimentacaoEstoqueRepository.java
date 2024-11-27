package repository;

import Conexao.DB;
import Entidades.movimentacaoEstoque;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class movimentacaoEstoqueRepository {

    EstoqueRepository estoqueRepository =  new EstoqueRepository();
    public  void entrada(movimentacaoEstoque movi){
        String sql = "insert into movimentacao_estoque(id_produto, id_filial, quantidade, tipo, data_movimentacao, referencia) values(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement entrada = conn.prepareStatement(sql);){
            conn.setAutoCommit(false);
            entrada.setInt(1, movi.getProduto());
            entrada.setInt(2, movi.getFilial());
            entrada.setInt(3, movi.getQuantidade());
            entrada.setString(4, movi.getTipo());
            entrada.setDate(5, Date.valueOf(LocalDate.now()));
            entrada.setString(6, movi.getReferencia());
            entrada.executeUpdate();
            if(estoqueRepository.produtoExists(movi.getProduto(), movi.getFilial())){
                estoqueRepository.update(movi.getQuantidade(), movi.getProduto(), movi.getFilial());
                conn.commit();
            }else {
                estoqueRepository.entrada(movi.getProduto(), movi.getFilial(), movi.getQuantidade());
                conn.commit();
            }

        }catch (SQLException e ) {
            e.printStackTrace();
            System.out.println("ERRO na ENTRADA");
            try (Connection conn = DB.getConnection()) {
                conn.rollback();

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("ERRO NO ROLLBACK");
            }
        }
    }
}
