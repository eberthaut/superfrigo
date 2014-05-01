package fr.insalyon.smartfridge.controleurs;


import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.EntreeArticles;
import fr.insalyon.smartfridge.vues.EntreeRecettes;
import fr.insalyon.smartfridge.vues.Fenetre;
import fr.insalyon.smartfridge.modeles.Recette;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fannygallais on 20/04/2014.
 */
public class EntreeRecettesControleur implements ActionListener{
    // On decouple la recuperation des actions des vues
    Fenetre fenetre;
    EntreeRecettes entreeRecettes;
    ListModel articles;
    DefaultListModel ingredientsListModel = new DefaultListModel();
    ListModel ingredients = new ListModel();

    public EntreeRecettesControleur(Fenetre fenetre, EntreeRecettes entreeRecettes) {
        this.fenetre = fenetre;
        this.entreeRecettes = entreeRecettes;
        articles = new ListModel(ServiceStock.listerArticles());
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == entreeRecettes.getRetourButton()) {
            fenetre.retourArriere();
        } else if(e.getSource() == entreeRecettes.getAjouterIngredientButton()) {
            Article a = articles.getArticleAt(entreeRecettes.getArticlesList().getSelectedIndex());
            if(ingredients.presence(a)){
                entreeRecettes.setAvancementAjout("Vous ne pouvez pas ajouter deux fois le même ingrégient");
            } else {
                ingredients.addArticle(a);
                this.transfertList();
                rafraichirListe();
            }
        } else if(e.getSource() == entreeRecettes.getValiderButton()) {
            String nom = entreeRecettes.getNomRecetteTexte().getText();
            if(nom.equals("")){
                entreeRecettes.setAvancementAjout("Vous devez donner un nom à la recette");
            } else if(ingredients.getSize() == 0){
                entreeRecettes.setAvancementAjout("Vous devez ajouter des ingrédients à la recette");
            } else {
                ServiceCourses.ajoutRecette(nom, ingredients.getList());
                entreeRecettes.setAvancementAjout("La recette " + nom + " a bien été ajoutée! Retournez au menu principal");
            }
        } else if(e.getSource() == entreeRecettes.getSuprrimerIngredientButton()){
            Article a = articles.getArticleAt(entreeRecettes.getArticlesList().getSelectedIndex());
            ingredients.supprimerArticle(a);
            supprimerElementList();
        } else if(e.getSource() == entreeRecettes.getEffacerButton()){
            supprimerList();
            ingredients.supprimerList();
            entreeRecettes.setAvancementAjout("Recette en cours d'ajout... Cliquez sur valider pour ajouter la recette!");
        }
    }


    public void rafraichirListe() {
        entreeRecettes.getIngredientsList().setModel(ingredientsListModel);
    }

    public void transfertList() {
        String item = (String) entreeRecettes.getArticlesList().getSelectedValue();
        ingredientsListModel.addElement(item);
    }

    public void supprimerElementList() {
        String item = (String) entreeRecettes.getIngredientsList().getSelectedValue();
        ingredientsListModel.removeElement(item);
    }


    public void supprimerList(){
        ingredientsListModel.removeAllElements();
    }


    public void creerListe() {
        entreeRecettes.getArticlesList().setModel(articles);
    }



    private class ListModel extends AbstractListModel {
        List<Article> list;

        public ListModel(List<Article> articles) {
            this.list = articles;
        }

        public ListModel(){ this.list = new ArrayList<Article>();}

        @Override // demande par la JList (on doit implementer un AbstractListModel)
        public int getSize() {
            return list.size();
        }

        public List getList() {return list;}

        @Override
        public Object getElementAt(int i) {
            return list.get(i).getNom();
        }



        public Article getArticleAt(int i) {
            return list.get(i);
        }


        public void addArticle(Article a){ list.add(a);}


        public void supprimerArticle(Article a){ list.remove(a);}


        public void supprimerList(){ list.clear();}


        public void getIngredients(){ //
            for(int i=0 ; i<list.size(); i++){
                Article a = list.get(i);
                System.out.println(a.getNom());
            }
        }


        public boolean presence(Article a){
            boolean presence = false;
            for (int i=0 ; i<list.size() ; i++){
                if(list.get(i).equals(a)){
                    presence = true;
                }
            }
            return presence;
        }


    }

}

