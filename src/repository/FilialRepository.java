package repository;

import Conexao.DB;
import Entidades.Filial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilialRepository {

    public  void create(Filial filial){
        String sql =  "insert into  filial(nome, endereco, telefone, email) values (?, ?, ?, ?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement create = conn.prepareStatement(sql)){
            create.setString(1, filial.getNome());
            create.setString(2, filial.getEndereco());
            create.setString(3, filial.getTelefone());
            create.setString(4, filial.getEmail());
            create.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO no CREATE");
        }
    }
    public  void delete(int id){
        String sql =  "Delete from filial where id = ?";
        try (Connection conn = DB.getConnection();
        PreparedStatement del =  conn.prepareStatement(sql)){
            del.setInt(1, id);
            del.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO no DELETE");
        }
    }
    public void update(Filial filial){
        String sql =  "update filial SET nome = ?, endereco = ?, telefone = ?, email = ? where id = ?";
        try(Connection conn = DB.getConnection();
        PreparedStatement update = conn.prepareStatement(sql)){
            update.setString(1, filial.getNome());
            update.setString(2, filial.getEndereco());
            update.setString(3, filial.getTelefone());
            update.setString(4, filial.getEmail());
            update.setInt(5, filial.getId());
            update.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO NO UPDATE");
        }
    }
    public List<Filial> read(){
        String sql =  "Select * from filial";
        List<Filial>filials = new ArrayList<>();
        try (Connection conn =  DB.getConnection();
             Statement read = conn.createStatement();
             ResultSet rs =  read.executeQuery(sql)){
            while (rs.next()){
                int id =  rs.getInt("id");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone  = rs.getString("telefone");
                String email =  rs.getString("email");
                Filial filial =  new Filial(id, nome, endereco, telefone, email);
                filials.add(filial);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO NO READ");
        }
        return filials;
    }
    public Filial findbyId(int id){
        String sql =  "Select * from filial where id =?";
        try (Connection conn = DB.getConnection();
        PreparedStatement find = conn.prepareStatement(sql)){
            find.setInt(1,id);
            ResultSet rs = find.executeQuery();
            if(rs.next()){
                id =  rs.getInt("id");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone  = rs.getString("telefone");
                String email =  rs.getString("email");
                Filial filial =  new Filial(id, nome, endereco, telefone, email);
                return filial;
            }

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO no findbyId");
        }
        return null;
    }
    public  Filial findbyNome(String nome){
        String sql =  "Select * from filial where nome =?";
        try (Connection conn = DB.getConnection();
             PreparedStatement find = conn.prepareStatement(sql)){
            find.setString(1,nome);
            ResultSet rs = find.executeQuery();
            if(rs.next()){
                int id =  rs.getInt("id");
                nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone  = rs.getString("telefone");
                String email =  rs.getString("email");
                Filial filial =  new Filial(id, nome, endereco, telefone, email);
                return filial;
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERRO no findbyId");
        }
        return null;
    }
}

