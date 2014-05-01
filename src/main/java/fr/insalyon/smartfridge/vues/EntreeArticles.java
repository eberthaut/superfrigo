package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeArticlesControleur;
import fr.insalyon.smartfridge.modeles.Type;

import java.awt.Dimension;

import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.JSpinner;

public class EntreeArticles extends JPanel {
    EntreeArticlesControleur controleur;

    private FlowLayout layout = new FlowLayout();
    private JList articlesList = new JList();
    private JButton ajouterButton = new JButton();
    private JButton retourButton = new JButton();
    private JSpinner quantiteSpinner = new JSpinner(); // c'est le compteur de qte

    public EntreeArticles(Fenetre fenetre, Type type) {
        controleur = new EntreeArticlesControleur(fenetre, this, type);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(layout);
        this.setSize(new Dimension(1000,700));
        articlesList.setPreferredSize(new Dimension(200, 300));
        articlesList.setMaximumSize(new Dimension(200, 1000));
        articlesList.setMinimumSize(new Dimension(200, 0));
        articlesList.setSize(new Dimension(200, 100));
        ajouterButton.setText("Ajouter");
        ajouterButton.addActionListener(controleur);
        retourButton.setText("Retour");
        retourButton.addActionListener(controleur);
        quantiteSpinner.setValue(1);
        this.add(articlesList, null);
        this.add(quantiteSpinner, null);
        this.add(ajouterButton, null);
        this.add(retourButton, null);
        controleur.creerListe();
    }

    public JList getArticlesList() {
        return articlesList;
    }

    public JButton getAjouterButton() {
        return ajouterButton;
    }

    public JButton getRetourButton() {
        return retourButton;
    }

    public JSpinner getQuantiteSpinner() {
        return quantiteSpinner;
    }
}
