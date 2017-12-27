package Storage;

import java.util.ArrayList;
import java.util.List;
import model.Classe;
import model.Ligacao;

public class Diagrama {

    private static String nome;
    private static List<Classe> classes = new ArrayList<>();
    private static List<Ligacao> ligacoes = new ArrayList<>();

    public static List<Classe> getClasses() {
        return classes;
    }

    public static Classe getClasseById(String id) {
        for (Classe obj : classes) {
            if (obj.getId().equals(id)) {
                return obj;
            }
        }
        return null;
    }

    public static void addClasse(Classe classe) {
        Diagrama.classes.add(classe);
    }

    public static void addLigacao(Ligacao ligacao) {
        Diagrama.ligacoes.add(ligacao);
    }

    public static void listaLigacoes() {
        for (int i = 0; i < ligacoes.size(); i++) {
            System.out.println("ID: " + ligacoes.get(i).getId() + " | Classe Origem: " + ligacoes.get(i).getClasseOrigem().getNome() + "| Classe Destino: " + ligacoes.get(i).getClasseDestino().getNome() + " | Cardinalidade Origem: " + ligacoes.get(i).getCardinalidadeOrigem() + " | Cardinalidade Destino: " + ligacoes.get(i).getCardinalidadeDestino() + " | Tipo: " + ligacoes.get(i).getTipo());
        }
    }

    public static List<Ligacao> getLigacoes() {
        return ligacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void calculaFiFit() {
        //FI
        for (int i = 0; i < ligacoes.size(); i++) {
            if (null != ligacoes.get(i).getCardinalidadeOrigem()) {
                switch (ligacoes.get(i).getCardinalidadeOrigem()) {
                    case Um:
                        ligacoes.get(i).getClasseOrigem().somaFi();
                        break;
                    case UmMuitos:
                        ligacoes.get(i).getClasseOrigem().somaFi();
                        break;
                    case GeneralizationDestino:
                        ligacoes.get(i).getClasseOrigem().somaFi();
                        break;
                    default:
                        break;
                }
            }
        }
        //FIT
        for (int i = 0; i < ligacoes.size(); i++) {
            if (null != ligacoes.get(i).getCardinalidadeDestino()) {
                switch (ligacoes.get(i).getCardinalidadeDestino()) {
                    case Um:
                        ligacoes.get(i).getClasseDestino().somaFit(ligacoes.get(i).getClasseDestino().getFi());
                        break;
                    case UmMuitos:
                        ligacoes.get(i).getClasseDestino().somaFit(ligacoes.get(i).getClasseDestino().getFi());
                        break;
                    case GeneralizationOrigem:
                        ligacoes.get(i).getClasseDestino().somaFit(ligacoes.get(i).getClasseDestino().getFi());
                        break;
                    default:
                        break;
                }
            }
        }
    }

}
