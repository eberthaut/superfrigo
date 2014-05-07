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
    private double prix;
    private int joursPeremption;
    private double masse;

    private int habitude;

    public Article(String nom, double prix, int joursPeremption, double masse, Type type) {
        this.nom = nom;
        this.prix = prix;
        this.joursPeremption = joursPeremption;
        this.masse = masse;
        this.type = type;
    }

    public Article () {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getHabitude() {
        return habitude;
    }

    public void setHabitude(int habitude) {
        this.habitude = habitude;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getJoursPeremption() {
        return joursPeremption;
    }

    public void setJoursPeremption(int joursPeremption) {
        this.joursPeremption = joursPeremption;
    }

    public double getMasse() {
        return masse;
    }

    public void setMasse(double masse) {
        this.masse = masse;
    }

    public long getId() {
        return id;
    }

    public String toString() {
        return nom;
    }
}
