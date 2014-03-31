package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.dao.AlimentDAO;
import fr.insalyon.smartfridge.modeles.dao.ArticleDAO;
import fr.insalyon.smartfridge.modeles.dao.BaseDAO;

import java.util.Date;


/**
 * S'occupe de la gestion de stock.
 */
public class ControleStock {
    public static boolean retraitAliment (Article article) {
        BaseDAO.creerTransaction();
        ArticleDAO.supprime(article);
        BaseDAO.faireTransaction();
        return true;
    }

    public static boolean ajouterAliment (Article article, int quantite) {
        Date datePeremption = new Date(new Date().getTime() + article.getJoursPeremption() * 60 * 60 * 24);
        Aliment aliment= new Aliment(article, datePeremption, quantite);
        BaseDAO.creerTransaction();
        AlimentDAO.persiste(aliment);
        BaseDAO.faireTransaction();
        return true;
    }


}
