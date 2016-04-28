package br.com.livraria440.bean;

import br.com.livraria440.dao.EditoraDAO;
import br.com.livraria440.model.Editora;
import br.com.livraria440.util.JSFUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean (name="MBEditora")
//escopo: session (criado quando o tomcat e iniciado e finalizado quando o tomcat fecha)
//escopo: request (a cada clique e instanciado)
//escopo: view (so existe quando estiver manipuilando a tela dele)
@ViewScoped
public class EditoraBean {
    private ArrayList<Editora> itens;
    private Editora editora;
    
    public ArrayList<Editora> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Editora> itens) {
        this.itens = itens;
    }
    
    @PostConstruct //metodo chamado antes da tag desenhada
    public void prepararPesquisa(){

            EditoraDAO dao = new EditoraDAO();
        try {
            itens = dao.listar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
       
    }
    
    public void prepararEditora(){
        editora = new Editora();
    }
    
    public void novaEditora(){
            EditoraDAO dao = new EditoraDAO();
        try {
            dao.salvar(editora);
            itens = dao.listar();
            JSFUtil.addMsgSucesso("Editora salva com sucesso.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        }
    
}
     public void excluirEditora(){
            EditoraDAO dao = new EditoraDAO();
        try {
            dao.excluir(editora);
            itens = dao.listar();
            JSFUtil.addMsgSucesso("Editora excluida com sucesso.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        } 
     }
        
       public void editarEditora(){
            EditoraDAO dao = new EditoraDAO();
        try {
            dao.editar(editora);
            itens = dao.listar();
            JSFUtil.addMsgSucesso("Editora editada com sucesso.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        }  
    
     }  
       
    public Editora getEditora() {
        return editora;
    }
   
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
  }
