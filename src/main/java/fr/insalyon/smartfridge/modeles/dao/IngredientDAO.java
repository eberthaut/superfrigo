package fr.insalyon.smartfridge.modeles.dao;


import fr.insalyon.smartfridge.modeles.Ingredient;

import java.util.List;

public class IngredientDAO extends BaseDAO {
    /**
     * @param id Id de l'Ingredient
     * @return Le type
     */
    public static Ingredient trouveId(long id) {
        return (Ingredient) trouveId(Ingredient.class, id);
    }

    /**
     * @return Toutes les entites d'Ingredient
     */
    public static List<Ingredient> tous() {
        return (List<Ingredient>) tous("Ingredient");
    }


}