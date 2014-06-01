package fr.insalyon.smartfridge.modeles;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** Represente une Recette */
@Entity
public class Recette {
    /** Les ingredients */
    @OneToMany(mappedBy = "recette")
    private final List<Ingredient> ingredients = new ArrayList<Ingredient>();

    /** L'identifiant */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** Le nom */
    private String nom;
    /** Prevue pour un nombre de personnes */
    private int prevuPour;
    /** Activee pour un nombre de personnes */
    private int activePour;

    /** Constructeur */
    public Recette() {
        this.activePour = this.prevuPour = 0;
    }

    /** Constructeur avec parametres
     *
     * @param nom Son nom
     * @param ingredients Ses ingredients
     * @param prevuPour Prevue pour combien de personnes ?
     */
    public Recette(String nom, List<Ingredient> ingredients, int prevuPour) {
        this();
        this.nom = nom;
        this.ingredients.addAll(ingredients);
        this.prevuPour = this.activePour = prevuPour;
    }

    /** Determine si elle est active
     *
     * @return Son etat d'activite
     */
    public boolean isActif () {
        return (this.activePour > 0);
    }

    /** Active ou desactive la Recette
     *
     * @param actif Son nouvel etat d'activite
     */
    public void setActivite (boolean actif) {
        if(actif) {
            this.activePour = this.prevuPour;
        } else {
            this.activePour = 0;
        }
    }

    /** Retourne Les ingredients
     *
     * @return Les ingredients
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /** Ajoute un ingredient
     *
     * @param ingredient L'ingredient a ajouter
     */
    public void ajouterIngredient (Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    /** Retire un ingredient
     *
     * @param ingredient L'ingredient a retirer
     */
    public void retirerIngredient (Ingredient ingredient) {
        this.ingredients.remove(ingredient);
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
    public String getNom(){
        return nom;
    }

    /** Retourne Le nombre de personnes prevues pour cette recette
     *
     * @return Le nombre de personnes prevues pour cette recette
     */
    public int getPrevuPour() {
        return prevuPour;
    }

    /** Redefinit Le nombre de personnes prevues pour cette recette
     *
     * @param prevuPour Le nombre de personnes prevues pour cette recette
     */
    public void setPrevuPour(int prevuPour) {
        this.prevuPour = prevuPour;
    }

    /** Retourne Le nombre de personnes activees pour cette recette
     *
     * @return Le nombre de personnes activees pour cette recette
     */
    public int getActivePour() {
        return activePour;
    }

    /** Redefinit Le nombre de personnes activees pour cette recette
     *
     * @param activePour Le nombre de personnes activees pour cette recette
     */
    public void setActivePour(int activePour) {
        this.activePour = activePour;
    }

    /** Resume la Recette
     *
     * @return Le resume
     */
    public String toString() {
        return (isActif()?(char)0x2713+" ":"   ") + nom;
    }
}
