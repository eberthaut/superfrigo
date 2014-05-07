package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Ingredient;
import fr.insalyon.smartfridge.modeles.Recette;
import fr.insalyon.smartfridge.modeles.dao.ArticleDAO;
import fr.insalyon.smartfridge.modeles.dao.BaseDAO;
import fr.insalyon.smartfridge.modeles.dao.IngredientDAO;
import fr.insalyon.smartfridge.modeles.dao.RecetteDAO;

import java.util.*;

/**
 * Service de gestion des habitudes dans le frigo.
 */
public class ServiceCourses {
    public static boolean ajoutRecette(String nom, List<Ingredient> ingredients) {
        BaseDAO.initialiserPersistence();
        Recette recette = new Recette(nom, ingredients);

        BaseDAO.creerTransaction();
        RecetteDAO.persiste(recette);
        for(Ingredient ingredient : ingredients) {
            ingredient.setRecette(recette);
            IngredientDAO.persiste(ingredient);
        }
        BaseDAO.faireTransaction();

        BaseDAO.detruirePersistence();
        return true;
    }

    public static boolean retraitRecette(String nom) {
        BaseDAO.initialiserPersistence();
        Recette recette = RecetteDAO.trouveNom(nom);

        BaseDAO.creerTransaction();
        for(Ingredient ingredient : recette.getIngredients()) {
            IngredientDAO.supprime(ingredient);
        }
        RecetteDAO.supprime(recette);
        BaseDAO.faireTransaction();

        BaseDAO.detruirePersistence();
        return true;
    }


    public static boolean changerHabitude(Article article, int habitude) {
        BaseDAO.initialiserPersistence();


        article.setHabitude(habitude);

        BaseDAO.creerTransaction();
        ArticleDAO.miseAJour(article);
        BaseDAO.faireTransaction();

        BaseDAO.detruirePersistence();
        return true;
    }


    public static boolean augmenterHabitude(Article article) {
        BaseDAO.initialiserPersistence();
        int modificationHabitude = 1;

        // TODO : Faire un algorithme

        article.setHabitude(article.getHabitude() + modificationHabitude);

        BaseDAO.creerTransaction();
        ArticleDAO.miseAJour(article);
        BaseDAO.faireTransaction();

        BaseDAO.detruirePersistence();
        return true;
    }

    public static boolean decrementerHabitude(Article article) {
        BaseDAO.initialiserPersistence();
        int modificationHabitude = 1;

        // TODO : Faire un algorithme

        article.setHabitude(article.getHabitude() - modificationHabitude);

        BaseDAO.creerTransaction();
        ArticleDAO.miseAJour(article);
        BaseDAO.faireTransaction();

        BaseDAO.detruirePersistence();
        return true;
    }

    public static List<Recette> listerRecettes() {
        BaseDAO.initialiserPersistence();
        List<Recette> recettes = RecetteDAO.tous();
        BaseDAO.detruirePersistence();
        return recettes;
    }

    public static boolean activerRecette(Recette recette) {

        if(recette.isActif()) {
            return false;
        } else {
            recette.setActivite(true);
            BaseDAO.initialiserPersistence();

            BaseDAO.creerTransaction();
            RecetteDAO.miseAJour(recette);
            BaseDAO.faireTransaction();
            BaseDAO.detruirePersistence();

            return true;
        }
    }

    public static boolean desactiverRecette(Recette recette) {
        if(! recette.isActif()) {
            return false;
        } else {
            recette.setActivite(false);

            BaseDAO.initialiserPersistence();

            BaseDAO.creerTransaction();
            RecetteDAO.miseAJour(recette);
            BaseDAO.faireTransaction();
            BaseDAO.detruirePersistence();

            return true;
        }
    }

    public static double calculPrixListe (List<Article> listeCourses) {
        BaseDAO.initialiserPersistence();
        double prixListe=0;
        for(Article article : listeCourses) {
            prixListe += article.getPrix();
        }

        BaseDAO.detruirePersistence();
        return prixListe;
    }

    public static List<Article> genererListeCourses () {
        BaseDAO.initialiserPersistence();
        List<Article> listeCourses = new ArrayList<Article>();

        List<Article> listeTriee = ArticleDAO.tousTriesParHabitude();
        // TODO: faire l'algo
        listeCourses= ArticleDAO.transformationAliment();
        List<Article> lHabitude = new ArrayList<Article>();
        /*lHabitude = ArticleDAO.traitementHabitude();
        for(int i=0; i<lHabitude.size(); i++ ){
            Article a = lHabitude.get(i);
            System.out.println(a.getNom());
            listeCourses.add(0,a);
        }*/
        BaseDAO.detruirePersistence();
        return listeCourses;
    }



}
