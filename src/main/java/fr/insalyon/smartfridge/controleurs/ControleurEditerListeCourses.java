package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceImpression;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.vues.VueChangerHabitude;
import fr.insalyon.smartfridge.vues.VueEditerListeCourses;
import fr.insalyon.smartfridge.utilitaires.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControleurEditerListeCourses implements ActionListener{

    private Fenetre fenetre;
    private VueEditerListeCourses vue;
    private List<Aliment> aliments;

    public ControleurEditerListeCourses(Fenetre fenetre, VueEditerListeCourses vue){
        this.fenetre = fenetre;
        this.vue = vue;
        this.creerListe();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vue.getChangerHabitudesButton())) {
            fenetre.allerA(new VueChangerHabitude(fenetre));
        } else if (e.getSource().equals(vue.getImpressionButton())) {
            ServiceImpression.imprimer(aliments);
        }
    }

    public void creerListe() {
        aliments = ServiceCourses.genererListeCourses();
        vue.getListeDeCourses().setModel(new ListModel<Aliment>(aliments));
    }
}

