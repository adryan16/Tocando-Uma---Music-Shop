package br.com.tocONe.bean;

import br.com.tocOne.dao.InstrumentosDAO;
import br.com.tocOne.dao.TiposDAO;
import br.com.tocOne.model.Instrumentos;
import br.com.tocOne.model.Tipos;
import br.com.tocOne.util.JSFUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "MBTipos")
//Escopo: Session (Criado quando o Tomcat é iniciado e finalizado quando o Tomcat) //
//Escopo: Request (A cada clique é instanciado) //
//Escopo: View (Só existe enquanto estiver manipulando a tela dele) //
@ViewScoped
public class TiposBean {

    private ArrayList<Tipos> itens;
    private Tipos tipo;

    public ArrayList<Tipos> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Tipos> itens) {
        this.itens = itens;
    }

    public Tipos getTipo() {
        return tipo;
    }

    public void setTipo(Tipos tipo) {
        this.tipo = tipo;
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

    public void prepararTipos() {
        tipo = new Tipos();
    }

    public void novoTipos() {
        try {
            TiposDAO dao = new TiposDAO();
            try {
                dao.cadastarTipos(tipo);
                itens = dao.listar();
                JSFUtil.addMsgSucesso("Produto salvo com sucesso.");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InstrumentosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.addMsgErro(e.getMessage());

        }
    }

    public void excluirTipos() {
        TiposDAO dao = new TiposDAO();

        try {
           dao.excluirTipos(tipo);
            itens = dao.listar();
            JSFUtil.addMsgSucesso("Produto excluído com sucesso.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InstrumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InstrumentosBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addMsgErro(ex.getMessage());
            
        }

    }

    public void alterarTipos() {
        TiposDAO dao = new TiposDAO();

        try {
            dao.alterarTipos(tipo);
            itens = dao.listar();
            JSFUtil.addMsgSucesso("Produto alterado com sucesso.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InstrumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InstrumentosBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addMsgErro(ex.getMessage());
        }
    }

}