package fr.insalyon.smartfridge.utilitaires;

import javax.swing.*;

/** Des utilitaires pour accelerer la conception des vues */
public class Raccourcis {
    /** Retourne une icone avec son nom seul
     *
     * @param nom Son nom
     * @return L'icone
     */
    public static Icon icone(String nom) {
        return new ImageIcon(Raccourcis.class.getResource("/icones/" + nom + ".png"));
    }

    /** Transforme un texte pour qu'il soit considere comme du HTML dans les JLabel
     *
     * @param contenu Le texte
     * @return Le HTML
     */
    public static String html(String contenu) {
        return "<html><body>" + contenu + "</body></html>";
    }
}
