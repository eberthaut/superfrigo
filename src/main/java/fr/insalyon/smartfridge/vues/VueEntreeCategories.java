package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurEntreeCategories;
import fr.insalyon.smartfridge.utilitaires.Changeable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import java.awt.*;

import javax.swing.*;

public class VueEntreeCategories extends VueSousPanneau implements Changeable {
    private ControleurEntreeCategories controleur;

    private JList typesList = new JList();
    private JButton choixButton = new JButton("Choisir", Raccourcis.icone("suivant"));

    public VueEntreeCategories(Fenetre fenetre) {
        super(fenetre);
        controleur = new ControleurEntreeCategories(fenetre, this);

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
