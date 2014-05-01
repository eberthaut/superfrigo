package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.SortieAlimentControleur;

import java.awt.Dimension;

import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.JSpinner;

public class SortieAliment extends JPanel {
    SortieAlimentControleur controleur;

    private FlowLayout layout = new FlowLayout();
    private JList alimentsList = new JList();
    private JButton enleverButton = new JButton();
    private JButton retourButton = new JButton();
    private JSpinner quantiteSpinner = new JSpinner(); // c'est le compteur de qte

    public SortieAliment(Fenetre fenetre) {
        controleur = new SortieAlimentControleur(fenetre, this);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(layout);
        this.setSize(new Dimension(1000,700));
        alimentsList.setPreferredSize(new Dimension(200, 300));
        alimentsList.setMaximumSize(new Dimension(200, 1000));
        alimentsList.setMinimumSize(new Dimension(200, 0));
        alimentsList.setSize(new Dimension(200, 100));
        alimentsList.addListSelectionListener(controleur);
        enleverButton.setText("Enlever");
        enleverButton.addActionListener(controleur);
        retourButton.setText("Retour");
        retourButton.addActionListener(controleur);
        quantiteSpinner.setValue(1);
        this.add(alimentsList, null);
        this.add(quantiteSpinner, null);
        this.add(enleverButton, null);
        this.add(retourButton, null);
        controleur.rafraichirListe();
    }

    public JList getAlimentsList() {
        return alimentsList;
    }

    public JButton getEnleverButton() {
        return enleverButton;
    }

    public JButton getRetourButton() {
        return retourButton;
    }

    public JSpinner getQuantiteSpinner() {
        return quantiteSpinner;
    }
}
