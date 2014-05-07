package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.EntreeArticles;
import fr.insalyon.smartfridge.vues.EntreeCategories;
import fr.insalyon.smartfridge.vues.Fenetre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EntreeCategoriesControleur implements ActionListener {
    // On decouple la recuperation des actions des vues
    Fenetre fenetre;
    EntreeCategories entreeCategories;
    ListModel types;

    public EntreeCategoriesControleur(Fenetre fenetre, EntreeCategories entreeCategories) {
        this.fenetre = fenetre;
        this.entreeCategories = entreeCategories;
        types = new ListModel(ServiceStock.listerTypes()); // On récupère tous les types
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == entreeCategories.getChoixButton()) {
            Type t = types.getTypeAt(entreeCategories.getTypesList().getSelectedIndex());
            fenetre.allerA(new EntreeArticles(fenetre, t));
        }
    }

    public void creerListe() {
        entreeCategories.getTypesList().setModel(types);
    }

    private class ListModel extends AbstractListModel {
        List<Type> types;

        public ListModel(List<Type> types) {
            this.types = types;
        }

        @Override // demande par la JList (on doit implementer un AbstractListModel)
        public int getSize() {
            return types.size();
        }

        @Override
        public Object getElementAt(int i) {
            return types.get(i).getNom();
        }

        public Type getTypeAt(int i) {
            return types.get(i);
        }
    }
}
