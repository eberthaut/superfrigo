package fr.insalyon.smartfridge.modeles;

import javax.persistence.*;
import java.util.Date;

/**
 * Represente un aliment dans le frigo
 */
@Entity
public class Aliment {
    @ManyToOne
    private Article article;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Temporal(javax.persistence.TemporalType.DATE) // Définit comment la date est stockée dans la BDD
    private Date datePeremption;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAjout;

    private int quantite;

    public Aliment() {
        dateAjout = new Date(); // Initialisé à la date du moment de la création de l'objet
    }

    public Aliment(Article article, Date datePeremption, int quantite) {
        this();
        this.article = article;
        this.datePeremption = datePeremption;
        this.quantite = quantite;
    }

    public long getId() {
        return id;
    }

    public Date getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(Date datePeremption) {
        this.datePeremption = datePeremption;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
