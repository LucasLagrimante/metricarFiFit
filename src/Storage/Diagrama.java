package Storage;

import java.util.ArrayList;
import java.util.List;
import model.Classe;
import model.Ligacao;

public class Diagrama {

    private static String nome;
    private static List<Classe> classes = new ArrayList<Classe>();
    private static List<Ligacao> ligacoes = new ArrayList<Ligacao>();

    public static List<Classe> getClasses() {
        return classes;
    }

    public static void addClasse(Classe classe) {
        Diagrama.classes.add(classe);
    }

    public static List<Ligacao> getLigacoes() {
        return ligacoes;
    }

    public static void addLigacoes(Ligacao ligacao) {
        Diagrama.ligacoes.add(ligacao);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
