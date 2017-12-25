package model;

import java.util.List;

public class Classe {

    private String id;
    private String nome;
    private List<Classe> dependencias;
    private List<Classe> pendencias;

    public Classe(String id, String nome) {
        this.id = id;
        this.nome = nome;
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

}
