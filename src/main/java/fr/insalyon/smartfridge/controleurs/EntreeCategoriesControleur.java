package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.EntreeArticles;
import fr.insalyon.smartfridge.vues.EntreeCategories;
import fr.insalyon.smartfridge.vues.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntreeCategoriesControleur implements ActionListener {
    // On decouple la recuperation des actions des vues
    Fenetre fenetre;
    EntreeCategories entreeCategories;
    ListModel<Type> types;

    public EntreeCategoriesControleur(Fenetre fenetre, EntreeCategories entreeCategories) {
        this.fenetre = fenetre;
        this.entreeCategories = entreeCategories;
        types = new ListModel<Type>(ServiceStock.listerTypes()); // On récupère tous les types
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == entreeCategories.getChoixButton()) {
            Type t = types.get(entreeCategories.getTypesList().getSelectedIndex());
            fenetre.allerA(new EntreeArticles(fenetre, t));
        }
    }

    public void creerListe() {
        entreeCategories.getTypesList().setModel(types);
    }
}
