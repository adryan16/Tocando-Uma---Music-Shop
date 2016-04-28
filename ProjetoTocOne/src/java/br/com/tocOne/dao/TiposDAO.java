package br.com.tocOne.dao;

import br.com.tocOne.factory.ConnectionFactory;
import br.com.tocOne.model.Instrumentos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TiposDAO {
    public void cadastarProduto(Instrumentos p) throws ClassNotFoundException, SQLException{
         String sql = "INSERT INTO toc_one (qtd, descricao, preco, id_editora) VALUES (?,?,?, ?)";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, p.getQtd());
        stmt.setString(2, p.getDescricao());
        stmt.setDouble(3, p.getPreco());
        stmt.setInt(4, p.getId_editora());
        stmt.execute();
        stmt.close();
    }

    public void excluirProduto(Instrumentos p) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM produto WHERE id = ? ";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, p.getId());
        stmt.execute();
        stmt.close();
    }

    public void alterarProduto(Instrumentos p) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE produto SET qtd = ?, descricao = ?, preco = ?, id_editora = ? WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, p.getQtd());
        stmt.setString(2, p.getDescricao());
        stmt.setDouble(3, p.getPreco());
        stmt.setInt(4, p.getId_editora());
        stmt.setInt(5, p.getId());
        stmt.execute();
        stmt.close();

    }

    public Instrumentos pesquisarProduto(Instrumentos p) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM produto WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, p.getId());

        ResultSet rs = stmt.executeQuery();

        Instrumentos retorno = null;

        if (rs.next()) {
            retorno = new Instrumentos();
            retorno.setId(rs.getInt("id"));
            retorno.setDescricao(rs.getString("descricao"));

        }
        return retorno;
    }

    public ArrayList<Instrumentos> listar() throws SQLException, ClassNotFoundException {
        
            String sql = "select produto.id, produto.descricao,produto.qtd, produto.preco,"
                    + "editora.razaosocial from produto inner join editora on editora.id = produto.id_editora";

            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Instrumentos> lista = new ArrayList<>();
            
            while(rs.next()){
                Instrumentos e = new Instrumentos();
                e.setId(rs.getInt("id"));
                e.setQtd(rs.getInt("qtd"));
                e.setDescricao(rs.getString("descricao"));
                e.setRazaoSocial(rs.getString("razaoSocial"));
                e.setPreco(rs.getDouble("preco"));
                lista.add(e);
            }
            return lista;
                
                
}}
