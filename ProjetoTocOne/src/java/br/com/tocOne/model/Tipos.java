package br.com.tocOne.model;

public class Tipos {
  private int id_tipo;  
  private String corda, sopro, percussao, teclas ;    

    public String getCorda() {
        return corda;
    }

    public void setCorda(String corda) {
        this.corda = corda;
    }

    public String getSopro() {
        return sopro;
    }

    public void setSopro(String sopro) {
        this.sopro = sopro;
    }

    public String getPercussao() {
        return percussao;
    }

    public void setPercussao(String percussao) {
        this.percussao = percussao;
    }

    public String getTeclas() {
        return teclas;
    }

    public void setTeclas(String teclas) {
        this.teclas = teclas;
    }

   
    public int getId_tipo() {
        return id_tipo;
    }

   
    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }
}
