package model;

import Enum.TipoLigacao;

public class Ligacao {

    private Classe classeOrigem;
    private Classe classeDestino;
    private TipoLigacao tipoOrigem;
    private TipoLigacao tipoDestino;

    public Ligacao(Classe classeOrigem, Classe classeDestino, TipoLigacao tipoOrigem, TipoLigacao tipoDestino) {
        this.classeOrigem = classeOrigem;
        this.classeDestino = classeDestino;
        this.tipoOrigem = tipoOrigem;
        this.tipoDestino = tipoDestino;
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

    public TipoLigacao getTipoOrigem() {
        return tipoOrigem;
    }

    public void setTipoOrigem(TipoLigacao tipoOrigem) {
        this.tipoOrigem = tipoOrigem;
    }

    public TipoLigacao getTipoDestino() {
        return tipoDestino;
    }

    public void setTipoDestino(TipoLigacao tipoDestino) {
        this.tipoDestino = tipoDestino;
    }

}
