package fr.insalyon.smartfridge.modeles;

import javax.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private long quantite;

    @ManyToOne
    private
    Recette recette;

    @ManyToOne
    private
    Article article;

    public long getId() {
        return id;
    }

    public long getQuantite() {
        return quantite;
    }

    public void setQuantite(long quantite) {
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
