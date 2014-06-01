package fr.insalyon.smartfridge.modeles;

import javax.persistence.*;
import java.util.List;


/** Represente un type d'article du frigo */
@Entity // Ce mot cle signifie que cette classe definit en meme temps une table dans la base de donnees.
public class Type {
    /** Les articles qui possedent ce type */
    @OneToMany(mappedBy = "type") // on peut avoir plusieurs articles pour un type
    private List<Article> articles;

    /** L'identifiant */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** Le nom */
    private String nom;

    /** Constructeur */
    public Type() {

    }

    /** Constructeur avec nom
     *
     * @param nom Le nom
     */
    public Type(String nom) {
        this.nom = nom;
    }

    /** Retourne L'identifiant
     *
     * @return L'identifiant
     */
    public long getId() {
        return id;
    }

    /** Retourne Le nom
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

    /** Retourne Les articles associes
     *
     * @return Les articles associes
     */
    public List<Article> getArticles() {
        return articles;
    }

    /** Resume le Type
     *
     * @return Le resume
     */
    public String toString() {
        return nom;
    }
}
