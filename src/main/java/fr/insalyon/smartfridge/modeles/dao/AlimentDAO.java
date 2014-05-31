package fr.insalyon.smartfridge.modeles.dao;

import fr.insalyon.smartfridge.modeles.Aliment;
import fr.insalyon.smartfridge.modeles.Article;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**  DAO de gestion des Aliment */
public class AlimentDAO extends BaseDAO {
    /** Trouve un Aliment par son identifiant
     *
     * @param id Id de l'aliment
     * @return L'aliment
     */
    public static Aliment trouveId(long id) {
        return (Aliment) trouveId(Aliment.class, id);
    }

    /** Trouve tous les Aliment
     *
     * @return Toutes les entites de Aliment
     */
    public static List<Aliment> tous() {
        return (List<Aliment>) tous("Aliment");
    }

    /** Retrouve tous les Aliment qui periment avant une certaine date
     *
     * @param date La date limite
     * @return Les Aliment qui vont perimer
     */
    public static List<Aliment> tousAvant(Date date) {
        Query q = getEntityManager().createQuery("SELECT aliment FROM Aliment aliment WHERE aliment.datePeremption < :date ");
        q.setParameter("date", date, TemporalType.DATE);
        return (List<Aliment>)q.getResultList();
    }

    /** Compte tous les Aliment qui periment avant une certaine date
     *
     * @param date La date limite
     * @return Le nombre d'Aliment qui vont perimer
     */
    public static long compteTousAvant(Date date) {
        Query q = getEntityManager().createQuery("SELECT COUNT(aliment.id) FROM Aliment aliment WHERE aliment.datePeremption < :date ");
        q.setParameter("date", date, TemporalType.DATE);
        return (Long)q.getSingleResult();
    }

    /** Donne tous les aliments uniques (assembles par Article)
     *
     * @return Des Aliments <strong>non persistes en base de donnees</strong>
     */
    public static List<Aliment> tousUniques() {
        Query q = getEntityManager().createQuery("SELECT aliment.article, SUM(aliment.quantite) FROM Aliment aliment GROUP BY aliment.article");
        List<Object[]> produitEtCompte = (List<Object[]>)q.getResultList();
        List<Aliment> aliments = new ArrayList<Aliment>();
        for(Object[] pec : produitEtCompte) {
            aliments.add(new Aliment((Article) pec[0], Integer.parseInt(pec[1].toString())));
        }
        return aliments;
    }

    /** Donne tous les aliments correspondandant a un article tries par date de peremption
     *
     * @param article L'Article
     * @return Les Aliment en question
     */
    public static List<Aliment> tousTriesParPeremption(Article article) {
        Query q = getEntityManager().createQuery("SELECT aliment FROM Aliment aliment WHERE aliment.article = :article ORDER BY aliment.datePeremption");
        q.setParameter("article", article);
        return (List<Aliment>)q.getResultList();
    }
}
