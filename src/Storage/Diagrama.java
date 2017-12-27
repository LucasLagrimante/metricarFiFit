package Storage;

import java.util.ArrayList;
import java.util.List;
import model.Classe;

public class Diagrama {

    private static String nome;
    private static List<Classe> classes = new ArrayList<>();

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void calculaFitDasClasses() {
        for (int i = 0; i < classes.size(); i++) {
            List<Classe> listaDependeDe = classes.get(i).getDependeDe();
            for (int j = 0; j < listaDependeDe.size(); j++) {
                classes.get(i).somaFit(listaDependeDe.get(j).getFi());
            }
        }
    }
}
