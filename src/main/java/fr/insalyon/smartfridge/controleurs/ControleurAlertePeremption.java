package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.services.ServiceAlerte;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.vues.VueAlertePeremption;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/** Gere l'affichage d'une alerte */
public class ControleurAlertePeremption implements ActionListener, Rafraichissable {
    /** La vue */
    private VueAlertePeremption vue;
    /** Le modele des aliments */
    private ListModel<Aliment> aliments;
    /** Le nombre de jours a considerer pour les aliments proches de la peremption */
    int nbJours;

    /** Constructeur
     *
     * @param vue La vue
     * @param nbJours Le nombre de jours a considerer pour les aliments proches de la peremption
     */
    public ControleurAlertePeremption(VueAlertePeremption vue, int nbJours) {
        this.vue = vue;
        this.nbJours = nbJours;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getEnleverButton()) {
            actionEnleverButton();
        }
    }

    @Override
    public void mettreAJour() {
        List<Aliment> liste;
        if(ServiceAlerte.statusAlerte(nbJours) == 1) {
            liste = ServiceAlerte.listeAlimentsProchePeremption(nbJours);
        } else {
            liste = ServiceAlerte.listeAlimentsPerimes();
        }
        aliments = new ListModel<Aliment>(liste);
        vue.getAlimentsList().setModel(aliments);
    }

    /** Quand on clique sur le bouton enlever */
    private void actionEnleverButton() {
        Aliment a = aliments.get(vue.getAlimentsList().getSelectedIndex());
        ServiceStock.retraitAliment(a, a.getQuantite());
        mettreAJour();
    }
}
