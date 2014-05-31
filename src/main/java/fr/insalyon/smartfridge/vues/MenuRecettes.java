package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.MenuRecettesControleur;

import javax.swing.*;
import java.awt.*;

public class MenuRecettes extends SousPanneau implements Changeable {
    private MenuRecettesControleur controleur;

    private JList recettesList = new JList();
    private JList ingredientsList = new JList();
    private JButton toggleButton = new JButton("Activer", UtilitairesVues.icone("ok"));
    private JButton ajouterButton = new JButton("Ajouter", UtilitairesVues.icone("ajout"));
    private JButton supprimerButton = new JButton("Supprimer", UtilitairesVues.icone("suppression"));
    private JButton importButton = new JButton("Importer depuis Marmiton.org", UtilitairesVues.icone("internet"));

    public MenuRecettes(Fenetre fenetre) {
        super(fenetre);
        controleur = new MenuRecettesControleur(fenetre, this);

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









