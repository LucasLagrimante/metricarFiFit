package Storage;

import Enum.TipoCardinalidade;
import Enum.TipoLigacao;
import model.Classe;
import model.Ligacao;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import Enum.TipoCardinalidade;
import Enum.TipoLigacao;

public class Helper {

    String lower = "", upper = "";
    TipoCardinalidade tipoCardinalidade;

    public NodeList getFilhos(Node pai) {
        if (pai.getNodeType() == Node.ELEMENT_NODE) {
            if (pai.hasChildNodes()) {
                return pai.getChildNodes();
            }
        }
        return null;
    }

    public void memorizaClasses(NodeList listaClasses) {
        for (int temp = 1; temp < listaClasses.getLength(); temp++) {
            Node nodeClasse = listaClasses.item(temp);
            if (nodeClasse.getNodeType() == Node.ELEMENT_NODE) {
                Element paiElement = (Element) nodeClasse;
                Classe classe = new Classe(paiElement.getAttribute("xmi:id"), paiElement.getAttribute("name"));
                Diagrama.addClasse(classe);
            }
        }
    }

    public int calculaFiAssociation(Element ownedMember, String id) {
        int fi = 0;
        NodeList ownedEnds = this.getFilhos(ownedMember);

        for (int count1 = 0; count1 < ownedEnds.getLength(); count1++) {
            //ownedEnd
            Node classe = ownedEnds.item(count1);

            if (classe.getNodeType() == Node.ELEMENT_NODE) {
                Element classElement = (Element) classe;

                // Se ownedEnd for a classe atual
                if (classElement.getNodeName().equals("ownedEnd") && classElement.getAttribute("type").equals(id)) {
                    NodeList cardinalidades = this.getFilhos(classe);
                    for (int count2 = 0; count2 < cardinalidades.getLength(); count2++) {
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
                            
                            // Verifica se causa depência
                            if (lower.equals("0") && upper.equals("1")) {

                            }
                            if (lower.equals("1") && upper.equals("1")) {
                                fi++;
                            }
                            if (lower.equals("1") && upper.equals("*")) {
                                fi++;
                            }
                        }
                    }
                }
            }
        }
        return fi;
    }

    public void fiGeneralization() {

    }
}
