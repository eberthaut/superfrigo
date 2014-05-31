package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.vues.VueEntreeArticles;
import fr.insalyon.smartfridge.vues.VueEntreeCategories;
import fr.insalyon.smartfridge.utilitaires.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurEntreeCategories implements ActionListener {
    // On decouple la recuperation des actions des vues
    private Fenetre fenetre;
    private VueEntreeCategories vue;
    private ListModel<Type> types;

    public ControleurEntreeCategories(Fenetre fenetre, VueEntreeCategories vue) {
        this.fenetre = fenetre;
        this.vue = vue;
        types = new ListModel<Type>(ServiceStock.listerTypes()); // On récupère tous les types
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getChoixButton()) {
            Type t = types.get(vue.getTypesList().getSelectedIndex());
            fenetre.allerA(new VueEntreeArticles(fenetre, t));
        }
    }

    public void creerListe() {
        vue.getTypesList().setModel(types);
    }
}
