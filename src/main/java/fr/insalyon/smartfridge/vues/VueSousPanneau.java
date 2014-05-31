package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurSousPanneau;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import javax.swing.*;
import java.awt.*;

/** Vue dont herite les autres (sauf la VueMenuPrincipal) qui apporte des boutons de navigaion.
 *
 * Des lors, la partie NORTH du layout est occupée par la navigation.
 */
public abstract class VueSousPanneau extends JPanel {
    /** Le bouton pour remonter un niveau */
    private JButton buttonRetour = new JButton("Retour", Raccourcis.icone("retour"));
    /** Le bouton pour revenir a la racine */
    private JButton buttonMenuPrincipal = new JButton("Menu Principal", Raccourcis.icone("menu-principal"));

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     */
    public VueSousPanneau(Fenetre fenetre) {
        ControleurSousPanneau controleurPrincipal = new ControleurSousPanneau(fenetre, this);

        buttonRetour.addActionListener(controleurPrincipal);
        buttonMenuPrincipal.addActionListener(controleurPrincipal);

        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(1000,700));
        this.setBackground(Color.WHITE);

        JPanel retours = new JPanel(new GridLayout(1, 2));
        retours.setBackground(Color.WHITE);
        retours.add(buttonRetour);
        retours.add(buttonMenuPrincipal);
        this.add(retours, BorderLayout.NORTH);
    }

    /** Retourne Le bouton pour remonter un niveau
     *
     * @return Le bouton pour remonter un niveau
     */
    public JButton getButtonRetour() {
        return buttonRetour;
    }

    /** Retourne Le bouton pour revenir a la racine
     *
     * @return Le bouton pour revenir a la racine
     */
    public JButton getButtonMenuPrincipal() {
        return buttonMenuPrincipal;
    }
}
