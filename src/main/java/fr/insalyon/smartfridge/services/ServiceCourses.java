package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Ingredient;
import fr.insalyon.smartfridge.modeles.Recette;
import fr.insalyon.smartfridge.modeles.dao.*;

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

    public static List<Aliment> genererListeCourses() {
        BaseDAO.initialiserPersistence();

        List<Aliment> lCourses = new ArrayList<Aliment>();
        List<Aliment> lAliments = AlimentDAO.tous();
        List<Article> listeArticle = ArticleDAO.tous();
        for (int i = 0; i < lAliments.size(); i++) {
            long id = lAliments.get(i).getArticle().getId();
            for (int j = 0; j < listeArticle.size(); j++) {
                if (id == listeArticle.get(j).getId()) {
                    Article a = listeArticle.get(j);
                    //System.out.println(a.getNom());
                    if (lAliments.get(i).getQuantite() < a.getHabitude()) {
                        lCourses.add(new Aliment(a, 1));
                        //System.out.println(lArticle.get(0).getNom());
                    }
                }
            }
        }

        List<Article> lArticleAbsentDuFrigo = ArticleDAO.tous();
        for(int e= 0; e<lArticleAbsentDuFrigo.size();e++) {
            Article b = lArticleAbsentDuFrigo.get(e);
            for (int g = 0; g < lCourses.size(); g++) {
                Aliment a = lCourses.get(g);
                if (b.equals(a.getArticle())) {
                    System.out.println(b.getNom());
                    lArticleAbsentDuFrigo.remove(e);
                }
            }
        }

        for (int h = 0; h <lArticleAbsentDuFrigo.size(); h++) {
            Article d = lArticleAbsentDuFrigo.get(h);
            if(d.getHabitude() != 0){
                //System.out.println(a.getNom());
                lCourses.add(new Aliment(d, 1));
                //System.out.println(lArticle.get(0).getNom());
            }
        }

        List<Recette> listeRecettes = RecetteDAO.tous();

        // TODO: Mettre a jour pour les ingredients
        for(int p=0; p< listeRecettes.size(); p++){
            if(listeRecettes.get(p).isActif()==true) {
                List<Ingredient> lIng = listeRecettes.get(p).getIngredients();
                for (int q = 0; q < lIng.size(); q++) {
                    Ingredient f = lIng.get(q);
                    lCourses.add(new Aliment(f.getArticle(), f.getQuantite()));
                }
            }
        }

        BaseDAO.detruirePersistence();
        return lCourses;
    }



}
