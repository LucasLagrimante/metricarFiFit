package model;

import Enum.TipoLigacao;
import Enum.TipoCardinalidade;

public class Ligacao {

    private String id;
    private Classe classeOrigem;
    private Classe classeDestino;
    private TipoCardinalidade cardinalidadeOrigem;
    private TipoCardinalidade cardinalidadeDestino;
    private TipoLigacao tipo;

    public Ligacao(String id) {
        this.id = id;
    }

    public Ligacao(String id, Classe classeOrigem, TipoCardinalidade cardinalidadeOrigem, TipoLigacao tipo) {
        this.id = id;
        this.classeOrigem = classeOrigem;
        this.cardinalidadeOrigem = cardinalidadeOrigem;
        this.tipo = tipo;
    }

    public Ligacao(String id, TipoLigacao tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Ligacao(String id, Classe classeOrigem, Classe classeDestino, TipoLigacao tipo) {
        this.id = id;
        this.classeOrigem = classeOrigem;
        this.classeDestino = classeDestino;
        this.tipo = tipo;
    }

    public Ligacao(String id, Classe classeOrigem, Classe classeDestino, TipoCardinalidade cardinalidadeOrigem, TipoCardinalidade cardinalidadeDestino, TipoLigacao tipo) {
        this.id = id;
        this.classeOrigem = classeOrigem;
        this.classeDestino = classeDestino;
        this.cardinalidadeOrigem = cardinalidadeOrigem;
        this.cardinalidadeDestino = cardinalidadeDestino;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoCardinalidade getCardinalidadeOrigem() {
        return cardinalidadeOrigem;
    }

    public void setCardinalidadeOrigem(TipoCardinalidade cardinalidadeOrigem) {
        this.cardinalidadeOrigem = cardinalidadeOrigem;
    }

    public TipoCardinalidade getCardinalidadeDestino() {
        return cardinalidadeDestino;
    }

    public void setCardinalidadeDestino(TipoCardinalidade cardinalidadeDestino) {
        this.cardinalidadeDestino = cardinalidadeDestino;
    }

    public Classe getClasseOrigem() {
        return classeOrigem;
    }

    public void setClasseOrigem(Classe classeOrigem) {
        this.classeOrigem = classeOrigem;
    }

    public Classe getClasseDestino() {
        return classeDestino;
    }

    public void setClasseDestino(Classe classeDestino) {
        this.classeDestino = classeDestino;
    }

    public TipoLigacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoLigacao tipo) {
        this.tipo = tipo;
    }

}
