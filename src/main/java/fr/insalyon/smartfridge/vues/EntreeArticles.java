package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeArticlesControleur;
import fr.insalyon.smartfridge.modeles.Type;

import java.awt.*;

import javax.swing.*;
import javax.swing.JSpinner;

public class EntreeArticles extends SousPanneau {
    EntreeArticlesControleur controleur;

    private JPanel principal = new JPanel();
    private JList articlesList = new JList();
    private JButton ajouterButton = new JButton();
    private JPanel options = new JPanel();
    private JLabel spinnerLabel = new JLabel();
    private JSpinner quantiteSpinner = new JSpinner(); // c'est le compteur de qte

    public EntreeArticles(Fenetre fenetre, Type type) {
        super(fenetre);
        controleur = new EntreeArticlesControleur(fenetre, this, type);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        principal.setLayout(new BorderLayout());
        options.setLayout(new FlowLayout());

        principal.add(articlesList, BorderLayout.CENTER);

        ajouterButton.setText("[Ajouter]+");
        ajouterButton.addActionListener(controleur);
        principal.add(ajouterButton, BorderLayout.EAST);

        spinnerLabel.setText("Quantite a ajouter : ");
        options.add(spinnerLabel, null);
        quantiteSpinner.setValue(1);
        options.add(quantiteSpinner, null);
        principal.add(options, BorderLayout.SOUTH);

        controleur.creerListe();

        this.add(principal, BorderLayout.CENTER);
    }

    public JList getArticlesList() {
        return articlesList;
    }

    public JButton getAjouterButton() {
        return ajouterButton;
    }

    public JSpinner getQuantiteSpinner() {
        return quantiteSpinner;
    }
}
