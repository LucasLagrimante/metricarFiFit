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

    public static void listaFiFit() {
        for (int i = 0; i < Diagrama.getClasses().size(); i++) {
            System.out.println(Diagrama.getClasses().get(i).getNome() + ": FI: " + Diagrama.getClasses().get(i).getFi() + " - FIT: " + Diagrama.getClasses().get(i).getFit()
            );
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void calculaFiFit1() {
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
                    case Muitos:
                        ligacoes.get(i).getClasseOrigem().somaFi();
                        break;
                    case GeneralizationOrigem:
                        ligacoes.get(i).getClasseDestino().somaFi();
                        break;
                    case AggregationOrigem:
                        ligacoes.get(i).getClasseOrigem().somaFi();
                        break;
                    case DependencyOrigem:
                        ligacoes.get(i).getClasseDestino().somaFi();
                        break;
                    default:
                        break;
                }
            }

            if (null != ligacoes.get(i).getCardinalidadeDestino()) {
                switch (ligacoes.get(i).getCardinalidadeDestino()) {
                    case Um:
                        ligacoes.get(i).getClasseDestino().somaFi();
                        break;
                    case UmMuitos:
                        ligacoes.get(i).getClasseDestino().somaFi();
                        break;
                    case Muitos:
                        ligacoes.get(i).getClasseDestino().somaFi();
                        break;
                }
            }
        }
        //FIT
        for (int i = 0; i < ligacoes.size(); i++) {
            if (null != ligacoes.get(i).getCardinalidadeDestino()) {
                switch (ligacoes.get(i).getCardinalidadeDestino()) {
                    case Um:
                        ligacoes.get(i).getClasseOrigem().somaFit(ligacoes.get(i).getClasseDestino().getFi());
                        break;
                    case UmMuitos:
                        ligacoes.get(i).getClasseOrigem().somaFit(ligacoes.get(i).getClasseDestino().getFi());
                        break;
                    case Muitos:
                        ligacoes.get(i).getClasseOrigem().somaFit(ligacoes.get(i).getClasseDestino().getFi());
                        break;
                    case GeneralizationDestino:
                        ligacoes.get(i).getClasseOrigem().somaFit(ligacoes.get(i).getClasseDestino().getFi());
                        break;
                    case AggregationDestino:
                        ligacoes.get(i).getClasseDestino().somaFit(ligacoes.get(i).getClasseOrigem().getFi());
                        break;
                    case DependencyDestino:
                        ligacoes.get(i).getClasseOrigem().somaFit(ligacoes.get(i).getClasseDestino().getFi());
                        break;
                    default:
                        break;
                }
            }

            if (null != ligacoes.get(i).getCardinalidadeOrigem()) {
                switch (ligacoes.get(i).getCardinalidadeOrigem()) {
                    case Um:
                        ligacoes.get(i).getClasseDestino().somaFit(ligacoes.get(i).getClasseOrigem().getFi());
                        break;
                    case UmMuitos:
                        ligacoes.get(i).getClasseDestino().somaFit(ligacoes.get(i).getClasseOrigem().getFi());
                        break;
                    case Muitos:
                        ligacoes.get(i).getClasseDestino().somaFit(ligacoes.get(i).getClasseOrigem().getFi());
                        break;
                    default:
                        break;
                }
            }
        }
    }

}
