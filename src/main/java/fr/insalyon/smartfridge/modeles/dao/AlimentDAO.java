package fr.insalyon.smartfridge.modeles.dao;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
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
    public static List<Aliment> tousTriesParPeremption() {
        Query q = getEntityManager().createQuery("SELECT aliment FROM Aliment aliment ORDER BY aliment.datePeremption");
        // SELECT * FROM aliment ORDER BY aliment.datePeremption
        return q.getResultList();
    }

    public static List<Aliment> tousAvant(Date date) {
        Query q = getEntityManager().createQuery("SELECT aliment FROM Aliment aliment WHERE aliment.datePeremption < :date ");
        q.setParameter("date", date, TemporalType.DATE);
        return q.getResultList();
    }

    public static long compteTousAvant(Date date) {
        Query q = getEntityManager().createQuery("SELECT COUNT(aliment.id) FROM Aliment aliment WHERE aliment.datePeremption < :date ");
        q.setParameter("date", date, TemporalType.DATE);
        return (Long)q.getSingleResult();
    }

    public static List<Aliment> tousTriesParQuantite() {
        Query q = getEntityManager().createQuery("SELECT quantite FROM Aliment ");
        // SELECT * FROM aliment ORDER BY aliment.datePeremption

        return q.getResultList();
    }

    public static List<Aliment> tousUniques() {
        Query q = getEntityManager().createQuery("SELECT aliment.article, SUM(aliment.quantite) FROM Aliment aliment GROUP BY aliment.article");
        List<Object[]> produitEtCompte = q.getResultList();
        List<Aliment> aliments = new ArrayList<Aliment>();
        for(Object[] pec : produitEtCompte) {
            aliments.add(new Aliment((Article) pec[0], Integer.parseInt(pec[1].toString())));
        }
        return aliments;
    }

    public static List<Aliment> tousTriesParPeremption(Article article) {
        Query q = getEntityManager().createQuery("SELECT aliment FROM Aliment aliment WHERE aliment.article = :article ORDER BY aliment.datePeremption");
        q.setParameter("article", article);
        return q.getResultList();
    }
}
