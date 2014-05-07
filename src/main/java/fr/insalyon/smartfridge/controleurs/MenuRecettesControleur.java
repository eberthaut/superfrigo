package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Recette;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.MenuRecettes;
import fr.insalyon.smartfridge.vues.Fenetre;
import fr.insalyon.smartfridge.vues.EntreeRecettes;
import org.eclipse.persistence.eis.RecordConverter;
import org.eclipse.persistence.internal.libraries.asm.tree.analysis.Value;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by agabriel on 17/04/14.
 */
public class MenuRecettesControleur implements ActionListener, ListSelectionListener {

    Fenetre fenetre;
    MenuRecettes menuRecettes;
    ListModel recettes;

    public MenuRecettesControleur(Fenetre fenetre, MenuRecettes menuRecettes) {
        this.fenetre = fenetre;
        this.menuRecettes = menuRecettes;
        recettes = new ListModel(ServiceCourses.listerRecettes());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuRecettes.getToggleButton()) {
            Recette r = recettes.getRecettesAt(menuRecettes.getRecettesList().getSelectedIndex());

            if(r.isActif()) {
                ServiceCourses.desactiverRecette(r);
                menuRecettes.getToggleButton().setText("Activer");
            } else {
                ServiceCourses.activerRecette(r);
                menuRecettes.getToggleButton().setText("Desactiver");
            }
            rafraichirListe();
        } else if(e.getSource().equals(menuRecettes.getAjouterButton())) {
            fenetre.allerA(new EntreeRecettes(fenetre));
        }else if(e.getSource().equals(menuRecettes.getSupprimerButton())) {
            Recette r = recettes.getRecettesAt(menuRecettes.getRecettesList().getSelectedIndex());
            ServiceCourses.retraitRecette(r.getNom());
            rafraichirListe();
        }
    }

    public void creerListe() {
        menuRecettes.getRecettesList().setModel(recettes);
    }


    public void rafraichirListe() {
        recettes = new ListModel(ServiceCourses.listerRecettes());
        menuRecettes.getRecettesList().setModel(recettes);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int i = menuRecettes.getRecettesList().getSelectedIndex();
        if(i >= 0) {
            Recette r = recettes.getRecettesAt(i);
            if(r.isActif()) {
                menuRecettes.getToggleButton().setText("Desactiver");
            } else {
                menuRecettes.getToggleButton().setText("Activer");
            }
        }
    }

    private class ListModel extends AbstractListModel {
        List<Recette> recettes;

        public ListModel(List<Recette> recettes) {
            this.recettes = recettes;
        }

        @Override // demande par la JList (on doit implementer un AbstractListModel)
        public int getSize() {
            return recettes.size();
        }

        @Override
        public Object getElementAt(int i) {
            String checkbox = "[ ] ";
            if(recettes.get(i).isActif()) {
                checkbox = "[x] ";
            }
            return checkbox + recettes.get(i).getNom();
        }

        public Recette getRecettesAt(int i) {
            return recettes.get(i);
        }
    }

}

