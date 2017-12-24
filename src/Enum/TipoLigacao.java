package Enum;

public enum TipoLigacao {
    Association("Association"),
    Generalization("Generalization");

    private final String tipo;

    private TipoLigacao(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoLigacao() {
        return tipo;
    }
}
