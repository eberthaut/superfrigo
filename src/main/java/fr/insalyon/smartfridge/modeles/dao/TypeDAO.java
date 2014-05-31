package fr.insalyon.smartfridge.modeles.dao;

import fr.insalyon.smartfridge.modeles.Article;
import fr.insalyon.smartfridge.modeles.Type;

import javax.persistence.Query;
import java.util.List;

/**
 * DAO de gestion de Types
 */
public class TypeDAO extends BaseDAO {
    /**
     * @param id Id du type
     * @return Le type
     */
    public static Type trouveId(long id) {
        return (Type) trouveId(Type.class, id);
    }

    public static List<Article> listerArticlesType(Type type){
        Query q = getEntityManager().createQuery("SELECT article FROM Article article WHERE article.type=:type ORDER BY article.nom ASC");
        q.setParameter("type", type);
        return q.getResultList();
    }

    /**
     * @return Toutes les entites de Type
     */
    public static List<Type> tous() {
        return (List<Type>) tous("Type");
    }

    public static Type trouve(String nom) {
        Query q = getEntityManager().createQuery("SELECT t FROM Type t WHERE t.nom = :nom");
        q.setParameter("nom", nom);
        return (Type)q.getSingleResult();
    }
}
