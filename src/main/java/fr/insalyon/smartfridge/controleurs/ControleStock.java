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
    //TODO: voir avec Robin pour les paramètres car si on veut juste un article, comment on fait pour obtenir la date de péremption et tout ?
    public static boolean retraitAliment (Article article) {
        BaseDAO.creerTransaction();
        ArticleDAO.supprime(article);
        BaseDAO.faireTransaction();
        return true;

    }

    public static boolean ajouterAliment (Article article, Date datePeremption, int quantite) {
        Aliment aliment= new Aliment(article, datePeremption, quantite);
        BaseDAO.creerTransaction();
        AlimentDAO.persiste(aliment);
        BaseDAO.faireTransaction();
        return true;
    }


}
