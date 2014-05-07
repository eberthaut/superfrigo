package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeArticlesControleur;
import fr.insalyon.smartfridge.modeles.Type;

import java.awt.*;

import javax.swing.*;
import javax.swing.JSpinner;

public class EntreeArticles extends SousPanneau {
    EntreeArticlesControleur controleur;
    private JList articlesList = new JList();
    private JScrollPane scroll = new JScrollPane(articlesList);
    private JButton ajouterButton = new JButton();
    private JPanel options = new JPanel();
    private JLabel spinnerLabel = new JLabel();
    private JSpinner quantiteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1)); // c'est le compteur de qte

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
        options.setLayout(new GridLayout(1,2));

        this.add(scroll, BorderLayout.CENTER);

        ajouterButton.setText("[Ajouter]+");
        ajouterButton.addActionListener(controleur);
        this.add(ajouterButton, BorderLayout.EAST);

        spinnerLabel.setText("Quantite a ajouter : ");
        spinnerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        options.add(spinnerLabel, null);
        options.add(quantiteSpinner, null);
        this.add(options, BorderLayout.SOUTH);

        controleur.creerListe();
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
