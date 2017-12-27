package Storage;

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

    public void adicionaDependencias(Element ownedMember, String id) {
        NodeList ownedEnds = this.getFilhos(ownedMember);

        for (count1 = 0; count1 < ownedEnds.getLength(); count1++) {
            //ownedEnd
            Node classe = ownedEnds.item(count1);

            if (classe.getNodeType() == Node.ELEMENT_NODE) {
                Element classElement = (Element) classe;

                // Se ownedEnd for a classe atual
                if (classElement.getNodeName().equals("ownedEnd") && classElement.getAttribute("type").equals(id)) {
                    NodeList cardinalidades = this.getFilhos(classe);
                    for (count2 = 0; count2 < cardinalidades.getLength(); count2++) {
                        // Lower/Upper
                        Node cardinalidade = cardinalidades.item(count2);
                        if (cardinalidade.getNodeType() == Node.ELEMENT_NODE) {
                            Element cardinalElement = (Element) cardinalidade;

                            // Pega valores da cardinalidade
                            if (cardinalElement.getNodeName().equals("lowerValue")) {
                                lower = cardinalElement.getAttribute("value");
                            } else if (cardinalElement.getNodeName().equals("upperValue")) {
                                upper = cardinalElement.getAttribute("value");
                            }

                        }
                    }
                    // Verifica se causa depência
                    if (lower.equals("0") && upper.equals("1")) {

                    }
                    if (lower.equals("1") && upper.equals("1")) {

                    }
                    if (lower.equals("1") && upper.equals("*")) {

                    }
                }
            }
        }
    }

    public void fiGeneralization() {

    }
}
