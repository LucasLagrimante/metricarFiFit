package Enum;

public enum TipoCardinalidade {
    ZEROUM("0..1"),
    UM("1"),
    ZEROMUITOS("0..*"),
    UMMUITOS("1..*"),
    MUITOS("1..*"),
    HERANCAORIGEM("->"),
    HERANCADESTINO("<-"),
    AGREGACAOORIGEM("-<"),
    AGREGACAODESTINO(">-");

    private final String tipo;

    private TipoCardinalidade(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoLigacao() {
        return tipo;
    }
}
