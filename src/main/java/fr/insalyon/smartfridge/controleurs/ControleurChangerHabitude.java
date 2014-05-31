package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.vues.VueChangerHabitude;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Gere les changements d'habitude */
public class ControleurChangerHabitude implements ActionListener, ListSelectionListener, Rafraichissable {
    /** La vue */
    private VueChangerHabitude vue;
    /** Le modele des articles */
    private ListModel<Article> articles;

    /** Constructeur
     *
     * @param vue La vue
     */
    public ControleurChangerHabitude(VueChangerHabitude vue){
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(vue.getChangerHabitudeButton())){
            actionChangerHabitude();
        }
    }

    @Override
    public void mettreAJour() {
        articles = new ListModel<Article>(ServiceStock.listerArticles());
        vue.getArticlesList().setModel(articles);
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        selectionArticle();
    }

    /** Quand on clique sur changer */
    private void actionChangerHabitude() {
        Article a = articles.get(vue.getArticlesList().getSelectedIndex());
        int i = (Integer) vue.getHabitudeSpinner().getValue();
        ServiceCourses.changerHabitude(a,i);
        vue.getHabitudeEtat().setText(Raccourcis.html("L'habitude de l'article "+a.getNom()+" est "+i+".<br/>Habitude souhaitée : "));
    }

    /** Quand on selectionne un article */
    private void selectionArticle() {
        if(vue.getArticlesList().isSelectionEmpty()){
            vue.getHabitudeEtat().setText("Veuillez sélectionner un article");
        } else {
            Article a =  articles.get(vue.getArticlesList().getSelectedIndex());
            int habitude = articles.get(vue.getArticlesList().getSelectedIndex()).getHabitude();
            vue.getHabitudeEtat().setText(Raccourcis.html("L'habitude de l'article "+a.getNom()+" est "+habitude+".<br/>Habitude souhaitée :"));
        }
    }
}
