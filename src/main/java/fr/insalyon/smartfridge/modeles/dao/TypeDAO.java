package fr.insalyon.smartfridge.modeles.dao;

import fr.insalyon.smartfridge.modeles.Type;

import javax.persistence.Query;
import java.util.List;

/**DAO de gestion de Types */
public class TypeDAO extends BaseDAO {
    /** Trouve un Type par son Id
     *
     * @param id Id du type
     * @return Le type
     */
    public static Type trouveId(long id) {
        return (Type) trouveId(Type.class, id);
    }

    /**
     * @return Toutes les entites de Type
     */
    public static List<Type> tous() {
        return (List<Type>) tous("Type");
    }

    /** Trouve un Type par son nom
     *
     * @param nom Le nom recherche
     * @return Le Type
     */
    public static Type trouve(String nom) {
        Query q = getEntityManager().createQuery("SELECT t FROM Type t WHERE t.nom = :nom");
        q.setParameter("nom", nom);
        return (Type)q.getSingleResult();
    }
}
