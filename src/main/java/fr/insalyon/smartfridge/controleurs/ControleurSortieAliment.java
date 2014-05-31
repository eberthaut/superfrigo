package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.vues.VueSortieAliment;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Gere la sortie des aliments */
public class ControleurSortieAliment implements ActionListener, ListSelectionListener, Rafraichissable {
    /** La vue */
    private final VueSortieAliment vue;
    /** La modele des aliments */
    private ListModel<Aliment> aliments;

    /** Constructeur
     *
     * @param vue La vue
     */
    public ControleurSortieAliment(VueSortieAliment vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getEnleverButton()) {
            actionEnlever();
        }
    }

    @Override
    public void mettreAJour() {
        aliments = new ListModel<Aliment>(ServiceStock.listerAliments());
        vue.getAlimentsList().setModel(aliments);
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        selectionAliment();
    }

    /** Quand on clique sur enlever */
    private void actionEnlever() {
        Aliment a = aliments.get(vue.getAlimentsList().getSelectedIndex()); // Recupere l'article selectionne dans la liste (en bleu)
        ServiceStock.retraitAliment(a, (Integer) vue.getQuantiteSpinner().getValue());
        mettreAJour();
    }

    /** Quand on selectionne un aliment */
    private void selectionAliment() {
        int i = vue.getAlimentsList().getSelectedIndex();
        if(i >= 0) {
            Aliment a = aliments.get(i);
            vue.getQuantiteSpinner().setModel(new SpinnerNumberModel(a.getQuantite(), 1, a.getQuantite(), 1));
        }
    }
}
