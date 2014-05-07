package fr.insalyon.smartfridge.modeles;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente une recette.
 */
@Entity
public class Recette {
    @OneToMany(mappedBy = "recette")
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private String nom;
    private boolean actif;

    public Recette() {
        this.actif = true;
    }

    public Recette(String nom, List<Ingredient> ingredients) {
        this();
        this.nom = nom;
        this.ingredients.addAll(ingredients);
    }

    public boolean isActif () {
        return this.actif;
    }

    public void setActivite (boolean actif) {
        this.actif = actif;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public boolean ajouterIngredient (Ingredient ingredient) {
        return this.ingredients.add(ingredient);
    }

    public boolean retirerIngredient (Ingredient ingredient) {
        return this.ingredients.remove(ingredient);
    }

    public long getId() {
        return id;
    }

    public String getNom(){
        return nom;
    }

    public String toString() {
        return "[" + (actif?"x":"  ") + "] " + nom;
    }
}
