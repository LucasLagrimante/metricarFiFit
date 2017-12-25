package model;

import java.util.List;

public class Classe {
    
    private String id;
    private String nome;
    private int fi;
    
    public Classe(String id, String nome) {
        this.fi = 0;
        this.id = id;
        this.nome = nome;
    }
    
    public int getFi() {
        return fi;
    }
    
    public void setFi(int fi) {
        this.fi = fi;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void somaFi(int valor) {
        this.setFi(this.fi + valor);
    }
}
