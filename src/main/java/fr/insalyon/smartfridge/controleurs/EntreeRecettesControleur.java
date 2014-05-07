package fr.insalyon.smartfridge.controleurs;


import fr.insalyon.smartfridge.modeles.*;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.EntreeArticles;
import fr.insalyon.smartfridge.vues.EntreeRecettes;
import fr.insalyon.smartfridge.vues.Fenetre;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fannygallais on 20/04/2014.
 */
public class EntreeRecettesControleur implements ActionListener, ListSelectionListener {
    // On decouple la recuperation des actions des vues
    Fenetre fenetre;
    EntreeRecettes entreeRecettes;
    ListModel<Article> articles;
    ListModel<Ingredient> ingredients = new ListModel<Ingredient>();

    public EntreeRecettesControleur(Fenetre fenetre, EntreeRecettes entreeRecettes) {
        this.fenetre = fenetre;
        this.entreeRecettes = entreeRecettes;
        articles = new ListModel<Article>(ServiceStock.listerArticles());
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == entreeRecettes.getAjouterIngredientButton()) {
            Article a = articles.get(entreeRecettes.getArticlesList().getSelectedIndex());
            int quantite = (Integer)entreeRecettes.getQuantiteSpinner().getValue();
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
        } else if(e.getSource() == entreeRecettes.getValiderButton()) {
            String nom = entreeRecettes.getNomRecetteTexte().getText();
            if(nom.equals("")){
                // TODO: error dialog
            } else if(ingredients.getSize() == 0){
                // TODO: error dialog
            } else {
                ServiceCourses.ajoutRecette(nom, ingredients.getList());
                fenetre.retourArriere();
            }
        } else if(e.getSource() == entreeRecettes.getSuprrimerIngredientButton()){
            int idx = entreeRecettes.getIngredientsList().getSelectedIndex();
            Ingredient ing = ingredients.get(idx);
            int quantite = (Integer)entreeRecettes.getQuantiteSpinner().getValue();
            if(ing.getQuantite() == quantite) {
                ingredients.remove(idx);
            } else {
                ing.setQuantite(ing.getQuantite() - quantite);
            }
            rafraichirListe();
        } else if(e.getSource() == entreeRecettes.getEffacerButton()){
            ingredients.clear();
            rafraichirListe();
        }
    }


    public void rafraichirListe() {
        entreeRecettes.getIngredientsList().setModel(ingredients);
    }

    public void creerListe() {
        entreeRecettes.getIngredientsList().setModel(ingredients);
        entreeRecettes.getArticlesList().setModel(articles);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if(e.getSource() == entreeRecettes.getIngredientsList()) {
            int idx = entreeRecettes.getIngredientsList().getSelectedIndex();
            if(idx >= 0) {
                Ingredient ing = ingredients.get(idx);
                entreeRecettes.getQuantiteSpinner().setValue(ing.getQuantite());
            }
        } else if(e.getSource() == entreeRecettes.getArticlesList()) {
            entreeRecettes.getQuantiteSpinner().setValue(1);
        }
    }
}

