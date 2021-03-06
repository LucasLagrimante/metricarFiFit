package Storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Classe;
import model.Ligacao;

public class Diagrama {

    private static String nome;
    private static List<Classe> classes = new ArrayList<>();
    private static List<Ligacao> ligacoes = new ArrayList<>();
    private static List<Classe> ordemIntegracao = new ArrayList<>();
    private static List<Classe> stubs = new ArrayList<>();

    public static List<Classe> getClasses() {
        return classes;
    }

    public static List<Classe> getOrdemIntegracao() {
        return ordemIntegracao;
    }

    public static void addOrdemIntegracao(Classe integra) {
        ordemIntegracao.add(integra);
    }

    public static List<Classe> getStubs() {
        return stubs;
    }

    private static void addStub(Classe stub) {
        stubs.add(stub);
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

    public static List<Ligacao> getLigacoes() {
        return ligacoes;
    }

    public static void listaFiFit() {
        for (int i = 0; i < Diagrama.getClasses().size(); i++) {
            System.out.println(Diagrama.getClasses().get(i).getNome() + ": FI: " + Diagrama.getClasses().get(i).getFi() + " - FIT: " + Diagrama.getClasses().get(i).getFit()
            );
        }
    }

    static void ordenaAlfabeticamente() {
        Collections.sort(classes);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void calculaFiFit1() {
        //FI
        Classe origem;
        Classe destino;
        for (int i = 0; i < ligacoes.size(); i++) {
            origem = ligacoes.get(i).getClasseOrigem();
            destino = ligacoes.get(i).getClasseDestino();
            if (null != ligacoes.get(i).getCardinalidadeOrigem()) {
                switch (ligacoes.get(i).getCardinalidadeOrigem()) {
                    case Um:
                        origem.somaFi();
                        origem.addLiberaQuem(destino);
                        break;
                    case UmMuitos:
                        origem.somaFi();
                        origem.addLiberaQuem(destino);
                        break;
                    case Muitos:
                        origem.somaFi();
                        origem.addLiberaQuem(destino);
                        break;
                    case GeneralizationOrigem:
                        destino.somaFi();
                        destino.addLiberaQuem(origem);
                        break;
                    case AggregationOrigem:
                        origem.somaFi();
                        origem.addLiberaQuem(destino);
                        break;
                    case DependencyOrigem:
                        destino.somaFi();
                        destino.addLiberaQuem(origem);
                        break;
                    default:
                        break;
                }
            }

            if (null != ligacoes.get(i).getCardinalidadeDestino()) {
                switch (ligacoes.get(i).getCardinalidadeDestino()) {
                    case Um:
                        destino.somaFi();
                        destino.addLiberaQuem(origem);
                        break;
                    case UmMuitos:
                        destino.somaFi();
                        destino.addLiberaQuem(origem);
                        break;
                    case Muitos:
                        destino.somaFi();
                        destino.addLiberaQuem(origem);
                        break;
                }
            }
        }
        //FIT
        for (int i = 0; i < ligacoes.size(); i++) {
            origem = ligacoes.get(i).getClasseOrigem();
            destino = ligacoes.get(i).getClasseDestino();
            if (null != ligacoes.get(i).getCardinalidadeDestino()) {
                switch (ligacoes.get(i).getCardinalidadeDestino()) {
                    case Um:
                        origem.somaFit(destino.getFi());
                        break;
                    case UmMuitos:
                        origem.somaFit(destino.getFi());
                        break;
                    case Muitos:
                        origem.somaFit(destino.getFi());
                        break;
                    case GeneralizationDestino:
                        origem.somaFit(destino.getFi());
                        break;
                    case AggregationDestino:
                        destino.somaFit(origem.getFi());
                        break;
                    case DependencyDestino:
                        origem.somaFit(destino.getFi());
                        break;
                    default:
                        break;
                }
            }

            if (null != ligacoes.get(i).getCardinalidadeOrigem()) {
                switch (ligacoes.get(i).getCardinalidadeOrigem()) {
                    case Um:
                        destino.somaFit(origem.getFi());
                        break;
                    case UmMuitos:
                        destino.somaFit(origem.getFi());
                        break;
                    case Muitos:
                        destino.somaFit(origem.getFi());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static void integraClasses() {
        if (existeFitZero()) {
            for (int i = 0; i < classes.size(); i++) {
                if (classes.get(i).getFit() == 0 && classes.get(i).getFit() != 999) {
                    classes.get(i).setFit(999);
                    addOrdemIntegracao(classes.get(i));
                    for (int j = 0; j < classes.get(i).getLiberaQuem().size(); j++) {
                        classes.get(i).getLiberaQuem().get(j).setFit(classes.get(i).getLiberaQuem().get(j).getFit() - classes.get(i).getFi());
                    }
                    break;
                }
            }
        } else {
            criaStub();
        }
    }

    public static boolean existeFitZero() {
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getFit() == 0) {
                return true;
            }
        }
        return false;
    }

    public static Classe getMenorFit() {
        Classe menor = classes.get(0);
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getFit() < menor.getFit()) {
                menor = classes.get(i);
            }
        }
        return menor;
    }

    public static void criaStub() {
        Classe libera;
        Classe stub = getMenorFit();
        stub.setFit(1000);
        addOrdemIntegracao(stub);
        addStub(stub);
        for (int j = 0; j < stub.getLiberaQuem().size(); j++) {
            libera = stub.getLiberaQuem().get(j);
            libera.setFit(libera.getFit() - stub.getFi());
        }
    }

    public static void resetaDiagrama() {
        Diagrama.nome = "";
        Diagrama.classes = new ArrayList<>();
        Diagrama.ligacoes = new ArrayList<>();
        Diagrama.ordemIntegracao = new ArrayList<>();
        Diagrama.stubs = new ArrayList<>();
    }
}
