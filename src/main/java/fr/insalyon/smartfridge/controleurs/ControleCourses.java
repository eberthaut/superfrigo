package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Recette;
import fr.insalyon.smartfridge.modeles.dao.ArticleDAO;
import fr.insalyon.smartfridge.modeles.dao.BaseDAO;
import fr.insalyon.smartfridge.modeles.dao.RecetteDAO;

import java.util.*;

/**
 * Service de gestion des habitudes dans le frigo.
 */
public class ControleCourses {
    public static boolean ajoutRecette(String nom, List<Article> articles) {
        Recette recette = new Recette(nom, articles);

        BaseDAO.creerTransaction();
        RecetteDAO.persiste(recette);
        BaseDAO.faireTransaction();

        if(recette.getId() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean retraitRecette(String nom, List<Article> articles) {
        Recette recette = new Recette(nom, articles);

        BaseDAO.creerTransaction();
        RecetteDAO.supprime(recette);
        BaseDAO.faireTransaction();

        if(recette.getId() != 0) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean augmenterHabitude(Article article) {
        int modificationHabitude = 1;

        // TODO : Faire un algorithme

        article.setHabitude(article.getHabitude() + modificationHabitude);

        BaseDAO.creerTransaction();
        ArticleDAO.miseAJour(article);
        BaseDAO.faireTransaction();

        return true;
    }

    public static boolean decrementerHabitude(Article article) {
        int modificationHabitude = 1;

        // TODO : Faire un algorithme

        article.setHabitude(article.getHabitude() - modificationHabitude);

        BaseDAO.creerTransaction();
        ArticleDAO.miseAJour(article);
        BaseDAO.faireTransaction();

        return true;
    }

    public static List<Recette> listerRecettes() {
        return RecetteDAO.tous();
    }

    public static boolean activerRecette(Recette recette) {
        if(recette.isActif()) {
            return true;
        } else {
            recette.setActivite(true);

            BaseDAO.creerTransaction();
            RecetteDAO.miseAJour(recette);
            BaseDAO.faireTransaction();

            return true;
        }
    }

    public static boolean desactiverRecette(Recette recette) {
        if(! recette.isActif()) {
            return true;
        } else {
            recette.setActivite(true);

            BaseDAO.creerTransaction();
            RecetteDAO.miseAJour(recette);
            BaseDAO.faireTransaction();

            return true;
        }
    }

    public static double calculPrixListe () {
        double prixListe=0;
        //TODO
        return prixListe;
    }

   /* TODO: comment on écrit la liste. Il faut déjà créer  */
    public static List<Article> genererListeCourses () {
        List<Article> listeCourses= new ArrayList<Article>();



        return listeCourses;
    }



}