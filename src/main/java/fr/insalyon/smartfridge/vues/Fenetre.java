package fr.insalyon.smartfridge.vues;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Fenetre extends JFrame {
// cree pour plus facilement changer le contenu de la fenetre

    // historique de toutes les fenetres par lesquelles on est passe
    private ArrayList<JPanel> historique = new ArrayList<JPanel>();

    public Fenetre() {
        historique.add(new MenuPrincipal(this));
        try {
            jbInit();
            afficherDernier();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(719, 458));
        this.setTitle("SmartFridge");
    }

    private void afficherDernier() { // obtient la derniere vue de l'historique
        this.setContentPane(historique.get(historique.size() - 1));
    }

    public void revenirDebut() {
        JPanel debut = historique.get(0);
        historique.clear();
        historique.add(debut);
        afficherDernier(); // affiche debut
    }

    public void allerA(JPanel nouveau) {
        historique.add(nouveau);
        afficherDernier(); // affiche la nouvelle fenetre
    }

    public void retourArriere() {
        historique.remove(historique.size() - 1);
        afficherDernier(); // affiche la fenetre juste avant
    }
}
