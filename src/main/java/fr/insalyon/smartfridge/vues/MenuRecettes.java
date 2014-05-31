package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.MenuRecettesControleur;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agabriel on 17/04/14.
 */
public class MenuRecettes extends SousPanneau implements Changeable {
    MenuRecettesControleur controleur;

    private JList recettesList = new JList();
    private JScrollPane scroll = new JScrollPane(recettesList);
    private JList ingredientsList = new JList();
    private JScrollPane ingredientsRecette = new JScrollPane(ingredientsList);
    private JPanel options = new JPanel();
    private JPanel panneauCentre = new JPanel();
    private JButton toggleButton = new JButton();
    private JButton ajouterButton = new JButton();
    private JButton supprimerButton = new JButton();
    private JButton importButton = new JButton();

    public MenuRecettes(Fenetre fenetre) {
        super(fenetre);
        controleur = new MenuRecettesControleur(fenetre, this);

        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        options.setLayout(new GridLayout(1, 4));

        recettesList.addListSelectionListener(controleur);
        panneauCentre.setLayout(new GridLayout(1,3));
        panneauCentre.add(scroll);
        panneauCentre.add(ingredientsRecette);
        this.add(panneauCentre, BorderLayout.CENTER);

        toggleButton.setText("Activer");
        toggleButton.setIcon(new ImageIcon(getClass().getResource("/icones/ok.png")));
        toggleButton.addActionListener(controleur);
        options.add(toggleButton);
        ajouterButton.setText("Ajouter");
        ajouterButton.setIcon(new ImageIcon(getClass().getResource("/icones/ajout.png")));
        ajouterButton.addActionListener(controleur);
        options.add(ajouterButton);
        supprimerButton.setText("Supprimer");
        supprimerButton.setIcon(new ImageIcon(getClass().getResource("/icones/suppression.png")));
        supprimerButton.addActionListener(controleur);
        options.add(supprimerButton);
        importButton.setText("Importer depuis Marmiton.org");
        importButton.setIcon(new ImageIcon(getClass().getResource("/icones/internet.png")));
        importButton.addActionListener(controleur);
        options.add(importButton);
        this.add(options, BorderLayout.SOUTH);

        controleur.creerListe();

    }

    public void mettreAJour() {
        controleur.rafraichirListeRecette();
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









