package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.ChangerHabitude;
import fr.insalyon.smartfridge.vues.EditerListeCourses;
import fr.insalyon.smartfridge.vues.Fenetre;
import fr.insalyon.smartfridge.vues.MenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by fannygallais on 27/04/2014.
 */
public class EditerListeCoursesControleur implements ActionListener{

    private Fenetre fenetre;
    private EditerListeCourses editerListeCourses;
    private ListModel<Article> articles;

    public EditerListeCoursesControleur(Fenetre fenetre, EditerListeCourses editerListeCourses){
        this.fenetre = fenetre;
        this.editerListeCourses = editerListeCourses;
        //articles = new ListModel(ServiceStock.listerArticles());
       // articles = new ListModel(ServiceCourses.genererListeCourses());
        this.creerListe();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(editerListeCourses.getChangerHabitudesButton())) {
            fenetre.allerA(new ChangerHabitude(fenetre));
        } else if (e.getSource().equals(editerListeCourses.getReinitialiserButton())) {
            fenetre.allerA(new EditerListeCourses(fenetre));
        }
    }

    public void creerListe() {
        articles = new ListModel<Article>(ServiceCourses.genererListeCourses());
        editerListeCourses.getListeDeCourses().setModel(articles);

    }
}

