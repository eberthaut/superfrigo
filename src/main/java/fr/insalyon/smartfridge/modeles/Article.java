package fr.insalyon.smartfridge.modeles;

import javax.persistence.*;

/**
 * Représente un article.
 */
@Entity
public class Article {
    @ManyToOne // on peut avoir plusieurs articles pour un type
    private Type type;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private String nom;
    private String marque;

    private int habitude;

    public Article(String nom, String marque) {
        this.nom = nom;
        this.marque = marque;
    }

    public Article () {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int gethabitude() {
        return habitude;
    }

    public void sethabitude(int habitude) {
        this.habitude = habitude;
    }
}
