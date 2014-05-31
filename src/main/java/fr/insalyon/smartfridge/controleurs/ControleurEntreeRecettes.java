package fr.insalyon.smartfridge.controleurs;


import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Ingredient;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.utilitaires.*;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.vues.VueEntreeRecettes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Gere la saisie d'une recette */
public class ControleurEntreeRecettes implements ActionListener, ListSelectionListener, Rafraichissable {
    /** La fenetre de l'application */
    private final Fenetre fenetre;
    /** La vue */
    private final VueEntreeRecettes vue;
    /** Le modele des articles disponibles */
    private ListModel<Article> articles;
    /** Le modele des ingredients ajoutes */
    private final ListModel<Ingredient> ingredients = new ListModel<Ingredient>();

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     * @param vue La vue
     */
    public ControleurEntreeRecettes(Fenetre fenetre, VueEntreeRecettes vue) {
        this.fenetre = fenetre;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getAjouterIngredientButton()) {
            actionAjouter();
        } else if(e.getSource() == vue.getValiderButton()) {
            actionValider();
        } else if(e.getSource() == vue.getSuprrimerIngredientButton()){
            actionSupprimmer();
        } else if(e.getSource() == vue.getEffacerButton()){
            actionEffacer();
        }
    }

    @Override
    public void mettreAJour() {
        articles = new ListModel<Article>(ServiceStock.listerArticles());
        vue.getArticlesList().setModel(articles);
        vue.getIngredientsList().setModel(ingredients);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource() == vue.getIngredientsList()) {
            selectionIngredient();
        } else if(e.getSource() == vue.getArticlesList()) {
            selectionArticle();
        }
    }

    /** Quand on clique sur ajouter */
    private void actionAjouter() {
        Article a = articles.get(vue.getArticlesList().getSelectedIndex());
        int quantite = (Integer) vue.getQuantiteSpinner().getValue();
        boolean trouve = false;
        for(Ingredient ancien : ingredients.getList()) {
            if(ancien.getArticle() == a) {
                ancien.setQuantite(ancien.getQuantite() + quantite);
                trouve = true;
                break;
            }
        }
        if(!trouve) {
            ingredients.addElement(new Ingredient(a, quantite));
        }
        mettreAJour();
    }

    /** Quand on clique sur valider */
    private void actionValider() {
        String nom = vue.getNomRecetteTexte().getText();
        int pour = (Integer) vue.getPourSpinner().getValue();
        if(nom.equals("")) {
            JOptionPane.showMessageDialog(null, "Entrez un nom de recette !", "Invalide !",
                    JOptionPane.ERROR_MESSAGE);
        } else if(pour < 1){
            JOptionPane.showMessageDialog(null, "Donnez le nombre de personnes correspondant à la recette !", "Invalide !",
                    JOptionPane.ERROR_MESSAGE);
        } else if(ingredients.getSize() == 0){
            JOptionPane.showMessageDialog(null, "Ajoutez des ingredients !", "Invalide !",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            ServiceCourses.ajoutRecette(nom, pour, ingredients.getList());
            fenetre.retourArriere();
        }
    }

    /** Quand on clique sur supprimmer */
    private void actionSupprimmer() {
        int idx = vue.getIngredientsList().getSelectedIndex();
        Ingredient ing = ingredients.get(idx);
        int quantite = (Integer) vue.getQuantiteSpinner().getValue();
        if(ing.getQuantite() == quantite) {
            ingredients.remove(idx);
        } else {
            ing.setQuantite(ing.getQuantite() - quantite);
        }
        mettreAJour();
    }

    /** Quand on clique sur effacer */
    private void actionEffacer() {
        ingredients.clear();
        mettreAJour();
    }

    /** Quand on selectionne un ingredient */
    private void selectionIngredient() {
        int idx = vue.getIngredientsList().getSelectedIndex();
        if(idx >= 0) {
            Ingredient ing = ingredients.get(idx);
            vue.getQuantiteSpinner().setModel(new SpinnerNumberModel(ing.getQuantite(), 1, ing.getQuantite(), 1));
        }
    }

    /** Quand on selectionne dans un article */
    private void selectionArticle() {
        vue.getQuantiteSpinner().setModel(new SpinnerNumberModel(1, 1, 1000, 1));
    }

}

