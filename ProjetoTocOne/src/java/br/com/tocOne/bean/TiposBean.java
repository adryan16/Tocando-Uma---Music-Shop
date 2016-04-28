package br.com.tocONe.bean;

import br.com.tocOne.dao.InstrumentosDAO;
import br.com.tocOne.dao.TiposDAO;
import br.com.livraria.model.Editora;
import br.com.livraria.model.Produto;
import br.com.livraria.util.JSFUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "MBProduto")
//Escopo: Session (Criado quando o Tomcat é iniciado e finalizado quando o Tomcat) //
//Escopo: Request (A cada clique é instanciado) //
//Escopo: View (Só existe enquanto estiver manipulando a tela dele) //
@ViewScoped
public class TiposBean {

    private ArrayList<Produto> itens;
    private Produto produto;

    public ArrayList<Produto> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Produto> itens) {
        this.itens = itens;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    

    

    @PostConstruct // Método chamado antes da tag desenhada //
    public void prepararPesquisa() {

        TiposDAO dao = new TiposDAO();
        try {
            itens = dao.listar();
        } catch (SQLException ex) {
            Logger.getLogger(TiposBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TiposBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void prepararProduto() {
        produto = new Produto();
    }

    public void novoProduto() {
        try {
            TiposDAO dao = new TiposDAO();
            try {
                dao.cadastarProduto(produto);
                itens = dao.listar();
                JSFUtil.addMsgSucesso("Produto salvo com sucesso.");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EditoraBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.addMsgErro(e.getMessage());

        }
    }

    public void excluirProduto() {
        TiposDAO dao = new TiposDAO();

        try {
           dao.excluirProduto(produto);
            itens = dao.listar();
            JSFUtil.addMsgSucesso("Produto excluído com sucesso.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TiposBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TiposBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addMsgErro(ex.getMessage());
            
        }

    }

    public void alterarProduto() {
        TiposDAO dao = new TiposDAO();

        try {
            dao.alterarProduto(produto);
            itens = dao.listar();
            JSFUtil.addMsgSucesso("Produto alterado com sucesso.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditoraBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditoraBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addMsgErro(ex.getMessage());
        }
    }

}
