package fr.insalyon.smartfridge.modeles.dao;

import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Type;

import javax.persistence.Query;
import java.util.List;

/** DAO de gestion d'articles */
public class ArticleDAO extends BaseDAO{
    /** Trouve un Article par son identifiant
     *
     * @param id Id de l'article
     * @return L'article
     */
    public static Article trouveId(long id) {
        return (Article) trouveId(Article.class, id); //Article.class retourne le type de la classe
    }

    /** Trouve tous les Article
     *
     * @return Toutes les entites de Article
     */
    public static List<Article> tous() {
        Query q = getEntityManager().createQuery("SELECT article FROM Article article ORDER BY article.nom ASC");
        return (List<Article>)q.getResultList();
    }

    /** Trouve tous les Aliment classes par habitudes de façon descendante
     *
     * @return Toutes les entites de Article
     */
    public static List<Article> tousTriesParHabitude() {
        Query q = getEntityManager().createQuery("SELECT article FROM Article article ORDER BY article.habitude DESC");
        return (List<Article>)q.getResultList();
    }

    /** Trouve un Article par son nom
     *
     * @param nom Le nom recherche
     * @return L'Article
     */
    public static Article trouve(String nom) {
        Query q = getEntityManager().createQuery("SELECT a FROM Article a WHERE a.nom = :nom");
        q.setParameter("nom", nom);
        return (Article)q.getSingleResult();
    }

    /** Trouve tous les Article a partir d'un Type
     *
     * @param type Le Type
     * @return Les Article
     */
    public static List<Article> listerArticlesType(Type type){
        Query q = getEntityManager().createQuery("SELECT article FROM Article article WHERE article.type=:type ORDER BY article.nom ASC");
        q.setParameter("type", type);
        return (List<Article>)q.getResultList();
    }
}

