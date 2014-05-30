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
    private int prevuPour;
    private int activePour;

    public Recette() {
        this.activePour = this.prevuPour = 0;
    }

    public Recette(String nom, List<Ingredient> ingredients, int prevuPour) {
        this();
        this.nom = nom;
        this.ingredients.addAll(ingredients);
        this.prevuPour = this.activePour = prevuPour;
    }

    public boolean isActif () {
        return (this.activePour > 0);
    }

    public void setActivite (boolean actif) {
        if(actif) {
            this.activePour = this.prevuPour;
        } else {
            this.activePour = 0;
        }

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
        return (isActif()?"✓ ":"   ") + nom + " (prevu pour " + prevuPour + " personnes" + (isActif()?", active pour " + activePour + " personnes":"") + ")";
    }

    public int getPrevuPour() {
        return prevuPour;
    }

    public void setPrevuPour(int prevuPour) {
        this.prevuPour = prevuPour;
    }

    public int getActivePour() {
        return activePour;
    }

    public void setActivePour(int activePour) {
        this.activePour = activePour;
    }
}
