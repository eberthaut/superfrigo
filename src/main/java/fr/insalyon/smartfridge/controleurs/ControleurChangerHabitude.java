package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.vues.VueChangerHabitude;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by fannygallais on 27/04/2014.
 */
public class ControleurChangerHabitude implements ActionListener, ListSelectionListener{
    private VueChangerHabitude vue;
    private ListModel<Article> articles;

    public ControleurChangerHabitude(VueChangerHabitude vue){
        this.vue = vue;
        articles = new ListModel<Article>(ServiceStock.listerArticles());
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(vue.getChangerHabitudeButton())){
            Article a = articles.get(vue.getArticlesList().getSelectedIndex());
            int i = (Integer) vue.getHabitudeSpinner().getValue();
            ServiceCourses.changerHabitude(a,i);
            vue.getHabitudeEtat().setText("L'habitude de l'article "+a.getNom()+" est "+i+". Habitude souhaitée : ");
        }
    }




    public void creerListe() {
        vue.getArticlesList().setModel(articles);
    }


    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if(vue.getArticlesList().isSelectionEmpty()){
            vue.getHabitudeEtat().setText("Veuillez sélectionner un article");
        } else {
            Article a =  articles.get(vue.getArticlesList().getSelectedIndex());
            int habitude = articles.get(vue.getArticlesList().getSelectedIndex()).getHabitude();
            vue.getHabitudeEtat().setText("<html><body>L'habitude de l'article "+a.getNom()+" est "+habitude+".<br/> Habitude souhaitée :</html></body>");
        }
    }
}
