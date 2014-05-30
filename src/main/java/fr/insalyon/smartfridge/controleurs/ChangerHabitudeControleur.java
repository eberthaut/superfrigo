package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.ChangerHabitude;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by fannygallais on 27/04/2014.
 */
public class ChangerHabitudeControleur implements ActionListener, ListSelectionListener{
    private ChangerHabitude changerHabitude;
    ListModel<Article> articles;

    public ChangerHabitudeControleur(ChangerHabitude changerHabitude){
        this.changerHabitude = changerHabitude;
        articles = new ListModel<Article>(ServiceStock.listerArticles());
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(changerHabitude.getChangerHabitudeButton())){
            Article a = articles.get(changerHabitude.getArticlesList().getSelectedIndex());
            int i = (Integer)changerHabitude.getHabitudeSpinner().getValue();
            ServiceCourses.changerHabitude(a,i);
            changerHabitude.getHabitudeEtat().setText("L'habitude de l'article "+a.getNom()+" est "+i+". Habitude souhaitée : ");
        }
    }




    public void creerListe() {
        changerHabitude.getArticlesList().setModel(articles);
    }


    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if(changerHabitude.getArticlesList().isSelectionEmpty()){
            changerHabitude.getHabitudeEtat().setText("Veuillez sélectionner un article");
        } else {
            Article a =  articles.get(changerHabitude.getArticlesList().getSelectedIndex());
            int habitude = articles.get(changerHabitude.getArticlesList().getSelectedIndex()).getHabitude();
            changerHabitude.getHabitudeEtat().setText("L'habitude de l'article "+a.getNom()+" est "+habitude+". Habitude souhaitée : ");
        }
    }
}
