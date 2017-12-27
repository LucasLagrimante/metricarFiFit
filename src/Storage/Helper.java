package Storage;

import Enum.TipoCardinalidade;
import java.util.List;
import model.Classe;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Helper {

    String lower;
    String upper;
    int count, count1, count2;

    public NodeList getFilhos(Node pai) {
        return pai.getChildNodes();
    }

    public void memorizaClasses(NodeList listaClasses) {
        for (count = 1; count < listaClasses.getLength(); count++) {
            Node nodeClasse = listaClasses.item(count);
            if (nodeClasse.getNodeType() == Node.ELEMENT_NODE) {

                Element paiElement = (Element) nodeClasse;
                Classe classe = new Classe(paiElement.getAttribute("xmi:id"), paiElement.getAttribute("name"));
                Diagrama.addClasse(classe);

            }
        }
    }

    public TipoCardinalidade getTipoCardinalidade(String lower, String upper) {
        if (lower.equals("0") && upper.equals("1")) {
            return TipoCardinalidade.ZeroUm;
        }
        if (lower.equals("1") && upper.equals("1")) {
            return TipoCardinalidade.Um;

        }
        if (lower.equals("0") && upper.equals("*")) {
            return TipoCardinalidade.ZeroMuitos;

        }
        if (lower.equals("1") && upper.equals("*")) {
            return TipoCardinalidade.UmMuitos;
        }
        if (lower.equals("*") && upper.equals("*")) {
            return TipoCardinalidade.Muitos;

        }
        return null;
    }

    public void listaOrdemIntegracao() {
        List<Classe> ordem = Diagrama.getOrdemIntegracao();
        if (ordem.size() > 0) {
            System.out.println("Ordem de Integração:");
            for (int i = 0; i < Diagrama.getOrdemIntegracao().size(); i++) {
                System.out.println(i + 1 + " - " + Diagrama.getOrdemIntegracao().get(i).getNome());
            }
        }
    }

    public void listaStubs() {
        if (Diagrama.getStubs().size() > 0) {
            System.out.println("\nUtilizando stub para:");
            for (int i = 0; i < Diagrama.getStubs().size(); i++) {
                System.out.println(Diagrama.getStubs().get(i).getNome());
            }
        }
    }
}
