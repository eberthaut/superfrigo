package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeCategoriesControleur;

import java.awt.*;

import javax.swing.*;

public class EntreeCategories extends SousPanneau {
    private EntreeCategoriesControleur controleur;

    private JList typesList = new JList();
    private JScrollPane scroll = new JScrollPane(typesList);
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
        this.add(scroll, BorderLayout.CENTER);

        choixButton.setText("Choisir");
        choixButton.setIcon(new ImageIcon(getClass().getResource("/icones/suivant.png")));
        choixButton.addActionListener(controleur);
        this.add(choixButton, BorderLayout.EAST);

        controleur.creerListe();
    }

    public JList getTypesList() {
        return typesList;
    }

    public JButton getChoixButton() {
        return choixButton;
    }
}
