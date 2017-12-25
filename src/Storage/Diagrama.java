package Storage;

import java.util.ArrayList;
import java.util.List;
import model.Classe;
import model.Ligacao;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Diagrama {

    private static String nome;
    private static List<Classe> classes = new ArrayList<Classe>();
    private static List<Ligacao> ligacoes = new ArrayList<Ligacao>();

    public static List<Classe> getClasses() {
        return classes;
    }

    public static Classe getClassePorId(String id) {
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

    public static List<Ligacao> getLigacoes() {
        return ligacoes;
    }

    public static void addLigacao(Ligacao ligacao) {
        Diagrama.ligacoes.add(ligacao);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public NodeList getFilhos(Node pai) {
        if (pai.getNodeType() == Node.ELEMENT_NODE) {
            if (pai.hasChildNodes()) {
                return pai.getChildNodes();
            }
        }
        return null;
    }

    
}
