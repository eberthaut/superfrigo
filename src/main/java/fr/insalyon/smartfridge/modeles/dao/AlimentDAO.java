package fr.insalyon.smartfridge.modeles.dao;

import fr.insalyon.smartfridge.modeles.Aliment;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de gestion d'aliments
 */
public class AlimentDAO extends BaseDAO {
    /**
     * @param id Id de l'aliment
     * @return L'aliment
     */
    public static Aliment trouveId(long id) {
        return (Aliment) trouveId(Aliment.class, id);
    }

    /**
     * @return Toutes les entites de Aliment
     */
    public static List<Aliment> tous() {
        return (List<Aliment>) tous("Aliment");
    }

    /**
     * @return Toutes les entites de Aliment classees par dates de peremption
     */
    public static ArrayList<Aliment> tousTriesParPeremption() {
        Query q = getEntityManager().createQuery("SELECT aliment FROM Aliment aliment ORDER BY aliment.datePeremption");
        // SELECT * FROM aliment ORDER BY aliment.datePeremption
        return (ArrayList) q.getResultList();
    }
}
