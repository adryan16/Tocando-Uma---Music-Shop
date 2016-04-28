package br.com.tocOne.bean;

import br.com.tocOne.dao.InstrumentosDAO;
import br.com.tocOne.model.Instrumentos;
import br.com.tocOne.util.JSFUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "MBInstrumentos")
//Escopo: Session (Criado quando o Tomcat é iniciado e finalizado quando o Tomcat) //
//Escopo: Request (A cada clique é instanciado) //
//Escopo: View (Só existe enquanto estiver manipulando a tela dele) //
@ViewScoped
public class InstrumentosBean {

    private ArrayList<Instrumentos> itens;
    private Instrumentos instrumentos;

    public ArrayList<Instrumentos> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Instrumentos> itens) {
        this.itens = itens;
    }

    public Instrumentos getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(Instrumentos instrumentos) {
        this.instrumentos = instrumentos;
    }

   

   

    @PostConstruct // Método chamado antes da tag desenhada //
    public void prepararPesquisa() {

        InstrumentosDAO dao = new InstrumentosDAO();
        try {
            itens = dao.listar();
        } catch (SQLException ex) {
            Logger.getLogger(InstrumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InstrumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void prepararInstrumento() {
        instrumentos = new Instrumentos();
    }

    public void novoInstrumento() {
        try {
            InstrumentosDAO dao = new InstrumentosDAO();
            try {
                dao.cadastrarInstrumento(instrumentos);
                itens = dao.listar();
                JSFUtil.addMsgSucesso("Editora salva com sucesso.");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InstrumentosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.addMsgErro(e.getMessage());

        }
    }

    public void excluirInstrumento() {
        InstrumentosDAO dao = new InstrumentosDAO();

        try {
            dao.excluirInstrumento(instrumentos);
            itens = dao.listar();
            JSFUtil.addMsgSucesso("Editora excluída com sucesso.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InstrumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InstrumentosBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addMsgErro(ex.getMessage());
            
        }

    }

    public void alterarInstrumento() {
        InstrumentosDAO dao = new InstrumentosDAO();

        try {
            dao.alterarInstrumento(instrumentos);
            itens = dao.listar();
            JSFUtil.addMsgSucesso("Editora alterada com sucesso.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InstrumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InstrumentosBean.class.getName()).log(Level.SEVERE, null, ex);
            JSFUtil.addMsgErro(ex.getMessage());
        }
    }

}
