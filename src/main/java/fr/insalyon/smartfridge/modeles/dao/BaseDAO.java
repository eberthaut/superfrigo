package fr.insalyon.smartfridge.modeles.dao;

import javax.persistence.*;
import java.util.List;

/** DAO de base permettant une gestion basique du contexte de persistence */
public class BaseDAO {
    /** Nom de l'unite de persistance */
    private static final String NOM_PERSISTANCE_UNIT = "SmartFridgePU";
    /** Une factory pour l'entity manager */
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(NOM_PERSISTANCE_UNIT);
    /** Un thread conteneur pour l'entity manager */
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>() {
        @Override
        protected EntityManager initialValue() {
            return null;
        }
    };

    /** Cree le contexte de persistence */
    public static void initialiserPersistence() {
        threadLocal.set(entityManagerFactory.createEntityManager());
    }

    /** Detruit le contexte de persistence */
    public static void detruirePersistence() {
        EntityManager em = threadLocal.get();
        em.close(); // Coupe la connexion avec la base
        threadLocal.set(null);
    }

    /** Cree une transaction dans la base */
    public static void creerTransaction() {
        threadLocal.get().getTransaction().begin();
    }

    /**  Annule une transaction */
    public static void annulerTransaction() {
        threadLocal.get().getTransaction().rollback();
    }

    /** Valide une transaction */
    public static void faireTransaction() {
        threadLocal.get().getTransaction().commit();
    }

    /** Valide une transaction de maniere securisee
     *
     * @return Le succes de l'operation
     */
    public static boolean faireTransactionSecurisee() {
        try {
            faireTransaction();
            return true;
        } catch (RollbackException e) {
            annulerTransaction();
            return false;
        }
    }

    /** Persiste une entite dans la base de donnees
     * 
     * @param entity L'entite JPA a enregistrer
     */
    public static void persiste(Object entity) {
        getEntityManager().persist(entity);
    }

    /** Persiste une mise a jour d'entite dans la base de donnees
     * 
     * @param entity L'entite JPA a mettre a jour
     */
    public static void miseAJour(Object entity) {
        getEntityManager().merge(entity);
    }

    /** Supprime un objet de la base
     * 
     * @param o L'objet a supprimer
     */
    public static void supprime(Object o) {
        getEntityManager().remove(o);
    }

    /** Retrouve un element stocke a partir de sa cle primaire
     * 
     * @param type La classe de l'element
     * @param id La cle primaire de l'element
     * @return L'element stocke a la cle primaire
     */
    static Object trouveId(Class type, long id) {
        return getEntityManager().find(type, id);
    }

    /** Retrouve tous les elements pour un type d'entite
     * 
     * @param entityName Le nom de la classe de l'element
     * @return Tous ces elements
     */
    static List tous(String entityName) {
        Query q = getEntityManager().createQuery("select e from " + entityName + " e");
        return q.getResultList();
    }

    /** Retourne l'entity manager pour une DAO
     *
     * @return L'entity manager global
     */
    static EntityManager getEntityManager() {
        return threadLocal.get();
    }
}
