package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeCategoriesControleur;

import java.awt.*;

import javax.swing.*;

public class EntreeCategories extends SousPanneau implements Changeable {
    private EntreeCategoriesControleur controleur;

    private JList typesList = new JList();
    private JButton choixButton = new JButton("Choisir", UtilitairesVues.icone("suivant"));

    public EntreeCategories(Fenetre fenetre) {
        super(fenetre);
        controleur = new EntreeCategoriesControleur(fenetre, this);

        choixButton.addActionListener(controleur);

        JScrollPane scroll = new JScrollPane(typesList);
        this.add(scroll, BorderLayout.CENTER);

        this.add(choixButton, BorderLayout.EAST);
    }

    @Override
    public void mettreAJour() {
        controleur.creerListe();
    }

    public JList getTypesList() {
        return typesList;
    }

    public JButton getChoixButton() {
        return choixButton;
    }
}
