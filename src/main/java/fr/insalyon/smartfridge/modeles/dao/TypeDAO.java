package fr.insalyon.smartfridge.modeles.dao;

import fr.insalyon.smartfridge.modeles.Type;

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

    /**
     * @return Toutes les entites de Type
     */
    public static List<Type> tous() {
        return (List<Type>) tous("Type");
    }
}
