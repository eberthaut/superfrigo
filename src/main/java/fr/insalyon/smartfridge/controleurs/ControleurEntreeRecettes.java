package fr.insalyon.smartfridge.controleurs;


import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Ingredient;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.utilitaires.*;
import fr.insalyon.smartfridge.vues.VueEntreeRecettes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by fannygallais on 20/04/2014.
 */
public class ControleurEntreeRecettes implements ActionListener, ListSelectionListener {
    // On decouple la recuperation des actions des vues
    private Fenetre fenetre;
    private VueEntreeRecettes vue;
    private fr.insalyon.smartfridge.utilitaires.ListModel<Article> articles;
    private fr.insalyon.smartfridge.utilitaires.ListModel<Ingredient> ingredients = new fr.insalyon.smartfridge.utilitaires.ListModel<Ingredient>();

    public ControleurEntreeRecettes(Fenetre fenetre, VueEntreeRecettes vue) {
        this.fenetre = fenetre;
        this.vue = vue;
        articles = new fr.insalyon.smartfridge.utilitaires.ListModel<Article>(ServiceStock.listerArticles());
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getAjouterIngredientButton()) {
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
            rafraichirListe();
        } else if(e.getSource() == vue.getValiderButton()) {
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
        } else if(e.getSource() == vue.getSuprrimerIngredientButton()){
            int idx = vue.getIngredientsList().getSelectedIndex();
            Ingredient ing = ingredients.get(idx);
            int quantite = (Integer) vue.getQuantiteSpinner().getValue();
            if(ing.getQuantite() == quantite) {
                ingredients.remove(idx);
            } else {
                ing.setQuantite(ing.getQuantite() - quantite);
            }
            rafraichirListe();
        } else if(e.getSource() == vue.getEffacerButton()){
            ingredients.clear();
            rafraichirListe();
        }
    }


    public void rafraichirListe() {
        vue.getIngredientsList().setModel(ingredients);
    }

    public void creerListe() {
        vue.getIngredientsList().setModel(ingredients);
        vue.getArticlesList().setModel(articles);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource() == vue.getIngredientsList()) {
            int idx = vue.getIngredientsList().getSelectedIndex();
            if(idx >= 0) {
                Ingredient ing = ingredients.get(idx);
                vue.getQuantiteSpinner().setModel(new SpinnerNumberModel(ing.getQuantite(), 1, ing.getQuantite(), 1));
            }
        } else if(e.getSource() == vue.getArticlesList()) {
            vue.getQuantiteSpinner().setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        }
    }
}

