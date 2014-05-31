package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Ingredient;
import fr.insalyon.smartfridge.modeles.Recette;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.utilitaires.*;
import fr.insalyon.smartfridge.vues.VueEntreeRecettes;
import fr.insalyon.smartfridge.vues.VueImportRecette;
import fr.insalyon.smartfridge.vues.VueMenuRecettes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by agabriel on 17/04/14.
 */
public class ControleurMenuRecettes implements ActionListener, ListSelectionListener {

    private Fenetre fenetre;
    private VueMenuRecettes vue;
    private fr.insalyon.smartfridge.utilitaires.ListModel<Recette> recettes;
    private fr.insalyon.smartfridge.utilitaires.ListModel<Ingredient> ingredients = new fr.insalyon.smartfridge.utilitaires.ListModel<Ingredient>();


    public ControleurMenuRecettes(Fenetre fenetre, VueMenuRecettes vue) {
        this.fenetre = fenetre;
        this.vue = vue;
        recettes = new fr.insalyon.smartfridge.utilitaires.ListModel(ServiceCourses.listerRecettes());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getToggleButton()) {
            Recette r = recettes.get(vue.getRecettesList().getSelectedIndex());

            if(r.isActif()) {
                ServiceCourses.desactiverRecette(r);
                vue.getToggleButton().setText("Activer");
                vue.getToggleButton().setIcon(new ImageIcon(getClass().getResource("/icones/ok.png")));
            } else {
                int pour = 0;
                while(pour < 1) {
                   pour = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le nombre de personnes a activer.", "1"));
                }
                ServiceCourses.activerRecette(r, pour);
                vue.getToggleButton().setText("Desactiver");
                vue.getToggleButton().setIcon(new ImageIcon(getClass().getResource("/icones/desactiver.png")));
            }
            rafraichirListeRecette();
        } else if(e.getSource().equals(vue.getAjouterButton())) {
            fenetre.allerA(new VueEntreeRecettes(fenetre));
        } else if(e.getSource().equals(vue.getSupprimerButton())) {
            Recette r = recettes.get(vue.getRecettesList().getSelectedIndex());
            ServiceCourses.retraitRecette(r.getNom());
            rafraichirListeRecette();
        } else if(e.getSource().equals(vue.getImportButton())) {
            fenetre.allerA(new VueImportRecette(fenetre));
        }
    }

    public void creerListe() {
        vue.getIngredientsList().setModel(ingredients);
        vue.getRecettesList().setModel(recettes);
    }


    public void rafraichirListeRecette() {
        recettes = new fr.insalyon.smartfridge.utilitaires.ListModel<Recette>(ServiceCourses.listerRecettes());
        vue.getRecettesList().setModel(recettes);

    }

    public void rafraichirListeIngredients(){
        vue.getIngredientsList().setModel(ingredients);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(vue.getRecettesList().isSelectionEmpty()){
            ingredients.removeAllElements();
            rafraichirListeIngredients();
        }
        else {
            int i = vue.getRecettesList().getSelectedIndex();
            Recette r = recettes.get(i);
            this.ingredients = new fr.insalyon.smartfridge.utilitaires.ListModel(ServiceCourses.listerIngredients(r));
            rafraichirListeIngredients();
            if(r.isActif()) {
                vue.getToggleButton().setText("Desactiver");
                vue.getToggleButton().setIcon(new ImageIcon(getClass().getResource("/icones/desactiver.png")));
            } else {
                vue.getToggleButton().setText("Activer");
                vue.getToggleButton().setIcon(new ImageIcon(getClass().getResource("/icones/ok.png")));
            }

        }
    }

}

