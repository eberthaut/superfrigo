package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeCategoriesControleur;

import java.awt.*;

import javax.swing.*;

public class EntreeCategories extends SousPanneau {
    EntreeCategoriesControleur controleur;

    private JPanel principal = new JPanel();
    private JList typesList = new JList();
    private JButton choixButton = new JButton();

    public EntreeCategories(Fenetre fenetre) {
        super(fenetre);
        controleur = new EntreeCategoriesControleur(fenetre, this);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        principal.setLayout(new BorderLayout());

        principal.add(typesList, BorderLayout.CENTER);

        choixButton.setText("[Choisir]>");
        choixButton.addActionListener(controleur);
        principal.add(choixButton, BorderLayout.EAST);

        controleur.creerListe();

        this.add(principal, BorderLayout.CENTER);
    }

    public JList getTypesList() {
        return typesList;
    }

    public JButton getChoixButton() {
        return choixButton;
    }
}
