package fr.insalyon.smartfridge.modeles;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente une recette.
 */
@Entity
public class Recette {
    @ManyToMany //définit la liste d'articles de la recette
    private List<Article> ingredients = new ArrayList<Article>();

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private String nom;
    private boolean actif;

    public Recette() {
        this.actif = true;
    }

    public Recette(String nom, List<Article> articles) {
        this();
        this.nom = nom;
        this.ingredients.addAll(articles);
    }

    public boolean isActif () {
        return this.actif;
    }

    public void setActivite (boolean actif) {
        this.actif = actif;
    }

    public List<Article> getArticles() {
        return ingredients;
    }

    public boolean ajouterArticle (Article article) {
        return this.ingredients.add(article);
    }

    public boolean retirerArticle (Article article) {
        return this.ingredients.remove(article);
    }

    public long getId() {
        return id;
    }
}
