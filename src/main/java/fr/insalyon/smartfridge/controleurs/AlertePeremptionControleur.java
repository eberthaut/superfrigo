package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.services.ServiceAlerte;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.AlertePeremption;
import fr.insalyon.smartfridge.vues.Fenetre;

import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AlertePeremptionControleur implements ActionListener {
    // On decouple la recuperation des actions des vues
    Fenetre fenetre;
    AlertePeremption alertePeremption;
    ListModel<Aliment> aliments;

    public AlertePeremptionControleur(Fenetre fenetre, AlertePeremption alertePeremption) {
        this.fenetre = fenetre;
        this.alertePeremption = alertePeremption;
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
        if(ServiceAlerte.statusAlerte(2) == 1) {
            liste = ServiceAlerte.listeAlimentsProchePeremption(2);
        } else {
            liste = ServiceAlerte.listeAlimentsPerimes();
        }
        aliments = new ListModel<Aliment>(liste);
        alertePeremption.getAlimentsList().setModel(aliments);
    }
}