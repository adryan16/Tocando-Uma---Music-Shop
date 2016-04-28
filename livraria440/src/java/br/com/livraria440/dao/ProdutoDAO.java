package br.com.livraria440.dao;

import br.com.livraria440.factory.ConnectionFactory;
import br.com.livraria440.model.Editora;
import br.com.livraria440.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {
    public void salvar(Produto e)
            throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO produto (descricao,preco,qtd,id_editora) VALUES (?,?,?,?)";
        
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, e.getDescricao());
            stmt.setDouble(2, e.getPreco());
            stmt.setInt(3, e.getQtd());
            stmt.setInt(4, e.getId_editora());
            stmt.execute();
        }
    }
    
    public void excluir(Produto e)
            throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM produto WHERE id_produto = ?";
         Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, e.getId_produto());
            stmt.execute();
            stmt.close();
        }
      
   }
    
    public void editar(Produto e)
            throws ClassNotFoundException, SQLException{
        String sql = "UPDATE produto SET descricao = ?,"
                + "preco = ?,qtd = ?,id_editora = ?  WHERE id_produto = ?";
        
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, e.getDescricao());
            stmt.setDouble(2, e.getPreco());
            stmt.setLong(3, e.getQtd());
            stmt.setInt(4, e.getId_editora());
            stmt.setLong(5, e.getId_produto());
            
            stmt.execute();
        }
    }
     
   public Produto pesquisar(Produto e)
           throws ClassNotFoundException, SQLException{
       String sql = "SELECT * FROM produto WHERE id_produto = ?";
       
       Connection connection = ConnectionFactory.getConnection();
       
       PreparedStatement stmt = connection.prepareStatement(sql);
       stmt.setInt(1, e.getId_produto());
       
       ResultSet rs = stmt.executeQuery();
       
       Produto retorno = null;
       
       if(rs.next()){
           retorno = new Produto();
           retorno.setId_produto(rs.getInt("produto"));
           retorno.setDescricao(rs.getString("Descrição"));
           retorno.setPreco(rs.getInt("Preço"));
           retorno.setQtd(rs.getInt("Quantidade"));
       }
       return retorno;
   }
   
   public static void main(String[] args)
         throws ClassNotFoundException, SQLException{
        Produto p1 = new Produto();
        p1.setId_produto(1);
        Produto p2 = new Produto();
        p2.setId_produto(2);
    
        ProdutoDAO fdao = new ProdutoDAO();
    
        try{
            Produto p3 = fdao.pesquisar(p1);
            Produto p4 = fdao.pesquisar(p2);

            System.out.println("Resultado 1: "+p3);
            System.out.println("Resultado 2: "+p4);
        }catch(SQLException e) {
            System.out.println("Erro ao pesquisar o produto");
        }
   }
   
   public ArrayList<Produto> listar()
           throws SQLException, ClassNotFoundException{
       String sql = "SELECT produto.id_produto, produto.descricao, produto.preco, produto.qtd, editora.razao_social from produto "
               + "inner join editora on editora.id_editora = produto.id_editora";
               
       
       Connection connection = ConnectionFactory.getConnection();
       PreparedStatement stmt = connection.prepareStatement(sql);
       ResultSet rs = stmt.executeQuery();
       ArrayList<Produto> lista = new ArrayList<Produto>();
       
       while(rs.next()){
           Produto p = new Produto();
          
           
           p.setId_produto(rs.getInt("id_produto"));
           p.setDescricao(rs.getString("descricao"));
           p.setPreco(rs.getDouble("preco"));
           p.setQtd(rs.getInt("qtd"));
           p.setRazao_social(rs.getString("razao_social"));
           
           lista.add(p);
       }
        return lista;
   }
}
