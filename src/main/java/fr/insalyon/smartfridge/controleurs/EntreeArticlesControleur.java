package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.EntreeArticles;
import fr.insalyon.smartfridge.vues.Fenetre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EntreeArticlesControleur implements ActionListener {
    // On decouple la recuperation des actions des vues
    Fenetre fenetre;
    EntreeArticles entreeArticles;
    ListModel articles;

    public EntreeArticlesControleur(Fenetre fenetre, EntreeArticles entreeArticles, Type type) {
        this.fenetre = fenetre;
        this.entreeArticles = entreeArticles;
        articles = new ListModel(ServiceStock.listerArticles(type)); // On récupère tous les types
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == entreeArticles.getAjouterButton()) {
            Article a = articles.getArticleAt(entreeArticles.getArticlesList().getSelectedIndex()); // Recupere l'article selectionne dans la liste (en bleu)
            ServiceStock.ajouterAliment(a, (Integer)entreeArticles.getQuantiteSpinner().getValue());
        }
    }

    public void creerListe() {
        entreeArticles.getArticlesList().setModel(articles);
    }

    private class ListModel extends AbstractListModel {
        List<Article> articles;

        public ListModel(List<Article> articles) {
            this.articles = articles;
        }

        @Override // demande par la JList (on doit implementer un AbstractListModel)
        public int getSize() {
            return articles.size();
        }

        @Override
        public Object getElementAt(int i) {
            return articles.get(i).getNom();
        }

        public Article getArticleAt(int i) {
            return articles.get(i);
        }
    }
}
