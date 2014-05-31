package fr.insalyon.smartfridge.modeles.dao;

import fr.insalyon.smartfridge.modeles.Article;

import javax.persistence.Query;
import java.util.List;

/**
 * DAO de gestion d'articles
 */
public class ArticleDAO extends BaseDAO{
    /**
     * @param id Id de l'article
     * @return L'article
     */
    public static Article trouveId(long id) {
        return (Article) trouveId(Article.class, id); //Article.class retourne le type de la classe
    }

    /**
     * @return Toutes les entites de Article
     */
    public static List<Article> tous() {
        Query q = getEntityManager().createQuery("SELECT article FROM Article article ORDER BY article.nom ASC");
        return q.getResultList();
    }

    /**
     * @return Toutes les entites de Article classees par habitudes de façon descendante
     */
    public static List<Article> tousTriesParHabitude() {
        Query q = getEntityManager().createQuery("SELECT article FROM Article article ORDER BY article.habitude DESC");
        return q.getResultList();
    }

    public static Article trouve(String nom) {
        Query q = getEntityManager().createQuery("SELECT a FROM Article a WHERE a.nom = :nom");
        q.setParameter("nom", nom);
        return (Article)q.getSingleResult();
    }
}

