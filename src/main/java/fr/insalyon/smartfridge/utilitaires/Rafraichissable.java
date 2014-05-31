package fr.insalyon.smartfridge.utilitaires;

/** Un element recharge a chaque fois qu'il est remis au premier plan */
public interface Rafraichissable {
    /** Appele quand l'element est reappele */
    public void mettreAJour();
}
