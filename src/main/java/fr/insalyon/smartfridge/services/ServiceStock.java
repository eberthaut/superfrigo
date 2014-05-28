package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.modeles.dao.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * S'occupe de la gestion de stock.
 */
public class ServiceStock {

    public static List<Type> listerTypes() {
        BaseDAO.initialiserPersistence();
        List<Type> types = TypeDAO.tous();
        BaseDAO.detruirePersistence();
        return types;
    }

    public static List<Aliment> listerAliments() {
        BaseDAO.initialiserPersistence();
        List<Aliment> aliments = AlimentDAO.tous();
        for(int j=0; j<aliments.size(); j++){
            for(int k=j+1 ; k<aliments.size(); k++) {
                //System.out.println(lCourses.get(j).toString());
                if (aliments.get(j).getArticle().equals(aliments.get(k).getArticle())) {
                    System.out.println(aliments.get(j).toString());
                    aliments.get(k).setQuantite(aliments.get(j).getQuantite() + aliments.get(k).getQuantite());
                    aliments.remove(j);
                }
            }
        }
        BaseDAO.detruirePersistence();
        return aliments;
    }

    public static List<Article> listerArticles(Type type) {
        BaseDAO.initialiserPersistence();
        List<Article> articles = TypeDAO.listerArticlesType(type);
        BaseDAO.detruirePersistence();
        return articles;
    }

    public static List<Article> listerArticles() {
        BaseDAO.initialiserPersistence();
        List<Article> articles = ArticleDAO.tous();
        BaseDAO.detruirePersistence();
        return articles;
    }

    public static boolean retraitAliment (Aliment aliment, int quantite) {
        System.out.println(quantite);
        BaseDAO.initialiserPersistence();
        aliment = AlimentDAO.trouveId(aliment.getId());
        BaseDAO.creerTransaction();
        if(aliment.getQuantite() <= quantite) {
            AlimentDAO.supprime(aliment);
        } else {
            aliment.setQuantite(aliment.getQuantite() - quantite);
            System.out.println(aliment.getQuantite());
            AlimentDAO.miseAJour(aliment);
        }
        BaseDAO.faireTransaction();
        BaseDAO.detruirePersistence();
        return true;
    }

    public static boolean ajouterAliment (Article article, int quantite) {
        BaseDAO.initialiserPersistence();
        Date datePeremption = new Date(new Date().getTime() + article.getJoursPeremption() * 60 * 60 * 24 * 1000);
        Aliment aliment= new Aliment(article, datePeremption, quantite);
        BaseDAO.creerTransaction();
        AlimentDAO.persiste(aliment);
        BaseDAO.faireTransaction();
        BaseDAO.detruirePersistence();
        return true;
    }


}
