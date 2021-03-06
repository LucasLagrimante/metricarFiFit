/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Enum.TipoCardinalidade;
import Enum.TipoLigacao;
import Storage.Diagrama;
import Storage.Helper;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import model.Ligacao;
import org.xml.sax.SAXException;

/**
 *
 * @author Lucas Lagrimante
 */
public class main extends javax.swing.JFrame {

    Helper helper = new Helper();
    String lower;
    String upper;
    int count, count1, count2, count3, count4;

    public main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlCreditos = new javax.swing.JLabel();
        jbAbrirXmi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        jlCreditos.setText("Desenvolvido por Lucas Lagrimante e Leonardo Smoginski");

        jbAbrirXmi.setText("Abrir XMI Gerado pela extension XMI 0.9.2 do StarUML v2.8.0");
        jbAbrirXmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAbrirXmiActionPerformed(evt);
            }
        });

        jLabel1.setText("Entregue ao Dr. Marco Antônio Araújo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jbAbrirXmi, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlCreditos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbAbrirXmi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlCreditos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbAbrirXmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAbrirXmiActionPerformed
        Diagrama.resetaDiagrama();
        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setFileFilter(new FileNameExtensionFilter("Filtro .xml", "xml"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(this);

        File xmlFileLer = new File(fileChooser.getSelectedFile().getName());

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFileLer);
            doc.getDocumentElement().normalize();
            NodeList packagedElementS = doc.getElementsByTagName("packagedElement");
            helper.memorizaClasses(packagedElementS);

            for (count = 1; count < packagedElementS.getLength(); count++) {
                Node nodePackagedElement = packagedElementS.item(count);
                if (nodePackagedElement.getNodeType() == Node.ELEMENT_NODE) {
                    Element packagedElementElement = (Element) nodePackagedElement;

                    //aqui pegou os filhos do packge, que são os ownedMember e os generalization
                    NodeList relacoeS = helper.getFilhos(nodePackagedElement);

                    for (count1 = 0; count1 < relacoeS.getLength(); count1++) {
                        Node nodeRelacao = relacoeS.item(count1);
                        if (nodeRelacao.getNodeType() == Node.ELEMENT_NODE) {
                            Element relacaoElement = (Element) nodeRelacao;

                            if (nodeRelacao.getNodeName().equals("ownedMember") && relacaoElement.getAttribute("xmi:type").equals(TipoLigacao.Association.getTipoLigacao())) {
                                Ligacao ligacao = new Ligacao(relacaoElement.getAttribute("xmi:id"), TipoLigacao.Association);

                                //pega filhos do ownedMember 
                                NodeList ownedEndS = helper.getFilhos(nodeRelacao);
                                for (count2 = 0; count2 < ownedEndS.getLength(); count2++) {
                                    Node nodeOwnedEnd = ownedEndS.item(count2);
                                    if (nodeOwnedEnd.getNodeType() == Node.ELEMENT_NODE) {
                                        if (nodeOwnedEnd.getNodeName().equals("ownedEnd")) {
                                            Element ownerEndElement = (Element) nodeOwnedEnd;

                                            if (ownerEndElement.getAttribute("aggregation").equals("shared") || ownerEndElement.getAttribute("aggregation").equals("composite")) {
                                                ligacao.setTipo(TipoLigacao.Aggregation);
                                                ligacao.setClasseOrigem(Diagrama.getClasseById(ownerEndElement.getAttribute("type")));
                                                ligacao.setCardinalidadeOrigem(TipoCardinalidade.AggregationOrigem);
                                                Node cardNodeDestino = ownedEndS.item(3);
                                                Element ownerEndElementDestino = (Element) cardNodeDestino;
                                                ligacao.setClasseDestino(Diagrama.getClasseById(ownerEndElementDestino.getAttribute("type")));
                                                ligacao.setCardinalidadeDestino(TipoCardinalidade.AggregationDestino);
                                                count2 = count2 + 2;
                                            } else {
                                                if (count2 == 1) {
                                                    ligacao.setClasseOrigem(Diagrama.getClasseById(ownerEndElement.getAttribute("type")));

                                                    NodeList cardinalidades = helper.getFilhos(nodeOwnedEnd);
                                                    for (count4 = 0; count4 < cardinalidades.getLength(); count4++) {
                                                        Node cardinalidade = cardinalidades.item(count4);
                                                        if (cardinalidade.getNodeType() == Node.ELEMENT_NODE) {
                                                            Element cardinalidadeElement = (Element) cardinalidade;

                                                            if (cardinalidade.getNodeName().equals("lowerValue")) {
                                                                lower = cardinalidadeElement.getAttribute("value");
                                                            } else if (cardinalidade.getNodeName().equals("upperValue")) {
                                                                upper = cardinalidadeElement.getAttribute("value");
                                                            }
                                                        }
                                                    }
                                                    ligacao.setCardinalidadeOrigem(helper.getTipoCardinalidade(lower, upper));

                                                } else if (count2 == 3) {
                                                    ligacao.setClasseDestino(Diagrama.getClasseById(ownerEndElement.getAttribute("type")));

                                                    NodeList cardinalidades = helper.getFilhos(nodeOwnedEnd);
                                                    for (count4 = 0; count4 < cardinalidades.getLength(); count4++) {
                                                        Node cardinalidade = cardinalidades.item(count4);
                                                        if (cardinalidade.getNodeType() == Node.ELEMENT_NODE) {
                                                            Element cardinalidadeElement = (Element) cardinalidade;

                                                            if (cardinalidade.getNodeName().equals("lowerValue")) {
                                                                lower = cardinalidadeElement.getAttribute("value");
                                                            } else if (cardinalidade.getNodeName().equals("upperValue")) {
                                                                upper = cardinalidadeElement.getAttribute("value");
                                                            }
                                                        }
                                                    }
                                                    ligacao.setCardinalidadeDestino(helper.getTipoCardinalidade(lower, upper));

                                                }
                                            }
                                        }
                                    }
                                }
                                //adicionando ligacao
                                Diagrama.addLigacao(ligacao);
                            } else if (nodeRelacao.getNodeName().equals("generalization") && relacaoElement.getAttribute("xmi:type").equals(TipoLigacao.Generalization.getTipoLigacao())) {
                                Ligacao ligacao = new Ligacao(relacaoElement.getAttribute("xmi:id"), Diagrama.getClasseById(relacaoElement.getAttribute("specific")), Diagrama.getClasseById(relacaoElement.getAttribute("general")), TipoCardinalidade.GeneralizationOrigem, TipoCardinalidade.GeneralizationDestino, TipoLigacao.Generalization);
                                Diagrama.addLigacao(ligacao);
                            } else if (nodeRelacao.getNodeName().equals("ownedMember") && relacaoElement.getAttribute("xmi:type").equals(TipoLigacao.Dependency.getTipoLigacao())) {
                                Ligacao ligacao = new Ligacao(relacaoElement.getAttribute("xmi:id"), Diagrama.getClasseById(relacaoElement.getAttribute("client")), Diagrama.getClasseById(relacaoElement.getAttribute("supplier")), TipoCardinalidade.DependencyOrigem, TipoCardinalidade.DependencyDestino, TipoLigacao.Dependency);
                                Diagrama.addLigacao(ligacao);
                            }
                        }
                    }
                }

            }
//            list fi e fit1
//            Diagrama.listaFiFit();

            //só pode ser feito uma única vez
            Diagrama.calculaFiFit1();
            //integrando classes
            helper.integraClasses();

            //lista ordem de integração
            System.out.println("*********************************");
            helper.listaOrdemIntegracao();
            helper.listaStubs();
            System.out.println("*********************************");

        } catch (IOException | ParserConfigurationException | SAXException e) {
        }
    }//GEN-LAST:event_jbAbrirXmiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbAbrirXmi;
    private javax.swing.JLabel jlCreditos;
    // End of variables declaration//GEN-END:variables
}
