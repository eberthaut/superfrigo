package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Ingredient;
import fr.insalyon.smartfridge.modeles.Recette;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.vues.VueEntreeRecettes;
import fr.insalyon.smartfridge.vues.VueImportRecette;
import fr.insalyon.smartfridge.vues.VueMenuRecettes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Gere l'ensemble des recettes */
public class ControleurMenuRecettes implements ActionListener, ListSelectionListener, Rafraichissable {
    /** La fenetre de l'application */
    private final Fenetre fenetre;
    /** La vue */
    private final VueMenuRecettes vue;
    /** Le modele des recettes */
    private ListModel<Recette> recettes;
    /** Le modele des ingredients contenus dans la recette */
    private ListModel<Ingredient> ingredients = new ListModel<Ingredient>();

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     * @param vue La vue
     */
    public ControleurMenuRecettes(Fenetre fenetre, VueMenuRecettes vue) {
        this.fenetre = fenetre;
        this.vue = vue;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getToggleButton()) {
            actionToggle();
        } else if(e.getSource().equals(vue.getAjouterButton())) {
            actionAjouter();
        } else if(e.getSource().equals(vue.getSupprimerButton())) {
            actionSupprimmer();
        } else if(e.getSource().equals(vue.getImportButton())) {
            actionImport();
        }
    }


    @Override
    public void mettreAJour() {
        recettes = new ListModel<Recette>(ServiceCourses.listerRecettes());
        vue.getRecettesList().setModel(recettes);
        vue.getIngredientsList().setModel(ingredients);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        selectionRecette();
    }

    /** Quand on clique sur le toggle */
    private void actionToggle() {
        Recette r = recettes.get(vue.getRecettesList().getSelectedIndex());

        if(r.isActif()) {
            ServiceCourses.desactiverRecette(r);
            vue.getToggleButton().setText("Activer");
            vue.getToggleButton().setIcon(Raccourcis.icone("ok"));
        } else {
            int pour = 0;
            while(pour < 1) {
                pour = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le nombre de personnes a activer.", "1"));
            }
            ServiceCourses.activerRecette(r, pour);
            vue.getToggleButton().setText("Desactiver");
            vue.getToggleButton().setIcon(Raccourcis.icone("desactiver"));
        }
        mettreAJour();
    }

    /** Quand on clique sur ajouter */
    private void actionAjouter() {
        fenetre.allerA(new VueEntreeRecettes(fenetre));
    }

    /** Quand on clique sur supprimmer */
    private void actionSupprimmer() {
        Recette r = recettes.get(vue.getRecettesList().getSelectedIndex());
        ServiceCourses.retraitRecette(r.getNom());
        mettreAJour();
    }

    /** Quand on clique sur import */
    private void actionImport() {
        fenetre.allerA(new VueImportRecette(fenetre));
    }

    /** Quand on selectionne une recette */
    private void selectionRecette() {
        if(vue.getRecettesList().isSelectionEmpty()){
            ingredients.removeAllElements();
            vue.getIngredientsList().setModel(ingredients);
        } else {
            int i = vue.getRecettesList().getSelectedIndex();
            Recette r = recettes.get(i);
            this.ingredients = new ListModel<Ingredient>(ServiceCourses.listerIngredients(r));
            vue.getIngredientsList().setModel(ingredients);
            if(r.isActif()) {
                vue.getToggleButton().setText("Desactiver");
                vue.getToggleButton().setIcon(new ImageIcon(getClass().getResource("/icones/desactiver.png")));
                vue.getEtatLabel().setText(Raccourcis.html(
                    "Recette prevue pour "
                    + r.getPrevuPour()
                    + " personne(s).<br/>"
                    + "Activee pour "
                    + r.getActivePour()
                    + " personne(s)."));
            } else {
                vue.getToggleButton().setText("Activer");
                vue.getToggleButton().setIcon(new ImageIcon(getClass().getResource("/icones/ok.png")));
                vue.getEtatLabel().setText(
                    "Recette prevue pour "
                    + r.getPrevuPour()
                    + " personne(s).");
            }
        }
    }
}

