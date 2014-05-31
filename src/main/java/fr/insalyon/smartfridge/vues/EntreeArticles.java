package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EntreeArticlesControleur;
import fr.insalyon.smartfridge.modeles.Type;

import java.awt.*;

import javax.swing.*;
import javax.swing.JSpinner;

public class EntreeArticles extends SousPanneau implements Changeable {
    private EntreeArticlesControleur controleur;

    private JList articlesList = new JList();
    private JButton ajouterButton = new JButton("Ajouter", UtilitairesVues.icone("ajout"));
    private JSpinner quantiteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1)); // c'est le compteur de qte

    public EntreeArticles(Fenetre fenetre, Type type) {
        super(fenetre);
        controleur = new EntreeArticlesControleur(fenetre, this, type);

        ajouterButton.addActionListener(controleur);

        JScrollPane scroll = new JScrollPane(articlesList);
        this.add(scroll, BorderLayout.CENTER);


        JPanel options = new JPanel(new GridLayout(1,3));

        JLabel spinnerLabel = new JLabel("Quantite a ajouter : ");
        spinnerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        options.add(spinnerLabel, null);
        options.add(quantiteSpinner, null);
        options.add(ajouterButton);
        this.add(options, BorderLayout.SOUTH);
    }

    @Override
    public void mettreAJour() {
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
