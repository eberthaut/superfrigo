package fr.insalyon.smartfridge.modeles.dao;

import fr.insalyon.smartfridge.modeles.Recette;

import javax.persistence.*;
import java.util.List;

/**
 * DAO de gestion de recettes.
 */
public class RecetteDAO extends BaseDAO {
    /**
     * @param id Id de la recette
     * @return la recette
     */
    public static Recette trouveId(long id) {
        return (Recette) trouveId(Recette.class, id);
    }

    public static Recette trouveNom(String nom) {
        Query q = getEntityManager().createQuery("SELECT recette FROM Recette recette WHERE recette.nom=:nom");
        q.setParameter("nom", nom);
        return (Recette) q.getSingleResult();
    }

    /**
     * @return Toutes les entites de Recette
     */
    public static List<Recette> tous() {
        return (List<Recette>) tous("Recette"); //cf. BaseDAO
    }


}
