package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeCategoriesControleur;

import java.awt.Dimension;

import java.awt.FlowLayout;

import javax.swing.*;

public class EntreeCategories extends JPanel {
    EntreeCategoriesControleur controleur;

    private FlowLayout layout = new FlowLayout();
    private JList typesList = new JList();
    private JButton choixButton = new JButton();
    private JButton retourButton = new JButton();

    public EntreeCategories(Fenetre fenetre) {
        controleur = new EntreeCategoriesControleur(fenetre, this);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(layout);
        this.setSize(new Dimension(1000,700));
        typesList.setPreferredSize(new Dimension(200, 300));
        typesList.setMaximumSize(new Dimension(200, 1000));
        typesList.setMinimumSize(new Dimension(200, 0));
        typesList.setSize(new Dimension(200, 100));
        choixButton.setText("Choisir");
        choixButton.addActionListener(controleur);
        retourButton.setText("Retour");
        retourButton.addActionListener(controleur);
        this.add(typesList, null);
        this.add(choixButton, null);
        this.add(retourButton, null);
        controleur.creerListe();
    }

    public JList getTypesList() {
        return typesList;
    }

    public JButton getChoixButton() {
        return choixButton;
    }

    public JButton getRetourButton() {
        return retourButton;
    }
}
