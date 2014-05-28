package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Ingredient;
import fr.insalyon.smartfridge.modeles.Recette;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.vues.EntreeRecettes;
import fr.insalyon.smartfridge.vues.Fenetre;
import fr.insalyon.smartfridge.vues.MenuRecettes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by agabriel on 17/04/14.
 */
public class MenuRecettesControleur implements ActionListener, ListSelectionListener {

    Fenetre fenetre;
    MenuRecettes menuRecettes;
    ListModel<Recette> recettes;
    ListModel<Ingredient> ingredients = new ListModel<Ingredient>();


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
                menuRecettes.getToggleButton().setIcon(new ImageIcon(getClass().getResource("/icones/ok.png")));
            } else {
                ServiceCourses.activerRecette(r);
                menuRecettes.getToggleButton().setText("Desactiver");
                menuRecettes.getToggleButton().setIcon(new ImageIcon(getClass().getResource("/icones/desactiver.png")));
            }
            rafraichirListeRecette();
        } else if(e.getSource().equals(menuRecettes.getAjouterButton())) {
            fenetre.allerA(new EntreeRecettes(fenetre));
        }else if(e.getSource().equals(menuRecettes.getSupprimerButton())) {
            Recette r = recettes.get(menuRecettes.getRecettesList().getSelectedIndex());
            ServiceCourses.retraitRecette(r.getNom());
            rafraichirListeRecette();
        }
    }

    public void creerListe() {
        menuRecettes.getIngredientsList().setModel(ingredients);
        menuRecettes.getRecettesList().setModel(recettes);
    }


    public void rafraichirListeRecette() {
        recettes = new ListModel<Recette>(ServiceCourses.listerRecettes());
        menuRecettes.getRecettesList().setModel(recettes);

    }

    public void rafraichirListeIngredients(){
        menuRecettes.getIngredientsList().setModel(ingredients);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int i = menuRecettes.getRecettesList().getSelectedIndex();
        if(i >= 0) {
            Recette r = recettes.get(i);
            ListModel ingredients = new ListModel(ServiceCourses.listerIngredients(r)) ;
            this.ingredients = ingredients;
            rafraichirListeIngredients();
            if(r.isActif()) {
                menuRecettes.getToggleButton().setText("Desactiver");
                menuRecettes.getToggleButton().setIcon(new ImageIcon(getClass().getResource("/icones/desactiver.png")));
            } else {
                menuRecettes.getToggleButton().setText("Activer");
                menuRecettes.getToggleButton().setIcon(new ImageIcon(getClass().getResource("/icones/ok.png")));
            }

        }
    }

}

