package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Recette;
import fr.insalyon.smartfridge.modeles.dao.ArticleDAO;
import fr.insalyon.smartfridge.modeles.dao.BaseDAO;
import fr.insalyon.smartfridge.modeles.dao.RecetteDAO;

import java.util.*;

/**
 * Service de gestion des habitudes dans le frigo.
 */
public class ServiceCourses {
    public static boolean ajoutRecette(String nom, List<Article> articles) {
        BaseDAO.initialiserPersistence();
        Recette recette = new Recette(nom, articles);

        BaseDAO.creerTransaction();
        RecetteDAO.persiste(recette);
        BaseDAO.faireTransaction();

        BaseDAO.detruirePersistence();
        return true;
    }

    public static boolean retraitRecette(String nom, List<Article> articles) {
        BaseDAO.initialiserPersistence();
        Recette recette = new Recette(nom, articles);

        BaseDAO.creerTransaction();
        RecetteDAO.supprime(recette);
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
            return true;
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
            return true;
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
        BaseDAO.detruirePersistence();
        return listeCourses;
    }



}
