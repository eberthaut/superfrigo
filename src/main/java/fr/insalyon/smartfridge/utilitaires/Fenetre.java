package fr.insalyon.smartfridge.utilitaires;

import fr.insalyon.smartfridge.vues.VueMenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Fenetre extends JApplet {
// cree pour plus facilement changer le contenu de la fenetre

    // historique de toutes les fenetres par lesquelles on est passe
    private ArrayList<JPanel> historique = new ArrayList<JPanel>();

    public Fenetre() {
        historique.add(new VueMenuPrincipal(this));
        try {
            jbInit();
            afficherDernier();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(1000, 700));
        this.setBackground(Color.WHITE);
    }

    private void afficherDernier() { // obtient la derniere vue de l'historique
        this.setContentPane(historique.get(historique.size() - 1));
        this.getContentPane().setSize(new Dimension(1000,700));
        if(this.getContentPane() instanceof Changeable) {
            ((Changeable)this.getContentPane()).mettreAJour();
        }

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
