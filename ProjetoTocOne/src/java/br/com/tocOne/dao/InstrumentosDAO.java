package br.com.tocOne.dao;

import br.com.tocOne.factory.ConnectionFactory;
import br.com.tocOne.model.Instrumentos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InstrumentosDAO {

    public void cadastrarInstrumento(Instrumentos e) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO toc_one (marca, cor, qtd, modelo, preco_unitario ) VALUES (?,?,?,?,?)";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, e.getMarca());
        stmt.setString(2, e.getCor());
        stmt.setString(3, e.getQtd());
        stmt.setString(4, e.getModelo());
        stmt.setString(5, e.getPrecoU());
        stmt.execute();
        stmt.close();
    }

    public void excluirInstrumento(Instrumentos e) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM toc_one WHERE id = ? ";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, e.getId());
        stmt.execute();
        stmt.close();
    }

    public void alterarInstrumento(Instrumentos e) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE toc_one SET marca = ?, cor = ? qtd = ? modelo = ? preco_unitario = ? WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, e.getMarca());
        stmt.setString(2, e.getCor());
        stmt.setString(3, e.getQtd());
        stmt.setString(4, e.getModelo());
        stmt.setString(5, e.getPrecoU());
        stmt.setInt(6, e.getId());
        stmt.execute();
        stmt.close();

    }

    public Instrumentos pesquisarInstrumento(Instrumentos e) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM tec_one WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, e.getId());

        ResultSet rs = stmt.executeQuery();

        Instrumentos retorno = null;

        if (rs.next()) {
            retorno = new Instrumentos();
            retorno.setId(rs.getInt("id"));

        }
        return retorno;
    }

    public ArrayList<Instrumentos> listar() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM instrumentos";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        ArrayList<Instrumentos> lista = new ArrayList<>();

        while (rs.next()) {
            Instrumentos e = new Instrumentos();
            e.setId(rs.getInt("id"));
            e.setMarca(rs.getString("marca"));
            e.setCor(rs.getString("cor"));
            e.setQtd(rs.getString("qtd"));
            e.setModelo(rs.getString("modelo"));
            e.setPrecoU(rs.getString("preco_unitario"));
            lista.add(e);
        }
        return lista;

    }
}
