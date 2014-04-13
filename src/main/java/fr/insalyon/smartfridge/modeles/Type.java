package fr.insalyon.smartfridge.modeles;

import javax.persistence.*;
import java.util.List;

/**
 * Represente un type d'article du frigo
 */

// Ce mot clé signifie que cette classe définit en même temps une table dans la base de données.
@Entity
public class Type {
    @OneToMany(mappedBy = "type") // on peut avoir plusieurs articles pour un type
    private List<Article> articles;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private String nom;

    public Type() {

    }

    public Type(String nom) {
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
