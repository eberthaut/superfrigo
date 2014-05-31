package fr.insalyon.smartfridge.vues;

import javax.swing.*;

public class UtilitairesVues {
    public static Icon icone(String nom) {
        return new ImageIcon(JPanel.class.getResource("/icones/" + nom + ".png"));
    }

    public static String html(String contenu) {
        return "<html><body>" + contenu + "</body></html>";
    }
}
