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
    ListModel articles;

    public ChangerHabitudeControleur(Fenetre fenetre, ChangerHabitude changerHabitude){
        this.fenetre = fenetre;
        this.changerHabitude = changerHabitude;
        articles = new ListModel(ServiceStock.listerArticles());
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(changerHabitude.getChangerHabitudeButton())){
            Article a = articles.getArticleAt(changerHabitude.getArticlesList().getSelectedIndex());
            int i = Integer.parseInt(changerHabitude.getHabitudeTextField().getText());
            ServiceCourses.changerHabitude(a,i);
            changerHabitude.getHabitude().setText("L'habitude de l'article "+a.getNom()+" est "+i);
        } else if( e.getSource().equals(changerHabitude.getRetourButton())){
            fenetre.retourArriere();
        }
    }




    public void creerListe() {
        changerHabitude.getArticlesList().setModel(articles);
    }


    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if(changerHabitude.getArticlesList().isSelectionEmpty()){
            changerHabitude.getSelection().setText("Veuillez sélectionner un article");
        } else {
            String nom = articles.getArticleAt(changerHabitude.getArticlesList().getSelectedIndex()).getNom();
            int habitude = articles.getArticleAt(changerHabitude.getArticlesList().getSelectedIndex()).getHabitude();
            changerHabitude.getHabitude().setText("L'habitude de l'article "+nom+" est "+habitude);
            changerHabitude.getSelection().setText("Habitude souhaitée pour l'article "+nom+" :");
        }
    }


    private class ListModel extends AbstractListModel {
        List<Article> list;

        public ListModel(List<Article> articles) {
            this.list = articles;
        }

        public int getSize() {
            return list.size();
        }

        @Override
        public Object getElementAt(int i) {
            return list.get(i).getNom();
        }

        public Article getArticleAt(int i) {
            return list.get(i);
        }
    }
}
