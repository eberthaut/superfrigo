package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.services.ServiceAlerte;
import fr.insalyon.smartfridge.services.ServiceThermodynamique;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.vues.VueEntreeCategories;
import fr.insalyon.smartfridge.vues.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Gere le menu principal */
public class ControleurMenuPrincipal implements ActionListener, Rafraichissable {
    /** La fenetre de l'application */
    private Fenetre fenetre;
    /** La vue */
    private VueMenuPrincipal vue;
    /** Le nombre de jours pour prevenir les produits proches de la peremption */
    private int nbJoursAlerte = 2;

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     * @param vue La vue
     */
    public ControleurMenuPrincipal(Fenetre fenetre, VueMenuPrincipal vue) {
        this.fenetre = fenetre;
        this.vue = vue;

        for(int i = 1; i <= 10; i ++) {
            vue.getAlerteCombo().addItem(i);
        }
        vue.getAlerteCombo().setSelectedItem(nbJoursAlerte);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(vue.getAjouterButton())) {
            fenetre.allerA(new VueEntreeCategories(fenetre));
        } else if(e.getSource().equals(vue.getRetirerButton())) {
            fenetre.allerA(new VueSortieAliment(fenetre));
        } else if(e.getSource().equals(vue.getRecettesButton())) {
            fenetre.allerA(new VueMenuRecettes(fenetre));
        } else if(e.getSource().equals(vue.getCoursesButton())) {
            fenetre.allerA(new VueEditerListeCourses(fenetre));
        } else if(e.getSource().equals(vue.getAlerteButton()) && vue.getAlerteButton().isEnabled()) {
            fenetre.allerA(new VueAlertePeremption(fenetre, this.nbJoursAlerte));
        } else if(e.getSource().equals(vue.getAlerteCombo())) {
            this.nbJoursAlerte = (Integer) vue.getAlerteCombo().getSelectedItem();
            mettreAJour();
        }
    }

    @Override
    public void mettreAJour() {
        int status = ServiceAlerte.statusAlerte(nbJoursAlerte);
        switch(status) {
            case 0:
                vue.getAlerteButton().setEnabled(false);
                vue.getAlerteButton().setText("Rien a signaler");
                vue.getAlerteButton().setIcon(new ImageIcon(getClass().getResource("/icones/ok.png")));
                vue.getAlerteButton().removeActionListener(this);
                break;
            case 1:
                vue.getAlerteButton().setEnabled(true);
                vue.getAlerteButton().setForeground(new Color(200, 150, 0));
                vue.getAlerteButton().setText("Des aliments sont proches de la peremption");
                vue.getAlerteButton().setIcon(new ImageIcon(getClass().getResource("/icones/danger.png")));
                vue.getAlerteButton().removeActionListener(this);
                vue.getAlerteButton().addActionListener(this);
                break;
            case 2:
                vue.getAlerteButton().setEnabled(true);
                vue.getAlerteButton().setForeground(new Color(200, 0, 0));
                vue.getAlerteButton().setText("Des aliments sont perimes");
                vue.getAlerteButton().setIcon(new ImageIcon(getClass().getResource("/icones/erreur.png")));
                vue.getAlerteButton().removeActionListener(this);
                vue.getAlerteButton().addActionListener(this);
                break;
        }
        vue.getTemperatureLabel().setText(String.format("%.2f degres Celcius",ServiceThermodynamique.mettreAJourTemperature()));
    }
}
