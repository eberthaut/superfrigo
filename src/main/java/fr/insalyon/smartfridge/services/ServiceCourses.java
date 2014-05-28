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
        //List<Aliment> lAliments = AlimentDAO.tous();
        List<Article> listeArticle = ArticleDAO.tous();
        List<Article> lArticleAbsentDuFrigo = ArticleDAO.tous();
        List<Recette> listeRecettes = RecetteDAO.tous();
        BaseDAO.detruirePersistence();
        List<Aliment> lAli = ServiceStock.listerAliments();

        for (Aliment lAliment : lAli) {
            System.out.println(lAliment.toString());
            long id = lAliment.getArticle().getId();
            for (int j = 0; j < listeArticle.size(); j++) {
                if (id == listeArticle.get(j).getId()) {
                    Article a = listeArticle.get(j);
                    if(lAliment.getQuantite() < a.getHabitude()) {
                        lCourses.add(new Aliment(a, a.getHabitude()-lAliment.getQuantite()));
                    }
                    if(lAliment.getQuantite() == a.getHabitude()){
                        lCourses.add(new Aliment(a, a.getHabitude()-lAliment.getQuantite()));
                    }
                }
            }
        }

        for(int e= 0; e<lArticleAbsentDuFrigo.size();e++) {
            Article b = lArticleAbsentDuFrigo.get(e);
            for (Aliment a : lCourses) {
                if (b.equals(a.getArticle())) {
                    lArticleAbsentDuFrigo.remove(e);
                }
            }
        }


        for (Article d : lArticleAbsentDuFrigo) {
            if (d.getHabitude() != 0) {
                // System.out.println( "je suis absent du frigo mais g une habitude "+ d.getNom());
                lCourses.add(new Aliment(d, d.getHabitude()));
                //System.out.println(lArticle.get(0).getNom());
            }
        }
        for(int y = 0; y<lCourses.size(); y++){
            System.out.println(lCourses.get(y).toString());
        }



        // TODO: Mettre a jour pour les ingredients
        for (Recette listeRecette : listeRecettes) {
            if (listeRecette.isActif()) {
                List<Ingredient> lIng = listeRecette.getIngredients();
                for (Ingredient f : lIng) {
                    lCourses.add(new Aliment(f.getArticle(), f.getQuantite()));
                }
            }
        }
        for(int j=0; j<lCourses.size(); j++){
            for(int k=j+1 ; k<lCourses.size(); k++) {
                //System.out.println(lCourses.get(j).toString());
                if (lCourses.get(j).getArticle().equals(lCourses.get(k).getArticle())) {
                    //System.out.println(lCourses.get(j).toString());
                    //lCourses.add(new Aliment(lCourses.get(j).getArticle(), (lCourses.get(j).getQuantite()+ lCourses.get(k).getQuantite())));
                    int q = lCourses.get(k).getQuantite();
                    lCourses.get(j).setQuantite(lCourses.get(j).getQuantite()+ q);
                    lCourses.remove(k);
                }
            }
        }

        for(int i=0; i<lCourses.size(); i++ ){
            if(lCourses.get(i).getQuantite()==0){
                lCourses.remove(i);
            }
        }

        return lCourses;
    }

    public static List<Ingredient> listerIngredients(Recette r){
        BaseDAO.initialiserPersistence();
        List<Ingredient> ingredients = r.getIngredients();
        BaseDAO.detruirePersistence();
        return ingredients;
    }

}
