package fr.insalyon.smartfridge.utilitaires;

import fr.insalyon.smartfridge.vues.VueMenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** Utilitaire gerant la fenetre de l'application pour le multi-vue */
public class Fenetre extends JApplet {
    /** Historique des vues */
    private final ArrayList<JPanel> historique = new ArrayList<JPanel>();

    /** Constructeur
     *
     * Lance le menu principal
     */
    public Fenetre() {
        historique.add(new VueMenuPrincipal(this));
        this.setSize(new Dimension(1000, 700));
        this.setBackground(Color.WHITE);
        revenirDebut();
    }

    /** Affiche la derniere fenetre de l'historique */
    private void afficherDernier() { // obtient la derniere vue de l'historique
        this.setContentPane(historique.get(historique.size() - 1));
        this.getContentPane().setSize(new Dimension(1000,700));
        if(this.getContentPane() instanceof Rafraichissable) {
            ((Rafraichissable)this.getContentPane()).mettreAJour();
        }

    }

    /** Revient a la racine */
    public void revenirDebut() {
        JPanel debut = historique.get(0);
        historique.clear();
        historique.add(debut);
        afficherDernier(); // affiche debut
    }

    /** Empile une nouvelle vue
     *
     * @param nouveau La nouvelle vue
     */
    public void allerA(JPanel nouveau) {
        historique.add(nouveau);
        afficherDernier(); // affiche la nouvelle fenetre
    }

    /** Remonte un niveau d'historique */
    public void retourArriere() {
        historique.remove(historique.size() - 1);
        afficherDernier(); // affiche la fenetre juste avant
    }
}
