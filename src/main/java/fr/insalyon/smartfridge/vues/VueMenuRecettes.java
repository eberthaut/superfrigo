package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurMenuRecettes;
import fr.insalyon.smartfridge.utilitaires.Changeable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import javax.swing.*;
import java.awt.*;

public class VueMenuRecettes extends VueSousPanneau implements Changeable {
    private ControleurMenuRecettes controleur;

    private JList recettesList = new JList();
    private JList ingredientsList = new JList();
    private JButton toggleButton = new JButton("Activer", Raccourcis.icone("ok"));
    private JButton ajouterButton = new JButton("Ajouter", Raccourcis.icone("ajout"));
    private JButton supprimerButton = new JButton("Supprimer", Raccourcis.icone("suppression"));
    private JButton importButton = new JButton("Importer depuis Marmiton.org", Raccourcis.icone("internet"));

    public VueMenuRecettes(Fenetre fenetre) {
        super(fenetre);
        controleur = new ControleurMenuRecettes(fenetre, this);

        recettesList.addListSelectionListener(controleur);
        toggleButton.addActionListener(controleur);
        ajouterButton.addActionListener(controleur);
        supprimerButton.addActionListener(controleur);
        importButton.addActionListener(controleur);

        JPanel panneauCentre = new JPanel(new GridLayout(1, 2));
        JScrollPane recettesScroll = new JScrollPane(recettesList);
        panneauCentre.add(recettesScroll);
        JScrollPane ingredientsScroll = new JScrollPane(ingredientsList);
        panneauCentre.add(ingredientsScroll);
        this.add(panneauCentre, BorderLayout.CENTER);

        JPanel options = new JPanel(new GridLayout(1, 4));
        options.add(toggleButton);
        options.add(ajouterButton);
        options.add(supprimerButton);
        options.add(importButton);
        this.add(options, BorderLayout.SOUTH);
    }

    public void mettreAJour() {
        controleur.creerListe();
    }

    public JButton getToggleButton() {
        return toggleButton;
    }

    public JButton getAjouterButton() {
        return ajouterButton;
    }

    public JButton getSupprimerButton() { return supprimerButton; }

    public JList getRecettesList() {
        return recettesList;
    }

    public JList getIngredientsList() {
        return ingredientsList;
    }

    public JButton getImportButton() {
        return importButton;
    }
}









