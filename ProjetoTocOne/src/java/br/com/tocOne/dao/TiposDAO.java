package br.com.tocOne.dao;

import br.com.tocOne.factory.ConnectionFactory;
import br.com.tocOne.model.Tipos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TiposDAO {
    public void cadastarTipos(Tipos p) throws ClassNotFoundException, SQLException{
         String sql = "INSERT INTO toc_one (cordas, sopro, percussao, teclas) VALUES (?,?,?,?)";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, p.getCorda());
        stmt.setString(2, p.getSopro());
        stmt.setString(3, p.getPercussao());
        stmt.setString(4, p.getTeclas());
        stmt.execute();
        stmt.close();
    }

    public void excluirTipos(Tipos p) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM toc_one WHERE id_tipo = ? ";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, p.getId_tipo());
        stmt.execute();
        stmt.close();
    }

    public void alterarTipos(Tipos p) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE tipo_instrumento SET cordas = ?, sopro = ?, percussao = ?, teclas = ? WHERE id_tipo = ?";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, p.getCorda());
        stmt.setString(2, p.getSopro());
        stmt.setString(3, p.getPercussao());
        stmt.setString(4, p.getTeclas());
        stmt.setInt(5, p.getId_tipo());
        stmt.execute();
        stmt.close();

    }

    public Tipos pesquisarTipos(Tipos p) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM  WHERE id_tipo = ?";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, p.getId_tipo());

        ResultSet rs = stmt.executeQuery();

        Tipos retorno = null;

        if (rs.next()) {
            retorno = new Tipos();
            retorno.setId_tipo(rs.getInt("id_tipo"));
            

        }
        return retorno;
    }

    public ArrayList<Tipos> listar() throws SQLException, ClassNotFoundException {
        
            String sql = "select tipo_instrumento.id, tipo_instrumento.cordas,tipo_instrumento.sopro, tipo_instrumento.percussao, tipo_instrumento.teclas,"
                    + "instrumentos.id_instrumento from produto inner join instrumentos on instrumentos.id_instrumentos = tipo_instrumento.id_tipo";

            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Tipos> lista = new ArrayList<>();
            
            while(rs.next()){
                Tipos e = new Tipos();
                e.setId_tipo(rs.getInt("id_tipo"));
                e.setCorda(rs.getString("cordas"));
                e.setSopro(rs.getString("sopro"));
                e.setPercussao(rs.getString("percussao"));
                e.setTeclas(rs.getString("teclas"));
                lista.add(e);
            }
            return lista;
                
                
}}
