package fr.insalyon.smartfridge.modeles;

import javax.persistence.*;

/** Represente un article */
@Entity
public class Article {
    /** Le type associe */
    @ManyToOne // on peut avoir plusieurs articles pour un type
    private Type type;

    /** L'identifiant */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** Le nom */
    private String nom;
    /** Le prix en Euros */
    private double prix;
    /** Le nombre de jours standards avant la peremption */
    private int joursPeremption;
    /** La masse en Kg */
    private double masse;
    /** L'habitude de l'utilisateur sur cet Article */
    private int habitude;

    /** Constructeur */
    public Article () {

    }

    /** Constructeur par parametres
     *
     * @param nom Son nom
     * @param prix Son prix
     * @param joursPeremption Sa peremption standard
     * @param masse Sa masse
     * @param type Son type
     */
    public Article(String nom, double prix, int joursPeremption, double masse, Type type) {
        this.nom = nom;
        this.prix = prix;
        this.joursPeremption = joursPeremption;
        this.masse = masse;
        this.type = type;
    }

    /** Retourne le nom
     *
     * @return Le nom
     */
    public String getNom() {
        return nom;
    }

    /** Redefinit le nom
     *
     * @param nom Le nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /** Retourne le type
     *
     * @return Le type
     */
    public Type getType() {
        return type;
    }

    /** Redefinit le type
     *
     * @param type Le nouveau type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /** Retourne l'habitude
     *
     * @return L'habitude
     */
    public int getHabitude() {
        return habitude;
    }

    /** Redefinit l'habitude
     *
     * @param habitude La nouvelle habitude
     */
    public void setHabitude(int habitude) {
        this.habitude = habitude;
    }

    /** Retourne le prix
     *
     * @return Le prix
     */
    public double getPrix() {
        return prix;
    }

    /** Redefinit le prix
     *
     * @param prix Le nouveau prix
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /** Retourne les jours de peremption standards
     *
     * @return Les jours de peremption standards
     */
    public int getJoursPeremption() {
        return joursPeremption;
    }

    /** Redefinit les jours de peremption standards
     *
     * @param joursPeremption Les nouveaux jours de peremption standards
     */
    public void setJoursPeremption(int joursPeremption) {
        this.joursPeremption = joursPeremption;
    }

    /** Retourne la masse
     *
     * @return La masse
     */
    public double getMasse() {
        return masse;
    }

    /** Redefinit la masse
     *
     * @param masse la masse
     */
    public void setMasse(double masse) {
        this.masse = masse;
    }

    /** Retourne l'identifiant
     *
     * @return L'identifiant
     */
    public long getId() {
        return id;
    }

    /** Resume l'Article
     *
     * @return Le resume
     */
    public String toString() {
        return nom;
    }
}
