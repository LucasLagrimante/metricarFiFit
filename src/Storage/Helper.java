package Storage;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Helper {

    public NodeList getFilhos(Node pai) {
        if (pai.getNodeType() == Node.ELEMENT_NODE) {
            if (pai.hasChildNodes()) {
                return pai.getChildNodes();
            }
        }
        return null;
    }
}
