package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.vues.ChangerHabitude;
import fr.insalyon.smartfridge.vues.EditerListeCourses;
import fr.insalyon.smartfridge.vues.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by fannygallais on 27/04/2014.
 */
public class EditerListeCoursesControleur implements ActionListener{

    private Fenetre fenetre;
    private EditerListeCourses editerListeCourses;

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
        editerListeCourses.getListeDeCourses().setModel(new ListModel<Aliment>(ServiceCourses.genererListeCourses()));

    }

    public void rafraichirListe() {
        this.creerListe();
    }
}

