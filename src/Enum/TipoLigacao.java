package Enum;

public enum TipoLigacao {
    Association("uml:Association"),
    Generalization("generalization"),
    Dependency("uml:Dependency");

    private final String tipo;

    private TipoLigacao(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoLigacao() {
        return tipo;
    }
}
