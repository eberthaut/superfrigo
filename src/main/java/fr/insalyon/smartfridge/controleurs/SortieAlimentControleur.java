package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.Fenetre;
import fr.insalyon.smartfridge.vues.SortieAliment;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SortieAlimentControleur implements ActionListener, ListSelectionListener {
    // On decouple la recuperation des actions des vues
    Fenetre fenetre;
    SortieAliment sortieAliment;
    ListModel aliments;

    public SortieAlimentControleur(Fenetre fenetre, SortieAliment sortieAliment) {
        this.fenetre = fenetre;
        this.sortieAliment = sortieAliment;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sortieAliment.getEnleverButton()) {
            Aliment a = aliments.getAlimentAt(sortieAliment.getAlimentsList().getSelectedIndex()); // Recupere l'article selectionne dans la liste (en bleu)
            ServiceStock.retraitAliment(a, (Integer)sortieAliment.getQuantiteSpinner().getValue());
            rafraichirListe();
        }
    }

    public void rafraichirListe() {
        aliments = new ListModel(ServiceStock.listerAliments());
        sortieAliment.getAlimentsList().setModel(aliments);
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        int i = sortieAliment.getAlimentsList().getSelectedIndex();
        if(i > 0) {
            Aliment a = aliments.getAlimentAt(i);
            sortieAliment.getQuantiteSpinner().setValue(a.getQuantite());
        }
    }

    private class ListModel extends AbstractListModel {
        List<Aliment> aliments;

        public ListModel(List<Aliment> aliments) {
            this.aliments = aliments;
        }

        @Override // demande par la JList (on doit implementer un AbstractListModel)
        public int getSize() {
            return aliments.size();
        }

        @Override
        public Object getElementAt(int i) {
            Aliment a = aliments.get(i);
            return a.getQuantite() + "x " + a.getArticle().getNom();
        }

        public Aliment getAlimentAt(int i) {
            return aliments.get(i);
        }
    }
}
