package fr.insalyon.smartfridge.modeles;

import javax.persistence.*;
import java.util.Date;

/** Represente un aliment present dans le frigo */
@Entity
public class Aliment {
    /** L'Article lie a cet aliment */
    @ManyToOne
    private Article article;

    /** Son identifiant unique */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** Sa date de peremption */
    @Temporal(javax.persistence.TemporalType.DATE) // Definit comment la date est stockee dans la BDD
    private Date datePeremption;

    /** Sa date d'ajout */
    @Temporal(javax.persistence.TemporalType.DATE)
    private final Date dateAjout;

    /** Sa quantite */
    private int quantite;

    /** Constructeur standard */
    public Aliment() {
        dateAjout = new Date(); // Initialise a la date du moment de la creation de l'objet
    }

    /** Constructeur avec parametres
     *
     * @param article L'article lie
     * @param datePeremption Sa date de peremption
     * @param quantite Sa quantite
     */
    public Aliment(Article article, Date datePeremption, int quantite) {
        this();
        this.article = article;
        this.datePeremption = datePeremption;
        this.quantite = quantite;
    }

    /** Constructeur avec parametres
     *
     * @param article L'article lie
     * @param quantite Sa quantite
     */
    public Aliment(Article article, int quantite) {
        this();
        this.article = article;
        this.quantite = quantite;
        this.datePeremption = this.dateAjout;
    }

    /** Retourne l'id */
    public long getId() {
        return id;
    }

    /** Retourne la date de peremption
     *
     * @return La date de peremption
     */
    public Date getDatePeremption() {
        return datePeremption;
    }

    /** Redefinit la date de peremption
     *
     * @param datePeremption La nouvelle date
     */
    public void setDatePeremption(Date datePeremption) {
        this.datePeremption = datePeremption;
    }

    /** Retourne la date d'ajout
     *
     * @return La date d'ajout
     */
    public Date getDateAjout() {
        return dateAjout;
    }

    /** Retourne la quantite
     *
     * @return La quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /** Redefinit la quantite
     *
     * @param quantite La nouvelle quantite
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /** Retourne l'Article
     *
     * @return L'Article
     */
    public Article getArticle() {
        return article;
    }

    /** Resume l'Aliment
     *
     * @return Le resume
     */
    public String toString() {
        return quantite + "x " + article;
    }
}
