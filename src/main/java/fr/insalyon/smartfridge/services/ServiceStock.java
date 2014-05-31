package fr.insalyon.smartfridge.services;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.modeles.dao.AlimentDAO;
import fr.insalyon.smartfridge.modeles.dao.ArticleDAO;
import fr.insalyon.smartfridge.modeles.dao.BaseDAO;
import fr.insalyon.smartfridge.modeles.dao.TypeDAO;

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
        List<Aliment> aliments = AlimentDAO.tousUniques();
        BaseDAO.detruirePersistence();
        return aliments;
    }

    public static List<Article> listerArticles(Type type) {
        BaseDAO.initialiserPersistence();
        List<Article> articles = ArticleDAO.listerArticlesType(type);
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
        BaseDAO.initialiserPersistence();
        List<Aliment> aliments = AlimentDAO.tousTriesParPeremption(aliment.getArticle());
        BaseDAO.creerTransaction();
        for(Aliment a : aliments) {
            if(a.getQuantite() <= quantite) {
                quantite -= a.getQuantite();
                AlimentDAO.supprime(a);
            } else {
                a.setQuantite(a.getQuantite() - quantite);
                AlimentDAO.miseAJour(a);
                break;
            }
        }
        BaseDAO.faireTransactionSecurisee();
        BaseDAO.detruirePersistence();
        return true;
    }

    public static boolean ajouterAliment (Article article, int quantite) {
        BaseDAO.initialiserPersistence();
        long maintenant = new Date().getTime();
        long finPeremption =  (long)article.getJoursPeremption() * 60 * 60 * 24 * 1000;
        Date datePeremption = new Date(maintenant + finPeremption);
        Aliment aliment= new Aliment(article, datePeremption, quantite);
        BaseDAO.creerTransaction();
        AlimentDAO.persiste(aliment);
        BaseDAO.faireTransactionSecurisee();
        BaseDAO.detruirePersistence();
        return true;
    }


}
