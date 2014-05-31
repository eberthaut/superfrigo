package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeArticlesControleur;
import fr.insalyon.smartfridge.modeles.Type;

import java.awt.*;

import javax.swing.*;
import javax.swing.JSpinner;

public class EntreeArticles extends SousPanneau {
    private EntreeArticlesControleur controleur;
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
        options.setLayout(new GridLayout(1,3));

        this.add(scroll, BorderLayout.CENTER);

        spinnerLabel.setText("Quantite a ajouter : ");
        spinnerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        options.add(spinnerLabel, null);
        options.add(quantiteSpinner, null);
        ajouterButton.setText("Ajouter");
        ajouterButton.setIcon(new ImageIcon(getClass().getResource("/icones/ajout.png")));
        ajouterButton.addActionListener(controleur);
        options.add(ajouterButton);
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
