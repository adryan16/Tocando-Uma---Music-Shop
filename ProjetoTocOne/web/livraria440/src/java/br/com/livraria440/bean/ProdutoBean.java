package br.com.livraria440.bean;

import br.com.livraria440.dao.ProdutoDAO;
import br.com.livraria440.model.Produto;
import br.com.livraria440.util.JSFUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean (name="MBProduto")
//escopo: session (criado quando o tomcat e iniciado e finalizado quando o tomcat fecha)
//escopo: request (a cada clique e instanciado)
//escopo: view (so existe quando estiver manipuilando a tela dele)
@ViewScoped
public class ProdutoBean {
    private ArrayList<Produto> itens;
    private Produto produto;
    
    public ArrayList<Produto> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Produto> itens) {
        this.itens = itens;
    }
    
    @PostConstruct //metodo chamado antes da tag desenhada
    public void prepararPesquisa(){

            ProdutoDAO dao = new ProdutoDAO();
        try {
            itens = dao.listar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
       
    }
    
    public void prepararProduto(){
        produto = new Produto();
    }
    
    public void novoProduto(){
            ProdutoDAO dao = new ProdutoDAO();
        try {
            dao.salvar(produto);
            itens = dao.listar();
            JSFUtil.addMsgSucesso("Produto salvo com sucesso.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        }
    
}
     public void excluirProduto(){
            ProdutoDAO dao = new ProdutoDAO();
        try {
            dao.excluir(produto);
            itens = dao.listar();
            JSFUtil.addMsgSucesso("Produto excluido com sucesso.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        } 
     }
        
       public void editarProduto(){
            ProdutoDAO dao = new ProdutoDAO();
        try {
            dao.editar(produto);
            itens = dao.listar();
            JSFUtil.addMsgSucesso("Produto editado com sucesso.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        }  
    
     }  
       
    public Produto getProduto() {
        return produto;
    }
   
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
  }
