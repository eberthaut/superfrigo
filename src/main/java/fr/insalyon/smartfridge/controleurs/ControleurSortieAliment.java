package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.utilitaires.*;
import fr.insalyon.smartfridge.vues.VueSortieAliment;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurSortieAliment implements ActionListener, ListSelectionListener {
    // On decouple la recuperation des actions des vues
    private Fenetre fenetre;
    private VueSortieAliment vue;
    private fr.insalyon.smartfridge.utilitaires.ListModel<Aliment> aliments;

    public ControleurSortieAliment(Fenetre fenetre, VueSortieAliment vue) {
        this.fenetre = fenetre;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getEnleverButton()) {
            Aliment a = aliments.get(vue.getAlimentsList().getSelectedIndex()); // Recupere l'article selectionne dans la liste (en bleu)
            ServiceStock.retraitAliment(a, (Integer) vue.getQuantiteSpinner().getValue());
            rafraichirListe();
        }
    }

    public void rafraichirListe() {
        aliments = new fr.insalyon.smartfridge.utilitaires.ListModel<Aliment>(ServiceStock.listerAliments());
        vue.getAlimentsList().setModel(aliments);
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        int i = vue.getAlimentsList().getSelectedIndex();
        if(i >= 0) {
            Aliment a = aliments.get(i);
            vue.getQuantiteSpinner().setModel(new SpinnerNumberModel(a.getQuantite(), 1, a.getQuantite(), 1));
        }
    }
}
