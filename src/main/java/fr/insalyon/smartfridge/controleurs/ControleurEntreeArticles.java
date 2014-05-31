package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.utilitaires.*;
import fr.insalyon.smartfridge.vues.VueEntreeArticles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurEntreeArticles implements ActionListener {
    // On decouple la recuperation des actions des vues
    private Fenetre fenetre;
    private VueEntreeArticles vue;
    private fr.insalyon.smartfridge.utilitaires.ListModel<Article> articles;

    public ControleurEntreeArticles(Fenetre fenetre, VueEntreeArticles vue, Type type) {
        this.fenetre = fenetre;
        this.vue = vue;
        articles = new fr.insalyon.smartfridge.utilitaires.ListModel<Article>(ServiceStock.listerArticles(type)); // On récupère tous les types
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getAjouterButton()) {
            Article a = articles.get(vue.getArticlesList().getSelectedIndex()); // Recupere l'article selectionne dans la liste (en bleu)
            int nb = (Integer) vue.getQuantiteSpinner().getValue();
            ServiceStock.ajouterAliment(a, nb);
            JOptionPane.showMessageDialog(null, nb + " " + a.getNom() + " ajoute(s) !", "OK !",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void creerListe() {
        vue.getArticlesList().setModel(articles);
    }
}
