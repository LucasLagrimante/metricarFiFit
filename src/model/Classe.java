package model;

public class Classe {

    private String id;
    private String nome;
    private int fi;
    private int fit;

    public Classe(String id, String nome) {
        this.fi = 0;
        this.fit = 0;
        this.id = id;
        this.nome = nome;
    }

    public int getFi() {
        return fi;
    }

    public void setFi(int fi) {
        this.fi = fi;
    }

    public void somaFi() {
        this.setFi(this.fi + 1);
    }

    public int getFit() {
        return fit;
    }

    public void setFit(int fit) {
        this.fit = fit;
    }

    public void somaFit(int valor) {
        this.setFit(this.fit + valor);
    }

    public void subtraiFit(int valor) {
        this.setFit(this.fit - valor);
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
