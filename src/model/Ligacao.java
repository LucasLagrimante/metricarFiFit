package model;

import Enum.TipoLigacao;

public class Ligacao {

    private Classe classeOrigem;
    private Classe classeDestino;
    private TipoLigacao tipo;

    public Ligacao(Classe classeOrigem, Classe classeDestino, TipoLigacao tipo) {
        this.classeOrigem = classeOrigem;
        this.classeDestino = classeDestino;
        this.tipo = tipo;
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
