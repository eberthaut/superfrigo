package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.modeles.dao.*;

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

    public static List<Article> listerArticles(Type type) {
        BaseDAO.initialiserPersistence();
        List<Article> articles = type.getArticles();
        BaseDAO.detruirePersistence();
        return articles;
    }

    public static boolean retraitAliment (Article article) {
        BaseDAO.initialiserPersistence();
        BaseDAO.creerTransaction();
        ArticleDAO.supprime(article);
        BaseDAO.faireTransaction();
        BaseDAO.detruirePersistence();
        return true;
    }

    public static boolean ajouterAliment (Article article, int quantite) {
        BaseDAO.initialiserPersistence();
        Date datePeremption = new Date(new Date().getTime() + article.getJoursPeremption() * 60 * 60 * 24);
        Aliment aliment= new Aliment(article, datePeremption, quantite);
        BaseDAO.creerTransaction();
        AlimentDAO.persiste(aliment);
        BaseDAO.faireTransaction();
        BaseDAO.detruirePersistence();
        return true;
    }


}
