package fr.insalyon.smartfridge.modeles;

import javax.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private int quantite;

    @ManyToOne
    private
    Recette recette;

    @ManyToOne
    private
    Article article;

    public Ingredient(Article article, int quantite) {
        this.article = article;
        this.quantite = quantite;
    }

    public Ingredient() {
    }

    public long getId() {
        return id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) {
        this.recette = recette;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String toString() {
        return quantite + "x " + article;
    }
}
