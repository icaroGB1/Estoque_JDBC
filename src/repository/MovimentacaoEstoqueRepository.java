package repository;

import Conexao.DB;
import Entidades.movimentacaoEstoque;
import Enums.Tipo;

import java.sql.*;
import java.time.LocalDate;

public class MovimentacaoEstoqueRepository {

    EstoqueRepository estoqueRepository =  new EstoqueRepository();
    public  void movimentacao(movimentacaoEstoque movi){
        String sql = "insert into movimentacao_estoque(id_produto, id_filial, quantidade, tipo, data_movimentacao, referencia) values(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement entrada = conn.prepareStatement(sql);){
            conn.setAutoCommit(false);
            entrada.setInt(1, movi.getProduto());
            entrada.setInt(2, movi.getFilial());
            entrada.setInt(3, movi.getQuantidade());
            entrada.setString(4,  movi.getTipo().name());
            entrada.setDate(5, Date.valueOf(LocalDate.now()));
            entrada.setString(6, movi.getReferencia());
            entrada.executeUpdate();
            if(movi.getTipo() == Tipo.entrada) {
                if (estoqueRepository.produtoExists(movi.getProduto(), movi.getFilial())) {
                    estoqueRepository.updateEntrada(movi.getQuantidade(), movi.getProduto(), movi.getFilial());
                } else {
                    estoqueRepository.entrada(movi.getProduto(), movi.getFilial(), movi.getQuantidade());
                }
            } else if (movi.getTipo() == Tipo.saida) {
                if (estoqueRepository.produtoExists(movi.getProduto(), movi.getFilial())){
                    estoqueRepository.updatesaida(movi.getQuantidade(), movi.getProduto(), movi.getFilial());
                }
            }
            conn.commit();
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
    public  void cancelarMovi(int id){
        String sql =  "select * from movimentacao_estoque where id = ?";
        String sql2 = "delete from movimentacao_estoque where id = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        PreparedStatement delete = conn.prepareStatement(sql2)){
            ps.setInt(1, id);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()){
                id = rs.getInt("id");
                int idProduto = rs.getInt("id_produto");
                int idFilial = rs.getInt("id_filial");
                int quantidade =rs.getInt("quantidade");
                Tipo tipo = Tipo.valueOf(rs.getString("tipo"));
                if(Tipo.entrada.equals(tipo)){
                    estoqueRepository.updatesaida(quantidade, idProduto, idFilial);
                } else if (Tipo.saida.equals(tipo)) {
                    estoqueRepository.updateEntrada(quantidade, idProduto, idFilial);
                }
                delete.setInt(1, id);
                delete.executeUpdate();
            }

        }catch(SQLException e ){
            e.printStackTrace();
        }
    }

}
