package Enum;

public enum TipoCardinalidade {
    ZeroUm("0..1"),
    Um("1..1"),
    ZeroMuitos("0..*"),
    UmMuitos("1..*"),
    Muitos("*..*"),
    GeneralizationOrigem("->"),
    GeneralizationDestino("<-"),
    AgregacaoOrigem("-<"),
    AgregacaoDestino(">-");

    private final String tipo;

    private TipoCardinalidade(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoCardinalidade() {
        return tipo;
    }
}
