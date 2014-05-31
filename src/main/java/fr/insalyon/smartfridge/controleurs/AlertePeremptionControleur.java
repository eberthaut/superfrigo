package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.services.ServiceAlerte;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.AlertePeremption;
import fr.insalyon.smartfridge.vues.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AlertePeremptionControleur implements ActionListener {
    // On decouple la recuperation des actions des vues
    private Fenetre fenetre;
    private AlertePeremption alertePeremption;
    private ListModel<Aliment> aliments;

    int nbJours;

    public AlertePeremptionControleur(Fenetre fenetre, AlertePeremption alertePeremption, int nbJours) {
        this.fenetre = fenetre;
        this.alertePeremption = alertePeremption;
        this.nbJours = nbJours;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == alertePeremption.getEnleverButton()) {
            Aliment a = aliments.get(alertePeremption.getAlimentsList().getSelectedIndex());
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
        alertePeremption.getAlimentsList().setModel(aliments);
    }
}
