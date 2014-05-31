package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurEntreeArticles;
import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import java.awt.*;

import javax.swing.*;
import javax.swing.JSpinner;

/** La vue pour ajouter un article */
public class VueEntreeArticles extends VueSousPanneau implements Rafraichissable {
    /** Le controleur */
    private ControleurEntreeArticles controleur;

    /** La liste des articles */
    private JList articlesList = new JList();
    /** Le bouton d'ajout */
    private JButton ajouterButton = new JButton("Ajouter", Raccourcis.icone("ajout"));
    /** Le spinner de quantite */
    private JSpinner quantiteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1)); // c'est le compteur de qte

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     * @param type Le Type d'Article a afficher
     */
    public VueEntreeArticles(Fenetre fenetre, Type type) {
        super(fenetre);
        controleur = new ControleurEntreeArticles(this, type);

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
        controleur.mettreAJour();
    }

    /** Retourne La liste des Articles
     *
     * @return La liste des Articles
     */
    public JList getArticlesList() {
        return articlesList;
    }

    /** Retourne Le bouton d'ajout
     *
     * @return Le bouton d'ajout
     */
    public JButton getAjouterButton() {
        return ajouterButton;
    }

    /** Retourne Le spinner de quantite
     *
     * @return Le spinner de quantite
     */
    public JSpinner getQuantiteSpinner() {
        return quantiteSpinner;
    }
}
