package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceImpression;
import fr.insalyon.smartfridge.vues.ChangerHabitude;
import fr.insalyon.smartfridge.vues.EditerListeCourses;
import fr.insalyon.smartfridge.vues.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by fannygallais on 27/04/2014.
 */
public class EditerListeCoursesControleur implements ActionListener{

    private Fenetre fenetre;
    private EditerListeCourses editerListeCourses;
    private List<Aliment> aliments;

    public EditerListeCoursesControleur(Fenetre fenetre, EditerListeCourses editerListeCourses){
        this.fenetre = fenetre;
        this.editerListeCourses = editerListeCourses;
        this.creerListe();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(editerListeCourses.getChangerHabitudesButton())) {
            fenetre.allerA(new ChangerHabitude(fenetre));
        } else if (e.getSource().equals(editerListeCourses.getImpressionButton())) {
            ServiceImpression.imprimer(aliments);
        }
    }

    public void creerListe() {
        aliments = ServiceCourses.genererListeCourses();
        editerListeCourses.getListeDeCourses().setModel(new ListModel<Aliment>(aliments));
    }

    public void rafraichirListe() {
        this.creerListe();
    }
}

