package fr.insalyon.smartfridge.modeles.dao;


import fr.insalyon.smartfridge.modeles.Ingredient;

import java.util.List;

/** DAO de gesion des ingredients */
public class IngredientDAO extends BaseDAO {
    /** Trouve un Ingredient par son id
     *
     * @param id Id de l'Ingredient
     * @return L'ingredient
     */
    public static Ingredient trouveId(long id) {
        return (Ingredient) trouveId(Ingredient.class, id);
    }

    /** Trouve tous les ingredients
     *
     * @return Toutes les entites d'Ingredient
     */
    public static List<Ingredient> tous() {
        return (List<Ingredient>) tous("Ingredient");
    }


}