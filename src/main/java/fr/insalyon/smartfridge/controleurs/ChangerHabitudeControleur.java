package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.ChangerHabitude;
import fr.insalyon.smartfridge.vues.Fenetre;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by fannygallais on 27/04/2014.
 */
public class ChangerHabitudeControleur implements ActionListener, ListSelectionListener{
    private Fenetre fenetre;
    private ChangerHabitude changerHabitude;
    ListModel<Article> articles;

    public ChangerHabitudeControleur(Fenetre fenetre, ChangerHabitude changerHabitude){
        this.fenetre = fenetre;
        this.changerHabitude = changerHabitude;
        articles = new ListModel<Article>(ServiceStock.listerArticles());
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(changerHabitude.getChangerHabitudeButton())){
            Article a = articles.get(changerHabitude.getArticlesList().getSelectedIndex());
            int i = (Integer)changerHabitude.getHabitudeSpinner().getValue();
            ServiceCourses.changerHabitude(a,i);
            changerHabitude.getHabitude().setText("L'habitude de l'article "+a.getNom()+" est "+i);
        }
    }




    public void creerListe() {
        changerHabitude.getArticlesList().setModel(articles);
    }


    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if(changerHabitude.getArticlesList().isSelectionEmpty()){
            changerHabitude.getHabitude().setText("Veuillez sélectionner un article");
        } else {
            int habitude = articles.get(changerHabitude.getArticlesList().getSelectedIndex()).getHabitude();
            changerHabitude.getHabitude().setText("Habitude souhaitée :");
            changerHabitude.getHabitudeSpinner().setValue(habitude);
        }
    }
}
