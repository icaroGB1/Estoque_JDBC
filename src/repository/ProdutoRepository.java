package repository;

import Conexao.DB;
import Entidades.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    public void create(Produto produto) {
        String sql = " insert into produtos (nome, descricao, unidade, categoria, preco) values (?, ?, ?, ?, ? )";
        try(Connection conn = DB.getConnection();
            PreparedStatement create  = conn.prepareStatement(sql);){
            create.setString(1, produto.getNome());
            create.setString(2, produto.getDescricao());
            create.setString(3, produto.getUnidade());
            create.setString(4, produto.getCategoria());
            create.setDouble(5, produto.getPreco());
            create.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO NO CREATE");
        }
    }
    public void delete (int id){
        String sql = "Delete from produtos where id = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement delete  = conn.prepareStatement(sql);){
            delete.setInt(1,id);
            delete.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO no DELETE");
        }
    }
    public void update(Produto produto){
        String sql = "update  produtos SET nome = ?, descricao = ?, unidade = ?, categoria = ?, preco = ?  where id = ?";
        try (Connection conn =  DB.getConnection();
        PreparedStatement update = conn.prepareStatement(sql)){
            update.setString(1, produto.getNome());
            update.setString(2, produto.getDescricao());
            update.setString(3, produto.getUnidade());
            update.setString(4, produto.getCategoria());
            update.setDouble(5, produto.getPreco());
            update.setInt(6,produto.getId());
            update.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO no UPDATE");
        }
    }
    public List<Produto> read(){
        List<Produto> produtos =  new ArrayList<>();
        String sql = "Select * from produtos";
        try(Connection conn = DB.getConnection();
            Statement read = conn.createStatement();
        ResultSet rs  = read.executeQuery(sql)){
            while (rs.next()){
                int id =  rs.getInt("id");
                String nome =  rs.getString("nome");
                String descricao = rs.getString("descricao");
                String unidade =  rs.getString("unidade");
                String categoria = rs.getString("categoria");
                double  preco =  rs.getDouble("preco");
                Produto produto =  new Produto(id, nome, descricao, unidade, categoria, preco);
                produtos.add(produto);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO NO READ");
        }
        return  produtos;
    }
    public  Produto findbyId (int id){
        String sql = "Select * from produtos where id = ?";
        Produto produto = new Produto();
        try (Connection conn  = DB.getConnection();
        PreparedStatement find = conn.prepareStatement(sql);){
            find.setInt(1, id);
            ResultSet rs = find.executeQuery();
            if(rs.next()){
                id =  rs.getInt("id");
                String nome =  rs.getString("nome");
                String descricao = rs.getString("descricao");
                String unidade =  rs.getString("unidade");
                String categoria = rs.getString("categoria");
                double  preco =  rs.getDouble("preco");
                 produto =  new Produto(id, nome, descricao, unidade, categoria, preco);
                return  produto;
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO no findbyId");
        }
        return produto;
    }
}
