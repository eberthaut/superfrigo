package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.EntreeArticles;
import fr.insalyon.smartfridge.vues.Fenetre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntreeArticlesControleur implements ActionListener {
    // On decouple la recuperation des actions des vues
    private Fenetre fenetre;
    private EntreeArticles entreeArticles;
    private ListModel<Article> articles;

    public EntreeArticlesControleur(Fenetre fenetre, EntreeArticles entreeArticles, Type type) {
        this.fenetre = fenetre;
        this.entreeArticles = entreeArticles;
        articles = new ListModel<Article>(ServiceStock.listerArticles(type)); // On récupère tous les types
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == entreeArticles.getAjouterButton()) {
            Article a = articles.get(entreeArticles.getArticlesList().getSelectedIndex()); // Recupere l'article selectionne dans la liste (en bleu)
            int nb = (Integer)entreeArticles.getQuantiteSpinner().getValue();
            ServiceStock.ajouterAliment(a, nb);
            JOptionPane.showMessageDialog(null, nb + " " + a.getNom() + " ajoute(s) !", "OK !",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void creerListe() {
        entreeArticles.getArticlesList().setModel(articles);
    }
}
