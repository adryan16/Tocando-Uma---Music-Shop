package br.com.livraria440.dao;

import br.com.livraria440.factory.ConnectionFactory;
import br.com.livraria440.model.Editora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditoraDAO {
    public void salvar(Editora e)
            throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO editora (razao_social) VALUES (?)";
        
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, e.getRazao_social());
            stmt.execute();
        }
    }
    
    public void excluir(Editora e)
            throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM editora WHERE id_editora = ?";
         Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, e.getId_editora());
            stmt.execute();
            stmt.close();
        }
      
   }
    
    public void editar(Editora e)
            throws ClassNotFoundException, SQLException{
        String sql = "UPDATE editora SET razao_social = ? WHERE id_editora = ?";
        
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, e.getRazao_social());
            stmt.setLong(2, e.getId_editora());
            stmt.execute();
        }
    }
     
   public Editora pesquisar(Editora e)
           throws ClassNotFoundException, SQLException{
       String sql = "SELECT * FROM editora WHERE id_editora = ?";
       
       Connection connection = ConnectionFactory.getConnection();
       
       PreparedStatement stmt = connection.prepareStatement(sql);
       stmt.setInt(1, e.getId_editora());
       
       ResultSet rs = stmt.executeQuery();
       
       Editora retorno = null;
       
       if(rs.next()){
           retorno = new Editora();
           retorno.setId_editora(rs.getInt("editora"));
           retorno.setRazao_social(rs.getString("Razao social"));
       }
       return retorno;
   }
   
   public static void main(String[] args)
         throws ClassNotFoundException, SQLException{
        Editora e1 = new Editora();
        e1.setId_editora(1);
        Editora e2 = new Editora();
        e2.setId_editora(2);
    
        EditoraDAO fdao = new EditoraDAO();
    
        try{
            Editora e3 = fdao.pesquisar(e1);
            Editora e4 = fdao.pesquisar(e2);

            System.out.println("Resultado 1: "+e3);
            System.out.println("Resultado 2: "+e4);
        }catch(SQLException e) {
            System.out.println("Erro ao pesquisar a editora");
        }
   }
   
   public ArrayList<Editora> listar()
           throws SQLException, ClassNotFoundException{
       String sql = "SELECT * FROM editora";
       
       Connection connection = ConnectionFactory.getConnection();
       PreparedStatement stmt = connection.prepareStatement(sql);
       ResultSet rs = stmt.executeQuery();
       ArrayList<Editora> lista = new ArrayList<Editora>();
       
       while(rs.next()){
           Editora e = new Editora();
           e.setId_editora(rs.getInt("id_editora"));
           e.setRazao_social(rs.getString("razao_social"));
           lista.add(e);
       }
        return lista;
   }
}

