package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Ingredient;
import fr.insalyon.smartfridge.modeles.Recette;
import fr.insalyon.smartfridge.modeles.dao.ArticleDAO;
import fr.insalyon.smartfridge.modeles.dao.BaseDAO;
import fr.insalyon.smartfridge.modeles.dao.IngredientDAO;
import fr.insalyon.smartfridge.modeles.dao.RecetteDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Service de gestion des habitudes dans le frigo.
 */
public class ServiceCourses {
    public static boolean ajoutRecette(String nom, int pour, List<Ingredient> ingredients) {
        BaseDAO.initialiserPersistence();
        Recette recette = new Recette(nom, ingredients, pour);

        BaseDAO.creerTransaction();
        RecetteDAO.persiste(recette);
        for(Ingredient ingredient : ingredients) {
            ingredient.setRecette(recette);
            IngredientDAO.persiste(ingredient);
        }
        BaseDAO.faireTransactionSecurisee();

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
        BaseDAO.faireTransactionSecurisee();

        BaseDAO.detruirePersistence();
        return true;
    }


    public static boolean changerHabitude(Article article, int habitude) {
        BaseDAO.initialiserPersistence();


        article.setHabitude(habitude);

        BaseDAO.creerTransaction();
        ArticleDAO.miseAJour(article);
        BaseDAO.faireTransactionSecurisee();

        BaseDAO.detruirePersistence();
        return true;
    }

    public static List<Recette> listerRecettes() {
        BaseDAO.initialiserPersistence();
        List<Recette> recettes = RecetteDAO.tous();
        BaseDAO.detruirePersistence();
        return recettes;
    }

    public static boolean activerRecette(Recette recette, int pour) {

        if(recette.isActif()) {
            return false;
        } else {
            recette.setActivePour(pour);
            BaseDAO.initialiserPersistence();

            BaseDAO.creerTransaction();
            RecetteDAO.miseAJour(recette);
            BaseDAO.faireTransactionSecurisee();
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
            BaseDAO.faireTransactionSecurisee();
            BaseDAO.detruirePersistence();

            return true;
        }
    }

    public static double calculPrixListe (List<Article> listeCourses) {
        double prixListe=0;
        for(Article article : listeCourses) {
            prixListe += article.getPrix();
        }
        return prixListe;
    }

    public static List<Aliment> genererListeCourses() {
        // TODO: A opt

        BaseDAO.initialiserPersistence();

        List<Aliment> lCourses = new ArrayList<Aliment>();
        List<Article> listeArticle = ArticleDAO.tous();
        List<Article> lArticleAbsentDuFrigo = ArticleDAO.tous();
        List<Recette> listeRecettes = RecetteDAO.tous();
        BaseDAO.detruirePersistence();
        List<Aliment> lAli = ServiceStock.listerAliments();

        for (Aliment lAliment : lAli) {
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
                lCourses.add(new Aliment(d, d.getHabitude()));
            }
        }



        // TODO: Mettre a jour pour les ingredients
        for (Recette listeRecette : listeRecettes) {
            if (listeRecette.isActif()) {
                List<Ingredient> lIng = listeRecette.getIngredients();
                float ratio =  listeRecette.getActivePour() / listeRecette.getPrevuPour();
                for (Ingredient f : lIng) {
                    lCourses.add(new Aliment(f.getArticle(), Math.round(f.getQuantite() * ratio)));
                }
            }
        }
        for(int j=0; j<lCourses.size(); j++){
            for(int k=j+1 ; k<lCourses.size(); k++) {
                if (lCourses.get(j).getArticle().equals(lCourses.get(k).getArticle())) {
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
