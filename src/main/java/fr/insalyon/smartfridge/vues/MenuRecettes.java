package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.MenuRecettesControleur;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agabriel on 17/04/14.
 */
public class MenuRecettes extends SousPanneau {
    MenuRecettesControleur controleur;

    private JList recettesList = new JList();
    private JScrollPane scroll = new JScrollPane(recettesList);
    private JPanel options = new JPanel();
    private JButton toggleButton = new JButton();
    private JButton ajouterButton = new JButton();
    private JButton supprimerButton = new JButton();

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
        options.setLayout(new GridLayout(1, 3));

        recettesList.addListSelectionListener(controleur);
        this.add(scroll, BorderLayout.CENTER);

        toggleButton.setText("Changer");
        toggleButton.addActionListener(controleur);
        options.add(toggleButton);
        ajouterButton.setText("Ajouter");
        ajouterButton.addActionListener(controleur);
        options.add(ajouterButton);
        supprimerButton.setText("Supprimer");
        supprimerButton.addActionListener(controleur);
        options.add(supprimerButton);
        this.add(options, BorderLayout.SOUTH);

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
}









