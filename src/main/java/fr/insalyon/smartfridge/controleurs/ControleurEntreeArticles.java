package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.utilitaires.*;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.vues.VueEntreeArticles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Gere l'ajout d'articles */
public class ControleurEntreeArticles implements ActionListener, Rafraichissable {
    /** La vue */
    private VueEntreeArticles vue;
    /** Le type */
    private Type type;
    /** Le modele d'articles */
    private ListModel<Article> articles;

    /** Constructeur
     *
     * @param vue La vue
     * @param type Le type pour lequel afficher les articles
     */
    public ControleurEntreeArticles(VueEntreeArticles vue, Type type) {
        this.vue = vue;
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getAjouterButton()) {
            actionAjouter();
        }
    }

    @Override
    public void mettreAJour() {
        articles = new ListModel<Article>(ServiceStock.listerArticles(type));
        vue.getArticlesList().setModel(articles);
    }

    /** Quand ajouter est clique */
    private void actionAjouter() {
        Article a = articles.get(vue.getArticlesList().getSelectedIndex()); // Recupere l'article selectionne dans la liste (en bleu)
        int nb = (Integer) vue.getQuantiteSpinner().getValue();
        ServiceStock.ajouterAliment(a, nb);
        JOptionPane.showMessageDialog(null, nb + " " + a.getNom() + " ajoute(s) !", "OK !",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
