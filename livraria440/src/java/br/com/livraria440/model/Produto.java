package br.com.livraria440.model;
public class Produto extends Editora {
    private int id_produto, id_editora;
    private String descricao;
    private int qtd;
    private double preco;

    
    public int getId_produto() {
        return id_produto;
    }

    
    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    
    public String getDescricao() {
        return descricao;
    }

   
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   
    public int getQtd() {
        return qtd;
    }

    
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    
    public double getPreco() {
        return preco;
    }

    
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
    
    
}
