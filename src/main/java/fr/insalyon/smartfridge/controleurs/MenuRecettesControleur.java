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
    ListModel<Recette> recettes;

    public MenuRecettesControleur(Fenetre fenetre, MenuRecettes menuRecettes) {
        this.fenetre = fenetre;
        this.menuRecettes = menuRecettes;
        recettes = new ListModel(ServiceCourses.listerRecettes());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuRecettes.getToggleButton()) {
            Recette r = recettes.get(menuRecettes.getRecettesList().getSelectedIndex());

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
            Recette r = recettes.get(menuRecettes.getRecettesList().getSelectedIndex());
            ServiceCourses.retraitRecette(r.getNom());
            rafraichirListe();
        }
    }

    public void creerListe() {
        menuRecettes.getRecettesList().setModel(recettes);
    }


    public void rafraichirListe() {
        recettes = new ListModel<Recette>(ServiceCourses.listerRecettes());
        menuRecettes.getRecettesList().setModel(recettes);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int i = menuRecettes.getRecettesList().getSelectedIndex();
        if(i >= 0) {
            Recette r = recettes.get(i);
            if(r.isActif()) {
                menuRecettes.getToggleButton().setText("Desactiver");
            } else {
                menuRecettes.getToggleButton().setText("Activer");
            }
        }
    }

}

