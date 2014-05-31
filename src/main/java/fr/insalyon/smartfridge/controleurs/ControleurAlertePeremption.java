package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.services.ServiceAlerte;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.vues.VueAlertePeremption;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControleurAlertePeremption implements ActionListener {
    // On decouple la recuperation des actions des vues
    private VueAlertePeremption vue;
    private ListModel<Aliment> aliments;

    int nbJours;

    public ControleurAlertePeremption(VueAlertePeremption vue, int nbJours) {
        this.vue = vue;
        this.nbJours = nbJours;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getEnleverButton()) {
            Aliment a = aliments.get(vue.getAlimentsList().getSelectedIndex());
            ServiceStock.retraitAliment(a, a.getQuantite());
            rafraichirListe();
        }
    }

    public void rafraichirListe() {
        List<Aliment> liste;
        if(ServiceAlerte.statusAlerte(nbJours) == 1) {
            liste = ServiceAlerte.listeAlimentsProchePeremption(nbJours);
        } else {
            liste = ServiceAlerte.listeAlimentsPerimes();
        }
        aliments = new ListModel<Aliment>(liste);
        vue.getAlimentsList().setModel(aliments);
    }
}
