package fr.insalyon.smartfridge.modeles;

import javax.persistence.*;

/** Represente un ingredient */
@Entity
public class Ingredient {
    /** L'identifiant */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** La quantite */
    private int quantite;

    /** La recette associee */
    @ManyToOne
    private
    Recette recette;

    /** L'article associe */
    @ManyToOne
    private
    Article article;

    /** Constructeur avec parametres
     *
     * @param article L'article
     * @param quantite L'ingredient
     */
    public Ingredient(Article article, int quantite) {
        this.article = article;
        this.quantite = quantite;
    }

    /** Constructeur */
    public Ingredient() {
    }

    /** Retourne L'identifiant
     *
     * @return L'identifiant
     */
    public long getId() {
        return id;
    }

    /** Retourne La quantite
     *
     * @return La quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /** Redefinit La quantite
     *
     * @param quantite La quantite
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /** Retourne La recette
     *
     * @return La recette
     */
    public Recette getRecette() {
        return recette;
    }

    /** Redefinit La recette
     *
     * @param recette La recette
     */
    public void setRecette(Recette recette) {
        this.recette = recette;
    }

    /** Retourne L'article
     *
     * @return L'article
     */
    public Article getArticle() {
        return article;
    }

    /** Redefinit L'article
     *
     * @param article L'article
     */
    public void setArticle(Article article) {
        this.article = article;
    }

    /** Resume l'Ingredient
     *
     * @return Le resume
     */
    public String toString() {
        return quantite + "x " + article;
    }
}
