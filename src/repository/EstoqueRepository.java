package repository;

import Conexao.DB;
import Entidades.Estoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueRepository {

    public void  entrada(int produto, int filial, int quantidade ) {
        String sql = "insert into estoque(id_produto, id_filial, quantidade) values(?, ?,?)";
        try(Connection conn = DB.getConnection();
            PreparedStatement entrada = conn.prepareStatement(sql)){
            conn.setAutoCommit(false);
            entrada.setInt(1,produto);
            entrada.setInt(2, filial);
            entrada.setInt(3, quantidade);
            entrada.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO NA ENTRADA");
            try (Connection conn = DB.getConnection()) {
                conn.rollback();

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("ERRO NO ROLLBACK");
            }
        }
    }
    public boolean produtoExists(int produtoId, int filialId){
        String sql = "select * from estoque where id_produto = ? and id_filial = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement exists = conn.prepareStatement(sql);){
            exists.setInt(1,produtoId);
            exists.setInt(2, filialId);
            ResultSet rs =  exists.executeQuery();
            if (rs.next()){
                return  true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO NO produtoExists");
        }
        return  false;
    }
    public void updateEntrada(int quantidade, int produtoId, int filialId){
        String sql =  "update estoque SET quantidade = quantidade  + ?  WHERE id_produto = ? AND  id_filial = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement update = conn.prepareStatement(sql)) {
            update.setInt(1,quantidade);
            update.setInt(2, produtoId);
            update.setInt(3, filialId);
            update.executeUpdate();
        }catch (SQLException e ){
            e.printStackTrace();
            System.out.println("ERRO NO AUMENTO");
        }
    }
    public void updatesaida(int quantidade, int produtoId, int filialId){
        String sql =  "update estoque SET quantidade = quantidade  - ?  WHERE id_produto = ? AND  id_filial = ?";
        try(Connection conn = DB.getConnection();
            PreparedStatement update = conn.prepareStatement(sql)) {
            update.setInt(1,quantidade);
            update.setInt(2, produtoId);
            update.setInt(3, filialId);
            update.executeUpdate();
        }catch (SQLException e ){
            e.printStackTrace();
            System.out.println("ERRO NA SAIDA");
        }
    }
    public Estoque consultarEstoque (Estoque estoque){
        String sql =  "Select * from estoque  WHERE id_produto = ? AND  id_filial = ? ";
        try (Connection conn =  DB.getConnection();
        PreparedStatement consulta = conn.prepareStatement(sql);){
            consulta.setInt(1,estoque.getProduto());
            consulta.setInt(2, estoque.getFilial());
            ResultSet rs = consulta.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                int idProduto = rs.getInt("id_produto");
                int idFilial = rs.getInt("id_filial");
                int quantidade = rs.getInt("quantidade");
                Estoque estoque1 = new Estoque(id, idProduto, quantidade, idFilial);
                return estoque1;
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO  NO consultarEstoque ");
        }
        return null;
    }
}
