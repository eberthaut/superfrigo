package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceImpression;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.vues.VueChangerHabitude;
import fr.insalyon.smartfridge.vues.VueEditerListeCourses;
import fr.insalyon.smartfridge.utilitaires.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/** Gere la generation de la liste de courses */
public class ControleurEditerListeCourses implements ActionListener, Rafraichissable {
    /** La fenetre de l'application */
    private final Fenetre fenetre;
    /** La vue */
    private final VueEditerListeCourses vue;
    /** Le modele des aliments dans la liste */
    private List<Aliment> aliments;

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     * @param vue La vue
     */
    public ControleurEditerListeCourses(Fenetre fenetre, VueEditerListeCourses vue){
        this.fenetre = fenetre;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vue.getChangerHabitudesButton())) {
            actionChangerHabitude();
        } else if (e.getSource().equals(vue.getImpressionButton())) {
            actionImprimer();
        }
    }

    @Override
    public void mettreAJour() {
        aliments = ServiceCourses.genererListeCourses();
        vue.getListeDeCourses().setModel(new ListModel<Aliment>(aliments));
    }

    /** Quand changer habitude est clique */
    private void actionChangerHabitude() {
        fenetre.allerA(new VueChangerHabitude(fenetre));
    }

    /** Quand imprime est clique */
    private void actionImprimer() {
        ServiceImpression.imprimer(aliments);
    }
}

